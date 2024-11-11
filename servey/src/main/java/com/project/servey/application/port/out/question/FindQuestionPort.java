package com.project.servey.application.port.out.question;

import java.util.List;

import com.project.servey.domain.Question;

public interface FindQuestionPort {
    List<Question> findQuestionList(Long serveyId);
}
