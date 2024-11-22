import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "Student.xlsx";

        List<Student> students = readExcelFile(filePath);

        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static List<Student> readExcelFile(String filePath) {
        List<Student> students = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Первая страница
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Пропустить заголовок

                String name = row.getCell(0).getStringCellValue();
                double currentScholarship = row.getCell(1).getNumericCellValue();
                double newScholarship = row.getCell(2).getNumericCellValue();

                students.add(new Student(name, currentScholarship, newScholarship));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
}
