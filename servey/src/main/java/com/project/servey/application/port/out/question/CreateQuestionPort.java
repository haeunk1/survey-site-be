package com.project.servey.application.port.out.question;

import java.util.List;

import com.project.servey.adapter.out.persistence.entity.servey.QuestionEntity;
import com.project.servey.domain.Question;

public interface CreateQuestionPort {
    List<Question> createQuestion(List<Question> list);
}
