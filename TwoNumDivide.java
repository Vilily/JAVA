
public class TwoNumDivide {
	 public int divide(int dividend, int divisor) {
		 if(dividend<divisor)
			 return 0;
		 int quotient = 0;
		 while(dividend>=0)
		 {
			 dividend-=divisor;
			 quotient++;
		 }
		 return quotient-1;
	 }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TwoNumDivide a = new TwoNumDivide();
		System.out.println(a.divide(7, 8));
	}

}
