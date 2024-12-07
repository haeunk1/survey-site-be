package com.project.survey.domain;


import java.time.LocalDateTime;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Question {
    private Long questionId;
    private Long surveyId;
    private String question;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Question of(Long questionId, Long surveyId, String question){
        return builder()
        .questionId(questionId)
        .surveyId(surveyId)
        .question(question)
        .createdAt(LocalDateTime.now())
        .updatedAt(null)
        .build();
    }
}
