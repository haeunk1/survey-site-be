package com.project.survey.adapter.in.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.survey.adapter.in.web.dto.api.ApiResponse;
import com.project.survey.adapter.in.web.dto.request.question.QuestionRequestDto;
import com.project.survey.adapter.in.web.dto.response.question.QuestionResponseDto;
import com.project.survey.application.command.question.CreateQuestionCommand;
import com.project.survey.application.port.in.question.CreateQuestionUseCase;
import com.project.survey.application.port.in.question.FindQuestionUseCase;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    private final CreateQuestionUseCase createQuestionUseCase;
    private final FindQuestionUseCase findQuestionUseCase;
    

    /**
     * @param surveyId,file 설문조사ID, 문항 리스트(파일)
     * @return 업로드 성공메세지
     * @apiNote 설문조사ID와 MultipartFile 형식으로 설문조사 문항 리스트 입력받아 저장.
     */
    @PostMapping("/upload")
    @Transactional
    public ResponseEntity<ApiResponse<String>> uploadFile(
        @RequestParam("surveyId") Long surveyId,
        @RequestParam("file") MultipartFile file) {
        QuestionRequestDto questionRequestDto = QuestionRequestDto.builder().surveyId(surveyId).file(file).build();
        if(questionRequestDto.getFile().isEmpty()){
            return ResponseEntity.ok(ApiResponse.success("파일이 비어있습니다."));
        }
        
        // 엑셀 파일을 처리하는 로직
        String fileName = questionRequestDto.getFile().getOriginalFilename();
        System.out.println("업로드된 파일 이름: " + fileName);
        System.out.println("설문조사 id: " + questionRequestDto.getSurveyId());

        // 엑셀 파일 읽기
        CreateQuestionCommand command = CreateQuestionCommand.of(questionRequestDto);
        createQuestionUseCase.createQuestion(command);
        return ResponseEntity.ok(ApiResponse.success("파일 업로드 성공: " + fileName));
        
    }

    /**
     * @param surveyId 설문조사
     * @return 설문조사 문항리스트
     * @apiNote 설문조사ID에 해당하는 문항 조회
     */
    @GetMapping("/list")
    @Transactional
    public ResponseEntity<List<QuestionResponseDto>> getQuestionList(@RequestParam(name="surveyId") Long id){
        List<QuestionResponseDto> rtnList = findQuestionUseCase.findQuestionList(id);
        return ResponseEntity.ok(rtnList);
    }
}
