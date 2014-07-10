package onLineProblem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class NQueens {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		NQueens nq = new NQueens();
//		ArrayList<String[]> alist = nq.solveNQueens(n);
//		Iterator<String[]> it = alist.iterator();
//		while(it.hasNext()){
//			String[] str = it.next();
//			for(int i = 0;i<str.length;i++){
//				System.out.println(str[i]);
//			}
//		}
		System.out.println(nq.solveNQueens(n));
	}

	public ArrayList<String[]> solveNQueens(int n) {
		ArrayList<String[]> alist = new ArrayList<String[]>();
		char[] str = new char[n];
		for(int i = 0;i<n;i++){
			str[i] =(char)('0'+i);
		}
		permutation(alist, str , 0);
		return alist;
	}

	public void permutation(ArrayList<String[]> alist,char[] str, int start) {
		if (str.length == 0)
			return;
		if (str.length == start) {
			if (isQueen(str)) {
				String[] temp = new String[str.length];
				for (int i = 0; i < str.length; i++) {
					temp[i] = "";
					for (int j = 0; j < str.length; j++) {
						if (j == str[i] - '0') {
							temp[i] = temp[i] + "Q";
						} else {
							temp[i] = temp[i] + ".";
						}
					}
				}
				alist.add(temp);
			}
			return;
		}

		for (int i = start; i < str.length; i++) {
			char str1 = str[i];
			str[i] = str[start];
			str[start] = str1;
			permutation(alist, str, start + 1);
			char str2 = str[i];
			str[i] = str[start];
			str[start] = str2;
		}

	}

	public boolean isQueen(char[] str) {
		boolean isqueen = true;
		for (int i = 0; i < str.length; i++) {
			for (int j = i+1; j < str.length; j++) {
				int dis = str[j] - str[i];
				if ((j - i) == Math.abs(dis)) {
					isqueen = false;
					break;
				}
			}
		}
		return isqueen;
	}

}
