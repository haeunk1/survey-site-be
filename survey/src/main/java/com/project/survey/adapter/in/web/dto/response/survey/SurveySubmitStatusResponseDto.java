package com.project.survey.adapter.in.web.dto.response.survey;

import com.project.survey.adapter.out.persistence.entity.constant.SurveySubmitStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveySubmitStatusResponseDto {
    private SurveySubmitStatus status;
    private String message;       // 상태에 따른 상세 메시지
}