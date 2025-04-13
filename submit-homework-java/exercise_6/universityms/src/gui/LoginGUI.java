package gui;

import dao.UserDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
	private UserDAO userDAO;

	public LoginGUI() {
		userDAO = new UserDAO();
		initUI();
	}

	private void initUI() {
		setTitle("Đăng nhập");
		setSize(400, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		JLabel emailLabel = new JLabel("Email:");
		JLabel passwordLabel = new JLabel("Password:");
		JTextField emailField = new JTextField(20);
		JPasswordField passwordField = new JPasswordField(20);
		JButton loginButton = new JButton("Login");

		panel.add(emailLabel);
		panel.add(emailField);
		panel.add(passwordLabel);
		panel.add(passwordField);
		panel.add(loginButton);

		add(panel);

		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = emailField.getText();
				String password = new String(passwordField.getPassword());

				if (userDAO.login(email, password)) {
					JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
					new StudentGUI().setVisible(true); 
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Sai email hoặc mật khẩu!");
				}
			}
		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new LoginGUI().setVisible(true);
		});
	}
}