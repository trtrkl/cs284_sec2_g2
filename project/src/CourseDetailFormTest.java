import static org.junit.Assert.*;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseDetailFormTest {

	// @Before
	// public void setUp() throws Exception {
	// JOptionPane.showMessageDialog(null, "Creating a new importer before each test
	// case.");
	// }
	//
	// @After
	// public void tearDown() throws Exception {
	// JOptionPane.showMessageDialog(null, "Deleting importer after each test
	// case.");
	// }

	@Test
	public void testCheckPercent() {
		String[] headers = { "Requirement", "Total Score", "Percentage%" };
		String[][] data = { { "Mid", "20", "50" }, { "Final", "5", "50" } };

		JTable table = new JTable(data, headers);
		assertTrue("Sum of all 'Percentage%' cells must equal 100 ", CourseDetailForm.checkPercent(table.getModel()));

		
		String[] headers2 = { "Requirement", "Total Score", "Percentage%" };
		String[][] data2 = { { "Mid", "20", "15" } };

		JTable table2 = new JTable(data2, headers2);
		assertFalse("Sum of all 'Percentage%' cells must equal 100 ", CourseDetailForm.checkPercent(table2.getModel()));
	}

	@Test
	public void testAllCellisFilled() {
		String[] headers = { "Requirement", "Total Score", "Percentage%" };
		String[][] data = { { "Mid", "20", "50" }, { "Final", "5", "50" } };

		JTable table = new JTable(data, headers);
		assertTrue("All cell must be filled", CourseDetailForm.checkPercent(table.getModel()));

		
		String[] headers2 = { "Requirement", "Total Score", "Percentage%" };
		String[][] data2 = { { "Mid", "20", null } };

		JTable table2 = new JTable(data2, headers2);
		assertFalse("All cell must be filled ", CourseDetailForm.checkAllCellIsFilled(table2.getModel()));
	
	}
	
	@Test
	public void testCheckIsIntegerInRow1and2() {
		String[] headers = { "Requirement", "Total Score", "Percentage%" };
		String[][] data = { { "Mid", "20", "50" }};

		JTable table = new JTable(data, headers);
		assertTrue("Row 1&2 must be integer", CourseDetailForm.checkIsIntegerInRow1and2(table.getModel()));

		
		String[] headers2 = { "Requirement", "Total Score", "Percentage%" };		
		String[][] data2 = { { "Mid", "20", "Not Integer" } };

		JTable table2 = new JTable(data2, headers2);
		assertFalse("Row 1&2 must be integer",CourseDetailForm.checkIsIntegerInRow1and2(table2.getModel()));
	
	}
	
	@Test
	public void testCheckRow1Row2IsLessThanZero() {
		String[] headers = { "Requirement", "Total Score", "Percentage%" };
		String[][] data = { { "Mid", "20", "50" }};

		JTable table = new JTable(data, headers);
		assertTrue("Row 1&2 must be positive", CourseDetailForm.checkRow1Row2IsLessThanZero(table.getModel()));

		
		String[] headers2 = { "Requirement", "Total Score", "Percentage%" };		
		String[][] data2 = { { "Mid", "20", "-8" } };

		JTable table2 = new JTable(data2, headers2);
		assertFalse("Row 1&2 must be positive",CourseDetailForm.checkRow1Row2IsLessThanZero(table2.getModel()));
	
	}
}
