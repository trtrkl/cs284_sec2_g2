import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.TableModel;

public class CourseDetailController implements ActionListener {
	private CourseDetailForm form;
	private Course course;

	public CourseDetailController(Course course) {
		form = new CourseDetailForm(course.getExamDetailList(),this);
		this.course = course;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
			case "ok" : {
				if(form.isValidInput(form.getTableModel())){
					TableModel model = form.getTableModel();
					int round = model.getRowCount();
					ArrayList<Exam> examList = new ArrayList<>();
					for(int i=0;i<round;i++){
						examList.add(new Exam((String)model.getValueAt(i, 0), Double.parseDouble((String)model.getValueAt(i, 1)) , Double.parseDouble((String)model.getValueAt(i, 2))));
					}
					course.setExamDetailList(examList);
					form.dispose();
				}
				
			}
			break;
			case "cancel" :  form.dispose();
			
		}
		
	}

}
