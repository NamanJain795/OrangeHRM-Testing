package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelDataReader {

	public static Object[][] getCellData(
			String filePath, String sheetName) throws IOException {
		 
	    List<Object[]> data = new ArrayList<>();
	 
	    try (FileInputStream fis = new FileInputStream(filePath);
	         Workbook workbook = WorkbookFactory.create(fis)) {
	 
	        Sheet sheet = workbook.getSheet(sheetName);
	        DataFormatter formatter = new DataFormatter();
	 
	        // Row 0 contains headers; data starts at row index 1
	        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	            Row row = sheet.getRow(i);
	 
	            if (row == null) {
	                continue;
	            }
	 
	            String username = formatter.formatCellValue(
	                    row.getCell(3)
	            ).trim();
	 
	            // Skip blank rows
	            if (username.isEmpty()) {
	                continue;
	            }
	 
	            String userRole = formatter.formatCellValue(
	                    row.getCell(0)).trim();
	            String employeeName = formatter.formatCellValue(
	                    row.getCell(1)).trim();
	            String status = formatter.formatCellValue(
	                    row.getCell(2)).trim();
	            String password = formatter.formatCellValue(
	                    row.getCell(4)).trim();
	            String confirmPassword = formatter.formatCellValue(
	                    row.getCell(5)).trim();
	 
	            data.add(new Object[] {
	                userRole,
	                employeeName,
	                status,
	                username,
	                password,
	                confirmPassword
	            });
	        }
	    }
	 
	    return data.toArray(new Object[0][]);
    }
}
