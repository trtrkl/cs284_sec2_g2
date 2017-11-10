import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.TableModel;

public class AddScoreController implements ActionListener {
	private ScoreEditor form;
	private Course course;

	public AddScoreController(Course course) {
		form = new ScoreEditor(course.getExamDetailList(), course.getStudentList(), this);
		this.course = course;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "ok":
			TableModel model = form.getTableModel();
			for (int i = 0; i < model.getRowCount(); i++) {
				Student aStudent = course.getStudentList().get(i);
				if (aStudent.getId().equals(model.getValueAt(i, 0))) {
					Exam anExam = aStudent.getExamByName(form.getSelectedExam().getName());
					if (anExam != null) {
						anExam.setScore(Integer.parseInt((String) model.getValueAt(i, 1)));
					}
				}
			}
			form.dispose();
			break;
		case "cancel": form.dispose();

			break;
		}
	}
}
