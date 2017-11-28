import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class ReadWriteUser {
	public static HashMap<String, User> read(File file) {
		HashMap<String, User> map = new HashMap<>();
		try (FileInputStream fileIn = new FileInputStream(file);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);) {
					while(true){
						User user = (User)objectIn.readObject();
						map.put(user.getUserName(), user);
					}
		} catch (EOFException|FileNotFoundException e) {
			//e.printStackTrace();
		} catch (ClassCastException|ClassNotFoundException|IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	public static void write(File file,HashMap<String, User> map) {
		try (FileOutputStream fileOut = new FileOutputStream(file);
				ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)){
				for(String userName:map.keySet()) {
					objectOut.writeObject(map.get(userName));
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
