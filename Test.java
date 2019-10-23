import java.util.Scanner;

public class Test {
	public static void main(String args[]) 
    { 
        Scanner cin=new Scanner(System.in); 
		while(cin.hasNext()){
			int a=cin.nextInt();
			int b=cin.nextInt(); 
			System.out.println(gcd(a, b)); 
		}
    }
    
    // 最大公约数
    public static int gcd(int n1, int n2) {
        int max;
        int min;
        if(n1>n2)
        {
            max = n1;
            min = n2;
        }
        else
        {
            max = n2;
            min = n1;
        }
        if(min == 0)
        	return 0;
        while(min !=0 && max != min)
        {
            int temp = min;
            min = max % min;
            max = temp;
        }

        int gcd = max;
		// TODO
      	
        return gcd;

    }
}
