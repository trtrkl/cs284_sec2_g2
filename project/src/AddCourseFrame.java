
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AddCourseFrame extends JFrame {
	JPanel mainPane = new JPanel();
	JPanel pane = new JPanel(new BorderLayout());
	JPanel leftPane = new JPanel(new FlowLayout());
	JPanel rightPane = new JPanel(new FlowLayout());
	JRadioButton addNewCourse = new JRadioButton("New course");
	JRadioButton addExistingCourse = new JRadioButton("Existing course");
	JTextField newCourseText = new JTextField(10);
	JComboBox selectCourse ;
	

	public AddCourseFrame(ArrayList<String> allCourse,ActionListener action){
		try {
			JButton addBtn = new JButton(
					new ImageIcon(ImageIO.read(getClass().getResource("addBtn.png")).getScaledInstance(70, 30, Image.SCALE_DEFAULT)));
			selectCourse = new JComboBox(allCourse.toArray());
			mainPane.setBackground(Color.white);
			pane.setBackground(Color.white);
			leftPane.setBackground(Color.white);
			rightPane.setBackground(Color.white);
			addNewCourse.setBackground(Color.white);
			addExistingCourse.setBackground(Color.white);
			selectCourse.setBackground(Color.white);
			addNewCourse.setAlignmentX(Component.CENTER_ALIGNMENT);
			newCourseText.setAlignmentX(Component.CENTER_ALIGNMENT);
			addExistingCourse.setAlignmentX(Component.CENTER_ALIGNMENT);
			selectCourse.setAlignmentX(Component.CENTER_ALIGNMENT);
			newCourseText.setMaximumSize(new Dimension(100, 20));
			selectCourse.setMaximumSize(new Dimension(100, 20));

			selectCourse.setEnabled(false);

			addBtn.setBorderPainted(false);
			addBtn.setContentAreaFilled(false);
			addBtn.setFocusPainted(false);
			addBtn.setOpaque(false);
			addBtn.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("pressedAddBtn.png")).getScaledInstance(70, 30, Image.SCALE_DEFAULT)));
			addBtn.addActionListener(action);
			addBtn.setActionCommand("update");

			LayoutManager boxL = new BoxLayout(leftPane, BoxLayout.Y_AXIS);
			leftPane.add(Box.createRigidArea(new Dimension(0, 20)));
			leftPane.setLayout(boxL);
			leftPane.add(addNewCourse);
			leftPane.add(Box.createRigidArea(new Dimension(0, 5)));
			leftPane.add(newCourseText);

			LayoutManager boxR = new BoxLayout(rightPane, BoxLayout.Y_AXIS);
			rightPane.add(Box.createRigidArea(new Dimension(0, 20)));
			rightPane.setLayout(boxR);
			rightPane.add(addExistingCourse);
			rightPane.add(Box.createRigidArea(new Dimension(0, 5)));
			rightPane.add(selectCourse);

			pane.add(leftPane, BorderLayout.CENTER);
			pane.add(rightPane, BorderLayout.EAST);
			mainPane.add(pane);
			mainPane.add(addBtn);

			addNewCourse.setSelected(true);
			addNewCourse.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (addNewCourse.isSelected()) {
						addExistingCourse.setSelected(false);
						selectCourse.setEnabled(false);
						newCourseText.setEnabled(true);
					} else {
						addExistingCourse.setSelected(true);
						selectCourse.setEnabled(true);
						addNewCourse.setSelected(false);
						newCourseText.setEnabled(false);
					}
				}
			});

			addExistingCourse.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (addExistingCourse.isSelected()) {
						addNewCourse.setSelected(false);
						newCourseText.setEnabled(false);
						selectCourse.setEnabled(true);
					} else {
						addNewCourse.setSelected(true);
						newCourseText.setEnabled(true);
						addExistingCourse.setSelected(false);
						selectCourse.setEnabled(false);
					}
				}
			});
			
			add(mainPane);
			setSize(300, 200);
			setResizable(false);
			setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2,
					(Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean addNewCourseIsSelected() {
		return addNewCourse.isSelected();
	}
	
	public String getNewCourseName() {
		return newCourseText.getText();
	}
	
	public String getSelectedCourseName() {
		return (String)selectCourse.getSelectedItem();
	}
	
}
