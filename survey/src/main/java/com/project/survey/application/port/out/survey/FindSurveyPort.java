package com.project.survey.application.port.out.survey;

import java.util.List;

import com.project.survey.adapter.in.web.dto.response.survey.SurveyListResponseDto;
import com.project.survey.application.command.survey.FindSurveyListCommand;
import com.project.survey.domain.Survey;

public interface FindSurveyPort {
    Survey findSurveyById(Long id);
    List<Survey> findSurveyAllList();
    List<SurveyListResponseDto> findSurveyFilteredList(FindSurveyListCommand command);
    boolean checkIsSurveyExist(Long id);
}
