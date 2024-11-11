package com.project.servey.application.port.in.question;

import java.util.List;

import com.project.servey.adapter.in.web.dto.response.QuestionResponseDto;

public interface FindQuestionUseCase {
    List<QuestionResponseDto> findQuestionList(Long serveyId);
}
