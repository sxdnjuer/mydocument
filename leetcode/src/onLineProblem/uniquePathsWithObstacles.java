package onLineProblem;

import java.util.Scanner;

public class uniquePathsWithObstacles {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int m = scan.nextInt();
		int n = scan.nextInt();
		int p = scan.nextInt();
		int q = scan.nextInt();
		uniquePathsWithObstacles up = new uniquePathsWithObstacles();
		System.out.println(up.uniquePathsObstacles(m, n , p ,q));
		System.out.println(up.uniquePathsObstacles_dp(m, n , p ,q));
	}
	
	public int uniquePathsObstacles(int m, int n , int p , int q) {
		if (m == 0 || n == 0)
			return 0;
		if (m == 1 && n == 1) {
			return 1;
		}
		if( m == p&&n == q){
			return 0;
		}
		if (m == 1) {
			return uniquePathsObstacles(m, n - 1 , p,q);
		} else if (n == 1) {
			return uniquePathsObstacles(m - 1, n , p ,q);
		} else
			return uniquePathsObstacles(m - 1, n, p ,q) + uniquePathsObstacles(m, n - 1, p ,q);
	}
	//存在1个障碍
	public int uniquePathsObstacles_dp(int m, int n , int p , int q) {
		if (m == 0 || n == 0)
			return 0;
		int[][] temp = new int[m + 1][n + 1];
		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if(i==p&&j==q){
					temp[i][j] = 0;
					continue;
				}
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
	//存在多个障碍
	// [0,0,0],
	// [0,1,0],
	// [1,0,0]
	 public int uniquePathsWithObstacles_dp(int[][] obstacleGrid) {
	        int m = obstacleGrid.length;
	        int n = obstacleGrid[0].length;
	        if (m == 0 || n == 0)
				return 0;
			int[][] temp = new int[m + 1][n + 1];
			for (int i = 1; i < m + 1; i++) {
				for (int j = 1; j < n + 1; j++) {
				    if(obstacleGrid[i-1][j-1]==1){
						temp[i][j] = 0;
						continue;
					}
					if(i!=1&&j!=1){
						temp[i][j] = temp[i-1][j]+temp[i][j-1];
					}
					else if(i!=1){
					    temp[i][j] = temp[i-1][j];
					}
					else if(j!=1){
					    temp[i][j] = temp[i][j-1];
					}
	    			else{		    
	    			    temp[i][j] = 1;
					}
				}
			}
			return temp[m][n];
	    }
	
}
