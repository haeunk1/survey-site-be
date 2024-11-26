package com.project.servey.application.service.validation;

import com.project.servey.adapter.out.persistence.entity.servey.AnswerEntity;
import com.project.servey.util.custom.UseCase;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = false)
@UseCase
public class AnswerValidationService {
    // // 각 답변의 유효성 검증 및 저장
    //     for (ServeyAnswerRequestDto answer : request.getAnswers()) {
    //         // 문항이 설문조사에 포함되어 있는지 확인
    //         boolean isValid = questionRepository.existsByServeyIdAndQuestionId(
    //             request.getServeyId(), answer.getQuestionId());
    //         if (!isValid) {
    //             throw new IllegalArgumentException(
    //                 "Invalid questionId: " + answer.getQuestionId());
    //         }

   
    //     }
}
