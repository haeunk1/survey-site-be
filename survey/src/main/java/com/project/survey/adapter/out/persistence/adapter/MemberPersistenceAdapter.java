package com.project.survey.adapter.out.persistence.adapter;

import com.project.survey.adapter.out.persistence.entity.member.MemberEntity;
import com.project.survey.adapter.out.persistence.repository.MemberRepository;
import com.project.survey.application.port.out.member.CreateMemberPort;
import com.project.survey.application.port.out.member.FindMemberPort;
import com.project.survey.domain.Member;
import com.project.survey.exception.ErrorCode;
import com.project.survey.exception.SurveyException;
import com.project.survey.exception.ErrorCode;
import com.project.survey.mapper.MemberMapper;
import com.project.survey.util.custom.PersistenceAdapter;

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
        MemberEntity memberEntity = memberMapper.domainToEntity(member);
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
                .orElseThrow(() -> new SurveyException(ErrorCode.MEMBER_NOT_FOUND));

        return Member.of(memberEntity.getMemberId(), memberEntity.getEmail(), memberEntity.getName(), memberEntity.getPassword());
    }

    
}
