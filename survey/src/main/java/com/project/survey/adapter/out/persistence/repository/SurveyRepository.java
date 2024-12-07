package com.project.survey.adapter.out.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.survey.adapter.out.persistence.entity.survey.SurveyEntity;

public interface SurveyRepository extends JpaRepository<SurveyEntity,Long>{
    Optional<SurveyEntity> findBySurveyIdAndDeleteYn(Long surveyId, String deleteYn);
    Optional<SurveyEntity> findBySurveyId(Long surveyId);
}
