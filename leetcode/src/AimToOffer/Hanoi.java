package AimToOffer;

import java.util.Scanner;

public class Hanoi {

	/**
	 * @param args
	 */
	static String from = "diskA";
	static String mid = "diskB";
	static String to = "diskC";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		move(n, from, mid, to);
	}

	public static void move(int num, String from, String mid, String to) {
		if (num == 1) {
			System.out.println("to="+to);
			System.out.println("move disk1 from " + from + " to " + to);
		} else {
			move(num - 1, from, to, mid);
			System.out.println("move disk" + num + " from " + from + " to "
					+ to);
			move(num - 1, mid, from, to);
		}
		return;
	}

}
