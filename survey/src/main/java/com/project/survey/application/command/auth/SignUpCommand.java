package com.project.survey.application.command.auth;

import com.project.survey.adapter.in.web.dto.request.auth.MemberSignUpRequestDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원가입 Command
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SignUpCommand {
    private String email;            // 이메일
    private String password;         // 소셜 로그인 사용자는 NULL일 수 있음
    private String name;             // 이름

    // factory method
    public static SignUpCommand of(MemberSignUpRequestDto requestDTO){
        return SignUpCommand.builder()
        .email(requestDTO.getEmail())
        .password(requestDTO.getPassword())
        .name(requestDTO.getName())
        .build();
    }

}
