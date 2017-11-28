
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CourseFrame extends JFrame {
	JPanel leftPane = new JPanel();
	JPanel rightPane = new JPanel();
	JPanel cbbPane = new JPanel(new FlowLayout());
	String userName = "User's name";
	JLabel welcome = new JLabel("Welcome user : " + userName);
	JTextArea noticeArea = new JTextArea();

	JLabel courseLabel = new JLabel("Your course list");
	JComboBox courseCombo;
	JLabel logo;
	JButton ctrBtn;
	JButton importBtn;
	JButton scBtn;
	JButton calBtn;
	JButton setEmailBtn;
	JButton addCourse;

	public CourseFrame(User user, ArrayList<Course> courseList, ActionListener action) {

		try {
			logo = new JLabel(new ImageIcon(
					ImageIO.read(getClass().getResource("thammasat.png")).getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
			ctrBtn = new JButton(
					new ImageIcon(ImageIO.read(getClass().getResource("ctrBtn.png")).getScaledInstance(160, 30, Image.SCALE_DEFAULT)));
			importBtn = new JButton(
					new ImageIcon(ImageIO.read(getClass().getResource("impBtn.png")).getScaledInstance(160, 30, Image.SCALE_DEFAULT)));
			scBtn = new JButton(
					new ImageIcon(ImageIO.read(getClass().getResource("scBtn.png")).getScaledInstance(230, 30, Image.SCALE_DEFAULT)));
			calBtn = new JButton(
					new ImageIcon(ImageIO.read(getClass().getResource("cclBtn.png")).getScaledInstance(230, 30, Image.SCALE_DEFAULT)));
			setEmailBtn = new JButton(new ImageIcon(
					ImageIO.read(getClass().getResource("setEmailBtn.png")).getScaledInstance(230, 30, Image.SCALE_DEFAULT)));
			addCourse = new JButton(
					new ImageIcon(ImageIO.read(getClass().getResource("addCBtn.png")).getScaledInstance(25, 25, Image.SCALE_DEFAULT)));

			welcome = new JLabel("Welcome user : " + user.getUserName());
			setLayout(new BorderLayout());
			courseCombo = new JComboBox<>(courseList.toArray());
			// left panel --------------------- //
			LayoutManager boxL = new BoxLayout(leftPane, BoxLayout.Y_AXIS);
			leftPane.setLayout(boxL);
			leftPane.setBackground(Color.WHITE);
			leftPane.setPreferredSize(new Dimension(450, 600));
			leftPane.add(Box.createRigidArea(new Dimension(0, 50)));
			leftPane.add(logo);
			leftPane.add(Box.createRigidArea(new Dimension(0, 20)));
			leftPane.add(welcome);
			leftPane.add(Box.createRigidArea(new Dimension(0, 50)));
			leftPane.add(noticeArea);

			welcome.setFont(new Font("Consolas", Font.BOLD, 20));

			noticeArea.setMaximumSize(new Dimension(350, 150));
			noticeArea.setBackground(null);
			noticeArea.setText("Please not use Thammasart University WIFI because it's unstable.");
			noticeArea.setForeground(Color.red);
			noticeArea.setWrapStyleWord(true);
			noticeArea.setLineWrap(true);
			noticeArea.setEditable(false);
			noticeArea.setBorder(BorderFactory.createTitledBorder(" Notice "));

			logo.setAlignmentX(Component.CENTER_ALIGNMENT);
			welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
			noticeArea.setAlignmentX(Component.CENTER_ALIGNMENT);

			// right panel ------------------- //
			LayoutManager boxR = new BoxLayout(rightPane, BoxLayout.Y_AXIS);
			rightPane.setLayout(boxR);
			rightPane.setBackground(new Color(255, 227, 214));

			rightPane.setMinimumSize(new Dimension(400, 600));
			rightPane.add(Box.createRigidArea(new Dimension(0, 80)));
			rightPane.add(cbbPane);
			rightPane.add(courseCombo);
			rightPane.add(addCourse);
			rightPane.add(Box.createRigidArea(new Dimension(0, 33)));
			rightPane.add(ctrBtn);
			rightPane.add(Box.createRigidArea(new Dimension(0, 33)));
			rightPane.add(importBtn);
			rightPane.add(Box.createRigidArea(new Dimension(0, 33)));
			rightPane.add(scBtn);
			rightPane.add(Box.createRigidArea(new Dimension(0, 33)));
			rightPane.add(calBtn);
			rightPane.add(Box.createRigidArea(new Dimension(0, 33)));
			rightPane.add(setEmailBtn);

			courseCombo.setBackground(Color.WHITE);
			courseCombo.setMaximumSize(new Dimension(100, 30));
			cbbPane.setMaximumSize(new Dimension(300, 40));
			cbbPane.setBackground(new Color(255, 227, 214));
			cbbPane.add(courseCombo);
			cbbPane.add(addCourse);

			addCourse.addActionListener(action);
			addCourse.setActionCommand("addCourse");

			cbbPane.setAlignmentX(Component.CENTER_ALIGNMENT);
			courseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			courseCombo.setAlignmentX(Component.CENTER_ALIGNMENT);
			ctrBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
			importBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
			scBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
			calBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
			setEmailBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

			ctrBtn.setBorderPainted(false);
			ctrBtn.setContentAreaFilled(false);
			ctrBtn.setFocusPainted(false);
			ctrBtn.setOpaque(false);
			ctrBtn.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("pressedCtrBtn.png"))
					.getScaledInstance(160, 30, Image.SCALE_DEFAULT)));
			ctrBtn.addActionListener(action);
			ctrBtn.setActionCommand("edit");

			importBtn.setBorderPainted(false);
			importBtn.setContentAreaFilled(false);
			importBtn.setFocusPainted(false);
			importBtn.setOpaque(false);
			importBtn.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("pressedImpBtn.png"))
					.getScaledInstance(160, 30, Image.SCALE_DEFAULT)));
			importBtn.addActionListener(action);
			importBtn.setActionCommand("importList");

			scBtn.setBorderPainted(false);
			scBtn.setContentAreaFilled(false);
			scBtn.setFocusPainted(false);
			scBtn.setOpaque(false);
			scBtn.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("pressedScBtn.png"))
					.getScaledInstance(230, 30, Image.SCALE_DEFAULT)));
			scBtn.addActionListener(action);
			scBtn.setActionCommand("addScore");

			addCourse.setBorderPainted(false);
			addCourse.setContentAreaFilled(false);
			addCourse.setFocusPainted(false);
			addCourse.setOpaque(false);
			addCourse.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("pressedAddCBtn.png"))
					.getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
			addCourse.setPreferredSize(new Dimension(40, 40));

			calBtn.setBorderPainted(false);
			calBtn.setContentAreaFilled(false);
			calBtn.setFocusPainted(false);
			calBtn.setOpaque(false);
			calBtn.addActionListener(action);
			calBtn.setActionCommand("calculateGrade");

			setEmailBtn.setBorderPainted(false);
			setEmailBtn.setContentAreaFilled(false);
			setEmailBtn.setFocusPainted(false);
			setEmailBtn.setOpaque(false);
			setEmailBtn.addActionListener(action);
			setEmailBtn.setActionCommand("setEmail");

			add(leftPane, BorderLayout.WEST);
			add(rightPane, BorderLayout.CENTER);
			setTitle("Main page");
			setSize(800, 600);
			setResizable(false);
			setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2,
					(Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
			addWindowListener((WindowListener) action);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Course getSelectedCourse() {
		return (Course) courseCombo.getSelectedItem();
	}

	public void addSelectableCourse(Course course) {
		courseCombo.addItem(course);
	}

}
