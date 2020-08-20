import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FullSort {
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> list = (List<List<Integer>>)new ArrayList<List<Integer>>();
        ArrayList<Integer> res = new ArrayList<Integer>();
        for(int a:nums)
        	res.add(a);
        ArrayList<Integer> number = new ArrayList<Integer>();
        recur(number, list, res);
        return list;
    }
	public void recur(ArrayList<Integer> nums, List<List<Integer>> list, ArrayList<Integer> res)
	{
		if(res.size() == 0)
		{
			ArrayList<Integer> temp = new ArrayList<Integer>((ArrayList<Integer>)nums.clone());
			list.add(temp);
			return;
		}
		for(int i=0;i<res.size();i++)
		{
			int temp = res.get(i);
			nums.add(temp);
			res.remove(i);
			recur(nums, list, res);
			res.add(i, temp);
			nums.remove(nums.size()-1);
		}
	}
	public static void main(String[] args)
	{
		int n;
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		int [] nums = new int[n];
		for(int i=0;i<n;i++)
		{
			nums[i] = in.nextInt();
		}
		FullSort a = new FullSort();
		List<List<Integer>> temp = a.permute(nums);
		System.out.print(temp.size()+"\n");
		System.out.print(temp.get(0).size()+"\n");
		for(int i=0;i<temp.size();i++)
		{
			for(int j=0;j<temp.get(i).size();j++)
			{
				System.out.print(temp.get(i).get(j) + " ");
				
			}
			System.out.print('\n');
		}
	}
}
