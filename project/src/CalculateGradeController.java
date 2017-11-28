import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CalculateGradeController implements ActionListener {
	private SetGraderFrame form;
	private Course course;

	public CalculateGradeController(Course course) {
		form = new SetGraderFrame(this);
		this.course = course;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = new File(jfc.getSelectedFile(), "grade.xlsx");
			HashMap<String, Double> map = form.getGraderSetting();
			Grader grader = new Grader(map.get("A "), map.get("B+"), map.get("B "), map.get("C+"), map.get("C "),
					map.get("D+"), map.get("D "));
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("grade");
			{
				Row row = sheet.createRow(0);
				Cell cell0 = row.createCell(0);
				cell0.setCellValue("เลขทะเบียนนักศึกษา");
				Cell cell1 = row.createCell(1);
				cell1.setCellValue("เกรด");
			}
			int rowCount = 1;
			for (Student student : course.getStudentList()) {
				System.out.println("loop:"+student);
				Row row = sheet.createRow(rowCount++);
				Cell cell0 = row.createCell(0);
				cell0.setCellValue(student.getId());
				double sum = 0;
				for (Exam exam : student.getExamList()) {
					sum += exam.getPercentage();
				}

				Cell cell1 = row.createCell(1);
				cell1.setCellValue(grader.calculateGrade(sum));

			}
			try (FileOutputStream fileOut = new FileOutputStream(file)) {
				workbook.write(fileOut);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Create excel file success@"+file.getPath());
			form.dispose();
		}
	}
}
