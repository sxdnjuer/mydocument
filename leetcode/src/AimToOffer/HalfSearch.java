package AimToOffer;

import java.util.Scanner;

public class HalfSearch {
	// static int result = 0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("请输入数组和目标数字");
		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine();
		int target = scan.nextInt();
		String[] input = line.split(" ");
		int length = input.length;
		System.out.println("length" + length);
		int[] num = new int[length + 1];
		for (int i = 0; i < length; i++) {
			num[i] = Integer.parseInt(input[i]);
			// System.out.print(" "+num[i]);
		}
		num[length] = 0;
		HalfSearch hs = new HalfSearch();
		int result = hs.search(num, 0, length - 1, target);
		System.out.println("result=" + result);
	}

	public int search(int[] num, int start, int end, int target) {
		if (num == null)
			return -1;
		int result = 0;
		System.out
				.println("num[start]=" + num[start] + " num[end]=" + num[end]);
		if (start == end && num[start] != target) {
			if (target > num[start]) {
				insert(num, start + 1, target);
			} else
				insert(num, start - 1, target);
			result = start + 1;
			return result;
		}
//		System.out.println("result=" + result);
		int middle = (start + end) / 2;
		if (num[middle] < target) {
			start = middle + 1;
			result = search(num, start, end, target);
		} else if (num[middle] > target) {
			end = middle - 1;
			result = search(num, start, end, target);
		} else {
			result = middle;
		}
		return result;
	}

	public void insert(int[] num, int location, int target) {
		for (int i = num.length - 2; i >= location; i--) {
			num[i + 1] = num[i];
		}
		num[location] = target;
		System.out.println("num[location]=" + num[location]);
	}

}
