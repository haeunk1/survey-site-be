package com.project.survey.application.port.in.answer;

import com.project.survey.application.command.anwer.CreateAnswerCommand;

public interface CreateAnswerUseCase {
    int createAnswer(CreateAnswerCommand command);
}
