package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelDataReader {

	public static String getCellData(
            String filePath,
            String sheetName,
            int rowNum,
            int colNum) throws IOException {

        FileInputStream fis = new FileInputStream(filePath);

        Workbook workbook = WorkbookFactory.create(fis);

        Sheet sheet = workbook.getSheet(sheetName);

        String data = sheet
                .getRow(rowNum)
                .getCell(colNum)
                .toString();

        workbook.close();
        fis.close();

        return data;
    }
}