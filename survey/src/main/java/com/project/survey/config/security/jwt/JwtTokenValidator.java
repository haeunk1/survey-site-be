package com.project.survey.config.security.jwt;

import org.springframework.stereotype.Component;

import com.project.survey.application.port.out.member.FindMemberPort;
import com.project.survey.domain.Member;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 유효한 access token인지 검증하는 클래스
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenValidator {
    private final FindMemberPort findMemberPort;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 유효한 access token인지 검증하는 클래스
     */
    public boolean isValidToken(String jwtToken){
        try{
            Long memberId = Long.valueOf(jwtTokenProvider.getMemberId(jwtToken));
            Member member = findMemberPort.findMemberById(memberId);
            return member != null;
        }catch(JwtException jwtException){
            log.debug("JWT validation error: {}", jwtException.getMessage());
            if (jwtException instanceof ExpiredJwtException) {
                log.debug("Expired JWT token: {}", jwtToken);
            } else if (jwtException instanceof MalformedJwtException) {
                log.debug("Malformed JWT token: {}", jwtToken);
            } else {
                log.debug("Other JWT error: {}", jwtException.getClass().getSimpleName());
            }
            return false;
        }
    }

}
