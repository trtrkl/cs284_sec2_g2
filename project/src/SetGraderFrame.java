import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SetGraderFrame extends JFrame{
	private ArrayList<SetGradePanel> panelList = new ArrayList<>();
	public SetGraderFrame(ActionListener action) {
		setLayout(new GridLayout(0, 1));
		JTextField text = new JTextField(10);
		panelList = new ArrayList<>();
		panelList.add(new SetGradePanel("A "));
		panelList.add(new SetGradePanel("B+"));
		panelList.add(new SetGradePanel("B "));
		panelList.add(new SetGradePanel("C "));
		panelList.add(new SetGradePanel("C+"));
		panelList.add(new SetGradePanel("D "));
		panelList.add(new SetGradePanel("D+"));
		
		JButton ok = new JButton("ok");
		ok.addActionListener(action);
		ok.setActionCommand("ok");
		
		for(SetGradePanel s :panelList ) {
			add(s);
		}
		add(ok);
		setSize(500, 400);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
	}
	
	public HashMap<String, Double> getGraderSetting() {
		HashMap<String, Double> map = new HashMap<>();
		for(SetGradePanel s :panelList ) {
			map.put(s.getGradename(), Double.parseDouble(s.getText()));
		}
		return map;
	}

	public class SetGradePanel extends JPanel{
		private JTextField textField = new JTextField(10);
		private String grade;
		public SetGradePanel(String grade) {
			this.grade = grade;
			add(new JLabel(grade + ">= "));
			add(textField);
		}
		public JTextField getTextField() {
			return textField;
		}
		public String getText() {
			return textField.getText();
		}
		public String getGradename() {
			return grade;
		}
	}
}
