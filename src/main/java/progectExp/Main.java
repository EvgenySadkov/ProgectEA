package progectExp;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static final String address = "C:/Users/Escape/Desktop/Программирование/Progects/file.xlsx";

    public static void main(String[] args) throws IOException, InvalidFormatException, ParseException {
        File file = new File(address);
        ReadXLSX readXLSX = new ReadXLSX(file);
//readXLSX.printBase();
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер магазина: ");
        int numberObjekt;
        numberObjekt = Integer.valueOf(sc.nextLine());
        for (int i = 0; i < readXLSX.baseApplication.size(); i++) {
            if (readXLSX.baseApplication.get(i).numberObject == numberObjekt) {
                System.out.println("Заявка № " + readXLSX.baseApplication.get(i).id);
                System.out.println("Текст заявки: " + readXLSX.baseApplication.get(i).text);
                System.out.println("Срок выходит: " + readXLSX.baseApplication.get(i).dataSLA);
                System.out.println();
            }
        }
    }
}
