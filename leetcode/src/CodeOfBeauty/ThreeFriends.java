package CodeOfBeauty;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class ThreeFriends {
	static long total = 0L;
	static int length = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		long results[] = new long[T];
		int times = 0;

		while (times < T) {
			int N = scan.nextInt();
			total = 0;
			length = N + 1;
			Stack<Integer> result = new Stack<Integer>();
			int numbers[] = new int[N];
			long temp = 0L;
			for (int i = 0; i < N; i++) {
				numbers[i] = i + 1;
				temp = temp+(i+1)*(i+1)*(i+1);
			}
			if (N == 1) {
				results[times] = 1;
			} else if (N == 2) {
				results[times] = 9;
			} else {
				Combination(numbers, 3, result);
				results[times] = (total * 6+temp) % (1000000007);
			}
			times++;
		}
		for (int i = 0; i < T; i++) {
			System.out.println("Case " + (i + 1) + ": " + results[i]);
		}
	}

	public static void Combination(int num[], int number, Stack<Integer> result) {
		if (number == 0) {
			total = total + Compute(result);
			return;
		}
		if (num.length == 0 || num == null) {
			return;
		}
		result.push(num[0]);
		Combination(Arrays.copyOfRange(num, 1, num.length), number - 1, result);
		result.pop();
		Combination(Arrays.copyOfRange(num, 1, num.length), number, result);

	}

	public static long Compute(Stack<Integer> result) {
		Iterator<Integer> it = result.iterator();
		long temp = 1L;
		while (it.hasNext()) {
			temp = temp * (length - it.next());
		}
		return temp;
	}
}
