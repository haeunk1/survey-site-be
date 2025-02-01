package com.project.survey.config.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Enumeration;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.project.survey.config.security.user.CustomUserDetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Key;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * JWT 토큰 생성 및 검증을 담당하는 클래스
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    @Value("${jwt.jwtSecret}")
    private String jwtSecretKey;
    
    @Value("${jwt.jwtExpirationMs}")
    private int jwtExpirationMs;

    private SecretKey secretKey;

    private final CustomUserDetailsService userDetailsService;

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

    /**
     * @param token
     * @return Authentication
     * @apiNote 인증 성공시 SecurityContextHolder에 저장할 Authentication 객체 생성
     */
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
    public String getUserEmail(String token){
        return getClainsFromToken(token).getSubject();
    }

    // public String resolveToken(HttpServletRequest request){
    //     return request.getHeader("Authorization");
    // }
    /*
     * Authorization 헤더에서 'Bearer '를 제거하고 순수 access token만 추출
     */
    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        Enumeration<String> tmpToken = request.getHeaders("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer "))
            return bearerToken.substring(7);
        else
            return null;
    }

    /*
    * 토큰 정보를 기반으로 Claims 정보를 반환
    * @return Claims : Claims
    */
    public Claims getClainsFromToken(String token){
        return Jwts.parser()  
            .setSigningKey(secretKey) 
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}
