package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LearnDAO {
	private Connection connection;

	public LearnDAO(Connection connection) {
		this.connection = connection;
	}

	
	public void getStudentsInClass(int classID) {
		String query = "SELECT s.StudentID, s.Name, s.Age, s.Email, s.GPA " + "FROM Student s "
				+ "JOIN Learn l ON s.StudentID = l.StudentID " + "WHERE l.ClassID = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setInt(1, classID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("ID: " + rs.getString("StudentID") + ", Name: " + rs.getString("Name") + ", Age: "
						+ rs.getInt("Age") + ", Email: " + rs.getString("Email") + ", GPA: " + rs.getDouble("GPA"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public boolean addStudentToClass(String studentID, int classID) {
		String query = "INSERT INTO Learn (StudentID, ClassID) VALUES (?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, studentID);
			pstmt.setInt(2, classID);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean removeStudentFromClass(String studentID, int classID) {
		String query = "DELETE FROM Learn WHERE StudentID = ? AND ClassID = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, studentID);
			pstmt.setInt(2, classID);
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0; 
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
