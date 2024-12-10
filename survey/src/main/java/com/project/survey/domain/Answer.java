package com.project.survey.domain;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Answer {
    private Long answerId;
    private Long surveyId;
    private Long questionId;
    private Long memberId;
    private String answer;
    private LocalDateTime submissionDate;

    public static Answer of(Long answerId, Long surveyId, Long questionId, Long memberId, String answer){
        return builder()
        .answerId(answerId)
        .surveyId(surveyId)
        .questionId(questionId)
        .memberId(memberId)
        .answer(answer)
        .submissionDate(LocalDateTime.now())
        .build();
    }
}
