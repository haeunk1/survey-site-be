package com.project.servey.adapter.in.web.dto.request.answer;

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
    private Long serveyId;
    private List<AnswerPairDto> answers;
}
