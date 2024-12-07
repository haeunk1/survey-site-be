package com.project.survey.adapter.out.persistence.adapter;

import java.util.List;
import java.util.Optional;

import com.project.survey.adapter.in.web.dto.response.survey.SurveyListResponseDto;
import com.project.survey.adapter.out.persistence.entity.survey.SurveyEntity;
import com.project.survey.adapter.out.persistence.repository.SurveyRepository;
import com.project.survey.adapter.out.persistence.repository.dsl.SurveyDslRepository;
import com.project.survey.application.command.survey.FindSurveyListCommand;
import com.project.survey.application.port.out.survey.CreateSurveyPort;
import com.project.survey.application.port.out.survey.DeleteSurveyPort;
import com.project.survey.application.port.out.survey.FindSurveyPort;
import com.project.survey.application.port.out.survey.UpdateSurveyPort;
import com.project.survey.domain.Survey;
import com.project.survey.exception.ErrorCode;
import com.project.survey.exception.SurveyException;
import com.project.survey.mapper.SurveyMapper;
import com.project.survey.util.custom.PersistenceAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class SurveyPersistenceAdapter implements CreateSurveyPort, FindSurveyPort, DeleteSurveyPort, UpdateSurveyPort{
    private final SurveyRepository surveyRepository;
    private final SurveyMapper surveyMapper;
    private final SurveyDslRepository dslRepository;
    
    @Override
    public Survey findSurveyById(Long id) {
        SurveyEntity surveyEntity = surveyRepository.findById(id)
                    .orElseThrow(() -> new SurveyException(ErrorCode.Survey_NOT_FOUND));
        return surveyMapper.entityToDomain(surveyEntity);
    }

    
    @Override
    public List<Survey> findSurveyAllList(){
        List<SurveyEntity> list = surveyRepository.findAll();
        return surveyMapper.entitiesToDomains(list);
    }

    /*
     * [Create] 설문조사 생성
    */
    @Override
    public Survey createSurvey(Survey survey) {
        SurveyEntity surveyEntity = surveyMapper.domainToEntity(survey);
        SurveyEntity savedEntity = surveyRepository.save(surveyEntity);

        return surveyMapper.entityToDomain(savedEntity);
    }

    /*
     * [Delete] 설문조사 삭제
    */
    @Override
    public Long deleteSurvey(Long surveyId) {
        return dslRepository.deleteSurvey(surveyId);
    }

    /*
     * [Update] 설문조사 수정
    */
    @Override
    public Long updateSurvey(Survey survey) {
        return dslRepository.updateSurvey(survey);
    }

    /*
     * [Read] 필터링 조회
    */
    @Override
    public List<SurveyListResponseDto> findSurveyFilteredList(FindSurveyListCommand command) {
         //command로 넘기기..
         return dslRepository.selectSurveyFilteredList(command);
    }

    /*
     * [Read] surveyId, deleteYn 으로 조회
     * 조건에 해당하는 게시글 존재하면 true, 존재하지 않으면 false
    */
    @Override
    public boolean checkIsSurveyExist(Long id) {
        Optional<SurveyEntity> surveyEntity = surveyRepository.findBySurveyIdAndDeleteYn(id,"N");
        return surveyEntity.isPresent();
    }
    
}