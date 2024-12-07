package com.project.survey.adapter.in.web.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.survey.adapter.in.web.dto.request.survey.SurveyListRequestDto;
import com.project.survey.adapter.in.web.dto.response.survey.SurveyListResponseDto;
import com.project.survey.adapter.in.web.dto.response.survey.SurveyResponseDto;

import com.project.survey.application.command.survey.CreateSurveyCommand;
import com.project.survey.application.command.survey.FindSurveyCommand;
import com.project.survey.application.command.survey.FindSurveyListCommand;
import com.project.survey.application.command.survey.UpdateSurveyCommand;
import com.project.survey.application.port.in.survey.CreateSurveyUseCase;
import com.project.survey.application.port.in.survey.DeleteSurveyUseCase;
import com.project.survey.application.port.in.survey.FindSurveyUseCase;
import com.project.survey.application.port.in.survey.UpdateSurveyUseCase;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/survey")
public class SurveyController {
    private final FindSurveyUseCase findSurveyUseCase;
    private final CreateSurveyUseCase createSurveyUseCase;
    private final DeleteSurveyUseCase deleteSurveyUseCase;
    private final UpdateSurveyUseCase updateSurveyUseCase;
    
    @GetMapping("/{surveyId}")
    public ResponseEntity<SurveyResponseDto> findSurvey(@PathVariable("surveyId") Long id){
        FindSurveyCommand command = FindSurveyCommand.of(id);
        SurveyResponseDto responseDto = findSurveyUseCase.findSurvey(command);
        return ResponseEntity.ok(responseDto);
    }

    
    @GetMapping("/listAll")
    public ResponseEntity<List<SurveyResponseDto>> findSurveyAllList(){
        List<SurveyResponseDto> list = findSurveyUseCase.findSurveyAllList();
        return ResponseEntity.ok(list);
    }

    /**
     * @param surveyListRequestDto 리스트 필터링 Dto
     * @return 필터링된 리스트
     * @apiNote 제목,정렬조건에 따른 리스트를 리턴합니다.
     */
    @PostMapping("/list")
    public ResponseEntity<List<SurveyListResponseDto>> findSurveyFilteredList(@RequestBody SurveyListRequestDto surveyListRequestDto){
        FindSurveyListCommand command = FindSurveyListCommand.of(surveyListRequestDto);
        List<SurveyListResponseDto> list = findSurveyUseCase.findSurveyFilteredList(command);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/create")
    public ResponseEntity<SurveyResponseDto> createSurvey(@RequestBody SurveyResponseDto surveyResponseDto){
        CreateSurveyCommand command = CreateSurveyCommand.of(surveyResponseDto);
        SurveyResponseDto responseDto = createSurveyUseCase.createSurvey(command);
        return ResponseEntity.ok(responseDto);
    }
    
    @Transactional
    @DeleteMapping("/delete")
    public ResponseEntity<Long> deleteSurvey(@RequestParam(name="surveyId") Long id) {
        Long rtn = deleteSurveyUseCase.deleteSurveyById(id);
        return ResponseEntity.ok(rtn);
    }

    @Transactional
    @PutMapping("/update")
    public ResponseEntity<Long> updateSurvey(@RequestBody SurveyResponseDto surveyResponseDto){
        UpdateSurveyCommand command = UpdateSurveyCommand.of(surveyResponseDto);
        Long rtn = updateSurveyUseCase.updateSurvey(command);
        return ResponseEntity.ok(rtn);
    }
}
