package FiveAlgorithm;

import java.util.Scanner;

//Dynamic Program
//最长公共子序列
public class LongestCommonSubsequence {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[] num1 = new int[n + 1];
		num1[0] = 0;
		for (int i = 1; i <= n; i++) {
			num1[i] = scan.nextInt();
		}
		int m = scan.nextInt();
		int[] num2 = new int[m + 1];
		num2[0] = 0;
		for (int i = 1; i <= m; i++) {
			num2[i] = scan.nextInt();
		}
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		lcs.print_lcs(lcs.lcs_length(num1, num2), num1, n, m);
	}

	public int[][] lcs_length(int[] num1, int[] num2) {
		int c[][] = new int[num1.length][num2.length];
		int b[][] = new int[num1.length][num2.length];
		for (int i = 0; i < num1.length; i++) {
			c[i][0] = 0;
		}
		for (int i = 0; i < num2.length; i++) {
			c[0][i] = 0;
		}
		for (int i = 1; i < num1.length; i++) {
			for (int j = 1; j < num2.length; j++) {
				if (num1[i] == num2[j]) {
					c[i][j] = c[i - 1][j - 1] + 1;
					b[i][j] = 2;
				} else if (c[i][j - 1] >= c[i - 1][j]) {
					c[i][j] = c[i][j - 1];
					b[i][j] = 1;
				} else {
					c[i][j] = c[i - 1][j];
					b[i][j] = 0;
				}
			}
		}
		return b;
	}

	public void print_lcs(int[][] b, int[] num, int n, int m) {
		if (n == 0 || m == 0)
			return;
		if (b[n][m] == 2) {
			print_lcs(b, num, n - 1, m - 1);
			System.out.println(num[n]);
		} else if (b[n][m] == 1) {
			print_lcs(b, num, n, m - 1);
		} else {
			print_lcs(b, num, n - 1, m);
		}
	}

}
