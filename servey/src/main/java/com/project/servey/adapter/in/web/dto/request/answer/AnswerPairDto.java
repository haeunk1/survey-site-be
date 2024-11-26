package com.project.servey.adapter.in.web.dto.request.answer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerPairDto {
    private Long questionId;
    private String answer;
}
