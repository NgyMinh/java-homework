package dao;

import database.DatabaseConnection;
import models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentDAO {
	private Connection connection;

	public StudentDAO() {
		connection = DatabaseConnection.getConnection();
	}

	
	public List<Student> getAllStudents() {
		List<Student> students = new ArrayList<>();
		String query = "SELECT * FROM Student";
		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				Student student = new Student(rs.getString("StudentID"), rs.getString("Name"), rs.getInt("Age"),
						rs.getString("Email"), rs.getDouble("GPA"));
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	
	public boolean addStudent(Student student) {
		
		if (student.getGpa() < 1.0 || student.getGpa() > 10.0) {
			System.err.println("GPA phải nằm trong khoảng từ 1.0 đến 10.0");
			return false;
		}

		String query = "INSERT INTO Student (StudentID, Name, Age, Email, GPA) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			
			if (student.getStudentID() == null || student.getStudentID().isEmpty()) {
				student.setStudentID(generateStudentID());
			}

			pstmt.setString(1, student.getStudentID()); 
			pstmt.setString(2, student.getName());
			pstmt.setInt(3, student.getAge());
			pstmt.setString(4, generateEmail(student.getName()));
			pstmt.setDouble(5, student.getGpa());
			pstmt.executeUpdate(); 

			System.out.println("Thêm sinh viên thành công vào database với ID: " + student.getStudentID());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Lỗi khi thêm sinh viên vào database: " + e.getMessage());
			return false;
		}
	}

	
	public boolean addStudentToClass(String studentID, String classIdentifier, boolean isUsingDescription) {
		String classQuery;
		if (isUsingDescription) {
			classQuery = "SELECT ClassID FROM Class WHERE Description = ?";
		} else {
			classQuery = "SELECT ClassID FROM Class WHERE ClassID = ?";
		}

		String insertQuery = "INSERT INTO Learn (StudentID, ClassID) VALUES (?, ?)";

		try (PreparedStatement classStmt = connection.prepareStatement(classQuery);
				PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {

			
			classStmt.setString(1, classIdentifier);
			ResultSet rs = classStmt.executeQuery();
			if (rs.next()) {
				int classID = rs.getInt("ClassID");

				
				insertStmt.setString(1, studentID);
				insertStmt.setInt(2, classID);
				insertStmt.executeUpdate();

				System.out.println("Thêm sinh viên vào lớp thành công.");
				return true;
			} else {
				System.err.println("Không tìm thấy lớp với " + (isUsingDescription ? "Description" : "ClassID") + ": "
						+ classIdentifier);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Lỗi khi thêm sinh viên vào lớp: " + e.getMessage());
			return false;
		}
	}

	public List<Student> getStudentsByClassID(String classID) {
		List<Student> students = new ArrayList<>();
		String query = "SELECT S.StudentID, S.Name, S.Age, S.GPA, S.Email " + "FROM Student S "
				+ "JOIN Learn L ON S.StudentID = L.StudentID " + "WHERE L.ClassID = ?";

		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, classID);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String studentID = rs.getString("StudentID");
				String name = rs.getString("Name");
				int age = rs.getInt("Age");
				double gpa = rs.getDouble("GPA");
				String email = rs.getString("Email");
				students.add(new Student(studentID, name, age, email, gpa));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Lỗi khi lấy danh sách sinh viên của lớp " + classID + ": " + e.getMessage());
		}

		return students;
	}

	
	private String generateStudentID() {
		Random random = new Random();
		int randomNumber = 100 + random.nextInt(900); 
		return "VKU" + randomNumber; 
	}

	
	public boolean editStudent(Student student) {
		
		if (student.getGpa() < 1.0 || student.getGpa() > 10.0) {
			System.err.println("GPA phải nằm trong khoảng từ 1.0 đến 10.0");
			return false;
		}

		String query = "UPDATE Student SET Name = ?, Age = ?, GPA = ?, Email = ? WHERE StudentID = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, student.getName());
			pstmt.setInt(2, student.getAge());
			pstmt.setDouble(3, student.getGpa());
			pstmt.setString(4, student.getEmail());
			pstmt.setString(5, student.getStudentID());
			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Cập nhật sinh viên thành công.");
				return true;
			} else {
				System.out.println("Không tìm thấy sinh viên với ID: " + student.getStudentID());
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Lỗi khi cập nhật sinh viên: " + e.getMessage());
			return false;
		}
	}

	
	public boolean deleteStudent(String studentID) {
		String deleteFromLearnQuery = "DELETE FROM Learn WHERE StudentID = ?";
		String deleteStudentQuery = "DELETE FROM Student WHERE StudentID = ?";

		try (PreparedStatement deleteLearnStmt = connection.prepareStatement(deleteFromLearnQuery);
				PreparedStatement deleteStudentStmt = connection.prepareStatement(deleteStudentQuery)) {

			
			deleteLearnStmt.setString(1, studentID);
			deleteLearnStmt.executeUpdate();

			
			deleteStudentStmt.setString(1, studentID);
			int rowsDeleted = deleteStudentStmt.executeUpdate();

			if (rowsDeleted > 0) {
				System.out.println("Xóa sinh viên thành công: " + studentID);
				return true;
			} else {
				System.out.println("Không tìm thấy sinh viên với ID: " + studentID);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Lỗi khi xóa sinh viên: " + e.getMessage());
			return false;
		}
	}

	
	private String generateEmail(String name) {
		String[] parts = name.split(" ");
		if (parts.length < 2) {
			return name.toLowerCase() + "@vku.udn.vn";
		}

		String baseEmail = parts[parts.length - 1].toLowerCase() + parts[0].toLowerCase().charAt(0);
		String email = baseEmail + "@vku.udn.vn";
		int counter = 1;

		
		while (isEmailExists(email)) {
			email = baseEmail + counter + "@vku.udn.vn";
			counter++;
		}

		return email;
	}

	
	private boolean isEmailExists(String email) {
		String query = "SELECT 1 FROM Student WHERE Email = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			return rs.next(); 
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	public boolean isStudentExists(int studentID) {
		String query = "SELECT 1 FROM Student WHERE StudentID = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setInt(1, studentID);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}