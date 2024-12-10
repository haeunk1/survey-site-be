package com.project.survey.application.service.validation;

import com.project.survey.adapter.out.persistence.entity.survey.SurveyEntity;
import com.project.survey.adapter.out.persistence.repository.SurveyRepository;
import com.project.survey.application.port.out.survey.FindSurveyPort;
import com.project.survey.exception.ErrorCode;
import com.project.survey.exception.SurveyException;
import com.project.survey.util.custom.UseCase;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = false)
@UseCase
public class SurveyValidationService {
    private final FindSurveyPort findSurveyPort;
    
    /**
     * [READ] 삭제된 게시글이 아닌지 검증
     * surveyId로 게시글이 존재하는지 검증하는 내부 메서드
     */
    public boolean checkIsSurveyExist(Long surveyId) {
        boolean isCommentExist = findSurveyPort.checkIsSurveyExist(surveyId);
        if(!isCommentExist) {
            throw new SurveyException(ErrorCode.SURVEY_NOT_FOUND);
        }
        return true;
    }
}
