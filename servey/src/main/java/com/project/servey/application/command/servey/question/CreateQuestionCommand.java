package com.project.servey.application.command.servey.question;

import org.springframework.web.multipart.MultipartFile;

import com.project.servey.adapter.in.web.dto.request.question.QuestionRequestDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class CreateQuestionCommand {
    private Long serveyId;
    private MultipartFile file;

    public static CreateQuestionCommand of(QuestionRequestDto questionRequestDto){
        return CreateQuestionCommand.builder()
        .serveyId(questionRequestDto.getServeyId())
        .file(questionRequestDto.getFile())
        .build();
    }
}
