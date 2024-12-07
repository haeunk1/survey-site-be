package com.project.survey.adapter.in.web.dto.response.question;

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
public class QuestionResponseDto {
    private Long questionId;
    private Long surveyId;
    private String question;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
