package com.project.servey.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.servey.adapter.out.persistence.entity.servey.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity,Long>{
    
}
