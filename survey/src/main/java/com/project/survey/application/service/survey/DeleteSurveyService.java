package com.project.survey.application.service.survey;

import com.project.survey.application.port.in.survey.DeleteSurveyUseCase;
import com.project.survey.application.port.out.survey.DeleteSurveyPort;
import com.project.survey.application.port.out.survey.FindSurveyPort;
import com.project.survey.exception.ErrorCode;
import com.project.survey.exception.SurveyException;
import com.project.survey.util.custom.UseCase;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = false)
@UseCase
public class DeleteSurveyService implements DeleteSurveyUseCase{

    private final DeleteSurveyPort deleteSurveyPort;
    private final FindSurveyService findService;

    @Override
    public Long deleteSurveyById(Long surveyId) {
        //삭제된 게시글이 아닌지 검증
        findService.checkIsSurveyExist(surveyId);
        return deleteSurveyPort.deleteSurvey(surveyId);
    }
    
}
