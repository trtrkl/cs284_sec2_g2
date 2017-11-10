import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.TableModel;

public class CourseDetailController implements ActionListener {
	private CourseDetailForm cdf;
	private Course course;
	public CourseDetailController(Course course) {
		cdf = new CourseDetailForm(course.getExamDetailList(),this);
		this.course = course;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
			case "ok" : {
				if(cdf.isValidInput(cdf.getTableModel())){
					TableModel model = cdf.getTableModel();
					int round = model.getRowCount();
					ArrayList<Exam> examList = new ArrayList<>();
					for(int i=0;i<round;i++){
						examList.add(new Exam((String)model.getValueAt(i, 0), Integer.parseInt((String)model.getValueAt(i, 1)) , Integer.parseInt((String)model.getValueAt(i, 2))));
					}
					course.setExamDetailList(examList);
					
					//System.out.println(examList);
					cdf.dispose();
				}
				
			}
			break;
			case "cancel" :  cdf.dispose();
		}
		
	}

}
