
public class Grader {
	private double a,bp,b,cp,c,dp,d;
	
	
	
	public Grader(double a, double bp, double b, double cp, double c, double dp, double d) {
		super();
		this.a = a;
		this.bp = bp;
		this.b = b;
		this.cp = cp;
		this.c = c;
		this.dp = dp;
		this.d = d;
	}

	
	public String calculateGrade(double sum) {
		if(sum>=a) {
			return "A";
		}else if(sum>=bp) {
			return "B+";		
		}else if(sum>=b) {
			return "B";
		}else if(sum>=cp) {
			return "C+";
		}else if(sum>=c) {
			return "C";		
		}else if(sum>=dp) {
			return "D+";
		}else if(sum>=d) {
			return "D";
		}else {
			return "F";
		}
	}
	
}
