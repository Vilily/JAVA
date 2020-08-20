/*
 ʵ�ֻ�ȡ��һ�����еĺ������㷨��Ҫ���������������������г��ֵ�������һ����������С�

�����������һ����������У��������������г���С�����У����������У���

����ԭ���޸ģ�ֻ����ʹ�ö��ⳣ���ռ䡣

������һЩ���ӣ�����λ������У�����Ӧ���λ���Ҳ��С�
1,2,3 �� 1,3,2
3,2,1 �� 1,2,3
1,1,5 �� 1,5,1
*/

import java.util.Scanner;

public class NextPermutation {
	public void nextPermutation(int[] nums) {
		boolean is_find;
		is_find = false;
        for(int i=nums.length-1;i>0;i--)
        {
        	for(int j=i-1;j>=0;j--)
        	{
        		if(nums[i] > nums[j])
        		{
        			is_find = true;
        			int temp = nums[i];
        			nums[i] = nums[j];
        			nums[j] = temp;
        			for(int sorti = j + 1;sorti < nums.length - 1;sorti++)
        			{
        				for(int sortj = sorti + 1;sortj < nums.length;sortj++)
        				{
        					if(nums[sorti] > nums[sortj])
        					{
        						int t = nums[sorti];
        						nums[sorti] = nums[sortj];
        						nums[sortj] = t;
        					}
        				}
        			}
        			break;
        		}
        	}
        	if(is_find)
        		break;
        }
        if(!is_find)
        {
        	for(int i=0;i<nums.length/2;i++)
        	{
        		int temp = nums[i];
        		nums[i] = nums[nums.length - i - 1];
        		nums[nums.length - i - 1] = temp;
        	}
        }
    }
	
	public static void main(String[] args)
	{
		int n;
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		int [] nums = new int[n];
		for(int i=0;i<n;i++)
			nums[i] = in.nextInt();
		NextPermutation a = new NextPermutation();
		a.nextPermutation(nums);
		for(int i=0;i<n;i++)
			System.out.print(nums[i] + " ");
		
	}

}
