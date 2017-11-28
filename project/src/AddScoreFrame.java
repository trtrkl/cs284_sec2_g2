
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class AddScoreFrame extends JFrame {
	JPanel leftPane = new JPanel();
	JPanel rightPane = new JPanel();
	JPanel tablePane = new JPanel();
	JLabel scoreLabel = new JLabel("Inputting score");
	JComboBox selectExam;
	JTable table;
	JButton saveBtn;
	JButton cclBtn;
	JButton ccBtn ;

	String[] headers = { "No.", "Id", "Score / Percentage%" };

	public AddScoreFrame(final ArrayList<Exam> examDetailList, final ArrayList<Student> studentList,ActionListener action){
		
		try {
			saveBtn = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("saveBtn.png"))
					.getScaledInstance(80, 30, Image.SCALE_DEFAULT)));
			cclBtn = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("cclScoreBtn.png")).getScaledInstance(200,
					40, Image.SCALE_DEFAULT)));
			ccBtn = new JButton(new ImageIcon(
					ImageIO.read(getClass().getResource("ccBtn.png")).getScaledInstance(80, 30, Image.SCALE_DEFAULT)));
			setLayout(new BorderLayout());
			String[] headers = { "Student", "Score" };
			String[][] data = new String[studentList.size()][headers.length];
			selectExam = new JComboBox<>(examDetailList.toArray());
			table = new JTable(data, headers) {
				@Override
				public boolean isCellEditable(int row, int col) {
					switch (col) {
					case 0:
						return false;
					default:
						return true;
					}
				}
			};
			table.getTableHeader().setReorderingAllowed(false);
			if (!studentList.isEmpty() && !examDetailList.isEmpty()) {
				for (int i = 0; i < table.getRowCount(); i++) {
					table.setValueAt(studentList.get(i).getId(), i, 0);
					Exam anExam = studentList.get(i).getExamByName(getSelectedExam().getName());
					if (anExam != null) {
						table.setValueAt(anExam.getScore() + "", i, 1);
					}

				}
			}
			selectExam.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					for (int i = 0; i < table.getRowCount(); i++) {
						table.setValueAt(studentList.get(i).getId(), i, 0);
						Exam anExam = studentList.get(i).getExamByName(getSelectedExam().getName());
						if (anExam != null) {
							table.setValueAt(anExam.getScore() + "", i, 1);
						}

					}

				}
			});
			LayoutManager boxL = new BoxLayout(leftPane, BoxLayout.Y_AXIS);
			leftPane.setLayout(boxL);
			leftPane.setBackground(new Color(255, 227, 214));
			leftPane.setPreferredSize(new Dimension(350, 600));
			leftPane.add(Box.createRigidArea(new Dimension(0, 50)));
			leftPane.add(scoreLabel);
			leftPane.add(selectExam);
			leftPane.add(Box.createRigidArea(new Dimension(0, 20)));
			leftPane.add(saveBtn);
			leftPane.add(Box.createRigidArea(new Dimension(0, 100)));
			//leftPane.add(calculateNetScore);
			leftPane.add(cclBtn);
			leftPane.add(Box.createRigidArea(new Dimension(0, 110)));
			leftPane.add(ccBtn);
			selectExam.setMaximumSize(new Dimension(300, 30));
			//selectExam.setPreferredSize(new Dimension(250, 30));
			selectExam.setBackground(Color.WHITE);
			saveBtn.setBorderPainted(false);
			saveBtn.setContentAreaFilled(false);
			saveBtn.setFocusPainted(false);
			saveBtn.setOpaque(false);
			saveBtn.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("pressedSaveBtn.png"))
					.getScaledInstance(80, 30, Image.SCALE_DEFAULT)));
			saveBtn.addActionListener(action);
			saveBtn.setActionCommand("save");
			cclBtn.setBorderPainted(false);
			cclBtn.setContentAreaFilled(false);
			cclBtn.setFocusPainted(false);
			cclBtn.setOpaque(false);
			cclBtn.setPressedIcon(new ImageIcon(
					ImageIO.read(getClass().getResource("pressedCclScoreBtn.png")).getScaledInstance(200, 40, Image.SCALE_DEFAULT)));
			cclBtn.addActionListener(action);
			cclBtn.setActionCommand("calculate net score");
			ccBtn.setBorderPainted(false);
			ccBtn.setContentAreaFilled(false);
			ccBtn.setFocusPainted(false);
			ccBtn.setOpaque(false);
			ccBtn.setPressedIcon(new ImageIcon(
					ImageIO.read(getClass().getResource("pressedCcBtn.png")).getScaledInstance(80, 30, Image.SCALE_DEFAULT)));
			ccBtn.addActionListener(action);
			ccBtn.setActionCommand("cancel");
			scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			selectExam.setAlignmentX(Component.CENTER_ALIGNMENT);
			saveBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
			cclBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
			ccBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
			// right panel -------------------------- //
			LayoutManager boxR = new BoxLayout(rightPane, BoxLayout.Y_AXIS);
			rightPane.setLayout(boxR);
			rightPane.setBackground(Color.white);
			JScrollPane scrPane = new JScrollPane(table);
			scrPane.setMaximumSize(new Dimension(400, 500));
			table.setAlignmentX(Component.CENTER_ALIGNMENT);
			rightPane.add(Box.createRigidArea(new Dimension(0, 25)));
			rightPane.add(scrPane);
			add(leftPane, BorderLayout.WEST);
			add(rightPane, BorderLayout.CENTER);
			setTitle("Add / Manage students' score");
			setSize(800, 600);
			setResizable(false);
			setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2,
					(Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public TableModel getTableModel() {
		return table.getModel();
	}

	public Exam getSelectedExam() {
		return (Exam) selectExam.getSelectedItem();
	}

	public static boolean isPositive(TableModel model) {
		if (isDigit(model) == false) {
			return false;
		}

		for (int i = 0; i < model.getRowCount(); i++) {
			double num = Double.parseDouble((String) model.getValueAt(i, 1));

			if (num < 0) {
				JOptionPane.showMessageDialog(null, "Score must be positive");
				return false;
			}
		}
		return true;
	}

	public static boolean isInThreshold(TableModel model, double limit) {

		if (isDigit(model) == false) {
			return false;
		}

		for (int i = 0; i < model.getRowCount(); i++) {
			double num = Double.parseDouble((String) model.getValueAt(i, 1));

			if (num > limit) {
				JOptionPane.showMessageDialog(null, "Score must not exceed threshold");
				return false;
			}
		}

		return true;
	};

	public static boolean isDigit(TableModel model) {

		for (int i = 0; i < model.getRowCount(); i++) {

			try {
				Double.parseDouble((String) model.getValueAt(i, 1));

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Score must be digit");
				return false;
			}
		}
		return true;
	}

	public boolean isValid(TableModel model, double threshold) {
		return isDigit(model) && isPositive(model) && isInThreshold(model, threshold);
	}
	// public static void main(String[] args) {
	// new AddScoreFrame();
	// }
}
