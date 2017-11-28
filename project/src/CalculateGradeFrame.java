import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CalculateGradeFrame extends JFrame{
	private JTable table;
	
	public CalculateGradeFrame(Course course,ActionListener action) {
		String[] headers = new String[course.getExamDetailList().size()+3];
		headers[0] = "ID";
		{
			int i=1;
			for(Exam exam:course.getExamDetailList()) {
				headers[i++] = exam.getName(); 
			}
			headers[i++] = "Sum";
			headers[i] = "Grade";
		}
		ArrayList<Student> studentList = course.getStudentList();
		String[][] data = new String[studentList.size()][headers.length];
		for(int i=0;i<studentList.size();i++) {
			
			data[i][0] = studentList.get(i).getId();
			for(int j=1;j<headers.length-2;j++) {
				data[i][j] = studentList.get(i).getExamByName(headers[j]).getPercentage()+"";
			}
		}
		table = new JTable(data, headers);
		JScrollPane scroll = new JScrollPane(table);
		
		add(scroll);
		setSize(500, 400);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
	}
}
