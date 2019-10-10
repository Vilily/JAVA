//给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列。
//
//一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
//例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
//
//若这两个字符串没有公共子序列，则返回 0。
//
// 
//
//示例 1:
//
//输入：text1 = "abcde", text2 = "ace" 
//输出：3  
//解释：最长公共子序列是 "ace"，它的长度为 3。
//示例 2:
//
//输入：text1 = "abc", text2 = "abc"
//输出：3
//解释：最长公共子序列是 "abc"，它的长度为 3。
//示例 3:
//
//输入：text1 = "abc", text2 = "def"
//输出：0
//解释：两个字符串没有公共子序列，返回 0。


import java.util.Scanner;

public class LongestPublicSubString {
	int [][] number = new int[1000][1000];
	public int longestCommonSubsequence(String text1, String text2) {
        for(int i = 0; i <= text1.length(); i++)
        	for(int j=0;j <= text2.length();j++)
        		this.number[i][j]=-1;
		return subSequence(text1, text2, text1.length(), text2.length());
    }
	
	public int subSequence(String text1, String text2, int n, int m)
	{
		if(n==0 || m==0)
			return 0;
		if(text1.charAt(n - 1) == text2.charAt(m - 1))
		{
			if(number[n-1][m-1]!=-1)
			{
				number[n][m] = number[n-1][m-1] + 1;
				return number[n][m];
			}
			else
			{
				number[n-1][m-1] = subSequence(text1, text2, n-1, m-1);
				number[n][m] = number[n-1][m-1] + 1;
				return number[n][m];
			}
		}
		else
		{
			int first,second;
			if(this.number[n-1][m]!=-1)
			{
				first = this.number[n-1][m];
			}
			else
			{
				first = subSequence(text1, text2, n-1, m);
				number[n-1][m] = first;
			}
			
			if(this.number[n][m-1]!=-1)
			{
				second = this.number[n][m-1];
			}
			else
			{
				second = subSequence(text1, text2, n, m-1);
				number[n][m-1] = second;
			}
			
			number[n][m] = Math.max(first, second);
			return number[n][m];
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text1 = new String();
		String text2 = new String();
		LongestPublicSubString a = new LongestPublicSubString();
		Scanner in = new Scanner(System.in);
		text1 = in.next();
		text2 = in.next();
		System.out.println(a.longestCommonSubsequence(text1, text2));
	}
}

//accepted
