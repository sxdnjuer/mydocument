package TreeProblem;

import java.util.Arrays;
import java.util.Scanner;

public class UniqueBinarySearchTree {
	int count = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int numbers[] = new int[n];
		int i = 0;
		for (; i < n; i++) {
			int temp = scan.nextInt();
//			System.out.println(temp);
			numbers[i] = temp;
		}
//		System.out.println("i= " + i);
		UniqueBinarySearchTree ubst = new UniqueBinarySearchTree();
		ubst.binarysearch(numbers, 0, n);
//		System.out.println("count=  " + ubst.count);
	}

	public void binarysearch(int num[], int start, int n) {
		if (start == n) {
			boolean result = isbinarysearchtree(num, n);
			
			if (result == true) {
				System.out.println(Arrays.toString(num));
				count++;
			}
			return;
		}

		for (int i = start; i < n; i++) {
			swap(num, i, start);
			binarysearch(num, start + 1, n);
			swap(num, i, start);
		}
	}

	public void swap(int num[], int a, int b) {
		int temp = num[a];
		num[a] = num[b];
		num[b] = temp;
	}

	public  boolean isbinarysearchtree(int[] result, int n) {
		if (result == null || n == 0) {
			return false;
		}
		int root = result[n - 1];
		int i;
		for (i = 0; i < n-1; i++) {
			if (result[i] > root) {
				break;
			}
		}
		int j = i;
		for (; j < n-1; j++) {
			if (result[j] < root) {
				return false;
			}
		}
		boolean left = true;
//		System.out.println("i= "+i);
		if (i > 0) {
			left = isbinarysearchtree(result, i);
		}
		boolean right = true;
		if (i < n - 1) {
			right = isbinarysearchtree(Arrays.copyOfRange(result, i, n-1), n -i- 1);
		}
//		System.out.println(left&&right);
		return left&&right;
	}

}
