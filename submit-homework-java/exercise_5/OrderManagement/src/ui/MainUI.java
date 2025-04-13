package ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.text.DecimalFormat;

public class MainUI extends JFrame {
    private JPanel mainPanel; // Panel chính để chứa các màn hình
    private CardLayout cardLayout; // CardLayout để chuyển đổi giữa các màn hình
    private JTable orderTable; // Bảng danh sách đơn hàng
    private DefaultTableModel orderTableModel; // Model cho bảng đơn hàng
    
 // Dữ liệu mẫu cho lịch sử đơn hàng
    private HashMap<String, List<String[]>> orderHistoryData;

    public MainUI() {
        // Cài đặt cơ bản cho JFrame
        setTitle("Hệ thống Quản lý Đơn hàng");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo thanh công cụ (toolbar)
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false); // Không cho phép kéo thanh công cụ

        // Tạo các nút trong thanh công cụ
        JButton btnOrders = new JButton("Quản lý đơn hàng");
        JButton btnCustomers = new JButton("Quản lý khách hàng");
        JButton btnTotal = new JButton("Tính tổng tiền");
        JButton btnHistory = new JButton("Lịch sử đơn hàng"); // Nút mới

        // Thêm nút vào thanh công cụ
        toolBar.add(btnOrders);
        toolBar.addSeparator(); // Thêm khoảng cách giữa các nút
        toolBar.add(btnCustomers);
        toolBar.addSeparator();
        toolBar.add(btnTotal);
        toolBar.addSeparator();
        toolBar.add(btnHistory); // Thêm nút "Lịch sử đơn hàng"

        // Tạo CardLayout cho mainPanel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Tạo các màn hình
        JPanel orderPanel = createOrderPanel(); // Màn hình Quản lý đơn hàng
        JPanel customerPanel = createCustomerPanel(); // Màn hình Quản lý khách hàng
        JPanel welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.add(new JLabel("Chào mừng bạn đến với Hệ thống Quản lý Đơn hàng", JLabel.CENTER), BorderLayout.CENTER);

        // Thêm các màn hình vào mainPanel
        mainPanel.add(welcomePanel, "welcome");
        mainPanel.add(orderPanel, "orders");
        mainPanel.add(customerPanel, "customers");

        // Thêm sự kiện cho các nút
        btnOrders.addActionListener(e -> cardLayout.show(mainPanel, "orders")); // Hiển thị Quản lý đơn hàng
        btnCustomers.addActionListener(e -> cardLayout.show(mainPanel, "customers")); // Hiển thị Quản lý khách hàng
        btnTotal.addActionListener(e -> handleCalculateTotal()); // Tính tổng tiền
        btnHistory.addActionListener(e -> showOrderHistory()); // Hiển thị lịch sử đơn hàng

        // Thêm các thành phần vào JFrame
        setLayout(new BorderLayout());
        add(toolBar, BorderLayout.NORTH); // Thanh công cụ ở phía trên
        add(mainPanel, BorderLayout.CENTER); // Màn hình chính ở giữa
        
        initializeOrderHistoryData();

