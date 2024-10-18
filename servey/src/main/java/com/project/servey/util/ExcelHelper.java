package com.project.servey.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.project.servey.adapter.in.web.dto.response.QuestionResponseDto;

public class ExcelHelper {
    public static void readExcelFile(Long id, MultipartFile file) {
        try (InputStream is = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(is)) {
                
                List<QuestionResponseDto> rtnList = new ArrayList<>();

                Sheet sheet = workbook.getSheetAt(0);
                for (Row row : sheet) {
                    for (Cell cell : row) {
                        switch (cell.getCellType()) {
                            case STRING:
                            //rtnList.add(cell.)
                                System.out.print(cell.getStringCellValue() + "\t");
                                break;
                            case NUMERIC:
                                System.out.print(cell.getNumericCellValue() + "\t");
                                break;
                            default:
                                System.out.print("Unknown type\t");
                                break;
                        }
                    }
                    System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
