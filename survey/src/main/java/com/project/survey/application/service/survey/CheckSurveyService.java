package com.project.survey.application.service.survey;

import com.project.survey.adapter.in.web.dto.response.survey.SurveySubmitStatusResponseDto;
import com.project.survey.application.port.in.survey.CheckSurveyUseCase;
import com.project.survey.config.security.jwt.JwtTokenProvider;
import com.project.survey.util.custom.UseCase;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import io.jsonwebtoken.Claims;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class CheckSurveyService implements CheckSurveyUseCase{

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public SurveySubmitStatusResponseDto checkSubmitStatus(Long surveyId, String token) {
        String accessToken = token.replace("Berer ","");
        Claims claims = jwtTokenProvider.getClainsFromToken(accessToken);
        String userEmail = claims.getSubject();

        //작성중
        return null;
    }
    
}
