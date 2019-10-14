import java.util.Scanner;

public class LongestUpSubSequence {
	
	public int lengthOfLIS(int[] nums) {
        int []leng = new int[nums.length];
        for(int i = 0; i < nums.length; i++)
        {
        	for(int j = 0; j <= i; j++)
        	{
        		if(leng[j] > leng[i] && nums[j] < nums[i])
        			leng[i] = leng[j];
        	}
        	leng[i]++;
        }
        int longest = 0;
        for(int i = 0; i < leng.length; i++)
        {
        	if(leng[i] > longest)
        		longest = leng[i];
        }
		return longest;
    }
	
	public static void main(String[] args)
	{
		LongestUpSubSequence a = new LongestUpSubSequence();
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int []s = new int[n];
		for(int i = 0; i < n; i++)
		{
			s[i] = in.nextInt();
		}
		System.out.println(a.lengthOfLIS(s));
	}
}

//too slow
