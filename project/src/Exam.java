import java.io.Serializable;

public class Exam implements Serializable{
	private String name;
	private int rawScore;
	private int percentage;
	
	public Exam(String name, int rawScore,int percentage) {
		super();
		this.name = name;
		this.rawScore = rawScore;
		this.percentage = percentage;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getScore() {
		return rawScore;
	}
	
	public void setScore(int rawScore) {
		this.rawScore = rawScore;
	}
	
	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public String toString(){
		return name +": rawScore "+rawScore+" percentage "+percentage; 
	}

}
