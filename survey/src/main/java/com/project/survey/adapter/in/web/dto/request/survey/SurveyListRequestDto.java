package com.project.survey.adapter.in.web.dto.request.survey;

import com.project.survey.adapter.out.persistence.entity.constant.ListOrderType;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SurveyListRequestDto {
    private String title;
    private ListOrderType orderType;

    public static SurveyListRequestDto of(String title, ListOrderType orderType){
        return builder()
        .title(title)
        .orderType(orderType)
        .build();
    }
}