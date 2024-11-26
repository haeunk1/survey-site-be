package com.project.servey.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.servey.adapter.out.persistence.entity.member.MemberEntity;
import com.project.servey.adapter.out.persistence.entity.servey.AnswerEntity;

public interface AnswerRepository extends JpaRepository<AnswerEntity,Long>{
    
}
