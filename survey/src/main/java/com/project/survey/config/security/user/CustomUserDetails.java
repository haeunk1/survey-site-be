package com.project.survey.config.security.user;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.survey.adapter.out.persistence.entity.member.MemberEntity;
import com.project.survey.exception.ErrorCode;
import com.project.survey.exception.SurveyException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class CustomUserDetails implements UserDetails{
@Serial
    private static final long serialVersionUID = 1L;

    private final Long memberId;

    private final String email;

    private final String name;

    private final Collection<? extends GrantedAuthority> authorities;

    // factory method
    public static CustomUserDetails fromEntity(MemberEntity memberEntity) {
        // 1. 회원이 존재하지 않을 경우 예외 처리
        if (memberEntity == null) {
            throw new SurveyException(ErrorCode.MEMBER_NOT_FOUND);
        }

        // 2. 권한을 부여 (임시지정)
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("member"));
        
        // 3. CustomUserDetails 객체를 생성하여 반환 (회원 정보를 기반으로)
        return new CustomUserDetails(
                memberEntity.getMemberId(),
                memberEntity.getEmail(),
                memberEntity.getName(), authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("member"));
    }


    // 보안상 비밀번호는 다루지 않음
    @Override
    public String getPassword() {
        throw new SurveyException(ErrorCode.PASSWORD_IS_NOT_HANDLE_IN_SECURITY_USER_DETAILS);
    }

    @Override
    public String getUsername() {
        return name;
    }
    
}
