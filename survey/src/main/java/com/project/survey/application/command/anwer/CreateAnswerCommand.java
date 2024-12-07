package com.project.survey.application.command.anwer;

import java.util.List;

import com.project.survey.adapter.in.web.dto.request.answer.AnswerPairDto;
import com.project.survey.adapter.in.web.dto.request.answer.AnswerRequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.AccessLevel;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class CreateAnswerCommand {
    Long memberId;
    Long surveyId;
    List<AnswerPairDto> answers;

    public static CreateAnswerCommand of(AnswerRequestDto requestDto){
        return builder()
        .memberId(requestDto.getMemberId())
        .surveyId(requestDto.getSurveyId())
        .answers(requestDto.getAnswers())
        .build();
    }
}
