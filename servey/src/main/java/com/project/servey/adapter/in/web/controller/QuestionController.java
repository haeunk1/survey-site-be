package com.project.servey.adapter.in.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.servey.adapter.in.web.dto.api.ApiResponse;
import com.project.servey.adapter.in.web.dto.request.question.QuestionRequestDto;
import com.project.servey.adapter.in.web.dto.response.QuestionResponseDto;
import com.project.servey.application.command.servey.question.CreateQuestionCommand;
import com.project.servey.application.port.in.question.CreateQuestionUseCase;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    private final CreateQuestionUseCase createQuestionUseCase;
    
    @PostMapping("/upload")
    @Transactional
    public ResponseEntity<ApiResponse<String>> uploadFile(
        @RequestParam("serveyId") Long serveyId,
        @RequestParam("file") MultipartFile file) {
        QuestionRequestDto questionRequestDto = QuestionRequestDto.builder().serveyId(serveyId).file(file).build();
        if(questionRequestDto.getFile().isEmpty()){
            return ResponseEntity.ok(ApiResponse.success("파일이 비어있습니다."));
        }
        
        // 엑셀 파일을 처리하는 로직
        String fileName = questionRequestDto.getFile().getOriginalFilename();
        System.out.println("업로드된 파일 이름: " + fileName);
        System.out.println("설문조사 id: " + questionRequestDto.getServeyId());

        // 엑셀 파일 읽기
        CreateQuestionCommand command = CreateQuestionCommand.of(questionRequestDto);
        createQuestionUseCase.createQuestion(command);
        return ResponseEntity.ok(ApiResponse.success("파일 업로드 성공: " + fileName));
        
    }
}
