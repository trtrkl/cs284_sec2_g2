import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Importer {
	private String line = new String();
	private ArrayList<String> id = new ArrayList<>();
	private JFileChooser chooser = new JFileChooser();
	private File file;

	public Importer() {
		
		chooser = new JFileChooser();
		chooser.setDialogTitle("Choose file to import");
		chooser.setApproveButtonText("Import");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	}
	
	public File selectFile(){
		chooser.showOpenDialog(null); 
		return  chooser.getSelectedFile(); 
	}
	
	public boolean isValidFile(File file){
		if(file==null){
			return false;
		}
				
		return (checkContentFormat(file) && checkExtension(file));
	}
	
	public boolean importFromFile() {
		File file = selectFile();
		if(isValidFile(file)){
			Scanner scanner;
			try {
				scanner = new Scanner(file);
				while (scanner.hasNextLine()) {
					line = scanner.nextLine();
					if (line.matches(".*\\d{10}.*")) {
						break;
					}
				}

			} catch (FileNotFoundException e) {
				return false;
			}
		
			{
				StringTokenizer tokenizer = new StringTokenizer(line, ",");
				tokenizer.nextToken();
				id.add(tokenizer.nextToken());
				
			}
			
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(line, ",");
				try{
					tokenizer.nextToken();
					id.add(tokenizer.nextToken());
				}catch (NoSuchElementException e) {
					
				}	
			}
			return true;
		}
		return false;
	}
	
	
	public boolean checkExtension(File file) {
		if(file == null) {
			return false;
		}
		
		String[] tmp = file.getName().split("\\.");

		
		if(!tmp[tmp.length-1].equals("csv")){
			System.out.println("not csv");
			return false;
		}
		System.out.println("csv");
		
		return true;
		
	}
	
	public boolean checkContentFormat(File file){
		Scanner scanner;
		try {
			scanner = new Scanner(file);
			boolean valid = false;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();

				if (line.matches(".*\\d{10}.*")) {
					valid = true;
				}
			}
			
			scanner.close();
			
			if (valid == false) {
				return false;
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return true;
	}

	public ArrayList<String> getArrayList() {
		return id;
	}
	
	

}
