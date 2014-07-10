package onLineProblem;

import java.util.Scanner;

public class MeidanSortArray {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("请输入两个数组");
		Scanner scan = new Scanner(System.in);
		String LineA = scan.nextLine();
		String LineB = scan.nextLine();
		String[] InputA = LineA.split(" ");
		String[] InputB = LineB.split(" ");
		int[] A = new int[InputA.length];
		int[] B = new int[InputB.length];
		for (int i = 0; i < InputA.length; i++) {
			A[i] = Integer.parseInt(InputA[i]);
			// System.out.print(" "+num[i]);
		}
		for (int i = 0; i < InputB.length; i++) {
			B[i] = Integer.parseInt(InputB[i]);
			// System.out.print(" "+num[i]);
		}
		MeidanSortArray msa = new MeidanSortArray();
		double result = msa.findMedianSortedArrays(A, B);
		System.out.println("result = " + result);
	}

	public double findMedianSortedArrays(int A[], int B[]) {
		double result = 0.0;
		int m = A.length;
		int n = B.length;
		int startA = -1;
		int startB = -1;
		int end = 0;
		if(m == 0&&n==0){
			return -1 ;
		}
		if (m == 0) {
			if (n % 2 != 0)
				return (double) B[n / 2];
			else
				return (double) (B[n / 2 -1] + B[n / 2]) / 2;
		}
		if(n==0){
			if (m % 2 != 0)
				return (double) A[m / 2];
			else
				return (double) (A[m / 2 -1] + A[m / 2]) / 2;
		}
		if ((m + n) % 2 == 0) {
			int[] temp = new int[2];
			while ((m + n - 1) / 2 > end) {
				if (A[startA + 1] < B[startB + 1]) {
					startA++;
					end++;
				} else if (A[startA + 1] == B[startB + 1]) {
					startA++;
					startB++;
					end = end + 2;
				} else {
					startB++;
					end++;
				}
			}
			if ((m + n - 1) / 2 == end) {
				for (int i = 0; i < 2; i++) {
					if (A[startA + 1] < B[startB + 1]) {//10000,10001数组产生越界问题！！！error
						startA++;
						temp[i] = A[startA];
					} else if (A[startA + 1] == B[startB + 1]) {
						startA++;
						startB++;
						if (i == 0) {
							temp[i] = A[startA];
							temp[i + 1] = B[startB];
							break;
						} else {
							temp[i] = A[startA];
						}
					} else {
						startB++;
						temp[i] = B[startB];
					}
				}
			} else {
				temp[0] = A[startA];
				if (A[startA++] >= B[startB++]) {
					temp[1] = B[startB];
				} else {
					temp[1] = A[startA];
				}
			}
			result = (double) (temp[0] + temp[1]) / 2;
		}

		else {
			while ((m + n) / 2 > end) {
				if (A[startA + 1] < B[startB + 1]) {
					startA++;
					end++;
				} else if (A[startA + 1] == B[startB + 1]) {
					startA++;
					startB++;
					end = end + 2;
				} else {
					startB++;
					end++;
				}
			}
			if (end > (m + n) / 2) {
				result = (double) A[startA];
			} else {
				if (A[startA + 1] < B[startB + 1]) {
					// startA++;
					result = (double) A[startA + 1];
				} else if (A[startA + 1] == B[startB + 1]) {
					result = (double) A[startA + 1];
				} else {
					result = (double) B[startB + 1];
				}
			}
		}
		return result;
	}
}
