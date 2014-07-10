package AimToOffer;

import java.util.Arrays;
import java.util.Scanner;

public class InversePairs {
	static int data[];

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int num[] = new int[n];
		data = new int[n];
		for (int i = 0; i < n; i++) {
			num[i] = scan.nextInt();
		}
		merge(num, 0, num.length-1);
		System.out.println(Arrays.toString(num));
	}

	public static void merge(int[] num, int start, int end) {
		if (start == end) {
			return;
		}
		int length = (end - start) / 2;
		merge(num, start, start + length);
		merge(num, start + length + 1, end);
		int i = start;
		int j = start + length + 1;
		int k = start;
		while (i <= start + length && j <= end) {
			if (num[i] < num[j]) {
				data[k] = num[i];
				k++;
				i++;
			} else {
				data[k] = num[j];
				k++;
				j++;
			}
		}
		while (i <= start + length) {
			data[k] = num[i];
			k++;
			i++;
		}
		while (j <= end) {
			data[k] = num[j];
			k++;
			j++;
		}
		for(int m=start;m<=end;m++){
			num[m] = data[m];
		}
		return;
	}
}
