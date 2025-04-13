package Bai_tap_2;

import java.io.*;
public class WriteToFile_cau2 {
	public static void main(String[] args) {
		String fileName = "src/Bai_tap_2/input.txt"; 

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

			System.out.println("Nhập nội dung (gõ 'exit' để thoát):");

			String line;
			while (!(line = reader.readLine()).equalsIgnoreCase("exit")) {
				writer.write(line);
				writer.newLine();
			}

			System.out.println("Dữ liệu đã được ghi vào " + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
// đọc dữ liệu ghi vào file input.txt
