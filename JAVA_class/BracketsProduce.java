import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

//给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
//
//例如，给出 n = 3，生成结果为：
//
//[
//  "((()))",
//  "(()())",
//  "(())()",
//  "()(())",
//  "()()()"
//]

public class BracketsProduce {
	public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        String s = new String();
        backpath(n, 0, ans, s);
        return ans;
    }
	
	public void backpath(int n, int m, List<String> ans, String s)
	{
		if(n==0 && m==0)
		{
			ans.add(s);
			return;
		}
		
		if(n>0)
		{
			backpath(n - 1, m + 1, ans, s + "(");
		}
		if(m>0)
		{
			backpath(n, m - 1, ans, s + ")");
		}
	}
	
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		BracketsProduce a = new BracketsProduce();
		List<String> ans = a.generateParenthesis(n);
		for(int i = 0; i < ans.size(); i++)
		{
			System.out.println(ans.get(i));
		}
		in.close();
	}
}
