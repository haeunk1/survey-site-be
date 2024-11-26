package com.project.servey.application.port.in.answer;

import com.project.servey.application.command.anwer.CreateAnswerCommand;

public interface CreateAnswerUseCase {
    int createAnswer(CreateAnswerCommand command);
}
