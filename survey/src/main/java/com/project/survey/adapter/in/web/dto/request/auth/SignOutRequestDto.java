package com.project.survey.adapter.in.web.dto.request.auth;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignOutRequestDto {

    private String email;

}