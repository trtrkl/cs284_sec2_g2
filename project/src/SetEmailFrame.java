import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class SetEmailFrame extends JFrame{
	private JTable table;
	
	public SetEmailFrame(ArrayList<Student> studentList,ActionListener action) {
		String[] headers = {"ID","Email"};
		String[][] data = new String[studentList.size()][headers.length];
		for(int i=0;i<studentList.size();i++) {
			data[i][0] = studentList.get(i).getId();
			data[i][1] = studentList.get(i).getEmail();
		}
		table = new JTable(data,headers);
		JScrollPane scroll = new JScrollPane(table);
		
		JButton ok = new JButton("Ok");
		ok.addActionListener(action);
		ok.setActionCommand("ok");
		
		add(scroll);
		add(ok,BorderLayout.SOUTH);
		setSize(500, 400);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
	}
	
	public TableModel getTableModel() {
		return table.getModel();
	}
}
