import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<Exam> examDetailList;
	private ArrayList<Student> studentList;
	 
	public Course(String name) {
		this.name = name;
		examDetailList = new ArrayList<>();
		studentList = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Exam> getExamDetailList() {
		return examDetailList;
	}

	public void setExamDetailList(ArrayList<Exam> examDetailList) {
		this.examDetailList = examDetailList;
		for(int i=0;i<studentList.size();i++) {
			ArrayList<Exam> examList = new ArrayList<>();
			for(int j=0;j<examDetailList.size();j++){
				examList.add(new Exam(examDetailList.get(j).getName(), 0,0));
			}
			studentList.get(i).setExamList(examList);
		}
		
	}

	public ArrayList<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(ArrayList<Student> studentList) {
		this.studentList = studentList;
		for(int i=0;i<studentList.size();i++) {
			ArrayList<Exam> examList = new ArrayList<>();
			for(int j=0;j<examDetailList.size();j++){
				examList.add(new Exam(examDetailList.get(j).getName(), 0,0));
			}
			studentList.get(i).setExamList(examList);
		}
	}
	
//	public void saveCourseState() {
//		File folder = new File("C:\\test\\course");
//		if(!folder.exists()||!folder.isDirectory()){
//			folder.mkdir();
//		}
//		File file = new File(folder,name+".dat");
//		try (FileOutputStream fileOut = new FileOutputStream(file);
//				ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)){
//				objectOut.writeObject(this);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	public String toString(){
//		String tmp = name+"\n";
//		tmp+="Standard : "+examDetailList+"\n";
//		tmp+="AllStudent :\n";
//		for(Student aStudent:studentList){
//			tmp+=aStudent.toString()+"\n";
//		}
//		//tmp+=studentList.toString()+"\n";
//		return tmp;
//	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
