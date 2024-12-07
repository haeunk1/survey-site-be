package com.project.survey.application.service.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.project.survey.adapter.in.web.dto.response.auth.JwtResponseDto;
import com.project.survey.adapter.in.web.dto.response.auth.MemberResponseDto;
import com.project.survey.application.command.auth.SignInCommand;
import com.project.survey.application.command.auth.SignOutCommand;
import com.project.survey.application.command.auth.SignUpCommand;
import com.project.survey.application.port.in.auth.AuthUseCase;
import com.project.survey.application.port.out.member.CreateMemberPort;
import com.project.survey.application.port.out.member.FindMemberPort;
import com.project.survey.application.port.out.refreshToken.CreateRefreshTokenPort;
import com.project.survey.application.port.out.refreshToken.DeleteRefreshTokenPort;
import com.project.survey.config.security.user.UserDetailsImpl;
import com.project.survey.domain.Jwt;
import com.project.survey.domain.Member;
import com.project.survey.domain.RefreshToken;
import com.project.survey.exception.ErrorCode;
import com.project.survey.exception.SurveyException;
import com.project.survey.mapper.JwtMapper;
import com.project.survey.mapper.MemberMapper;
import com.project.survey.util.JwtTokenProvider;
import com.project.survey.util.custom.UseCase;

import lombok.RequiredArgsConstructor;

/**
 * 회원 인증 관련 비즈니스 로직을 처리하는 서비스 클래스
 * 회원 인증, JWT 관련 로직을 처리합니다.
 * Auth service는 member와 jwt를 다루는 비즈니스 로직을 처리합니다.
 */
@RequiredArgsConstructor
@Transactional(readOnly = true)
@UseCase
public class AuthService implements AuthUseCase{

    private final CreateMemberPort createMemberPort;
    private final DeleteRefreshTokenPort deleteRefreshTokenPort;

    private final MemberMapper memberMapper;
    private final CreateRefreshTokenPort createRefreshTokenPort;
    private final FindMemberPort findMemberPort;
    
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    private final JwtMapper jwtMapper;
    

    @Value("${jwt.refreshTokenDurationMinutes}")
    private long refreshTokenDurationMinutes;
    
    /**
     * @param signUpCommand 회원가입 요청 도메인
     * @return 가입한 회원의 이메일
     * @apiNote 회원 생성
     */
    @Transactional
    @Override
    public MemberResponseDto signUp(SignUpCommand signUpCommand) {
        // 1. 회원가입 요청 도메인을 생성
        Member member = memberMapper.commandToDomain(signUpCommand);

        // 2. 비밀번호 암호화
        member.changePassword(passwordEncoder.encode(member.getPassword()));

        // 3. 회원 저장
        Member savedMember = createMemberPort.createMember(member);

        // 4. DTO로 변환하여 반환
        return memberMapper.domainToResponseDTO(savedMember);
    }

    /**
     * @param signInCommand 로그인 요청 도메인
     * @return JWT 토큰 발급 응답 DTO
     * @apiNote 로그인 요청을 처리하는 메서드
     */
    @Transactional
    @Override
    public JwtResponseDto signInAndCreateJwt(SignInCommand signInCommand) {
        // 1. 로그인 요청 도메인을 생성
        Member member = memberMapper.commandToDomain(signInCommand);

        // 2. db에서 회원 조회
        Member findMember = findMemberPort.findMemberByEmail(member.getEmail());

        // 3. JWT access 토큰 생성
        String accessToken = jwtTokenProvider
                .generateAccessToken(findMember.getEmail(), findMember.getName(), findMember.getMemberId());

        // 4. JWT refresh 토큰을 생성하고 저장
        RefreshToken refreshToken = RefreshToken.of(findMember, refreshTokenDurationMinutes);
        RefreshToken savedRefreshToken = createRefreshTokenPort.createRefreshToken(refreshToken);

        // 5. JWT 도메인을 생성하고 조회해온 회원 도메인에 저장
        Jwt jwt = Jwt.of(accessToken, savedRefreshToken.getToken(), findMember.getEmail());
        findMember.changeMemberInsideJwt(jwt);

        // 6. mapper를 통해 DTO로 변환하여 반환
        return jwtMapper.domainToResponseDTO(jwt);
    }
    /**
     * @param signOutCommand 로그아웃 요청 도메인
     * @apiNote 로그아웃 + JWT 삭제
     */
    @Transactional
    @Override
    public Long signOut(SignOutCommand signOutCommand) {
        // 1. 로그아웃 요청 도메인을 생성
        Member member = memberMapper.commandToDomain(signOutCommand);

        // 2. SecurityContext에서 인증 정보를 가져와서 이메일을 추출
        UserDetailsImpl userDetails = getUserDetails();

        // 3. 회원 정보를 검증한 후 RefreshToken 삭제 (검증 실패시 도메인 비즈니스에서 예외 처리)
        if (member.isSameEmail(userDetails.getEmail())) {
            // 회원 조회 후 RefreshToken 삭제
            Member findMember = findMemberPort.findMemberByEmail(member.getEmail());
            deleteRefreshTokenPort.deleteRefreshToken(findMember);

            // SecurityContext에서 인증 정보 삭제
            SecurityContextHolder.clearContext();
        }

        // 4. 로그아웃한 회원 ID 반환
        return member.getMemberId();
    }

    /**
     * @return UserDetailsImpl 객체
     * @apiNote SecurityContext에서 인증 정보를 가져와서 UserDetailsImpl 객체를 반환하는 메서드
     */
    private UserDetailsImpl getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new SurveyException(ErrorCode.MEMBER_NOT_FOUND);
        }

        return (UserDetailsImpl) authentication.getPrincipal();
    }
}
