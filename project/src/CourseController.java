import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class CourseController implements ActionListener, WindowListener {
	private CourseFrame form;
	private User currentUser;
	private AddCourseFrame addCourseFrame=null;
	private ArrayList<Course> courseList;
	private Timer timer;
	
	public CourseController(User user) {
		timer = new Timer(60000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveChange();

			}
		});
		timer.start();
		currentUser = user;
		courseList = new ArrayList<>();
		
		for (String name : user.getCourseMap().keySet()) {
			File file = user.getCourseMap().get(name);
			courseList.add(ReadWriteCourse.read(file));
		}
		form = new CourseFrame(user, courseList, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "edit":
			if(form.getSelectedCourse()==null) {
				JOptionPane.showMessageDialog(null, "No course");
			}else{
				new CourseDetailController(form.getSelectedCourse());
			}
			break;
		case "importList":
			if(form.getSelectedCourse()==null) {
				JOptionPane.showMessageDialog(null, "No course");
			}else{
				new ImporterController(form.getSelectedCourse());
			}
			
			break;
		case "addScore":
			if(form.getSelectedCourse()==null) {
				JOptionPane.showMessageDialog(null, "No course");
			}else{
				if (!form.getSelectedCourse().getExamDetailList().isEmpty()
						&& !form.getSelectedCourse().getStudentList().isEmpty()) {
					new AddScoreController(form.getSelectedCourse());
				} else {
					JOptionPane.showMessageDialog(null, "Please set criteria and import classlist file");
				}
			}
			break;
		case "calculateGrade":
			if(form.getSelectedCourse()==null) {
				JOptionPane.showMessageDialog(null, "No course");
			}else{
				if (!form.getSelectedCourse().getExamDetailList().isEmpty()
						&& !form.getSelectedCourse().getStudentList().isEmpty()) {
					new CalculateGradeController(form.getSelectedCourse());
				} else {
					JOptionPane.showMessageDialog(null, "Please set criteria and import classlist file");
				}
			}
			break;
		case "setEmail":
			if(form.getSelectedCourse()==null) {
				JOptionPane.showMessageDialog(null, "No course");
			}else{
				if(!form.getSelectedCourse().getStudentList().isEmpty()) {
					new SetEmailController(form.getSelectedCourse().getStudentList());
				}else {
					JOptionPane.showMessageDialog(null, "Please import classlist file");
				}
			}
			
			break;
		case "addCourse" :
			File folder = new File("C:\\test\\course");
			if(!folder.exists()||!folder.isDirectory()){	
				folder.mkdir();
			}
			File[] files = folder.listFiles();
			ArrayList<String> allCourseName = new ArrayList<>();
			for(File file:folder.listFiles()) {
				allCourseName.add(file.getName());
			}
			addCourseFrame = new AddCourseFrame(allCourseName, this);
			break;
		case "update" :
			Course course;
			if(addCourseFrame.addNewCourseIsSelected()) {
				File file = new File("C:\\test\\course\\"+addCourseFrame.getNewCourseName());
				course = new Course(addCourseFrame.getNewCourseName());
				ReadWriteCourse.write(course,file);
				currentUser.addNewCourse(addCourseFrame.getNewCourseName(), file);
			}else {
				
				File file = new File("C:\\test\\course\\"+addCourseFrame.getSelectedCourseName());
				currentUser.addNewCourse(addCourseFrame.getSelectedCourseName(), file);
				course = ReadWriteCourse.read(file);
			}
			courseList.add(course);
			form.addSelectableCourse(course);
			addCourseFrame.dispose();
			break;
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		saveChange();
		timer.stop();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}
	
	public void saveChange() {
		File file = new File("C:\\test\\user.dat");
		HashMap<String, User> userMap = ReadWriteUser.read(file);
		userMap.put(currentUser.getUserName(), currentUser);
		ReadWriteUser.write(file, userMap);
		
		for(Course course:courseList) {

			ReadWriteCourse.write(course, currentUser.getCourseFileByName(course.getName()));
		}
		System.out.println("Saved");
	}
}
