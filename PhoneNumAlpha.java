import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneNumAlpha {
	public List<String> letterCombinations(String digits) {
		List<String> digit = new ArrayList<String>();
		digit.add("abc");
		digit.add("def");
		digit.add("ghi");
		digit.add("jkl");
		digit.add("mno");
		digit.add("pqrs");
		digit.add("tuv");
		digit.add("wxyz");
		List<String> s = new ArrayList<String>();
		String letter = new String();
		if(!digits.isEmpty())
			this.produceLetter(digits, letter, digit, s);
        return s;
    }
	public void produceLetter(String digits,String letter, List<String>digit, List<String>s)
	{
		if(digits.isEmpty())
		{
			s.add(letter);
			return;
		}
		else
		{
			int n = digits.charAt(0) - '2';
			digits = digits.substring(1, digits.length());
			for(int i=0;i<digit.get(n).length();i++)
			{
				this.produceLetter(digits, letter + digit.get(n).charAt(i), digit, s);
			}
		}
	}
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		String s = new String();
		s = in.next();
		PhoneNumAlpha a = new PhoneNumAlpha();
		List<String> temp = a.letterCombinations(s);
		for(int i=0;i<temp.size();i++)
		{
			System.out.print(temp.get(i)+", ");
		}
	}

}
