package Bai_tap_2;

import java.io.*;
import java.util.Scanner;

public class IntegerFileIO_cau4 {
	public static void main(String[] args) {
		String fileName = "src/Bai_tap_2/numbers.dat";

		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName));
				Scanner scanner = new Scanner(System.in)) {

			System.out.println("Nhập số lượng số nguyên:");
			int n = scanner.nextInt();

			System.out.println("Nhập " + n + " số nguyên:");
			for (int i = 0; i < n; i++) {
				dos.writeInt(scanner.nextInt());
			}

			System.out.println("Dữ liệu đã được ghi vào " + fileName);
		} catch (IOException e) {
			System.out.println("Lỗi khi ghi file: " + e.getMessage());
		}

		try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
			System.out.println("Các số nguyên được đọc từ file:");
			while (true) {
				try {
					System.out.print(dis.readInt() + " ");
				} catch (EOFException e) {
					break;
				}
			}
		} catch (IOException e) {
			System.out.println("Lỗi khi đọc file: " + e.getMessage());
		}
	}
}
// nhập số nguyên
// ghi vào file number.dat sau đó đọc file và in ra màn hình
