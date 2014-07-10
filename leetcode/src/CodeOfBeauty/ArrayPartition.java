package CodeOfBeauty;

import java.util.Scanner;

public class ArrayPartition {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[] array = new int[2 * n+1];
		array[0] = 0;
		int sum = 0;
		for (int i = 1; i <= 2 * n; i++) {
			array[i] = scan.nextInt();
			sum = sum + array[i];
		}
		ArrayPartition ap = new ArrayPartition();
		System.out.println(ap.twonearest(array, sum , n));
	}

	public int twonearest(int[] array, int sum ,int n) {
		if (array == null || array.length == 0)
			return 0;
		boolean[][] isok = new boolean[n+1][sum / 2 + 1];
		isok[0][0] = true;
		int result = 0;
		for (int k = 1; k <= 2*n; k++) {
			for (int i = min(k, n); i >= 1; i--) {
				for (int v = 1; v <= sum / 2; v++) {
					if (v >= array[k] && isok[i - 1][v - array[k]]) {
						isok[i][v] = true;
						result = v;
					}
				}
			}
		}
		return result;
	}

	int min(int a, int b) {
		return (a > b) ? b : a;
	}

}
