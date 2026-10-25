
package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

    private static final String TEST_DATA_PATH =
            "src/test/resources/";

    private final DataFormatter formatter = new DataFormatter();

    public String getExcelData(
            String fileName, String sheetName,
            int rowNum, int cellNum) throws IOException {

        try (InputStream input = new FileInputStream(
                TEST_DATA_PATH + fileName);
             Workbook workbook = WorkbookFactory.create(input)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException(
                        "Sheet not found: " + sheetName
                        + " in " + fileName);
            }

            Row row = sheet.getRow(rowNum);

            if (row == null || row.getCell(cellNum) == null) {
                return "";
            }

            return formatter.formatCellValue(
                    row.getCell(cellNum)).trim();
        }
    }

    public int getRowCount(
            String fileName, String sheetName) throws IOException {

        try (InputStream input = new FileInputStream(
                TEST_DATA_PATH + fileName);
             Workbook workbook = WorkbookFactory.create(input)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException(
                        "Sheet not found: " + sheetName
                        + " in " + fileName);
            }

            return sheet.getLastRowNum();
        }
    }

    public Map<String, String> getRowData(
            String fileName, String sheetName,
            int rowNum) throws IOException {

        Map<String, String> data = new HashMap<>();

        try (InputStream input = new FileInputStream(
                TEST_DATA_PATH + fileName);
             Workbook workbook = WorkbookFactory.create(input)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException(
                        "Sheet not found: " + sheetName
                        + " in " + fileName);
            }

            Row header = sheet.getRow(0);
            Row row = sheet.getRow(rowNum);

            if (header == null || row == null) {
                throw new IllegalArgumentException(
                        "Header or data row not found: " + rowNum);
            }

            for (int i = 0; i < header.getLastCellNum(); i++) {
                if (header.getCell(i) == null) {
                    continue;
                }

                String key = formatter.formatCellValue(
                        header.getCell(i)).trim();

                String value = row.getCell(i) == null
                        ? ""
                        : formatter.formatCellValue(
                                row.getCell(i)).trim();

                if (!key.isEmpty()) {
                    data.put(key, value);
                }
            }
        }

        return data;
    }
}
