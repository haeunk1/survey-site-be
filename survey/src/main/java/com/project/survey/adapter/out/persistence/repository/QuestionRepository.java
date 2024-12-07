package com.project.survey.adapter.out.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.survey.adapter.out.persistence.entity.survey.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity,Long>{
    List<QuestionEntity> findBySurveyId(Long surveyId);
}
