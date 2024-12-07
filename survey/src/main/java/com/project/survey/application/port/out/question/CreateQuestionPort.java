package com.project.survey.application.port.out.question;

import java.util.List;

import com.project.survey.adapter.out.persistence.entity.survey.QuestionEntity;
import com.project.survey.domain.Question;

public interface CreateQuestionPort {
    List<Question> createQuestion(List<Question> list);
}
