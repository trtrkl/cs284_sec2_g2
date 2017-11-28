import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class ReadWriteCourse {
	public static Course read(File file) {
		Course course=null;
		try (FileInputStream fileIn = new FileInputStream(file);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);) {
				course = (Course)objectIn.readObject();
		} catch (EOFException e) {
			//e.printStackTrace();
		} catch (ClassCastException|ClassNotFoundException|IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return course;
	}
	
	public static void write(Course course,File file) {
		try (FileOutputStream fileOut = new FileOutputStream(file);
				ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)){
			objectOut.writeObject(course);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
