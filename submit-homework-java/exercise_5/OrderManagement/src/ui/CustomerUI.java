package ui;

import dao.CustomerDAO;
import model.Customer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CustomerUI extends JFrame {
    public CustomerUI() {
        setTitle("Danh sách khách hàng");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Lấy dữ liệu khách hàng từ DAO
        CustomerDAO customerDAO = new CustomerDAO();
        List<Customer> customers = customerDAO.getAllCustomers();

        // Chuyển dữ liệu thành mảng để hiển thị trong JTable
        String[] columnNames = {"ID", "Tên", "Email", "Số điện thoại"};
        String[][] data = new String[customers.size()][4];
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            data[i][0] = String.valueOf(customer.getId());
            data[i][1] = customer.getName();
            data[i][2] = customer.getEmail();
            data[i][3] = customer.getPhone();
        }

        // Tạo JTable và JScrollPane
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CustomerUI customerUI = new CustomerUI();
            customerUI.setVisible(true);
        });
    }
}