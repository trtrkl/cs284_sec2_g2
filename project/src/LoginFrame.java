
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {
	JLabel usernameLabel = new JLabel("User name:");
	JLabel passwordLabel = new JLabel("Password:");
	JTextField userField = new JTextField(10);
	JPasswordField passwordField = new JPasswordField(10);
	JPanel mainPane = new JPanel();
	JPanel midPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
	JLabel logo;
	JButton loginBtn;
	JButton createNewAccount;
	JPanel btnPane = new JPanel(new FlowLayout());

	public LoginFrame(ActionListener action) {

		try {
			logo = new JLabel(new ImageIcon(
					ImageIO.read(getClass().getResource("thammasat.png")).getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
			loginBtn = new JButton(
					new ImageIcon(ImageIO.read(getClass().getResource("loginBtn.png")).getScaledInstance(70, 30, Image.SCALE_DEFAULT)));
			createNewAccount = new JButton(
					new ImageIcon(ImageIO.read(getClass().getResource("nABtn.png")).getScaledInstance(90, 30, Image.SCALE_DEFAULT)));
			btnPane = new JPanel(new FlowLayout());

			LayoutManager boxL = new BoxLayout(mainPane, BoxLayout.Y_AXIS);
			mainPane.setLayout(boxL);
			mainPane.setBackground(Color.WHITE);

			logo.setAlignmentX(Component.CENTER_ALIGNMENT);
			midPane.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnPane.setAlignmentX(Component.CENTER_ALIGNMENT);
			// createNewAccount.setAlignmentX(Component.CENTER_ALIGNMENT);

			loginBtn.setBorderPainted(false);
			loginBtn.setContentAreaFilled(false);
			loginBtn.setFocusPainted(false);
			loginBtn.setOpaque(false);
			loginBtn.setPreferredSize(new Dimension(70, 30));
			loginBtn.setPressedIcon(new ImageIcon(
					ImageIO.read(getClass().getResource("pressedBtn.png")).getScaledInstance(70, 30, Image.SCALE_DEFAULT)));
			loginBtn.addActionListener(action);
			loginBtn.setActionCommand("login");

			createNewAccount.setBorderPainted(false);
			createNewAccount.setContentAreaFilled(false);
			createNewAccount.setFocusPainted(false);
			createNewAccount.setOpaque(false);
			createNewAccount.setPreferredSize(new Dimension(90, 30));
			createNewAccount.setPressedIcon(new ImageIcon(
					ImageIO.read(getClass().getResource("pressedNABtn.png")).getScaledInstance(90, 30, Image.SCALE_DEFAULT)));
			createNewAccount.addActionListener(action);
			createNewAccount.setActionCommand("createNewAccount");

			btnPane.setMaximumSize(new Dimension(200, 50));
			btnPane.setBackground(Color.white);
			// btnPane.setBorder(BorderFactory.createLineBorder(new Color(255, 227, 214)));
			btnPane.add(loginBtn);
			btnPane.add(createNewAccount);

			userField.setBorder(BorderFactory.createLineBorder(new Color(255, 227, 214)));
			passwordField.setBorder(BorderFactory.createLineBorder(new Color(255, 227, 214)));

			midPane.setMaximumSize(new Dimension(200, 60));
			midPane.setBackground(Color.white);
			midPane.setBorder(BorderFactory.createLineBorder(new Color(255, 227, 214)));
			midPane.add(usernameLabel);
			midPane.add(userField);
			midPane.add(passwordLabel);
			midPane.add(passwordField);

			mainPane.add(Box.createRigidArea(new Dimension(0, 30)));
			mainPane.add(logo);
			mainPane.add(Box.createRigidArea(new Dimension(0, 30)));
			mainPane.add(midPane, BorderLayout.CENTER);
			mainPane.add(Box.createRigidArea(new Dimension(0, 5)));
			// mainPane.add(loginBtn);
			// mainPane.add(createNewAccount);
			mainPane.add(btnPane);

			add(mainPane);
			setTitle("Login");
			setSize(500, 400);
			setResizable(false);
			setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2,
					(Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUserName() {
		return userField.getText();
	}

	public String getPassword() {
		return passwordField.getText();
	}

}
