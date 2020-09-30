package progectExp;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class ReadXLSX {
    private File file;
    private ArrayList<Application> baseApplication;


    public ReadXLSX(File file) throws IOException, InvalidFormatException, ParseException {
        this.file = file;

        // Read XSL file
        FileInputStream inputStream = new FileInputStream(file);
        ZipSecureFile.setMinInflateRatio(-1.0d);         // Get the workbook instance for XLS file
        XSSFWorkbook workbook = new XSSFWorkbook(file);        // Get first sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = sheet.iterator();         //  Iterator<Cell> cellIterator = rowIterator.cellIterator();

        baseApplication = new ArrayList<Application>();


        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();            // Get iterator to all cells of current row
            Iterator<Cell> cellIterator = row.cellIterator();
            String[] arr = new String[16];
            int i = 0;
            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next();                // Change to getCellType() if using POI 4.x

                CellType cellType = cell.getCellTypeEnum();

                switch (cellType) {
                    case _NONE:
                        break;
                    case BOOLEAN:
                        arr[i] = String.valueOf(cell.getBooleanCellValue());
                        break;
                    case BLANK:
                        break;
                    case FORMULA:
                        // Formula
                        arr[i] = cell.getCellFormula();
                        System.out.print("\t");
                        break;
                    case NUMERIC:
                        arr[i] = String.valueOf(cell.getNumericCellValue());
                        break;
                    case STRING:
                        arr[i] = cell.getStringCellValue();

                        break;
                    case ERROR:
                        System.out.print("!");

                        break;
                }
                i++;
            }
            Application application = new Application();
            application.id = Double.parseDouble(arr[0]);
            application.data = arr[1];
            application.lvl1 = arr[2];
            application.lvl2 = arr[3];
            application.lvl3 = arr[4];
            application.lvl4 = arr[5];
            application.type = arr[6];
            application.text = arr[7];
            application.status = arr[8];
            application.initiator = arr[9];
            application.numberObject = Integer.parseInt(arr[10]);
            application.address = arr[11];
            application.engineer = arr[12];
            application.contractor = arr[13];
            application.ChangeDate = arr[14];
            application.dataSLA = arr[15];
            baseApplication.add(application);

        }
    }


    public void printBase() {
        for (int i = 0; i < baseApplication.size(); i++) {
            System.out.println(baseApplication.get(i));
        }
    }

}

