package com.project.survey.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.survey.adapter.out.persistence.entity.member.MemberEntity;
import com.project.survey.adapter.out.persistence.entity.survey.AnswerEntity;

public interface AnswerRepository extends JpaRepository<AnswerEntity,Long>{
    
}
