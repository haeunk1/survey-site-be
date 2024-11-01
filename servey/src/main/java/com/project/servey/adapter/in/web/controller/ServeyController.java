package com.project.servey.adapter.in.web.controller;

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

import com.project.servey.adapter.in.web.dto.request.servey.ServeyListRequestDto;
import com.project.servey.adapter.in.web.dto.response.servey.ServeyListResponseDto;
import com.project.servey.adapter.in.web.dto.response.servey.ServeyResponseDto;

import com.project.servey.application.command.servey.CreateServeyCommand;
import com.project.servey.application.command.servey.FindServeyCommand;
import com.project.servey.application.command.servey.FindServeyListCommand;
import com.project.servey.application.command.servey.UpdateServeyCommand;
import com.project.servey.application.port.in.servey.CreateServeyUseCase;
import com.project.servey.application.port.in.servey.DeleteServeyUseCase;
import com.project.servey.application.port.in.servey.FindServeyUseCase;
import com.project.servey.application.port.in.servey.UpdateServeyUseCase;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/servey")
public class ServeyController {
    private final FindServeyUseCase findServeyUseCase;
    private final CreateServeyUseCase createServeyUseCase;
    private final DeleteServeyUseCase deleteServeyUseCase;
    private final UpdateServeyUseCase updateServeyUseCase;
    
    @GetMapping("/{serveyId}")
    public ResponseEntity<ServeyResponseDto> findServey(@PathVariable("serveyId") Long id){
        FindServeyCommand command = FindServeyCommand.of(id);
        ServeyResponseDto responseDto = findServeyUseCase.findServey(command);
        return ResponseEntity.ok(responseDto);
    }

    
    @GetMapping("/listAll")
    public ResponseEntity<List<ServeyResponseDto>> findServeyAllList(){
        List<ServeyResponseDto> list = findServeyUseCase.findServeyAllList();
        return ResponseEntity.ok(list);
    }

    /**
     * @param serveyListRequestDto 리스트 필터링 Dto
     * @return 필터링된 리스트
     * @apiNote 제목,정렬조건에 따른 리스트를 리턴합니다.
     */
    @GetMapping("/list")
    public ResponseEntity<List<ServeyListResponseDto>> findServeyFilteredList(@RequestBody ServeyListRequestDto serveyListRequestDto){
        FindServeyListCommand command = FindServeyListCommand.of(serveyListRequestDto);
        List<ServeyListResponseDto> list = findServeyUseCase.findServeyFilteredList(command);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/create")
    public ResponseEntity<ServeyResponseDto> createServey(@RequestBody ServeyResponseDto serveyResponseDto){
        CreateServeyCommand command = CreateServeyCommand.of(serveyResponseDto);
        ServeyResponseDto responseDto = createServeyUseCase.createServey(command);
        return ResponseEntity.ok(responseDto);
    }
    
    @Transactional
    @DeleteMapping("/delete")
    public ResponseEntity<Long> deleteServey(@RequestParam(name="serveyId") Long id) {
        Long rtn = deleteServeyUseCase.deleteServeyById(id);
        return ResponseEntity.ok(rtn);
    }

    @Transactional
    @PutMapping("/update")
    public ResponseEntity<Long> updateServey(@RequestBody ServeyResponseDto serveyResponseDto){
        UpdateServeyCommand command = UpdateServeyCommand.of(serveyResponseDto);
        Long rtn = updateServeyUseCase.updateServey(command);
        return ResponseEntity.ok(rtn);
    }
}
