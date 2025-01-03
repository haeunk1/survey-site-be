package com.project.survey.domain;
import lombok.*;
import org.springframework.util.ObjectUtils;

import com.project.survey.exception.ErrorCode;
import com.project.survey.exception.SurveyException;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 리프레시 토큰
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Getter
public class RefreshToken {

    private Long id;
    private Member member;
    private String token;
    private LocalDateTime expiryDate;


    // factory method
    public static RefreshToken of(String requestRefreshToken) {
        return RefreshToken.builder()
                .token(requestRefreshToken)
                .build();
    }


    // factory method
    public static RefreshToken of(Member member, long refreshTokenDurationMinutes) {
        return RefreshToken.builder()
                .member(member)
                .token(UUID.randomUUID().toString())
                .expiryDate(LocalDateTime.now().plusMinutes(refreshTokenDurationMinutes))
                .build();
    }


    /**
     * @apiNote 토큰의 유효성 검증 (회원 정보, 만료 날짜 존재여부, 만료 기간 확인)
     */
    public void validRefreshToken() {
        // 토큰 내부 회원 정보 확인
        validTokenInnerMember();

        // 토큰 내부 만료 날짜 확인
        validTokenExpiryDateExist();

        // 토큰의 "만료 기간"이 "현재 시간"보다 이전이면 만료된 토큰이니 true를 반환하고 아니면 예외 발생
        validTokenExpiryDateExpiration();
    }


    /**
     * @apiNote 토큰에 회원 정보가 존재하는지 확인
     */
    private void validTokenInnerMember() {
        if (this.member == null) {
            throw new SurveyException(ErrorCode.REFRESH_TOKEN_NOT_HAVE_MEMBER);
        }
    }


    /**
     * @apiNote 토큰 내부의 만료 날짜가 존재하는지 확인
     */
    private void validTokenExpiryDateExist() {
        if (ObjectUtils.isEmpty(this.expiryDate)) {
            throw new SurveyException(ErrorCode.REFRESH_TOKEN_EXPIRATION_DATE_NOT_FOUND);
        }
    }


    /**
     * @apiNote 리프레시 토큰이 만료되었는지 확인
     */
    private void validTokenExpiryDateExpiration() {
        if (this.expiryDate.isBefore(LocalDateTime.now())) {
            throw new SurveyException(ErrorCode.REFRESH_TOKEN_EXPIRED);
        }
    }


    /**
     * @param email 이메일
     * @apiNote 회원을 생성하면서 이메일 정보를 세팅한다.
     */
    // public void changeEmail(String email) {
    //     this.member = Member.fromEmail(email);
    // }

}