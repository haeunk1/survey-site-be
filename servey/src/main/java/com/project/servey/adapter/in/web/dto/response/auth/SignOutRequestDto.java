package com.project.servey.adapter.in.web.dto.response.auth;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignOutRequestDto {

    private String email;

}