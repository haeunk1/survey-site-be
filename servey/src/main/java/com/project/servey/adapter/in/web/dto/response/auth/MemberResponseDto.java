package com.project.servey.adapter.in.web.dto.response.auth;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Getter
public class MemberResponseDto {
    private Long id;                 // PK
    private String email;            // 이메일
    private String password;         // 소셜 로그인 사용자는 NULL일 수 있음
    private String name;             // 이름
    private LocalDateTime joinedDate; // 가입일
}
