import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class CourseDetailForm extends JFrame {
	private JButton ok = new JButton("OK");
	private JButton cancel = new JButton("Cancel");
	private JPanel panel;
	private JTable table;

	public CourseDetailForm(ArrayList<Exam> examDetailList, ActionListener action) {
		CourseDetailForm edit = this;
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		int num;

		while (true) {
			try {
				num = Integer.parseInt(JOptionPane.showInputDialog("Enter Number of requirement", null));
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

		JPanel panel2 = new JPanel();

		panel.add(new JScrollPane(table));
		panel2.add(ok);
		panel2.add(cancel);
		panel.add(panel2);
		ok.addActionListener(action);
		ok.setActionCommand("ok");

		// ok.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// // TODO Auto-generated method stub
		//
		//

		// try {
		// for (int i = 0; i < table.getRowCount(); i++) {
		// totalPercent += Integer.parseInt((String) table.getValueAt(i, 2));
		// }
		//
		// // check column 1 format
		// for (int i = 0; i < table.getRowCount(); i++) {
		// Integer.parseInt((String) table.getValueAt(i, 1));
		// }
		//
		// } catch (NumberFormatException e1) {
		// JOptionPane.showMessageDialog(null, "Total Score and Percentage must be
		// integer");
		// return;
		// }
		//
		//
		//

		// File file = new File("output.txt");
		// try {
		// file.createNewFile();
		// } catch (IOException e2) {
		// // TODO Auto-generated catch block
		// e2.printStackTrace();
		// }
		// StringBuilder builder = new StringBuilder();
		//
		// try (Writer writer = new OutputStreamWriter(new FileOutputStream(file),
		// StandardCharsets.UTF_8)) {
		// for (int i = 0; i < table.getRowCount(); i++) {
		// for (int j = 0; j < table.getColumnCount(); j++) {
		// builder.append(table.getValueAt(i, j));
		// builder.append(",");
		// }
		// builder.append("\n");
		// }
		// writer.write(builder.toString());
		//
		// } catch (IOException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		//
		// JOptionPane.showMessageDialog(null, "Done");
		// edit.dispose();
		//
		// }
		// });

		cancel.addActionListener(action);
		cancel.setActionCommand("cancel");
		this.setContentPane(panel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		this.setTitle("GridLayout Frame");
		this.setVisible(true);

	}

	public TableModel getTableModel() {
		return table.getModel();
	}

	public boolean isValidInput(TableModel model) {

		return checkAllCellIsFilled(model)  && checkIsIntegerInRow1and2(model)
				&& checkRow1Row2IsLessThanZero(model) && checkPercent(model);
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

		if(checkIsIntegerInRow1and2(model) == false){
			return false;
		}
		
		int totalPercent = 0;

		for (int i = 0; i < model.getRowCount(); i++) {
			totalPercent += Integer.parseInt((String) model.getValueAt(i, 2));
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
				Integer.parseInt((String) model.getValueAt(i, 1));
			}

			// check column 2 format
			for (int i = 0; i < model.getRowCount(); i++) {
				Integer.parseInt((String) model.getValueAt(i, 2));
			}

		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, "Total Score and Percentage must be integer");
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
			if (Integer.parseInt((String) model.getValueAt(i, 1)) <= 0) {
				JOptionPane.showMessageDialog(null, "Total Score and Percentage must be positive");
				return false;
			}
		}

		// check column 2 value
		for (int i = 0; i < model.getRowCount(); i++) {
			if (Integer.parseInt((String) model.getValueAt(i, 2)) <= 0) {
				JOptionPane.showMessageDialog(null, "Total Score and Percentage must be positive");
				return false;
			}
		}

		return true;
	}
}
