package com.project.servey.domain;

import java.time.LocalDateTime;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {
    private Long userId;
    private String email;
    private String name;
    private String password;
    private LocalDateTime joinedDate;

    // factory method
    public static Member of(long id) {
        return Member.builder()
                .userId(id)
                .build();
    }

    /**
     * 비밀번호를 변경합니다.
     *
     * @param encode 암호화된 비밀번호
     */
    public void changePassword(String encode) {
        this.password = encode;
    }
}
