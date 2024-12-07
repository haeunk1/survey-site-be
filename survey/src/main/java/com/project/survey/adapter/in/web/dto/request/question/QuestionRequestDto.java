package com.project.survey.adapter.in.web.dto.request.question;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionRequestDto {
    private Long surveyId;
    private MultipartFile file;
}




