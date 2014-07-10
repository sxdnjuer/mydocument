package onLineProblem;

import java.util.Scanner;

public class ClimbingStairs {
	static int result = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		ClimbingStairs cs = new ClimbingStairs();
		cs.count(n, 0);
		System.out.println(cs.climbStairs(n));
		System.out.println(result);
	}
	//全排列方法求解
	public void count(int n, int total) {
		if (n == 0 || total > n) {
			return;
		}
		if (total == n) {
			result++;
			return;
		} else {
			int temp = total;
			for (int i = 1; i <= 2; i++) {
				total = temp + i;
				count(n, total);
			}
		}
	}
	
	//动态规划方法求解
	 int climbStairs(int n) {  
	        // Start typing your C/C++ solution below  
	        // DO NOT write int main() function  
	          
	        if(n <= 2)  
	        {  
	            return n;  
	        }  
	        else  
	        {  
	            int[] step = new int[n];  
	              
	            step[0] = 1;  
	            step[1] = 2;  
	              
	            for(int i = 2; i < n; i++)  
	            {  
	                step[i] = step[i-1] + step[i-2];  
	            }  
	            return step[n-1];  
	        }     
	    }  
}
