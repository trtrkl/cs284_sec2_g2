import java.util.ArrayList;

public class GenUser {
	public GenUser() {
		User ajan1 = new User("Songsak");
		Course course = new Course("cs284");
		ArrayList<Student> studentList = new ArrayList<>();
//		studentList.add(new Student("5909680109"));
//		studentList.add(new Student("5909680091"));
//		studentList.add(new Student("5909680017"));
		course.setStudentList(studentList);
		ajan1.addCourse(course);
		ajan1.addCourse(new Course("cs211"));
		ajan1.saveUserState();
		
		User ajan2 = new User("Kanom");
		ajan2.addCourse(new Course("cs284"));;
		ajan2.addCourse(new Course("cs213"));
		ajan2.saveUserState();
		
	}
	
	public static void main(String[] args) {
		new GenUser();
	}
}
