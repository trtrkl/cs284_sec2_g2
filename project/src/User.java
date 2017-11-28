import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String passWord;
	private HashMap<String,File> courseMap;
	
	
	public User(String userName,String passWord) {
		this.userName = userName;
		this.passWord = passWord;
		courseMap = new HashMap<>();
	}
	
//	public void saveUserState(){
//		File folder = new File("C:\\test");
//		if(!folder.exists()||!folder.isDirectory()){
//			folder.mkdir();
//		}
//		File file = new File("C:\\test\\"+userName+".dat");
//		if(!file.exists()){
//			try {
//				file.createNewFile();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		try (FileOutputStream fileOut = new FileOutputStream(file);
//				ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)){
//				objectOut.writeObject(this);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return userName;
	}
	
	public void addNewCourse(String name,File file) {
		courseMap.put(name, file);
	}
	
	public File getCourseFileByName(String name) {
		return courseMap.get(name);
	}

	public HashMap<String, File> getCourseMap() {
		return courseMap;
	}
}

