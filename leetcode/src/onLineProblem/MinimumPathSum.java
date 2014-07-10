package onLineProblem;

import java.util.Scanner;

public class MinimumPathSum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int m = scan.nextInt();
		int n = scan.nextInt();
		int[][] grid = new int[m][n];
		for(int i = 0;i<m;i++){
			for(int j = 0;j<n;j++){
				grid[i][j] = scan.nextInt();
			}
		}
		MinimumPathSum mps = new MinimumPathSum();
		System.out.println(mps.minPathSum(grid));
	}
	
	 public int minPathSum(int[][] grid) {
	     int m = grid.length;
	     int n = grid[0].length;
	     if(m==0||n==0)
	    	 return 0;
	     int temp[][] = new int[m+1][n+1];
	     for(int i = 1;i<m+1;i++){
	    	 for(int j =1;j<n+1;j++){
	    		 if(i!=1&&j!=1)
	    			 temp[i][j] = min(grid[i-1][j-1]+temp[i-1][j],grid[i-1][j-1]+temp[i][j-1]);
	    		 else if(i!=1){
	    			 temp[i][j] = grid[i-1][j-1]+temp[i-1][j];
	    		 }
	    		 else if(j!=1){
	    			 temp[i][j] = grid[i-1][j-1]+temp[i][j-1];
	    		 }
	    		 else 
	    			 temp[i][j] = grid[i-1][j-1];
	    	 }
	     }
	     return temp[m][n];
	 }
	 
	 public int min(int a, int b){
		 return (a<b)?a:b;
	 }

}
