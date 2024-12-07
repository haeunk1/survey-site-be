package com.project.survey.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;

/**
 * JWT 토큰 생성 및 검증을 담당하는 클래스
 */
@Slf4j
@Component
public class JwtTokenProvider {
    @Value("${jwt.jwtSecret}")
    private String jwtSecret;

    @Value("${jwt.jwtExpirationMs}")
    private int jwtExpirationMs;
    private SecretKey secretKey;
    /**
     * 객체 초기화 메서드, JWT 비밀 키를 설정
     */
    @PostConstruct
    public void init() {
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        if (keyBytes.length < 32) {
            throw new IllegalArgumentException("The key length should be at least 32 bytes");
        }
        this.secretKey = Keys.hmacShaKeyFor(keyBytes); // Secret Key 생성
    }


    /**
     * @param email    사용자 이메일
     * @param nickname 사용자 닉네임
     * @return 생성된 JWT Access 토큰
     * @apiNote JWT Access 토큰을 생성하는 메서드
     */
    public String generateAccessToken(String email, String name, Long memberId) {
        // 시간 정보 생성
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        // JWT 토큰 생성
        return Jwts.builder()
                .subject(email)
                .claim("name", name)
                .claim("memberId", memberId)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

}
