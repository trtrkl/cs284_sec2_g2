import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{
	private String userName;
	//String passWord;
	private ArrayList<Course> allCourse;
	
	public User(String name) {
		userName = name;
		allCourse = new ArrayList<>();
	}
	
	public void addCourse(Course aCourse){
		allCourse.add(aCourse);
	}
	
	public Course getCourse(int index){
		return allCourse.get(index);
	}
	
	public void saveUserState(){
		File folder = new File("C:\\test");
		if(!folder.exists()||!folder.isDirectory()){
			folder.mkdir();
		}
		File file = new File("C:\\test\\"+userName+".dat");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try (FileOutputStream fileOut = new FileOutputStream(file);
				ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)){
				objectOut.writeObject(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return userName;
	}

	public ArrayList<Course> getAllCourse() {
		return allCourse;
	}
	
	
}

