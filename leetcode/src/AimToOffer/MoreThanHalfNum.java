package AimToOffer;

import java.util.Scanner;

public class MoreThanHalfNum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[] num = new int[n];
		int i = 0;
		while (i < n) {
			num[i] = scan.nextInt();
			i++;
		}

		System.out.println(Morethanhalf(num));
	}

	public static int Morethanhalf(int[] numbers) {
		if (numbers.length == 0 || numbers == null)
			return 0;
		int result = 0;
		int length = numbers.length;
		int start = 0;
		int middle = (length - 1) >> 1;
		int end = length - 1;
		int index = Partition(numbers, start, end);

		while (index != middle) {
			if (index < middle) {
				start = index + 1;
				index = Partition(numbers, start, end);
			} else {
				end = index - 1;
				index = Partition(numbers, start, end);// 9 1 2 3 2 2 2 5 4 2
			}
		}
		result = numbers[middle];
		if (!ChechMoreThanHalf(numbers, result)) {
			result = 0;
		}
		return result;
	}

	public static boolean ChechMoreThanHalf(int[] numbers, int result) {
		// TODO Auto-generated method stub
		int times = 0;
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] == result)
				times++;
		}
		if (times > numbers.length / 2)
			return true;
		return false;
	}

	public static int Partition(int[] numbers, int start, int end) {
		int i = start;
		int j = end - 1;
		int temp = 0;
		while (i <= j) {
			while (i < end && numbers[i] <= numbers[end]) {
				i++;
			}
			System.out.println("i= " + i);
			while (j >= start && numbers[j] >= numbers[end]) {
				j--;
			}
			System.out.println("j= " + j);
			if (i < j) {
				temp = numbers[i];
				numbers[i] = numbers[j];
				numbers[j] = temp;
				i++;
				j--;
			}
		}
		temp = numbers[i];
		numbers[i] = numbers[end];
		numbers[end] = temp;
		return i;
	}
	
	
	
}
