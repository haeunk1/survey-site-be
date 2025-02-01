package com.project.survey.config.security;
import lombok.AllArgsConstructor;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.project.survey.config.security.filter.JwtAuthorizationFilter;
import com.project.survey.config.security.jwt.JwtTokenProvider;
import com.project.survey.config.security.jwt.JwtTokenValidator;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
/**
 * Spring Security 설정 클래스: 웹 보안 구성을 정의
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig{
    
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final JwtTokenValidator jwtTokenValidator;
    private final JwtTokenProvider jwtTokenProvider;

    /*
     * cors설정
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() { //Spring Security용 CORS 설정
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("http://localhost:8081"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.addExposedHeader("Authorization"); // Authorization 헤더 노출

        source.registerCorsConfiguration("/**", config);
        return source;
    }
    /**
     * @param http HttpSecurity 객체
     * @return SecurityFilterChain
     * @throws Exception 예외
     * @apiNote 보안 필터 체인 설정. (spring security 설정)
     * 필터 체인에서 JWT를 parsing하는 도중 예외가 발생하면 JwtEntryPoint가 호출되어 예외처리가 된다.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,JwtAuthorizationFilter jwtAuthorizationFilter) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)   
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))                              // CSRF 보호 비활성화
                .exceptionHandling(e -> e.authenticationEntryPoint(authenticationEntryPoint)) // 인증 예외 처리 (custom 예외 처리: AuthenticationEntryPointImpl 클래스)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 상태 비저장 설정 (JWT 토큰 사용)
                .authorizeHttpRequests(auth -> {
                    auth
                        .requestMatchers("/survey/listAll").permitAll()
                        .requestMatchers("/survey/list").permitAll()
                        .requestMatchers(new RegexRequestMatcher("/survey/[0-9]+$",null)).permitAll()
                        .requestMatchers("/auth/signUp").permitAll()
                        .requestMatchers("/auth/signIn").permitAll()
                        .requestMatchers("/question/list").permitAll()
                        .anyRequest().authenticated();                         // 나머지 요청은 인증 필요
                })
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class); // JWT 토큰 필터 추가 (UsernamePasswordAuthenticationFilter 앞에 위치)

        return http.build();
    }
    /**
     * @return PasswordEncoder
     * @apiNote 비밀번호 인코더 설정. (BCryptPasswordEncoder)
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(jwtTokenProvider, jwtTokenValidator);
    }

}
