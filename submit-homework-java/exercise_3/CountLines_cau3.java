package Bai_tap_2;

import java.io.*;

public class CountLines_cau3 {
    public static void main(String[] args) {
        String fileName = "src/Bai_tap_2/input.txt"; // đếm số dòng của file input.txt

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int lineCount = 0;
            while (reader.readLine() != null) {
                lineCount++;
            }
            System.out.println("Số dòng trong file: " + lineCount);
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }
}
// đếm số dòng trong file input.txt (đếm cả dòng k chứa kí tự)
