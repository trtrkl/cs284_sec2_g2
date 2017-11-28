import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class LoginController implements ActionListener{
	private LoginFrame form;
	
	public LoginController() {
		form = new LoginFrame(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		File folder = new File("C:\\test");
		if(!folder.exists()||!folder.isDirectory()){	
			folder.mkdir();
		}
		HashMap<String, User> userMap = ReadWriteUser.read(new File(folder,"user.dat"));
		switch (arg0.getActionCommand()) {
		case "login":
			if(userMap.get(form.getUserName())==null) {
				JOptionPane.showMessageDialog(null, "Invalid username or password.");
				return;
			}
			if(!userMap.get(form.getUserName()).getPassWord().equals(form.getPassword())) {
				JOptionPane.showMessageDialog(null, "Invalid username or password.");
				return;
			}
			form.dispose();
			new CourseController(userMap.get(form.getUserName()));
			break;
		case "createNewAccount":
			if(userMap.get(form.getUserName())==null) {
				User newUser = new User(form.getUserName(), form.getPassword());
				userMap.put(newUser.getUserName(), newUser);
				ReadWriteUser.write(new File(folder,"user.dat"), userMap);
				JOptionPane.showMessageDialog(null, "Create new acoount succesful");
			}else {
				JOptionPane.showMessageDialog(null, "This username is already use.");
			}
			break;
		default:
			break;
		}
		
		
	}
	
	public static void main(String[] args) {
		new LoginController();
	}
}
