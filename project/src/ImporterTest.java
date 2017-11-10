import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ImporterTest {
	private Importer importer;
	private File file;
	
	@Before
	public void setUp() throws Exception {
		importer = new Importer();
		JOptionPane.showMessageDialog(null, "Creating a new importer before each test case.");
	}

	@After
	public void tearDown() throws Exception {
		importer = null;
		JOptionPane.showMessageDialog(null, "Deleting importer after each test case.");
	}

	@Test
	public void testAcceptOnlyCsvFile() {
		File file = new File("C:\\test\\tmp.csv");
		assertTrue("Only accept '.csv' extension" , importer.checkExtension(file));
	
		
		file = new File("C:\\test\\tmp.txt");
		assertFalse("Only accept '.csv' extension" , importer.checkExtension(file));
	}
	
	@Test
	public void testIdIsTenInteger() {
		File file = new File("C:\\test\\tmp.csv");

		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("what the hell");
		}

		try (FileWriter writer = new FileWriter(file)) {
			writer.write("asdasd,5909680091,asdsasad");
			writer.write("\n");
			writer.write("sadasd,5909680021,sadsad");
			writer.write("\n");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		assertTrue("File's content doesn't include ten integer" , importer.checkContentFormat(file));
		
		file = new File("C:\\test\\tmp.csv");

		System.out.println(file);
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("what the hell");
		}

		try (FileWriter writer = new FileWriter(file)) {
			writer.write("asdasd,59096,asdsasad");
			writer.write("\n");
			writer.write("sadasd,590961,sadsad");
			writer.write("\n");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		assertFalse("File's content doesn't include ten integer" , importer.checkContentFormat(file));
	}
}
