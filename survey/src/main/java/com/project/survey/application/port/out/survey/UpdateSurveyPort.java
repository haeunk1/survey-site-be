package com.project.survey.application.port.out.survey;

import com.project.survey.domain.Survey;

public interface UpdateSurveyPort {
    Long updateSurvey(Survey survey);
}
