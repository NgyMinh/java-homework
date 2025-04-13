package util;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Connection connection = DBConnection.getConnection();
            if (connection != null) {
                System.out.println("Kết nối cơ sở dữ liệu thành công!");
            }
        } catch (Exception e) {
            System.out.println("Kết nối cơ sở dữ liệu thất bại!");
            e.printStackTrace();
        }
    }
}