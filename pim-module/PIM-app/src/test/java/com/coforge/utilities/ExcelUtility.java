package com.coforge.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    public static Object[][] getexceldata(String sheetName) throws IOException {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/data.xlsx";
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int totalRows = sheet.getLastRowNum(); // Exclude header row
        int totalCols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[totalRows][totalCols];
        DataFormatter formatter = new DataFormatter();

        for (int i = 1; i <= totalRows; i++) { // Start from row 1 (skip header)
            for (int j = 0; j < totalCols; j++) {
                data[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
            }
        }

        workbook.close();
        fis.close();
        return data;
    }
}