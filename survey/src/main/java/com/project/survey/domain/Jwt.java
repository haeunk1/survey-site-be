package com.project.survey.domain;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * JWT 토큰
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Getter
public class Jwt {

    private String accessToken;
    private String refreshToken;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    // factory method
    public static Jwt of(String accessToken, String refreshToken, String email, Collection<? extends GrantedAuthority> authorities) {
        return Jwt.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .email(email)
                .authorities(authorities)
                .build();
    }

    // factory method
    public static Jwt of(String accessToken, String refreshToken, String email) {
        return Jwt.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .email(email)
                .authorities(null)
                .build();
    }

}