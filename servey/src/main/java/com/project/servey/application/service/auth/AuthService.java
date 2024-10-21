package com.project.servey.application.service.auth;

import org.springframework.transaction.annotation.Transactional;

import com.project.servey.adapter.in.web.dto.response.auth.MemberResponseDto;
import com.project.servey.application.command.auth.SignUpCommand;
import com.project.servey.application.port.in.auth.AuthUseCase;
import com.project.servey.application.port.out.member.CreateMemberPort;
import com.project.servey.domain.Member;
import com.project.servey.mapper.MemberMapper;
import com.project.servey.util.custom.UseCase;

import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;

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

    //private final PasswordEncoder passwordEncoder;
    
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
        //member.changePassword(passwordEncoder.encode(member.getPassword()));

        // 3. 회원 저장
        Member savedMember = createMemberPort.createMember(member);

        // 4. DTO로 변환하여 반환
        return memberMapper.domainToResponseDTO(savedMember);
    }
    
}
