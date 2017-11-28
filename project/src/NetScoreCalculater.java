
public class NetScoreCalculater {
	private double fullScore;
	private double fullPercent;
	
	public NetScoreCalculater(double fullScore, double fullPercent) {
		super();
		this.fullScore = fullScore;
		this.fullPercent = fullPercent;
	}
	
	public double calculateNetScore(double studentScore) {
		return studentScore/fullScore*fullPercent;
	}
	
}
