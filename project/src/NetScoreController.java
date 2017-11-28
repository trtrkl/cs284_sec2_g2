import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.net.ssl.ExtendedSSLSession;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NetScoreController implements ActionListener {
	private Course course;
	private Exam examDetail;

	public NetScoreController(Course course, Exam examDetail) {
		this.course = course;
		this.examDetail = examDetail;
		NetScoreCalculater calculator = new NetScoreCalculater(examDetail.getScore(), examDetail.getPercentage());
		for (Student student : course.getStudentList()) {
			Exam exam = student.getExamByName(examDetail.getName());
			if (exam != null) {
				exam.setPercentage(calculator.calculateNetScore(exam.getScore()));
			}
		}
		new NetScoreFrame(course.getStudentList(), examDetail, this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		SendEmailStatusFrame status = new SendEmailStatusFrame();
		status.appendText("Start...\n");
		status.repaint();
		ReportScoreByEmail reporter = new ReportScoreByEmail("g2s2cs284@gmail.com", "cs28413579");
		String subject = course.getName() + " : report " + examDetail.getName() + " score";
		for (Student student : course.getStudentList()) {
			Exam exam = student.getExamByName(examDetail.getName());
			if (exam != null) {
				String text = "ID : " + student.getId() + "\n";
				text += examDetail.getName() + " Raw score : " + examDetail.getScore() + " = "
						+ examDetail.getPercentage() + "%\n";
				text += "You get " + exam.getScore() + " = " + exam.getPercentage() + "%\n";
				if (student.getEmail() != null) {
					if (reporter.sendEmail(student.getEmail(), subject, text)) {
						status.appendText(student.getId() + " Email : " + student.getEmail() + " sent succces.\n");
						// System.out.println(student.getId()+" Email : "+student.getEmail()+" sent
						// succces.");
					} else {
						status.appendText(student.getId() + " Email : " + student.getEmail() + " error.\n");
						// System.out.println(student.getId()+" Email : "+student.getEmail()+" error.");
					}

				} else {
					status.appendText(student.getId() + " no email.\n");
				}
			}
		}
		status.appendText("End...\n");

	}

	public class NetScoreFrame extends JFrame {
		public NetScoreFrame(ArrayList<Student> studentList, Exam examDetail, ActionListener action) {
			String[] headers = { "ID", "Percentage" };
			String[][] data = new String[studentList.size()][2];
			for (int i = 0; i < studentList.size(); i++) {
				data[i][0] = studentList.get(i).getId();
				data[i][1] = studentList.get(i).getExamByName(examDetail.getName()).getPercentage() + "";
			}

			JTable table = new JTable(data, headers) {
				@Override
				public boolean isCellEditable(int row, int col) {
					switch (col) {
					case 0:
					case 1:
						return false;
					default:
						return true;
					}
				}
			};
			table.getTableHeader().setReorderingAllowed(false);
			JScrollPane scroll = new JScrollPane(table);
			JButton announceScore = new JButton("Announce Score");
			announceScore.addActionListener(action);

			add(scroll);
			add(announceScore, BorderLayout.SOUTH);

			pack();
			setResizable(false);
			setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2,
					(Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		}
	}

	public class SendEmailStatusFrame extends JFrame {
		JTextArea text;

		public SendEmailStatusFrame() {
			text = new JTextArea(30 ,30);
			text.setEditable(false);
			JScrollPane scroll = new JScrollPane(text);
			add(scroll);
			pack();
			setTitle("Status");
			setResizable(false);
			setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2,
					(Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
			// TODO Auto-generated constructor stub
		}

		public void appendText(String str) {
			text.append(str);
		}
	}

}
