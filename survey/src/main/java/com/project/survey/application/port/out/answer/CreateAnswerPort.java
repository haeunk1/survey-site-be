package com.project.survey.application.port.out.answer;

import java.util.List;

import com.project.survey.domain.Answer;

public interface CreateAnswerPort {
    List<Answer> createAnswer(List<Answer> list);
}
