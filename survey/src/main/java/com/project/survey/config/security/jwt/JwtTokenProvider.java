package com.project.survey.config.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;

/**
 * JWT 토큰 생성 및 검증을 담당하는 클래스
 */
@Slf4j
@Component
public class JwtTokenProvider {
    @Value("${jwt.jwtSecret}")
    private static String jwtSecretKey;
    
    @Value("${jwt.jwtExpirationMs}")
        private int jwtExpirationMs;

    private static SecretKey secretKey;
    /**
     * 객체 초기화 메서드, JWT 비밀 키를 설정
     */
    @PostConstruct
    public void init() {
        // jwtSecretKey를 바이트 배열로 변환하고, 이를 사용하여 HMAC-SHA256 알고리즘에 사용할 키를 생성한다.
        byte[] keyBytes = jwtSecretKey.getBytes(StandardCharsets.UTF_8);
        
        if (keyBytes.length < 32) {
            throw new IllegalArgumentException("The key length should be at least 32 bytes");
        }
        secretKey = Keys.hmacShaKeyFor(keyBytes); // Secret Key 생성
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

    public String resolveToken(HttpServletRequest request){
        return request.getHeader("X-AUTH-TOKEN");
    }

    /*
    * 토큰 정보를 기반으로 Claims 정보를 반환
    * @return Claims : Claims
    */
    // public static Claims getClainsFromToken(String token){
    
    //     return Jwts.parserBuilder()
    //         .setSigningKey(secretKey) // Key 객체 사용
    //         .build()
    //         .parseClaimsJws(token)
    //         .getBody();
    // }

    // Jwt Token의 유효성 및 만료 기간 검사
    // public boolean validateToken(String jwtToken){
    //     try{
    //         Jwt<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);

    //     }
    // }

}
