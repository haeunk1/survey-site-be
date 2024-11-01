package com.project.servey.domain;


import java.time.LocalDateTime;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Question {
    private Long questionId;
    private Long serveyId;
    private String question;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Question of(Long questionId, Long serveyId, String question){
        return builder()
        .questionId(questionId)
        .serveyId(serveyId)
        .question(question)
        .createdAt(LocalDateTime.now())
        .updatedAt(null)
        .build();
    }
}
