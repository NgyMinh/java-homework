package ui;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class OrderTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Tên khách hàng", "Sản phẩm", "Số lượng", "Tổng tiền"};
    private final List<Order> orders;

    public OrderTableModel() {
        this.orders = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return orders.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Order order = orders.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return order.getId();
            case 1:
                return order.getCustomerName();
            case 2:
                return order.getProduct();
            case 3:
                return order.getQuantity();
            case 4:
                return order.getTotal();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; // Các ô trong bảng không thể chỉnh sửa trực tiếp
    }

    public void addOrder(Order order) {
        orders.add(order);
        fireTableRowsInserted(orders.size() - 1, orders.size() - 1);
    }

    public void updateOrder(int rowIndex, Order order) {
        orders.set(rowIndex, order);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    public void removeOrder(int rowIndex) {
        orders.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Order getOrderAt(int rowIndex) {
        return orders.get(rowIndex);
    }
}