package gui;

import dao.LearnDAO;
import dao.StudentDAO;
import database.DatabaseConnection;
import models.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentGUI extends JFrame {
	private StudentDAO studentDAO;
	private JPanel mainPanel;
	private CardLayout cardLayout;
	private JTable classTable;
	private JTable studentTable;
	private JPanel studentListPanel, classListPanel;

	public StudentGUI() {
		this.studentDAO = new StudentDAO();
		initUI();
		showStudentList();
	}

	private void showAllClasses() {
		try {
			Connection connection = DatabaseConnection.getConnection();
			String query = "SELECT * FROM Class";
			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			String[] columnNames = { "ClassID", "Description", "NumberOfCredits" };
			DefaultTableModel classTableModel = new DefaultTableModel(columnNames, 0);
			while (rs.next()) {
				classTableModel.addRow(new Object[] { rs.getInt("ClassID"), rs.getString("Description"),
						rs.getInt("NumberOfCredits") });
			}

			JTable classTable = new JTable(classTableModel);
			JScrollPane scrollPane = new JScrollPane(classTable);

			JDialog classDialog = new JDialog(this, "Danh sách lớp học", true);
			classDialog.setSize(600, 400);
			classDialog.setLayout(new BorderLayout());
			classDialog.add(scrollPane, BorderLayout.CENTER);

			JButton btnViewStudents = new JButton("Xem sinh viên");
			classDialog.add(btnViewStudents, BorderLayout.SOUTH);

			btnViewStudents.addActionListener(e -> {
				int selectedRow = classTable.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(classDialog, "Vui lòng chọn một lớp học!");
					return;
				}

				int classID = (int) classTableModel.getValueAt(selectedRow, 0);
				showStudentsInSelectedClass(classID);
			});

			classDialog.setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void showStudentsInSelectedClass(int classID) {
		try {
			Connection connection = DatabaseConnection.getConnection();
			String query = "SELECT s.StudentID, s.Name, s.Age, s.Email, s.GPA " + "FROM Student s "
					+ "JOIN Learn l ON s.StudentID = l.StudentID " + "WHERE l.ClassID = ?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, classID);
			ResultSet rs = pstmt.executeQuery();

			String[] columnNames = { "StudentID", "Name", "Age", "Email", "GPA" };
			DefaultTableModel studentTableModel = new DefaultTableModel(columnNames, 0);
			while (rs.next()) {
				studentTableModel.addRow(new Object[] { rs.getString("StudentID"), rs.getString("Name"),
						rs.getInt("Age"), rs.getString("Email"), rs.getDouble("GPA") });
			}

			JTable studentTable = new JTable(studentTableModel);
			JScrollPane scrollPane = new JScrollPane(studentTable);

			JDialog studentDialog = new JDialog(this, "Danh sách sinh viên của lớp " + classID, true);
			studentDialog.setSize(600, 400);
			studentDialog.setLayout(new BorderLayout());
			studentDialog.add(scrollPane, BorderLayout.CENTER);

			studentDialog.setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void initUI() {
		setTitle("Quản lý sinh viên VKU");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);

		add(mainPanel);
	}

	private void showStudentList() {
		studentListPanel = new JPanel(new BorderLayout());

		String[] columnNames = { "StudentID", "Name", "Age", "Email", "GPA" };
		DefaultTableModel studentTableModel = new DefaultTableModel(columnNames, 0);
		studentTable = new JTable(studentTableModel);

		try {
			Connection connection = DatabaseConnection.getConnection();
			String query = "SELECT * FROM Student";
			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				studentTableModel.addRow(new Object[] { rs.getString("StudentID"), rs.getString("Name"),
						rs.getInt("Age"), rs.getString("Email"), rs.getDouble("GPA") });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JScrollPane scrollPane = new JScrollPane(studentTable);
		studentListPanel.add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JButton btnAddToClass = new JButton("Thêm vào lớp");
		JButton btnAddStudent = new JButton("Thêm sinh viên");
		JButton btnEditStudent = new JButton("Sửa");
		JButton btnDeleteStudent = new JButton("Xóa");
		JButton btnShowClasses = new JButton("Hiển thị danh sách lớp");

		buttonPanel.add(btnAddStudent);
		buttonPanel.add(btnEditStudent);
		buttonPanel.add(btnDeleteStudent);
		buttonPanel.add(btnShowClasses);
		buttonPanel.add(btnAddToClass);

		studentListPanel.add(buttonPanel, BorderLayout.SOUTH);

		btnShowClasses.addActionListener(e -> showClassList());
		btnAddStudent.addActionListener(e -> addStudent());
		btnEditStudent.addActionListener(e -> editStudent());
		btnDeleteStudent.addActionListener(e -> deleteStudent());
		btnAddToClass.addActionListener(e -> addMultipleStudentsToClass());

		mainPanel.add(studentListPanel, "studentList");
		cardLayout.show(mainPanel, "studentList");
	}

	private void addMultipleStudentsToClass() {

		int[] selectedRows = studentTable.getSelectedRows();

		if (selectedRows.length == 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một sinh viên để thêm vào lớp!");
			return;
		}

		JTextField classIdentifierField = new JTextField();
		JCheckBox useDescriptionBox = new JCheckBox("Sử dụng Description để tìm lớp");

		Object[] message = { "ClassID hoặc Description:", classIdentifierField, useDescriptionBox };

		int option = JOptionPane.showConfirmDialog(this, message, "Thêm sinh viên vào lớp",
				JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			String classIdentifier = classIdentifierField.getText();
			boolean isUsingDescription = useDescriptionBox.isSelected();

			boolean allSuccess = true;
			for (int row : selectedRows) {
				String studentID = (String) studentTable.getValueAt(row, 0);
				if (!studentDAO.addStudentToClass(studentID, classIdentifier, isUsingDescription)) {
					allSuccess = false;
					System.err.println("Thêm thất bại cho sinh viên với ID: " + studentID);
				}
			}

			if (allSuccess) {
				JOptionPane.showMessageDialog(this, "Thêm tất cả sinh viên vào lớp thành công!");
			} else {
				JOptionPane.showMessageDialog(this,
						"Một số sinh viên không thể được thêm vào lớp. Vui lòng kiểm tra lại.");
			}
		}
	}

	private void addStudentToClass() {

		int selectedRow = studentTable.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một sinh viên để thêm vào lớp!");
			return;
		}

		String studentID = (String) studentTable.getValueAt(selectedRow, 0);

		JTextField classIdentifierField = new JTextField();
		JCheckBox useDescriptionBox = new JCheckBox("Sử dụng Description để tìm lớp");

		Object[] message = { "ClassID hoặc Description:", classIdentifierField, useDescriptionBox };

		int option = JOptionPane.showConfirmDialog(this, message, "Thêm sinh viên vào lớp",
				JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			String classIdentifier = classIdentifierField.getText();
			boolean isUsingDescription = useDescriptionBox.isSelected();

			if (studentDAO.addStudentToClass(studentID, classIdentifier, isUsingDescription)) {
				JOptionPane.showMessageDialog(this, "Thêm sinh viên vào lớp thành công!");
			} else {
				JOptionPane.showMessageDialog(this, "Thêm sinh viên vào lớp thất bại. Vui lòng kiểm tra thông tin.");
			}
		}
	}

	private void viewStudentsInClass() {

		int selectedClassRow = classTable.getSelectedRow();
		if (selectedClassRow == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một lớp để xem danh sách sinh viên!");
			return;
		}

		String classID = (String) classTable.getValueAt(selectedClassRow, 0);

		List<Student> students = studentDAO.getStudentsByClassID(classID);
		if (students.isEmpty()) {
			System.out.println("Không có sinh viên nào trong lớp " + classID);
			return;
		}

		System.out.println("Danh sách sinh viên trong lớp " + classID + ":");
		for (Student student : students) {
			System.out.printf("StudentID: %s, Name: %s, Age: %d, GPA: %.2f, Email: %s%n", student.getStudentID(),
					student.getName(), student.getAge(), student.getGpa(), student.getEmail());
		}
	}

	private void showClassList() {
		classListPanel = new JPanel(new BorderLayout());

		String[] columnNames = { "ClassID", "Description", "NumberOfCredits" };
		DefaultTableModel classTableModel = new DefaultTableModel(columnNames, 0);
		classTable = new JTable(classTableModel);

		try {
			Connection connection = DatabaseConnection.getConnection();
			String query = "SELECT * FROM Class";
			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				classTableModel.addRow(new Object[] { rs.getInt("ClassID"), rs.getString("Description"),
						rs.getInt("NumberOfCredits") });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JScrollPane scrollPane = new JScrollPane(classTable);
		classListPanel.add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton btnViewStudents = new JButton("Xem sinh viên");
		JButton btnBack = new JButton("Quay lại");

		buttonPanel.add(btnViewStudents);
		buttonPanel.add(btnBack);
		classListPanel.add(buttonPanel, BorderLayout.SOUTH);

		btnViewStudents.addActionListener(e -> {
			int selectedRow = classTable.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn một lớp học!");
				return;
			}

			int classID = (int) classTableModel.getValueAt(selectedRow, 0);
			showStudentListByClass(classID);
		});

		btnBack.addActionListener(e -> cardLayout.show(mainPanel, "studentList"));

		mainPanel.add(classListPanel, "classList");
		cardLayout.show(mainPanel, "classList");
	}

	private void showStudentListByClass(int classID) {
		JPanel studentByClassPanel = new JPanel(new BorderLayout());

		String[] columnNames = { "StudentID", "Name", "Age", "Email", "GPA" };
		DefaultTableModel studentTableModel = new DefaultTableModel(columnNames, 0);
		studentTable = new JTable(studentTableModel);

		try {
			Connection connection = DatabaseConnection.getConnection();
			String query = "SELECT s.StudentID, s.Name, s.Age, s.Email, s.GPA " + "FROM Student s "
					+ "JOIN Learn l ON s.StudentID = l.StudentID " + "WHERE l.ClassID = ?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, classID);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				studentTableModel.addRow(new Object[] { rs.getString("StudentID"), rs.getString("Name"),
						rs.getInt("Age"), rs.getString("Email"), rs.getDouble("GPA") });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JScrollPane scrollPane = new JScrollPane(studentTable);
		studentByClassPanel.add(scrollPane, BorderLayout.CENTER);

		JButton btnBack = new JButton("Quay lại");
		studentByClassPanel.add(btnBack, BorderLayout.SOUTH);

		btnBack.addActionListener(e -> cardLayout.show(mainPanel, "classList"));

		mainPanel.add(studentByClassPanel, "studentByClass");
		cardLayout.show(mainPanel, "studentByClass");
	}

	private void addStudent() {
		JTextField nameField = new JTextField();
		JTextField ageField = new JTextField();
		JTextField gpaField = new JTextField();

		Object[] message = { "Tên:", nameField, "Tuổi:", ageField, "GPA (1.0 - 10.0):", gpaField };

		int option = JOptionPane.showConfirmDialog(this, message, "Thêm sinh viên", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			String name = nameField.getText();
			int age;
			double gpa;

			try {
				age = Integer.parseInt(ageField.getText());
				gpa = Double.parseDouble(gpaField.getText());

				if (gpa < 1.0 || gpa > 10.0) {
					JOptionPane.showMessageDialog(this, "GPA phải nằm trong khoảng từ 1.0 đến 10.0!");
					return;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Tuổi và GPA phải là số!");
				return;
			}

			Student student = new Student();
			student.setName(name);
			student.setAge(age);
			student.setGpa(gpa);

			if (studentDAO.addStudent(student)) {
				JOptionPane.showMessageDialog(this, "Thêm sinh viên thành công!");
				loadStudents();
			} else {
				JOptionPane.showMessageDialog(this, "Thêm sinh viên thất bại!");
			}
		}
	}

	private void loadStudents() {
		DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
		model.setRowCount(0);

		try {
			Connection connection = DatabaseConnection.getConnection();
			String query = "SELECT * FROM Student";
			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				model.addRow(new Object[] { rs.getString("StudentID"), rs.getString("Name"), rs.getInt("Age"),
						rs.getString("Email"), rs.getDouble("GPA") });
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách sinh viên!");
		}
	}

	private void editStudent() {

		int selectedRow = studentTable.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một sinh viên để chỉnh sửa!");
			return;
		}

		String studentID = (String) studentTable.getValueAt(selectedRow, 0);

		JTextField nameField = new JTextField();
		JTextField ageField = new JTextField();
		JTextField gpaField = new JTextField();

		Object[] message = { "Tên mới:", nameField, "Tuổi mới:", ageField, "GPA mới (1.0 - 10.0):", gpaField };

		int option = JOptionPane.showConfirmDialog(this, message, "Cập nhật thông tin sinh viên",
				JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			String name = nameField.getText();
			int age;
			double gpa;

			try {
				age = Integer.parseInt(ageField.getText());
				gpa = Double.parseDouble(gpaField.getText());

				if (gpa < 1.0 || gpa > 10.0) {
					JOptionPane.showMessageDialog(this, "GPA phải nằm trong khoảng từ 1.0 đến 10.0!");
					return;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Tuổi và GPA phải là số hợp lệ!");
				return;
			}

			String email = generateEmail(name);

			Student student = new Student(studentID, name, age, email, gpa);

			if (studentDAO.editStudent(student)) {
				JOptionPane.showMessageDialog(this, "Cập nhật thông tin sinh viên thành công!");
				loadStudents();
			} else {
				JOptionPane.showMessageDialog(this,
						"Cập nhật thông tin sinh viên thất bại. Vui lòng kiểm tra thông tin.");
			}
		}
	}

	private String generateEmail(String name) {

		String normalizedName = name.trim().toLowerCase().replaceAll("\\s+", ".");
		return normalizedName + "@vku.udn.vn";
	}

	private void deleteStudent() {
		int selectedRow = studentTable.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn sinh viên để xóa!");
			return;
		}

		DefaultTableModel tableModel = (DefaultTableModel) studentTable.getModel();
		String studentID = (String) tableModel.getValueAt(selectedRow, 0);

		int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sinh viên này?", "Xác nhận xóa",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			if (studentDAO.deleteStudent(studentID)) {
				JOptionPane.showMessageDialog(this, "Xóa thành công!");
				loadStudents();
			} else {
				JOptionPane.showMessageDialog(this, "Xóa thất bại!");
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			StudentGUI gui = new StudentGUI();
			gui.setVisible(true);
		});
	}
}