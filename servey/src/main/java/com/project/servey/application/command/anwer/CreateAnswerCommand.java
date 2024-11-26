package com.project.servey.application.command.anwer;

import java.util.List;

import com.project.servey.adapter.in.web.dto.request.answer.AnswerPairDto;
import com.project.servey.adapter.in.web.dto.request.answer.AnswerRequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.AccessLevel;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class CreateAnswerCommand {
    Long memberId;
    Long serveyId;
    List<AnswerPairDto> answers;

    public static CreateAnswerCommand of(AnswerRequestDto requestDto){
        return builder()
        .memberId(requestDto.getMemberId())
        .serveyId(requestDto.getServeyId())
        .answers(requestDto.getAnswers())
        .build();
    }
}
