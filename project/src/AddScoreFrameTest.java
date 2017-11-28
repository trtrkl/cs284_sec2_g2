import static org.junit.Assert.*;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddScoreFrameTest {

	@Before
	public void setUp() throws Exception {
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsPositive() {
		String[] headers = { "Student", "Score" };
		String[][] data = {{"5909680109","0"},{"5909680109","1"}};
		JTable table = new JTable(	data, headers);
		assertTrue("Score is already positive. This method must return true",AddScoreFrame.isPositive(table.getModel()));
		
		String[] headers2 = { "Student", "Score" };
		String[][] data2 = {{"5909680109","0"},{"5909680109","-1"}};
		JTable table2 = new JTable(	data2, headers2);
		assertFalse("Score must be positive",AddScoreFrame.isPositive(table2.getModel()));
	}

	@Test
	public void testIsInThreshold() {
		int limit = 50;
		String[] headers = { "Student", "Score" };
		String[][] data = {{"5909680109","0"},{"5909680109","50"}};
		JTable table = new JTable(	data, headers);
		assertTrue("Score is already lower than limit. This method must return true ",AddScoreFrame.isInThreshold(table.getModel() , limit));
	
		String[] headers2 = { "Student", "Score" };
		String[][] data2 = {{"5909680109","50"},{"5909680109","51"}};
		JTable table2 = new JTable(	data2, headers2);
		assertFalse("Score is higher than limit. This method must return false",AddScoreFrame.isInThreshold(table2.getModel() , limit));
	}

	@Test
	public void testIsDigit() {
		String[] headers = { "Student", "Score" };
		String[][] data = {{"5909680109","0"},{"5909680109","50"}};
		JTable table = new JTable(	data, headers);
		assertTrue("Score is already digit. This method must return true ",AddScoreFrame.isDigit(table.getModel()));
		
		String[] headers2 = { "Student", "Score" };
		String[][] data2 = {{"5909680109","50"},{"5909680109","This is a string"}};
		JTable table2 = new JTable(	data2, headers2);
		assertFalse("Score is not digit. This method must return false",AddScoreFrame.isPositive(table2.getModel()));
	}

}
