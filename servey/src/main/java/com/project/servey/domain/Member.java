package com.project.servey.domain;

import java.time.LocalDateTime;

import org.hibernate.boot.model.source.spi.LocalMetadataBuildingContext;
import org.springframework.util.ObjectUtils;

import com.project.servey.exception.ErrorCode;
import com.project.servey.exception.ServeyException;
import com.project.servey.domain.Jwt;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {
    private Long memberId;
    private String email;
    private String name;
    private String password;
    private LocalDateTime joinedDate;
    private Jwt jwt;                  // JWT Object 객체
    // factory method
    public static Member of(long id) {
        return Member.builder()
                .memberId(id)
                .build();
    }

    // factory method
    public static Member fromEmail(String email) {
        return Member.builder()
                .email(email)
                .build();
    }


    // factory method
    public static Member of(Long memberId, String email, String name, String naLocalMetadataBuildingContext) {
        return Member.builder()
                .memberId(memberId)
                .email(email)
                .name(name)
                .build();
    }


    /**
     * 이메일을 변경합니다.
     *
     * @param email 이메일
     */
    public void changeEmail(String email) {
        if (ObjectUtils.isEmpty(email)) {
            throw new ServeyException(ErrorCode.CHANGE_EMAIL_VALUE_NOT_FOUND);
        }
        this.email = email;
    }


    /**
     * JWT 객체를 변경합니다.
     *
     * @param jwt JWT 객체
     */
    public void changeMemberInsideJwt(Jwt jwt) {
        if (!ObjectUtils.isEmpty(this.jwt)) {
            throw new ServeyException(ErrorCode.MEMBER_JWT_ALREADY_EXIST);
        }
        this.jwt = jwt;
    }


    /**
     * 비밀번호를 변경합니다.
     *
     * @param encode 암호화된 비밀번호
     */
    public void changePassword(String encode) {
        this.password = encode;
    }


    /**
     * 회원 가입시 필수 값 체크
     */
    public void validSignUpMemberData() {
        // 회원 가입시 필수 값 체크
        if (email == null || password == null || name == null) {
            throw new ServeyException(ErrorCode.MEMBER_REQUIRED_VALUE);
        }
    }


    /**
     * @param email 이메일
     * @return 이메일이 같으면 true, 다르면 false
     * @apiNote 로그아웃시 token 내부의 이메일과 요청을 보낸 이메일이 같은지 확인
     */
    public boolean isSameEmail(String email) {
        if (ObjectUtils.isEmpty(email)) {
            throw new ServeyException(ErrorCode.MEMBER_EMAIL_PARAM_NOT_FOUND);
        }

        if (ObjectUtils.isEmpty(this.email)) {
            throw new ServeyException(ErrorCode.MEMBER_INNER_EMAIL_NOT_FOUND);
        }

        if (isNotSameEmail(email)) {
            throw new ServeyException(ErrorCode.MEMBER_EMAIL_NOT_MATCH);
        }

        // 같은 이메일이면 true
        return true;
    }


    /**
     * @param email 이메일
     * @return 이메일이 같으면 false, 다르면 true
     * @apiNote 이메일이 같은지 확인
     */
    private boolean isNotSameEmail(String email) {
        return !this.email.equals(email);
    }


    /**
     * @param member Member
     * @apiNote 회원 정보 수정
     */
    public void updateMember(Member member) {
        this.email = member.email;
        this.password = member.password;
        this.name = member.name;
    }
}
