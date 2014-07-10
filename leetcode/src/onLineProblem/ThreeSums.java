package onLineProblem;

import java.util.ArrayList;
import java.util.Scanner;

public class ThreeSums {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine();
		String[] lines = line.split(" ");
		int length = lines.length;
		int[] number = new int[length];
		for (int i = 0; i < length; i++) {
			number[i] = Integer.parseInt(lines[i]);
		}
		ThreeSums ts = new ThreeSums();
		ts.partition(number, 0, length - 1);
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		list = ts.threeSum(number, 0);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.remove(i));
		}
	}

	public ArrayList<ArrayList<Integer>> threeSum(int[] numbers, int target) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		if (numbers == null || numbers.length == 0)
			return list;
		int length = numbers.length;
		int start;
		int end = length - 1;
		for (int i = 0; i < length - 2; i++) {
			target = -numbers[i];
			start = i + 1;
			end = length - 1;
			Integer[] res = new Integer[3];
			while (start != end) {
				if (numbers[start] + numbers[end] == target) {
					ArrayList<Integer> al = new ArrayList<Integer>();
					al.add(numbers[i]);
					al.add(numbers[start]);
					al.add(numbers[end]);
					list.add(al);
					end--;
				} else if (numbers[start] + numbers[end] > target) {
					end--;
				} else if (numbers[start] + numbers[end] < target) {
					start++;
				}
			}
		}
		return list;
	}

	// ¿ìËÙÅÅĞò
	public void partition(int[] num, int start, int end) {
		if (num == null)
			return;
		if (start == end) {
			return;
		}
		int first = start;
		int last = end - 1;
		int temp;
		while (first < last) {
			while (num[first] <= num[end] && first < end)
				first++;
			while (num[last] >= num[end] && last > start)
				last--;
			if (first < last) {
				temp = num[first];
				num[first] = num[last];
				num[last] = temp;
			}
		}
		if (num[first] > num[end]) {
			temp = num[end];
			num[end] = num[first];
			num[first] = temp;
		}
		if (first > start)
			partition(num, start, first - 1);
		if (first < end)
			partition(num, first + 1, end);
		return;
	}
}
