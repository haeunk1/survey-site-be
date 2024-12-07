package com.project.survey.application.command.question;

import org.springframework.web.multipart.MultipartFile;

import com.project.survey.adapter.in.web.dto.request.question.QuestionRequestDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class CreateQuestionCommand {
    private Long surveyId;
    private MultipartFile file;

    public static CreateQuestionCommand of(QuestionRequestDto questionRequestDto){
        return CreateQuestionCommand.builder()
        .surveyId(questionRequestDto.getSurveyId())
        .file(questionRequestDto.getFile())
        .build();
    }
}
