package com.project.servey.application.command.auth;
import com.project.servey.adapter.in.web.dto.response.auth.SignOutRequestDto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SignOutCommand {

    private String email;


    // factory method
    public static SignOutCommand of(SignOutRequestDto signOutRequestDTO) {
        return SignOutCommand.builder()
                .email(signOutRequestDTO.getEmail())
                .build();
    }

}