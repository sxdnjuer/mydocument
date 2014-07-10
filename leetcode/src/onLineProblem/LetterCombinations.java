package onLineProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LetterCombinations {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String digital = scan.nextLine();
		LetterCombinations lc = new LetterCombinations();
		ArrayList<String> allcombine = new ArrayList<String>();
		allcombine = lc.letterCombinations(digital);
		System.out.println(Arrays.asList(allcombine));
	}

	public ArrayList<String> letterCombinations(String digits) {
		ArrayList<String> alist = new ArrayList<String>();
		StringBuilder temp = new StringBuilder("");
		recursion(alist, digits, 0, temp);
		return alist;
	}

	public void recursion(ArrayList<String> alist, String digits, int n,
			StringBuilder temp) {
		String[] data = { " ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs",
				"tuv", "wxyz" };	
		if (n == digits.length()) {
			alist.add(temp + "");
			return;
		}
		int loc = digits.charAt(n)-'0';
		for (int i = 0; i < data[loc].length(); i++) {
			temp.append(data[loc].charAt(i));
			recursion(alist, digits, n + 1, temp);
			temp.deleteCharAt(temp.length()-1);
		}
		return;
	}

}
