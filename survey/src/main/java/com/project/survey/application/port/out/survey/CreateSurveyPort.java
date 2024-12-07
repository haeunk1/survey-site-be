package com.project.survey.application.port.out.survey;

import com.project.survey.domain.Survey;

public interface CreateSurveyPort {
    Survey createSurvey(Survey survey);
}
