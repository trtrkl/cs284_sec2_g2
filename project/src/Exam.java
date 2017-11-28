import java.io.Serializable;

public class Exam implements Serializable{
	private String name;
	private double rawScore;
	private double percentage;
	
	public Exam(String name,double rawScore,double percentage) {
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
	
	public double getScore() {
		return rawScore;
	}
	
	public void setScore(double rawScore) {
		this.rawScore = rawScore;
	}
	
	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public String toString(){
		return name +": Raw score = "+rawScore+ " Percentage = "+percentage; 
	}

}
