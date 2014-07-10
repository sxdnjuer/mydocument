package onLineProblem;

import java.util.Scanner;
//Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
//For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
//the contiguous subarray [4,−1,2,1] has the largest sum = 6.
public class MaximumSubarray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan  = new Scanner(System.in);
		
	}

	public int maxSubArray(int[] A) {
		if (A == null || A.length == 0)
			return 0;
		int start = A[0];
		int all = A[0];
		for (int i = 1; i < A.length; i++) {
			start = max(A[i] , A[i]+start);
			all = max(start , all);
		}
		return all;
	}
	
	public int max(int a , int b){
		return (a>b)?a:b;
	}

}
