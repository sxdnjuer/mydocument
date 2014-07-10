package onLineProblem;

import java.util.Scanner;

public class UniquePaths {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int m = scan.nextInt();
		int n = scan.nextInt();
		UniquePaths up = new UniquePaths();
		System.out.println(up.uniquePaths(m, n));
		System.out.println(up.uniquePaths_dp(m, n));
	}

	public int uniquePaths(int m, int n) {
		if (m == 0 || n == 0)
			return 0;
		if (m == 1 && n == 1) {
			return 1;
		}
		if (m == 1) {
			return uniquePaths(m, n - 1);
		} else if (n == 1) {
			return uniquePaths(m - 1, n);
		} else
			return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
	}

	public int uniquePaths_dp(int m, int n) {
		if (m == 0 || n == 0)
			return 0;
		int[][] temp = new int[m + 1][n + 1];
		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if(i!=1&&j!=1){
					temp[i][j] = temp[i-1][j]+temp[i][j-1];
				}
				else{
					temp[i][j] = 1;
				}
			}
		}
		return temp[m][n];
	}

}
