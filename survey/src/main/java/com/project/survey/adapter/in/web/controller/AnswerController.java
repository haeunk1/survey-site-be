package com.project.survey.adapter.in.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.survey.adapter.in.web.dto.api.ApiResponse;
import com.project.survey.adapter.in.web.dto.request.answer.AnswerRequestDto;
import com.project.survey.adapter.in.web.dto.request.question.QuestionRequestDto;
import com.project.survey.application.command.anwer.CreateAnswerCommand;
import com.project.survey.application.command.question.CreateQuestionCommand;
import com.project.survey.application.port.in.answer.CreateAnswerUseCase;
import com.project.survey.application.port.in.question.CreateQuestionUseCase;
import com.project.survey.application.port.in.question.FindQuestionUseCase;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final CreateAnswerUseCase answerUseCase;
    

    /**
     * @param surveyId,file 설문조사ID, 문항 리스트(파일)
     * @return 업로드 성공메세지
     * @apiNote 설문조사ID와 MultipartFile 형식으로 설문조사 문항 리스트 입력받아 저장.
     */
    @PostMapping("/submit")
    @Transactional
    public ResponseEntity<ApiResponse<String>> submitAnswers(@RequestBody AnswerRequestDto request) {
        
        //유효성 검사, 예외처리 추가 필요
        CreateAnswerCommand command = CreateAnswerCommand.of(request);
        int rtnInt = answerUseCase.createAnswer(command);

        return ResponseEntity.ok(ApiResponse.success("제출 완료"));
    }
}
