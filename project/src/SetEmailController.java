import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.TableModel;

public class SetEmailController implements ActionListener{
	private SetEmailFrame form ;
	private ArrayList<Student> studentList;
	
	public SetEmailController(ArrayList<Student> studentList) {
		form = new SetEmailFrame(studentList, this);
		this.studentList = studentList;	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "ok": 
			TableModel model = form.getTableModel();
			for (int i = 0; i < model.getRowCount(); i++) {
				Student student = studentList.get(i);
				if (student.getId().equals(model.getValueAt(i, 0))) {
					student.setEmail((String)model.getValueAt(i, 1));
				}
			}
			form.dispose();
			break;
		default:
			break;
		}
		
	}
}
