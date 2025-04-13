package Bai_tap_2;

import java.io.*;

public class FileCopy_cau1 {
	public static void main(String[] args) {
		String sourceFile = "src/Bai_tap_2/source.txt";
		String destFile = "src/Bai_tap_2/destination.txt";

		try (FileInputStream fis = new FileInputStream(sourceFile);
				FileOutputStream fos = new FileOutputStream(destFile)) {

			byte[] buffer = new byte[1024];
			int bytesRead;

			while ((bytesRead = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, bytesRead);
			}

			System.out.println("Sao chép file thành công!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
// sao chép dữ liệu từ file source.txt vào file destination.txt