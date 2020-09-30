package progectExp;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class Main {
   public static final String address = "C:/Users/Escape/Desktop/Программирование/Progects/file.xlsx";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    public static void main(String[] args) throws IOException, InvalidFormatException, ParseException {
        File file = new File(address);
ReadXLSX readXLSX = new ReadXLSX(file);
readXLSX.printBase();


    }
}
