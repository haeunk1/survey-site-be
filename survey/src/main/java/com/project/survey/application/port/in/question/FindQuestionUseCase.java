package com.project.survey.application.port.in.question;

import java.util.List;

import com.project.survey.adapter.in.web.dto.response.question.QuestionResponseDto;

public interface FindQuestionUseCase {
    List<QuestionResponseDto> findQuestionList(Long surveyId);
}
