package com.project.servey.adapter.in.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.servey.util.ExcelHelper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("파일이 비어있습니다.");
        }

        try {
            // 엑셀 파일을 처리하는 로직 (예: 파일을 읽어 데이터베이스에 저장 등)
            String fileName = file.getOriginalFilename();
            System.out.println("업로드된 파일 이름: " + fileName);

            // 엑셀 파일 읽기
            ExcelHelper.readExcelFile(file);

            return ResponseEntity.ok("파일 업로드 성공: " + fileName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 처리 중 오류 발생.");
        }
    }
}
