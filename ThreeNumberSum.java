import java.util.Arrays;
import java.util.Scanner;

public class ThreeNumberSum {
	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int first = 0, mid = 1, last = 2;
		int minSum = nums[first] + nums[mid] + nums[last];
		int minDistance = Math.abs(minSum - target);
		for(;first < nums.length; first++)
		{
			for(mid = first + 1, last = nums.length - 1; mid < last;)
			{
				int distance = nums[first] + nums[mid] + nums[last];
				if(Math.abs(distance - target) < minDistance)
				{
					minDistance = Math.abs(distance - target);
					minSum = distance;
				}
				if(distance - target > 0)
					last--;
				else
					mid++;
			}
			if(nums[first] >= target)
				break;
		}
		
		return minSum;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n = 3;
		int []a = new int[n];
		for(int i=0;i<n;i++)
		{
			a[i] = in.nextInt();
		}
		ThreeNumberSum temp = new ThreeNumberSum();
		System.out.println(temp.threeSumClosest(a, 1));
		
		in.close();
	}
}
//accepted
