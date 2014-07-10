package TestProblems;

import java.util.Scanner;

public class MS_Rectangle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		long[] result = new long[T];
//		int flag = 0;
		for (int i = 0; i < T; i++) {
			int n = scan.nextInt();
			int m = scan.nextInt();
			int k = scan.nextInt();
			if (n > m) {
				int t = n;
				n = m;
				m = t;
			}
			long max = 0;
			int maxa = (int) (Math.sqrt((double) k));
			if (maxa > n)
				maxa = n;
			for (int a = 2; a <= maxa; a++) {
				int r = k % a;
				int b = (k - r) / a;
				if (b > m)
					continue;
				long cnt = calRects(a, b);
				if (r >= 2) {
					// 添加剩余的 r 个石子为新一列
					int t = b;
					if (b == m || (a < n && a > b && r < b)) {
						// 需要将石子添加为新一行的情况
						t = a;
					}
					cnt += calExtRects(t, r);
				}
				if (cnt > max)
					max = cnt;
			}
		}
		// long result1 = calculate(m, k);
		// long result2 = calculate(n, k);
		// if (result1 > result2) {
		// result[i] = result1;
		// } else {
		// result[i] = result2;
		// }
		// int sqrt = (int) Math.sqrt(k);
		// long temp = 0;
		// int lon = sqrt;
		// int wide = sqrt;
		// if (sqrt < m && sqrt < n) {
		// for (wide = sqrt; wide >2&&flag==0; wide--) {
		// for (lon = sqrt; lon < k&&flag==0; lon++) {
		// if (wide * lon == k && (lon < m || lon < n)) {
		// flag = 1;
		// temp = calculate(wide, k);
		// if (temp > result[i])
		// result[i] = temp;
		// break;
		// }
		// }
		// }
		// for (int q = sqrt + 1; q>0&&k/q < Math.max(m, n); q--) {
		// temp = calculate(q, k);
		// if (temp > result[i])
		// result[i] = temp;
		// }
		// } else {
		// for (wide = Math.min(m, n); wide > 0&&flag==0; wide--) {
		// for (lon = Math.min(m, n); lon < k&&flag==0; lon++) {
		// if (wide * lon == k && (lon < m || lon < n)) {
		// flag = 1;
		// temp = calculate(wide, k);
		// if (temp > result[i])
		// result[i] = temp;
		// }
		// }
		// }
		// for (int q = Math.min(m, n); q>0&&k/q < Math.max(m, n); q--) {
		// temp = calculate(q, k);
		// if (temp > result[i])
		// result[i] = temp;
		// }
		// }
		// }
		for (int i = 0; i < T; i++) {
			int temp = i + 1;
			System.out.println("Case #" + temp + ": " + result[i]);
		}
	}

	public static boolean ifFullSquare(int points) {
		double n = Math.sqrt(points);
		int k = (int) n;
		if (n - k == 0)
			return true;
		else
			return false;
	}

	// public static long calculate(int length, int points) {
	// long result = 0;
	// if (points > 2 * length) {
	// int temp1 = (points - 2 * length) / length + 1;
	// int temp2 = points % length - 1;
	// result = length * (length - 1) / 2 * temp1 * (temp1 + 1) / 2
	// + (temp1 + 1) * temp2 * (temp2 + 1) / 2;
	// } else if (points >= 4) {
	// int temp = points / 2 - 1;
	// result = temp * (temp + 1) / 2;
	// } else {
	// result = 0;
	// }
	// return result;
	// }
	// 计算 a*b 的石子中包含的长方形数
	static long calRects(long a, long b) {
		return a * b * (a - 1) * (b - 1) / 4;
	}

	// 计算剩余的 r 个石子可以包含的长方形个数
	static long calExtRects(long t, long r) {
		return t * r * (r - 1) / 2;
	}
}
