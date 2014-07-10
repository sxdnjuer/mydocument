package AimToOffer;

import java.util.Scanner;

public class GetNumberOfK {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int number = scan.nextInt();
		int num[] = new int[n];
		for (int i = 0; i < n; i++) {
			num[i] = scan.nextInt();
		}
		int first = GetFirstnumber(num, number, 0, n - 1);
		int last = GetLastnumber(num, number, 0, n - 1);
		System.out.println("first" + first);
		System.out.println("last" + last);
		if (first == -1 || last == -1) {
			System.out.println("the number of " + number + " is 0");
		} else {
			System.out.println("the number of " + number + " is"
					+ (last - first + 1));
		}
	}

	public static int GetFirstnumber(int[] num, int number, int start, int end) {
		if (start > end)
			return -1;
		int middle = (end + start) / 2;
		int result = 0;
		if (num[middle] == number) {
			if ((middle > start && num[middle - 1] != number)
					|| middle == start)
				return middle;
			else
				result = GetFirstnumber(num, number, start, middle - 1);
		} else if (num[middle] > number) {
			result = GetFirstnumber(num, number, start, middle - 1);
		} else {
			result = GetFirstnumber(num, number, middle + 1, end);
		}
		return result;

	}

	public static int GetLastnumber(int[] num, int number, int start, int end) {
		if (start > end)
			return -1;
		int middle = (end + start) / 2;
//		System.out.println("middle"+middle);
		int result = 0;
		if (num[middle] == number) {
			if ((middle < end && num[middle + 1] != number) || middle == end) {
//				System.out.println(middle);
				return middle;
			} else
//				System.out.println(middle);
				result = GetLastnumber(num, number, middle + 1, end);
		} else if (num[middle] > number) {
			result = GetLastnumber(num, number, start, middle - 1);
		} else {
			result = GetLastnumber(num, number, middle + 1, end);
		}
		return result;
	}

}
