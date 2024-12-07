package com.project.survey.application.service.validation;

import com.project.survey.adapter.out.persistence.entity.survey.SurveyEntity;
import com.project.survey.adapter.out.persistence.repository.SurveyRepository;

import com.project.survey.util.custom.UseCase;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = false)
@UseCase
public class SurveyValidationService {
    //FindsurveyService - checkIsSurveyExist 수정 필요
    // private final SurveyRepository surveyRepository;

    // public void validateSurvey(Long surveyId) {
    //     SurveyEntity survey = surveyRepository.findById(surveyId)
    //             .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 설문입니다."));
    //     if (survey.isDeleted()) {
    //         throw new IllegalStateException("삭제된 설문입니다.");
    //     }
    //}
}
