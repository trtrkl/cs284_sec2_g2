
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class CourseDetailForm extends JFrame {
	String[] headers = { "Requirement", "Total Score", "Percentage%" };
	JPanel pane = new JPanel();
	JButton okBtn;
	JButton ccBtn;
	JTable table;

	public CourseDetailForm(final ArrayList<Exam> examDetailList, final ActionListener action) {
		
		try {
			okBtn = new JButton(new ImageIcon(
					ImageIO.read(getClass().getResource("okBtn.png")).getScaledInstance(60, 30, Image.SCALE_DEFAULT)));
			ccBtn = new JButton(new ImageIcon(
					ImageIO.read(getClass().getResource("ccBtn.png")).getScaledInstance(90, 30, Image.SCALE_DEFAULT)));
			LayoutManager boxL = new BoxLayout(pane, BoxLayout.Y_AXIS);
			pane.setLayout(boxL);
			pane.setBackground(Color.WHITE);
			int num = 0;
			while (true) {
				try {
					String tmp = JOptionPane.showInputDialog(null, "Enter Number of requirement");

					if (tmp == null) {
						return;
					} else {
						num = Integer.parseInt(tmp);
					}
					break;
				} catch (NumberFormatException e3) {
					JOptionPane.showMessageDialog(null, "Input must be integer");
				}
			}
			String[] headers = { "Requirement", "Total Score", "Percentage%" };
			// String[][] data = new String[num][headers.length];
			table = new JTable(new String[num][headers.length], headers);
			table.getTableHeader().setReorderingAllowed(false);
			for (int i = 0; i < table.getRowCount() && i < examDetailList.size(); i++) {
				table.setValueAt(examDetailList.get(i).getName(), i, 0);
				table.setValueAt(examDetailList.get(i).getScore() + "", i, 1);
				table.setValueAt(examDetailList.get(i).getPercentage() + "", i, 2);
			}
			JScrollPane scrPane = new JScrollPane(table);
			scrPane.setMaximumSize(new Dimension(400, 300));
			table.setAlignmentX(Component.CENTER_ALIGNMENT);
			okBtn.setBorderPainted(false);
			okBtn.setContentAreaFilled(false);
			okBtn.setFocusPainted(false);
			okBtn.setOpaque(false);
			okBtn.setPressedIcon(new ImageIcon(
					ImageIO.read(getClass().getResource("pressedOkBtn.png")).getScaledInstance(60, 30, Image.SCALE_DEFAULT)));
			okBtn.addActionListener(action);
			okBtn.setActionCommand("ok");
			ccBtn.setBorderPainted(false);
			ccBtn.setContentAreaFilled(false);
			ccBtn.setFocusPainted(false);
			ccBtn.setOpaque(false);
			ccBtn.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("pressedCcBtn.png"))
					.getScaledInstance(90, 30, Image.SCALE_DEFAULT)));
			ccBtn.addActionListener(action);
			ccBtn.setActionCommand("cancel");
			JPanel btnPane = new JPanel(new FlowLayout());
			btnPane.setMaximumSize(new Dimension(400, 100));
			btnPane.setBackground(Color.white);
			btnPane.add(okBtn);
			btnPane.add(Box.createHorizontalBox());
			btnPane.add(ccBtn);
			pane.add(Box.createRigidArea(new Dimension(0, 50)));
			pane.add(scrPane);
			pane.add(Box.createRigidArea(new Dimension(0, 20)));
			pane.add(btnPane);
			add(pane);
			setTitle("Add criterial score");
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

	public boolean isValidInput(TableModel model) {

		return checkAllCellIsFilled(model) && checkIsIntegerInRow1and2(model) && checkRow1Row2IsLessThanZero(model)
				&& checkPercent(model);
	}

	public static boolean checkAllCellIsFilled(TableModel model) {
		for (int i = 0; i < model.getRowCount(); i++) {
			for (int j = 0; j < model.getColumnCount(); j++)
				if (model.getValueAt(i, j) == null) {
					JOptionPane.showMessageDialog(null, "All cell must be filled");
					return false;
				}
		}
		return true;

	}

	public static boolean checkPercent(TableModel model) {

		if (checkIsIntegerInRow1and2(model) == false) {
			return false;
		}

		double totalPercent = 0;

		for (int i = 0; i < model.getRowCount(); i++) {
			totalPercent += Double.parseDouble((String) model.getValueAt(i, 2));
		}

		if (totalPercent != 100) {
			JOptionPane.showMessageDialog(null, "Percentage is not equal 100");
			return false;
		}

		return true;
	}

	public static boolean checkIsIntegerInRow1and2(TableModel model) {

		try {

			// check column 1 format
			for (int i = 0; i < model.getRowCount(); i++) {
				Double.parseDouble((String) model.getValueAt(i, 1));
			}

			// check column 2 format
			for (int i = 0; i < model.getRowCount(); i++) {
				Double.parseDouble((String) model.getValueAt(i, 2));
			}

		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, "Total Score and Percentage must be number");
			return false;
		}

		return true;
	}

	public static boolean checkRow1Row2IsLessThanZero(TableModel model) {
		if (checkIsIntegerInRow1and2(model) == false) {
			return false;
		}

		// check column 1 value
		for (int i = 0; i < model.getRowCount(); i++) {
			if (Double.parseDouble((String) model.getValueAt(i, 1)) <= 0) {
				JOptionPane.showMessageDialog(null, "Total Score and Percentage must be positive");
				return false;
			}
		}

		// check column 2 value
		for (int i = 0; i < model.getRowCount(); i++) {
			if (Double.parseDouble((String) model.getValueAt(i, 2)) <= 0) {
				JOptionPane.showMessageDialog(null, "Total Score and Percentage must be positive");
				return false;
			}
		}

		return true;
	}

}
