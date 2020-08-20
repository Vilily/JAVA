import java.util.ArrayList;
import java.util.Scanner;

/*
给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

示例 1:

输入: num1 = "2", num2 = "3"
输出: "6"
示例 2:

输入: num1 = "123", num2 = "456"
输出: "56088"
说明：

num1 和 num2 的长度小于110。
num1 和 num2 只包含数字 0-9。
num1 和 num2 均不以零开头，除非是数字 0 本身。
不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/multiply-strings
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class StringMultiply {
	public String multiply(String num1, String num2) {
		ArrayList n1 = new ArrayList();
		ArrayList n2 = new ArrayList();
		for(int i=num1.length()-1;i>=0;i--)
			n1.add(num1.charAt(i) - '0');
		for(int i=num2.length()-1;i>=0;i--)
			n2.add(num2.charAt(i) - '0');
		add(n1, n2);
		String s=n1.toString();
		return s;
        
    }
	public ArrayList<Integer> add(ArrayList<Integer> num1, ArrayList<Integer> num2) {
		if(num2.size()>0)
		{
			if(num1.size()>0)
			{
				num1.set(0, num1.get(0)+num2.get(0));
			}
			else 
			{
				num1.add(num2.get(0));
			}
		}
		
		for(int i=1;i<num2.size();i++)
		{
			if(num1.size() > i)
				num1.set(i, num1.get(i) + num2.get(i) + (num1.get(i-1)/10));
			else
			{
				num1.add(num1.get(i) + num2.get(i) + (num1.get(i-1)/10));
			}
		}
		int i = num2.size()-1;
		while(num1.get(i) > 9)
		{
			if(num1.size() > ++i)
			{
				num1.set(i, num1.get(i-1)/10 + num1.get(i));
			}
			else 
			{
				num1.add(num1.get(i-1)/10);
			}
		}
        return num1;
    }
	
	public static void main(String[] args)
	{
		String num1,num2;
		Scanner in = new Scanner(System.in);
		num1 = in.next();
		num2 = in.next();
		StringMultiply a = new StringMultiply();
		System.out.println(a.multiply(num1, num2));
	}

}
