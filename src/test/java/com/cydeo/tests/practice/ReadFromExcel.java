package com.cydeo.tests.practice;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadFromExcel {

    public static void main(String[] args) throws IOException {

       String filePath = "src/test/resources/Excel/data_APOI_1.xlsx";
       String sheetName = "data";

       InputStream in  = new FileInputStream(filePath);
       Workbook workbook = WorkbookFactory.create(in);
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(1);

        sheet.forEach(r->{
            r.cellIterator().forEachRemaining(c->System.out.print(c.toString()+" "));
            System.out.println();


        });


    }
}
