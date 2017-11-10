import java.util.ArrayList;

public class ImporterController {
	
	public ImporterController(Course course) {
		Importer importer = new Importer();
		if(importer.importFromFile()) {
			ArrayList<Student> studentList = new ArrayList<>();
			for(String id:importer.getArrayList()) {
				studentList.add(new Student(id));
			}
			course.setStudentList(studentList);
			System.out.println(studentList);
		}
	}
}
	