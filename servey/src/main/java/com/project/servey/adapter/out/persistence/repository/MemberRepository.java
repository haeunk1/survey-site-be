package com.project.servey.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.servey.adapter.out.persistence.entity.member.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity,Long>{
}
