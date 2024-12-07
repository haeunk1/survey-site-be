package com.project.survey.application.port.in.survey;

import com.project.survey.application.command.survey.UpdateSurveyCommand;

public interface UpdateSurveyUseCase {
    Long updateSurvey(UpdateSurveyCommand command);
}
