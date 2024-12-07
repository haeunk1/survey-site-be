package com.project.survey.application.port.in.question;

import java.util.List;

import com.project.survey.adapter.in.web.dto.response.question.QuestionResponseDto;
import com.project.survey.application.command.question.CreateQuestionCommand;

public interface CreateQuestionUseCase {
    List<QuestionResponseDto> createQuestion(CreateQuestionCommand connamd);
}
