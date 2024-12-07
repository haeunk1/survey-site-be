package com.project.survey.application.service.validation;

import com.project.survey.adapter.out.persistence.entity.survey.AnswerEntity;
import com.project.survey.util.custom.UseCase;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = false)
@UseCase
public class AnswerValidationService {
    // // 각 답변의 유효성 검증 및 저장
    //     for (SurveyAnswerRequestDto answer : request.getAnswers()) {
    //         // 문항이 설문조사에 포함되어 있는지 확인
    //         boolean isValid = questionRepository.existsBySurveyIdAndQuestionId(
    //             request.getSurveyId(), answer.getQuestionId());
    //         if (!isValid) {
    //             throw new IllegalArgumentException(
    //                 "Invalid questionId: " + answer.getQuestionId());
    //         }

   
    //     }
}
