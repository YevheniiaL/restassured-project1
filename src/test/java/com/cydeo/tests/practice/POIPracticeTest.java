package com.cydeo.tests.practice;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class POIPracticeTest {


    public static Workbook workbook;
    public static Sheet sheet;


    public static void main(String[] args) {


        String excelFilePath = "src/test/resources/Excel/data_APOI_1.xlsx";
        String sheetName = "data";

        try {
            FileInputStream inputStream = new FileInputStream(excelFilePath);

            workbook = WorkbookFactory.create(inputStream);
            sheet = workbook.getSheet(sheetName); //excepts name of the sheet
            // getSheetAt(0) provide number of the page , it starts with 0


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FAILED");

        }




        System.out.println("sheet.getRow(5).getCell(2) = " + sheet.getRow(5).getCell(2));

        List<String> headers = getColumnHeaders();
        System.out.println("headers = " + headers);

        List<Object> row = getAnyRowAsList(3);
        System.out.println("row = " + row);

        List<Map<String,Object>> map = getAnyColumnAsMap(2);
        System.out.println("map = " + map);
    }


    /**
     * By providing the row we are retrieving all the data from given row cell values
     * @return
     */
    public static List<String> getColumnHeaders() {

        List<String> columnHeaderList = new ArrayList<>();

        Row firstRow = sheet.getRow(0);

        for (int i = 0; i < firstRow.getPhysicalNumberOfCells(); i++) {
            columnHeaderList.add(firstRow.getCell(i).getStringCellValue());

        }
        return columnHeaderList;
    }

    /**
     * method returns info of the row  argument
     * @param rowNumber
     * @return
     */
    public  static  List<Object> getAnyRowAsList(int rowNumber){
        List<Object> rowList = new ArrayList<>();

        if (rowNumber>sheet.getPhysicalNumberOfRows() || rowNumber<0){
            return  rowList;
        }else {

            Row desiredRow = sheet.getRow(rowNumber);

            for (int i = 0; i < desiredRow.getPhysicalNumberOfCells(); i++) {
                rowList.add(desiredRow.getCell(i));

            }
        }
        return rowList;

    }






    public static List<Map<String,Object>> getAnyColumnAsMap(int columnNumber){

        List<String> columnHeaders = getColumnHeaders();
        List<Map<String, Object>> columnMapList = new ArrayList<>();

        if (columnNumber>columnHeaders.size() || columnNumber< 0){
            return  columnMapList;
        }else {

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {

                Map<String,Object> currentMap = new LinkedHashMap<>();
                Row desiredRow = sheet.getRow(i);

                currentMap.put(columnHeaders.get(columnNumber),desiredRow.getCell(columnNumber));
                columnMapList.add(currentMap);
            }


        }
        columnMapList.remove(0);
        return columnMapList;


    }
}
