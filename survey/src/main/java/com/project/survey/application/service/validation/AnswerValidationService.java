package com.project.survey.application.service.validation;

import com.project.survey.adapter.in.web.dto.request.answer.AnswerPairDto;
import com.project.survey.adapter.in.web.dto.response.question.QuestionResponseDto;
import com.project.survey.adapter.out.persistence.entity.survey.AnswerEntity;
import com.project.survey.application.service.question.FindQuestionService;
import com.project.survey.exception.ErrorCode;
import com.project.survey.exception.SurveyException;
import com.project.survey.util.custom.UseCase;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = false)
@UseCase
public class AnswerValidationService {
    private final FindQuestionService findQuestionService;

    public void validationAnswer(Long surveyId, List<AnswerPairDto> answers){
        List<QuestionResponseDto> questionList = findQuestionService.findQuestionList(surveyId);

        // 조회된 questionId 리스트 추출
        Set<Long> validQuestionIds = questionList.stream()
                .map(QuestionResponseDto::getQuestionId)
                .collect(Collectors.toSet());

        // 입력받은 answers의 questionId가 모두 유효한지 검사
        for (AnswerPairDto answer : answers) {
            if (!validQuestionIds.contains(answer.getQuestionId())) {
                throw new SurveyException(ErrorCode.ANSWER_QUESTION_NOT_MATCH);
            }
        }
    }
}
