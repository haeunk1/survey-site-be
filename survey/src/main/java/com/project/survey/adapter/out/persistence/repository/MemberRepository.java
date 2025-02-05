package com.project.survey.adapter.out.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.survey.adapter.out.persistence.entity.member.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity,Long>{
    Optional<MemberEntity> findMemberEntityByEmail(String email);
    Optional<MemberEntity> findMemberEntityByMemberId(Long memberId);
}
