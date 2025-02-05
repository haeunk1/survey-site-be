package com.project.survey.adapter.out.persistence.entity.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SurveySubmitStatus {
    ALREADY_SUBMIT("이미 제출한 설문조사입니다."),
    SURVEY_FULL("제출 가능 인원을 초과한 설문조사입니다."),
    SURVEY_NOT_FOUND("존재하지 않는 설문조사입니다."),
    SURVEY_EXPIRED("제출 기간이 지난 설문조사입니다."), 
    SUCCESS("설문조사를 제출할 수 있습니다.");

    private final String message;
}
