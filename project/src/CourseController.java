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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CourseController implements ActionListener,WindowListener{
	 private CourseForm form;	
	 private User currentUser;
	 
	 public CourseController() {
		// TODO Auto-generated constructor stub
		 currentUser = null;
		 do{
			 currentUser = getUserByName(JOptionPane.showInputDialog("Username"));
			 if(currentUser==null){
				 JOptionPane.showMessageDialog(null, "User Not Found");
			 }
		 }while(currentUser==null);
		 form = new CourseForm(currentUser,this);
	}
	 
	private User getUserByName(String name){
		User tmp = null;
		File file = new File("C:\\test");
		ArrayList<User> userList = new ArrayList<>();
		for (File aFile : file.listFiles()) {
			//System.out.println(aFile.getName());
			try (FileInputStream fileIn = new FileInputStream(aFile);
					ObjectInputStream objectIn = new ObjectInputStream(fileIn);) {
						while(true){
							Object aUser = objectIn.readObject();
							userList.add((User)aUser);
						}
							
			} catch (EOFException e) {
				//e.printStackTrace();
			} catch (ClassCastException|ClassNotFoundException|IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i=0;i<userList.size();i++){
			if(userList.get(i).getUserName().equals(name)){
				tmp = userList.get(i);
			}
		}
		return tmp;
	}

	public static void main(String[] args) throws IOException {
		new CourseController();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String tmp = e.getActionCommand();
		switch(tmp){
			case "edit" : new CourseDetailController(form.getSelectedCourse()); break;
			case "importList" : new ImporterController(form.getSelectedCourse()); break;
			case "addScore" :  new AddScoreController(form.getSelectedCourse()); 
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
		currentUser.saveUserState();
		
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
}
