package com.project.servey.adapter.in.web.dto.response.servey;

import java.time.LocalDateTime;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionResponseDto {
    private Long questionId;
    private Long serveyId;
    private String question;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
