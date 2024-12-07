package com.project.survey.application.service.survey;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.project.survey.adapter.in.web.dto.response.survey.SurveyListResponseDto;
import com.project.survey.adapter.in.web.dto.response.survey.SurveyResponseDto;
import com.project.survey.application.command.survey.FindSurveyCommand;
import com.project.survey.application.command.survey.FindSurveyListCommand;
import com.project.survey.application.port.in.survey.FindSurveyUseCase;
import com.project.survey.application.port.out.survey.FindSurveyPort;
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

    @Override
    public SurveyResponseDto findSurvey(FindSurveyCommand findCommand) {
        checkIsSurveyExist(findCommand.getSurveyId());
        Survey findSurvey = findSurveyPort.findSurveyById(findCommand.getSurveyId());
        return surveyMapper.domainToResponseDto(findSurvey);
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

    /**
     * [READ] 삭제된 게시글이 아닌지 검증
     * surveyId로 게시글이 존재하는지 검증하는 내부 메서드
     */
    public boolean checkIsSurveyExist(Long surveyId) {
        boolean isCommentExist = findSurveyPort.checkIsSurveyExist(surveyId);
        if(!isCommentExist) {
            throw new SurveyException(ErrorCode.Survey_NOT_FOUND);
        }
        return true;
    }
}
