package com.project.survey.config.security.user;

import java.io.Serial;
import java.util.Collection;
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
public class UserDetailsImpl implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Long memberId;

    private final String email;

    private final String name;

    private final Collection<? extends GrantedAuthority> authorities;

    // factory method
    public static UserDetailsImpl fromEntity(MemberEntity memberEntity) {
        // 1. 회원이 존재하지 않을 경우 예외 처리
        if (memberEntity == null) {
            throw new SurveyException(ErrorCode.MEMBER_NOT_FOUND);
        }

        // 2. 권한을 부여 (임시지정)
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("member"));
        
        // 3. UserDetailsImpl 객체를 생성하여 반환 (회원 정보를 기반으로)
        return new UserDetailsImpl(
                memberEntity.getMemberId(),
                memberEntity.getEmail(),
                memberEntity.getName(), authorities
        );
    }

    // 보안상 비밀번호는 다루지 않음 (무조건 구현은 해야해서 빈값으로 리턴)
    @Override
    public String getPassword() {
        throw new SurveyException(ErrorCode.PASSWORD_IS_NOT_HANDLE_IN_SECURITY_USER_DETAILS);
    }

    // 유저이름은 따로 없어서 닉네임으로 대체 (닉네임도 고유값)
    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    }
    
}
