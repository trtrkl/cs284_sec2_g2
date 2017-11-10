import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class CourseForm extends JFrame  {
	private JLabel userName = new JLabel("   User : ");
	private JComboBox subJ = new JComboBox();
	private JTextField inputSubj = new JTextField(15);
	private JButton edit = new JButton("Edit");
	private JButton importList = new JButton("Import ClassList");
	private JButton addScore = new JButton("Add Score");

	public CourseForm(User user,ActionListener action) {
		userName = new JLabel("   User : " + user.getUserName());
		subJ = new JComboBox<>(user.getAllCourse().toArray());
		JPanel top = new JPanel();
		top.setBorder(new TitledBorder("Welcome User"));
		top.setLayout(new BorderLayout());
		top.add(userName, BorderLayout.WEST);
		add(top, BorderLayout.NORTH);
		JPanel center = new JPanel();
		center.setBorder(new TitledBorder("Subject"));
		center.add(subJ);
		add(center, BorderLayout.CENTER);
		
		JPanel bottom = new JPanel();
		bottom.setLayout(new  GridLayout(1, 3));
		bottom.add(edit);
		bottom.add(importList);
		bottom.add(addScore);
		
		add(bottom, BorderLayout.SOUTH);

		edit.addActionListener(action); 
		edit.setActionCommand("edit");
		
		importList.addActionListener(action);
		importList.setActionCommand("importList");
		
		addScore.addActionListener(action);
		addScore.setActionCommand("addScore");
		addWindowListener((WindowListener)action);
		pack();
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		setTitle("CouseForm");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public Course getSelectedCourse(){
		return (Course)subJ.getSelectedItem();
	}

	


}
