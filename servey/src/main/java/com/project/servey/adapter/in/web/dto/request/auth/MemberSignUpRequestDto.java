package com.project.servey.adapter.in.web.dto.request.auth;
import lombok.*;

/**
 * 회원 생성 DTO
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberSignUpRequestDto {
    private String email;            // 이메일
    private String password;         // 소셜 로그인 사용자는 NULL일 수 있음
    private String name;             // 이름

    public static MemberSignUpRequestDto of(String email, String name, String password){
        return builder()
        .email(email)
        .password(password)
        .name(name)
        .build();
    }
}
