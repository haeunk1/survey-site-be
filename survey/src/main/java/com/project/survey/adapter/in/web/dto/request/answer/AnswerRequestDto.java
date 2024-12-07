package com.project.survey.adapter.in.web.dto.request.answer;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AnswerRequestDto {
    private Long memberId;
    private Long surveyId;
    private List<AnswerPairDto> answers;
}
