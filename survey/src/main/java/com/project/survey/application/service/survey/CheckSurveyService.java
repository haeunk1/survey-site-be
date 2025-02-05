package com.project.survey.application.service.survey;

import com.project.survey.adapter.in.web.dto.response.survey.SurveySubmitStatusResponseDto;
import com.project.survey.adapter.out.persistence.entity.constant.SurveySubmitStatus;
import com.project.survey.adapter.out.persistence.repository.AnswerRepository;
import com.project.survey.application.port.in.survey.CheckSurveyUseCase;
import com.project.survey.application.port.in.survey.FindSurveyUseCase;
import com.project.survey.config.security.jwt.JwtTokenProvider;
import com.project.survey.domain.Survey;
import com.project.survey.util.custom.UseCase;

import java.time.LocalDateTime;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import io.jsonwebtoken.Claims;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class CheckSurveyService implements CheckSurveyUseCase{

    private final JwtTokenProvider jwtTokenProvider;
    private final FindSurveyUseCase findSurveyUseCase;
    private final AnswerRepository answerRepository;

    /**
     * @param surveyId 설문조사id, token 사용자 정보
     * @return 상태값
     * @apiNote 설문조사 작성 전 상태값 체크
     */
    @Override
    public SurveySubmitStatusResponseDto checkSubmitStatus(Long surveyId, String token) {
        String accessToken = token.replace("Berer ","");
        Claims claims = jwtTokenProvider.getClainsFromToken(accessToken);
        Long memberId = Long.valueOf(claims.getSubject());

        //설문조사 존재 확인.
        Survey survey = findSurveyUseCase.findSurveyDomain(surveyId);
        
        //이미 제출함.
        if(answerRepository.existsByMemberIdAndSurveyId(memberId, surveyId)){
            return SurveySubmitStatusResponseDto.of(SurveySubmitStatus.ALREADY_SUBMIT);
        }

        //제출기간 지남.
        if(survey.getEnddate().isBefore(LocalDateTime.now())){
            return SurveySubmitStatusResponseDto.of(SurveySubmitStatus.SURVEY_EXPIRED); 
        }

        //제출인원 제한.
        if(survey.getRemainingPoints() <= 0){
            return SurveySubmitStatusResponseDto.of(SurveySubmitStatus.SURVEY_FULL); 
        }

        return SurveySubmitStatusResponseDto.of(SurveySubmitStatus.SUCCESS); 
    }
    
}
