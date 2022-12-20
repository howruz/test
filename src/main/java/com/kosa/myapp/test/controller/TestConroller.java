package com.kosa.myapp.test.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosa.myapp.test.model.Test;
import com.kosa.myapp.test.service.ITestService;

@Controller
public class TestConroller {
	
	@Autowired
	ITestService testService;
	
	@RequestMapping("/test")
	public void getList() {
		List<Test> list = testService.getList();
		System.out.println(list);
	}
	
	@RequestMapping("/excel")
	public void downExcel(HttpServletResponse response) throws IOException {
		Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("테스트");
        int rowNo = 0;
 
        Row headerRow = sheet.createRow(rowNo++);
        headerRow.createCell(0).setCellValue("사용자 아이디");
        headerRow.createCell(1).setCellValue("이름");
        headerRow.createCell(2).setCellValue("비밀번호");
        headerRow.createCell(3).setCellValue("이메일");
        headerRow.createCell(4).setCellValue("전화번호");
 
        List<Test> list = testService.getList();
        for (Test test : list) {
            Row row = sheet.createRow(rowNo++);
            row.createCell(0).setCellValue(test.getUserId());
            row.createCell(1).setCellValue(test.getName());
            row.createCell(2).setCellValue(test.getPassword());
            row.createCell(3).setCellValue(test.getEmail());
            row.createCell(4).setCellValue(test.getPhone());
        }
 
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=test.xls");
 
        workbook.write(response.getOutputStream());
        workbook.close();
	}

}
