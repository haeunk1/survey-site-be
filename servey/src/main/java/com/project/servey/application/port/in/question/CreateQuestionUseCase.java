package com.project.servey.application.port.in.question;

import java.util.List;

import com.project.servey.adapter.in.web.dto.response.question.QuestionResponseDto;
import com.project.servey.application.command.question.CreateQuestionCommand;

public interface CreateQuestionUseCase {
    List<QuestionResponseDto> createQuestion(CreateQuestionCommand connamd);
}
