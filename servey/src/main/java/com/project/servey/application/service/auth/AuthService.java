package com.project.servey.application.service.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.project.servey.adapter.in.web.dto.response.auth.JwtResponseDto;
import com.project.servey.adapter.in.web.dto.response.auth.MemberResponseDto;
import com.project.servey.application.command.auth.SignInCommand;
import com.project.servey.application.command.auth.SignUpCommand;
import com.project.servey.application.port.in.auth.AuthUseCase;
import com.project.servey.application.port.out.member.CreateMemberPort;
import com.project.servey.application.port.out.member.FindMemberPort;
import com.project.servey.application.port.out.refreshToken.CreateRefreshTokenPort;
import com.project.servey.domain.Jwt;
import com.project.servey.domain.Member;
import com.project.servey.domain.RefreshToken;
import com.project.servey.mapper.JwtMapper;
import com.project.servey.mapper.MemberMapper;
import com.project.servey.util.JwtTokenProvider;
import com.project.servey.util.custom.UseCase;

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
    
}
