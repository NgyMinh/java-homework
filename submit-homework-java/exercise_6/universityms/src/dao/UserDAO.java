package dao;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	private Connection connection;

	public UserDAO() {
		connection = DatabaseConnection.getConnection();
	}

	public boolean login(String email, String password) {
		String query = "SELECT * FROM Users WHERE Email = ? AND Password = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			return rs.next(); 
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean register(String email, String password) {
		String query = "INSERT INTO Users (Email, Password) VALUES (?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}