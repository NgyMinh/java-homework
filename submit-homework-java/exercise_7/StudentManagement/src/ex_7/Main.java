package ex_7;

import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.Pattern;

class Student {
	private String name;
	private double score;

	public Student(String name, double score) {
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public double getScore() {
		return score;
	}

	@Override
	public String toString() {
		return "Student{name='" + name + "', score=" + score + "}";
	}
}

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		List<Student> students = new ArrayList<>();

		int n = 0;
		boolean validInput = false;
		while (!validInput) {
			try {
				System.out.print("Nhập số lượng học sinh: ");
				n = scanner.nextInt();
				if (n <= 0) {
					System.out.println("Số lượng học sinh phải là số nguyên dương. Vui lòng nhập lại.");
					continue;
				}
				validInput = true;
			} catch (InputMismatchException e) {
				System.out.println("Vui lòng nhập một số nguyên hợp lệ.");
				scanner.nextLine();
			}
		}
		scanner.nextLine();

		// Nhập thông tin từng học sinh và kiểm tra lỗi
		for (int i = 0; i < n; i++) {
			System.out.println("Học sinh " + (i + 1) + ":");

			String name = "";
			validInput = false;
			while (!validInput) {
				System.out.print("Nhập tên: ");
				name = scanner.nextLine().trim();
				if (name.isEmpty()) {
					System.out.println("Tên không được để trống !!!");
					continue;
				}
				// Kiểm tra tên có chứa số không
				if (Pattern.compile("[0-9]").matcher(name).find()) {
					System.out.println("Tên không được chứa số !!!");
					continue;
				}
				validInput = true;
			}

			double score = 0.0;
			validInput = false;
			while (!validInput) {
				try {
					System.out.print("Nhập điểm: ");
					String scoreInput = scanner.nextLine();

					scoreInput = scoreInput.replace(",", ".");
					score = Double.parseDouble(scoreInput);
					if (score < 0 || score > 10) {
						System.out.println("Điểm phải nằm trong khoảng 0 đến 10");
						continue;
					}
					validInput = true;
				} catch (NumberFormatException e) {
					System.out.println("Dữ liệu không hợp lệ");
				}
			}
			students.add(new Student(name, score));
		}

		// Bài 1: Tìm học sinh có điểm cao nhất
		Student topStudent = students.stream().max(Comparator.comparingDouble(Student::getScore)).orElse(null);
		if (topStudent != null) {
			System.out.println(
					"Học sinh có điểm cao nhất là: " + topStudent.getName() + ", Điểm: " + topStudent.getScore());
		} else {
			System.out.println("Không tìm được học sinh có điểm cao nhất.");
		}

		// Bài 2: Tính điểm trung bình
		double averageScore = students.stream().mapToDouble(Student::getScore).average().orElse(0.0);
		System.out.printf("Điểm trung bình của lớp là: %.2f%n", averageScore);

		// Bài 3: Tách thành 2 nhóm Pass và Fail
		Map<String, List<Student>> result = students.stream()
				.collect(Collectors.groupingBy(student -> student.getScore() >= 5.0 ? "Pass" : "Fail"));

		System.out.println("Nhóm Pass (điểm >= 5.0): " + result.getOrDefault("Pass", new ArrayList<>()));
		System.out.println("Nhóm Fail (điểm < 5.0): " + result.getOrDefault("Fail", new ArrayList<>()));

		scanner.close();
	}
}