package onLineProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Palindrome_Partitioning {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Palindrome_Partitioning pp = new Palindrome_Partitioning();
		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		List<List<String>> list = pp.Initial(s);
		System.out.println(Arrays.asList(list));
	}

	public List<List<String>> Initial(String s) {
		List<List<String>> list = new ArrayList<List<String>>();
		ArrayList<String> arraylist = new ArrayList<String>();
		Partition(s, list, arraylist);
		return list;
	}

	public void Partition(String s, List<List<String>> list,
			ArrayList<String> arraylist) {
		if (s.length() == 0) {
			list.add(new ArrayList<String>(arraylist));
			return;
		}
		for (int i = 0; i < s.length(); i++) {
			if (isPalindrome(s.substring(0, i + 1))) {
				arraylist.add(s.substring(0, i + 1));
				Partition(s.substring(i + 1, s.length()), list, arraylist);
				if (!arraylist.isEmpty()) {
					arraylist.remove(arraylist.size() - 1);
				}
			}
		}

	}

	/*
	 * 判断是否为回文字符串
	 */
	public boolean isPalindrome(String s) {
		int i = 0;
		int j = s.length() - 1;
		while (i < j) {
			if (s.charAt(i) == s.charAt(j)) {
				i++;
				j--;
			} else {
				return false;
			}
		}
		return true;
	}

}