        // Thêm thông tin chân trang
        JLabel lblFooter = new JLabel("© 2025 - Hệ thống quản lý đơn hàng", JLabel.CENTER);
        lblFooter.setFont(new Font("Arial", Font.ITALIC, 12));
        lblFooter.setForeground(Color.GRAY);
        add(lblFooter, BorderLayout.SOUTH);
    }
    
    private void initializeOrderHistoryData() {
        orderHistoryData = new HashMap<>(); // Khởi tạo HashMap
        orderHistoryData.put("Nguyễn Văn A", Arrays.asList(
                new String[]{"1", "Laptop", "1", "20000000"},
                new String[]{"2", "Chuột máy tính", "2", "500000"}
        ));
        orderHistoryData.put("Trần Thị B", Arrays.asList(
                new String[]{"3", "Điện thoại", "1", "15000000"},
                new String[]{"4", "Ốp lưng", "3", "300000"}
        ));
    }

    private void showOrderHistory() {
        // Kiểm tra xem orderHistoryData đã được khởi tạo hay chưa
        if (orderHistoryData == null) {
            JOptionPane.showMessageDialog(this, "Dữ liệu lịch sử đơn hàng chưa được khởi tạo!");
            return;
        }

        // Hỏi tên khách hàng
        String customerName = JOptionPane.showInputDialog(this, "Nhập tên khách hàng cần xem lịch sử:");

        if (customerName == null || customerName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên khách hàng không được để trống!");
            return;
        }

        // Truy xuất dữ liệu lịch sử đơn hàng
        List<String[]> history = orderHistoryData.get(customerName);
        if (history == null || history.isEmpty()) { // Kiểm tra null hoặc rỗng
            JOptionPane.showMessageDialog(this, "Không tìm thấy lịch sử đơn hàng cho khách hàng: " + customerName);
            return;
        }

        // Hiển thị lịch sử đơn hàng trong bảng
        String[] columnNames = {"ID", "Sản phẩm", "Số lượng", "Tổng tiền"};
        DefaultTableModel historyTableModel = new DefaultTableModel(columnNames, 0);
        for (String[] row : history) {
            historyTableModel.addRow(row);
        }

        JTable historyTable = new JTable(historyTableModel);
        JScrollPane scrollPane = new JScrollPane(historyTable);

        // Hiển thị trong một dialog
        JOptionPane.showMessageDialog(this, scrollPane, "Lịch sử đơn hàng của " + customerName, JOptionPane.INFORMATION_MESSAGE);
    }

    // Tạo giao diện Quản lý đơn hàng với các chức năng Thêm, Sửa, Xóa
    private JPanel createOrderPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Tiêu đề
        JLabel lblTitle = new JLabel("Quản lý Đơn hàng", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(Color.BLUE);
        panel.add(lblTitle, BorderLayout.NORTH);

        // Bảng danh sách đơn hàng
        String[] columnNames = {"ID", "Tên khách hàng", "Sản phẩm", "Số lượng", "Tổng tiền"};
        String[][] data = {
            {"1", "Nguyễn Văn A", "Laptop", "1", "20000000"},
            {"2", "Trần Thị B", "Điện thoại", "2", "15000000"}
        };

        orderTableModel = new DefaultTableModel(data, columnNames);
        orderTable = new JTable(orderTableModel);
        JScrollPane scrollPane = new JScrollPane(orderTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Nút chức năng
        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Thêm");
        JButton btnEdit = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);

        // Thêm sự kiện cho các nút
        btnAdd.addActionListener(e -> handleAddOrder());
        btnEdit.addActionListener(e -> handleEditOrder());
        btnDelete.addActionListener(e -> handleDeleteOrder());

        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    // Chức năng Thêm đơn hàng
    private void handleAddOrder() {
        JTextField customerNameField = new JTextField();
        JTextField productField = new JTextField();
        JTextField quantityField = new JTextField();
        JTextField totalField = new JTextField();

        Object[] message = {
            "Tên khách hàng:", customerNameField,
            "Sản phẩm:", productField,
            "Số lượng:", quantityField,
            "Tổng tiền:", totalField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Thêm đơn hàng", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String customerName = customerNameField.getText();
            String product = productField.getText();
            String quantity = quantityField.getText();
            String total = totalField.getText();
            orderTableModel.addRow(new Object[]{orderTableModel.getRowCount() + 1, customerName, product, quantity, total});
        }
    }

    // Chức năng Sửa đơn hàng
    private void handleEditOrder() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một đơn hàng để sửa!");
            return;
        }

        String customerName = (String) orderTableModel.getValueAt(selectedRow, 1);
        String product = (String) orderTableModel.getValueAt(selectedRow, 2);
        String quantity = (String) orderTableModel.getValueAt(selectedRow, 3);
        String total = (String) orderTableModel.getValueAt(selectedRow, 4);

        JTextField customerNameField = new JTextField(customerName);
        JTextField productField = new JTextField(product);
        JTextField quantityField = new JTextField(quantity);
        JTextField totalField = new JTextField(total);

        Object[] message = {
            "Tên khách hàng:", customerNameField,
            "Sản phẩm:", productField,
            "Số lượng:", quantityField,
            "Tổng tiền:", totalField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Sửa đơn hàng", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            orderTableModel.setValueAt(customerNameField.getText(), selectedRow, 1);
            orderTableModel.setValueAt(productField.getText(), selectedRow, 2);
            orderTableModel.setValueAt(quantityField.getText(), selectedRow, 3);
            orderTableModel.setValueAt(totalField.getText(), selectedRow, 4);
        }
    }

    // Chức năng Xóa đơn hàng
    private void handleDeleteOrder() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một đơn hàng để xóa!");
            return;
        }

        int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa đơn hàng này?", "Xóa đơn hàng", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            orderTableModel.removeRow(selectedRow);
        }
    }

    // Chức năng Tính tổng tiền
    private void handleCalculateTotal() {
        double totalAmount = 0;
        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        for (int i = 0; i < orderTableModel.getRowCount(); i++) {
            String totalString = (String) orderTableModel.getValueAt(i, 4); // Cột "Tổng tiền"
            try {
                // Chuyển đổi giá trị thành số thực
                totalAmount += Double.parseDouble(totalString.replace(",", ""));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ ở dòng " + (i + 1));
                return;
            }
        }

        // Hiển thị tổng tiền
        String formattedTotal = decimalFormat.format(totalAmount) + " VND";
        JOptionPane.showMessageDialog(this, "Tổng số tiền của tất cả đơn hàng là: " + formattedTotal);
    }

    // Tạo giao diện Quản lý khách hàng (không thay đổi)
    private JPanel createCustomerPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Tiêu đề
        JLabel lblTitle = new JLabel("Quản lý Khách hàng", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(Color.BLUE);
        panel.add(lblTitle, BorderLayout.NORTH);

        // Bảng danh sách khách hàng
        String[] columnNames = {"ID", "Tên", "Email", "Số điện thoại"};
        String[][] data = {
            {"1", "Nguyễn Văn A", "a@gmail.com", "0123456789"},
            {"2", "Trần Thị B", "b@gmail.com", "0987654321"}
        };

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

 // Các phương thức khác (createOrderPanel, handleAddOrder, ...)
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainUI mainUI = new MainUI();
            mainUI.setVisible(true);
        });
    }
}