package com.project.survey.config.security.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.survey.adapter.out.persistence.entity.member.MemberEntity;
import com.project.survey.adapter.out.persistence.repository.MemberRepository;
import com.project.survey.exception.ErrorCode;
import com.project.survey.exception.SurveyException;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CustomUserDetailsService implements UserDetailsService{

    private final MemberRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MemberEntity memberEntity = repository.findMemberEntityByEmail(email).orElseThrow(() -> new SurveyException(ErrorCode.MEMBER_NOT_FOUND));
        return CustomUserDetails.fromEntity(memberEntity);
    }
    
}
