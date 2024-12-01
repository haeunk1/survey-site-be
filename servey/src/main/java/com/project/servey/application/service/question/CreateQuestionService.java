package com.project.servey.application.service.question;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.project.servey.adapter.in.web.dto.response.question.QuestionResponseDto;
import com.project.servey.application.command.question.CreateQuestionCommand;
import com.project.servey.application.port.in.question.CreateQuestionUseCase;
import com.project.servey.application.port.out.question.CreateQuestionPort;
import com.project.servey.domain.Question;
import com.project.servey.exception.ServeyException;
import com.project.servey.exception.ErrorCode;
import com.project.servey.mapper.QuestionMapper;
import com.project.servey.util.custom.UseCase;

import lombok.RequiredArgsConstructor;

import org.apache.poi.ss.usermodel.*;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class CreateQuestionService implements CreateQuestionUseCase{
    private final CreateQuestionPort createQuestionPort;
    private final QuestionMapper mapper;

    @Override
    @Transactional
    public List<QuestionResponseDto> createQuestion(CreateQuestionCommand command) {
        
        List<Question> list = getDataList(command);
        List<Question> rtnList;
        try{
            rtnList = createQuestionPort.createQuestion(list);
        }catch(Exception e){
            throw new ServeyException(ErrorCode.FILE_UPLOAD_ERROR, e);
        }
        return mapper.domainToResponseDto(rtnList);
        

    }
    
    public List<Question> getDataList(CreateQuestionCommand command){
        Long id = command.getServeyId();
        MultipartFile file = command.getFile();
        List<Question> rtnList = new ArrayList<>();

        try (InputStream is = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(is)) {

                Sheet sheet = workbook.getSheetAt(0);
                for (Row row : sheet) {
                    Cell cell = row.getCell(0);
                    Question rtnDto = Question.builder().serveyId(id).question(cell.getStringCellValue()).build();
                       
                    rtnList.add(rtnDto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rtnList;
    }
}
