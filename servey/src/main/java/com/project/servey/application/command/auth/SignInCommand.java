package com.project.servey.application.command.auth;

import com.project.servey.adapter.in.web.dto.request.auth.SignInRequestDto;

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
public class SignInCommand {
    private String email;
    private String password;

    public static SignInCommand of(SignInRequestDto requestDto){
        return SignInCommand.builder()
            .email(requestDto.getEmail())
            .password(requestDto.getPassword())
            .build();
    }
}
