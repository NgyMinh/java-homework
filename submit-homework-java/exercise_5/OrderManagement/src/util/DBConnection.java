package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Cấu hình thông tin kết nối
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Order_Management;encrypt=true;trustServerCertificate=true"; // Thêm trustServerCertificate=true
    private static final String USER = "sa"; // Tên đăng nhập SQL Server
    private static final String PASSWORD = "123456789"; // Mật khẩu SQL Server

    // Phương thức lấy kết nối
    public static Connection getConnection() throws SQLException {
        try {
            // Nạp driver SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver không tìm thấy!");
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}