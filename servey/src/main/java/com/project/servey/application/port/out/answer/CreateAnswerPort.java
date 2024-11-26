package com.project.servey.application.port.out.answer;

import java.util.List;

import com.project.servey.domain.Answer;

public interface CreateAnswerPort {
    List<Answer> createAnswer(List<Answer> list);
}
