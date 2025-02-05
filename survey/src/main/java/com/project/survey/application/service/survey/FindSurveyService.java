package com.project.survey.application.service.survey;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.project.survey.adapter.in.web.dto.response.survey.SurveyListResponseDto;
import com.project.survey.adapter.in.web.dto.response.survey.SurveyResponseDto;
import com.project.survey.application.command.survey.FindSurveyCommand;
import com.project.survey.application.command.survey.FindSurveyListCommand;
import com.project.survey.application.port.in.survey.FindSurveyUseCase;
import com.project.survey.application.port.out.survey.FindSurveyPort;
import com.project.survey.application.service.validation.SurveyValidationService;
import com.project.survey.domain.Survey;
import com.project.survey.exception.ErrorCode;
import com.project.survey.exception.SurveyException;
import com.project.survey.mapper.SurveyMapper;
import com.project.survey.util.custom.UseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@UseCase
public class FindSurveyService implements FindSurveyUseCase {
    private final FindSurveyPort findSurveyPort;
    private final SurveyMapper surveyMapper;
    private final SurveyValidationService validationService;

    //controller 사용
    @Override
    public SurveyResponseDto findSurvey(FindSurveyCommand findCommand) {
        validationService.checkIsSurveyExist(findCommand.getSurveyId());
        Survey findSurvey = findSurveyPort.findSurveyById(findCommand.getSurveyId());
        return surveyMapper.domainToResponseDto(findSurvey);
    }

    //service 사용
    @Override
    public Survey findSurveyDomain(Long surveyId) {
        validationService.checkIsSurveyExist(surveyId);
        return findSurveyPort.findSurveyById(surveyId);
    }

    @Override
    public List<SurveyResponseDto> findSurveyAllList() {
        List<Survey> list = findSurveyPort.findSurveyAllList();
        return surveyMapper.domainsToResponseDtos(list);
    }

    /**
     * @param findSurveyListCommand 리스트 필터링 도메인
     * @return 필터링된 설문조사 리스트
     * @apiNote 필터링된 설문조사 리스트 return
     */
    @Override
    public List<SurveyListResponseDto> findSurveyFilteredList(FindSurveyListCommand findSurveyListCommand) {
        List<SurveyListResponseDto> list = findSurveyPort.findSurveyFilteredList(findSurveyListCommand);
        return list;
    }

    
}
