package com.project.survey.application.port.out.question;

import java.util.List;

import com.project.survey.domain.Question;

public interface FindQuestionPort {
    List<Question> findQuestionList(Long surveyId);
}
