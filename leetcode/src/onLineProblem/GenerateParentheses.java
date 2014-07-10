package onLineProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GenerateParentheses {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n =scan.nextInt();
		GenerateParentheses gp = new GenerateParentheses();
		System.out.println(Arrays.asList(gp.generateParenthesis(n)));
	}

	public ArrayList<String> generateParenthesis(int n) {
		ArrayList<String> alist = new ArrayList<String>();
		StringBuffer temp = new StringBuffer("");
		recursion(alist,0,0,n,temp);
		return alist;
	}

	public void recursion(ArrayList<String> alist, int i, int j, int n,
			StringBuffer temp) {
		if (i == n && j == n) {
			alist.add(temp + "");
			return;
		}
		if (i == n) {
			temp.append(")");
			recursion(alist, i, j + 1, n, temp);
			temp.deleteCharAt(temp.length() - 1);
		} else {
			if (i == j) {
				temp.append("(");
				recursion(alist, i + 1, j, n, temp);
				temp.deleteCharAt(temp.length() - 1);
			} else {
				for (int k = 0; k < 2; k++) {
					if (k == 0) {
						temp.append("(");
						recursion(alist, i + 1, j, n, temp);
						temp.deleteCharAt(temp.length() - 1);
					} else {
						temp.append(")");
						recursion(alist, i, j + 1, n, temp);
						temp.deleteCharAt(temp.length() - 1);
					}
				}
			}
		}
	}

}
