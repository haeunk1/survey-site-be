package com.project.servey.adapter.out.persistence.adapter;

import com.project.servey.adapter.out.persistence.entity.member.MemberEntity;
import com.project.servey.adapter.out.persistence.repository.MemberRepository;
import com.project.servey.application.port.out.member.CreateMemberPort;
import com.project.servey.application.port.out.member.FindMemberPort;
import com.project.servey.domain.Member;
import com.project.servey.exception.ErrorCode;
import com.project.servey.exception.ServeyException;
import com.project.servey.exception.ErrorCode;
import com.project.servey.mapper.MemberMapper;
import com.project.servey.util.custom.PersistenceAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class MemberPersistenceAdapter implements CreateMemberPort, FindMemberPort{
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
    @Override
    public Member findMemberById(Long memberId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findMemberById'");
    }
    @Override
    public Member findMember(Member member) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findMember'");
    }
    /**
     * @param email 회원 이메일
     * @return 조회된 회원
     * @apiNote Email로 회원 조회
     */
    @Override
    public Member findMemberByEmail(String email) {
        MemberEntity memberEntity = memberRepository.findMemberEntityByEmail(email)
                .orElseThrow(() -> new ServeyException(ErrorCode.MEMBER_NOT_FOUND));

        return Member.of(memberEntity.getUserId(), memberEntity.getEmail(), memberEntity.getName(), memberEntity.getPassword());
    }

    
}