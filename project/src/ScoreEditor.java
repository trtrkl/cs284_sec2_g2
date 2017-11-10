import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class ScoreEditor extends JFrame {
	private JButton ok = new JButton("OK");
	private JButton cancel = new JButton("Cancel");
	JPanel down;
//	Importer importer = new Importer();
//	ArrayList<String> id;
	JPanel panel;
	private JComboBox selectExam;
	private JTable table;
	public ScoreEditor(final ArrayList<Exam> examDetailList,final ArrayList<Student> studentList,ActionListener action) {
		ScoreEditor edit = this;
		selectExam = new JComboBox<>(examDetailList.toArray());
		
		panel = new JPanel();
		

//		if (!importer.importFromFile()) {
//			System.out.println("Cancel");
//			this.dispose();
//		}

//		id = importer.getArrayList();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		down = new JPanel();
		down.add(ok);
		down.add(cancel);
		String[] headers = { "Student", "Score" };
		String[][] data = new String[studentList .size()][headers.length];

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
		
		for (int i = 0; i < table.getRowCount(); i++) {
			table.setValueAt(studentList.get(i).getId(), i, 0);
			Exam anExam = studentList.get(i).getExamByName(getSelectedExam().getName());
			if(anExam!=null) {
				table.setValueAt(anExam.getScore()+"", i, 1);
			}
			
		}
		JScrollPane pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension(300, 300));

		panel.add(pane);
		panel.add(down);
		ok.addActionListener(action);
		ok.setActionCommand("ok");
//		ok.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				File file = new File("score.txt");
//				try {
//					file.createNewFile();
//				} catch (IOException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}
//				StringBuilder builder = new StringBuilder();
//
//				try (FileWriter writer = new FileWriter(file)) {
//					for (int i = 0; i < table.getRowCount(); i++) {
//						for (int j = 0; j < table.getColumnCount(); j++) {
//							builder.append(table.getValueAt(i, j));
//							builder.append(",");
//						}
//						builder.append("\n");
//					}
//
//					writer.write(builder.toString());
//
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//
//			}
//
//		});

		cancel.addActionListener(action);
		cancel.setActionCommand("cancel");
		
		
		selectExam.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < table.getRowCount(); i++) {
					table.setValueAt(studentList.get(i).getId(), i, 0);
					Exam anExam = studentList.get(i).getExamByName(getSelectedExam().getName());
					if(anExam!=null) {
						table.setValueAt(anExam.getScore()+"", i, 1);
					}
					
				}
				
			}
		});
		add(selectExam,BorderLayout.NORTH);
		
		this.add(panel);
		this.pack();
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);

	}
	
	public TableModel getTableModel() {
		return table.getModel();
	}
	
	public Exam getSelectedExam() {
		return (Exam)selectExam.getSelectedItem();
	}

//	public ArrayList<String> getArrayList() {
//		return id;
//	}

//	public static void main(String[] args) {
//		ScoreEditor editor = new ScoreEditor();
//
//	}

}
