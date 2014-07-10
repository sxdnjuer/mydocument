package AimToOffer;

import java.util.Scanner;

public class LeftDownToRightUp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int m = scan.nextInt();
		int n = scan.nextInt();
		System.out.println(LeftToRight(m, n));
	}

	public static int LeftToRight(int m, int n) {
		if (m == 0 && n == 0)
			return 0;
		if ((m == 1 && n == 0) || (m == 0 && n == 1))
			return 1;
		if (m == 1 && n == 1)
			return 2;
		if (m == 2 && n == 0) {//中间某一点不能经过的情况
			return 0;
		}
		if (m > 0 && n > 0) {
			return LeftToRight(m - 1, n) + LeftToRight(m, n - 1);
		} else if (m > 0 && n == 0) {
			return LeftToRight(m - 1, n);
		} else {
			return LeftToRight(m, n - 1);
		}
	}

}
