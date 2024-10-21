package com.project.servey.adapter.out.persistence.adapter;

import com.project.servey.adapter.out.persistence.entity.member.MemberEntity;
import com.project.servey.adapter.out.persistence.repository.MemberRepository;
import com.project.servey.application.port.out.member.CreateMemberPort;
import com.project.servey.domain.Member;
import com.project.servey.mapper.MemberMapper;
import com.project.servey.util.custom.PersistenceAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class MemberPersistenceAdapter implements CreateMemberPort{
    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;
    /**
     * 회원 생성
     * @param member 회원 도메인
     * @return 생성된 회원
     */
    @Override
    public Member createMember(Member member) {
        MemberEntity memberEntity = memberMapper.toEntity(member);
        MemberEntity savedMember = memberRepository.save(memberEntity);
        return memberMapper.toDomain(savedMember);
    }
    
}
