package com.project.servey.config.security;
import lombok.AllArgsConstructor;
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

/**
 * Spring Security 설정 클래스: 웹 보안 구성을 정의
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig{
    private final AuthenticationEntryPoint authenticationEntryPoint;
    /**
     * @param http HttpSecurity 객체
     * @return SecurityFilterChain
     * @throws Exception 예외
     * @apiNote 보안 필터 체인 설정. (spring security 설정)
     * 필터 체인에서 JWT를 parsing하는 도중 예외가 발생하면 JwtEntryPoint가 호출되어 예외처리가 된다.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)                                 // CSRF 보호 비활성화
                .exceptionHandling(e -> e.authenticationEntryPoint(authenticationEntryPoint)) // 인증 예외 처리 (custom 예외 처리: AuthenticationEntryPointImpl 클래스)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 상태 비저장 설정 (JWT 토큰 사용)
                .authorizeHttpRequests(auth -> {
                    auth
                        .requestMatchers("/servey/listAll").permitAll()
                        .requestMatchers("/servey/list").permitAll()
                        .requestMatchers("/servey/{serveyId:[0-9]+}").permitAll()
                        .requestMatchers("/auth/signUp").permitAll()
                        .requestMatchers("/auth/signIn").permitAll()
                        .anyRequest().authenticated();                         // 나머지 요청은 인증 필요
                });
                //.authenticationProvider(authenticationProvider())                  // DaoAuthenticationProvider를 인증 제공자로 설정 (커스텀 로그인 인증)
                //.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class); // JWT 토큰 필터 추가 (UsernamePasswordAuthenticationFilter 앞에 위치)

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
}
