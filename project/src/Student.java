import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable{
	private String id;
	//private String name;
	private String email;
	private ArrayList<Exam> examList;
	private double sum;
	
	public Student(String id,String email) {
		this.id = id;
		this.email = email;
		examList = new ArrayList<>();
		sum=0;
	}
	
	public Student(String id) {
		this.id = id;
		examList = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public ArrayList<Exam> getExamList() {
		return examList;
	}

	public void setExamList(ArrayList<Exam> examList) {
		this.examList = examList;
	}
	
	public Exam getExamByName(String name) {
		Exam tmp=null;
		for(int i=0;i<examList.size();i++) {
			if(examList.get(i).getName().equals(name)) {
				tmp = examList.get(i);
			}
		}
		return tmp;
	}
	
	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public String toString(){
		String tmp = id+" "+ examList;
		return tmp;
	}
}
