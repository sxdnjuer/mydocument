package kupai;

import java.util.Scanner;

public class Monkey {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int m = scan.nextInt();
		double result = Math.pow(2, m-1)+Math.pow(2, m);
		System.out.println((int)(result-2));
	}

}
