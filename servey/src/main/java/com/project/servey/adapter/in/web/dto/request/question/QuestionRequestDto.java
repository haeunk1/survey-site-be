package com.project.servey.adapter.in.web.dto.request.question;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionRequestDto {
    private Long serveyId;
    private MultipartFile file;
}




