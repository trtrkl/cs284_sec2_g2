import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

public class AddScoreController implements ActionListener {
	private AddScoreFrame form;
	private Course course;

	public AddScoreController(Course course) {
		form = new AddScoreFrame(course.getExamDetailList(), course.getStudentList(), this);
		this.course = course;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "save":
			if(form.isValid(form.getTableModel(), form.getSelectedExam().getScore())) {
				TableModel model = form.getTableModel();
				for (int i = 0; i < model.getRowCount(); i++) {
					Student aStudent = course.getStudentList().get(i);
					if (aStudent.getId().equals(model.getValueAt(i, 0))) {
						Exam anExam = aStudent.getExamByName(form.getSelectedExam().getName());
						if (anExam != null) {
							anExam.setScore(Double.parseDouble((String) model.getValueAt(i, 1)));
						}
					}
				}
				form.dispose();
			}
			break;
		case "calculate net score" : 
			if(JOptionPane.showConfirmDialog(null, "Do you want to save changes", "", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				if(form.isValid(form.getTableModel(), form.getSelectedExam().getScore())) {
					TableModel model = form.getTableModel();
					for (int i = 0; i < model.getRowCount(); i++) {
						Student aStudent = course.getStudentList().get(i);
						if (aStudent.getId().equals(model.getValueAt(i, 0))) {
							Exam anExam = aStudent.getExamByName(form.getSelectedExam().getName());
							if (anExam != null) {
								anExam.setScore(Double.parseDouble((String) model.getValueAt(i, 1)));
							}
						}
					}
				}else {
					return;
				}
			}
			form.dispose();
			new NetScoreController(course, form.getSelectedExam()) ;
			break;

		case "cancel": form.dispose();
			break;
		}
	}
}
