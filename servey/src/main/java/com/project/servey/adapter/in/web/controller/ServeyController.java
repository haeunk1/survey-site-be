package com.project.servey.adapter.in.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.servey.adapter.in.web.dto.response.servey.ServeyResponseDto;

import com.project.servey.application.command.servey.CreateServeyCommand;
import com.project.servey.application.command.servey.FindServeyCommand;
import com.project.servey.application.port.in.servey.CreateServeyUseCase;
import com.project.servey.application.port.in.servey.FindServeyUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/servey")
public class ServeyController {
    private final FindServeyUseCase findServeyUseCase;
    private final CreateServeyUseCase createServeyUseCase;
    //private final DeleteServeyUseCase deleteServeyUseCase;
    
    @GetMapping("/{serveyId}")
    public ResponseEntity<ServeyResponseDto> findServey(@PathVariable("serveyId") Long id){
        FindServeyCommand command = FindServeyCommand.of(id);
        ServeyResponseDto responseDto = findServeyUseCase.findServey(command);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ServeyResponseDto>> findServeyList(){
        List<ServeyResponseDto> list = findServeyUseCase.findServeyList();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/create")
    public ResponseEntity<ServeyResponseDto> createServey(@RequestBody ServeyResponseDto serveyResponseDto){
        CreateServeyCommand command = CreateServeyCommand.of(serveyResponseDto);
        ServeyResponseDto responseDto = createServeyUseCase.createServey(command);
        return ResponseEntity.ok(responseDto);
    }

    // @DeleteMapping("/delete/servey")
    // public ResponseEntity<ServeyResponseDto> deleteServey(@RequestParam(name="serveyId") Long id) {

    //     // commentUseCase.softDeleteComment(commentConverter.deleteRequestDtoToDomain(requestDto));
    //     // return ResponseEntity.ok(
    //     //         ResponseDto.success()
    //     // );
    // }

    /**
     * 댓글 수정
     */
    // @PostMapping("/update/comment")
    // public ResponseEntity<ResponseDto<Void>> updateComment(@Valid @RequestBody CommentUpdateRequestDto requestDto) {
    //     commentUseCase.updateComment(commentConverter.updateRequestDtoToDomain(requestDto));
    //     return ResponseEntity.ok(
    //             ResponseDto.success()
    //     );
    // }

    


}
