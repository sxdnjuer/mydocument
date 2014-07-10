package AimToOffer;

import java.util.Scanner;

public class FindNumberAppearOnce {

	static int num1 = 0;
	static int num2 = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[] num = new int[n];
		for (int i = 0; i < n; i++) {
			num[i] = scan.nextInt();
		}
		findnumber(num);
		System.out.println(num1+" "+num2);
	}

	public static void findnumber(int[] num) {
		if(num.length<2||num==null)
			return;
		int temp = num[0];
		for (int i = 1; i < num.length; i++) {
			temp = temp ^ num[i];
		}
//		System.out.println("temp="+temp);
		int n = findfirstbit1(temp);
//		System.out.println("n="+n);
		for (int i = 0; i < num.length; i++) {
			if (((num[i] >> n) & 1) == 0) {
				num1 = num1 ^ num[i];
			} else {
				num2 = num2 ^ num[i];
			}
		}
	}

	public static int findfirstbit1(int temp) {
		int n = 0;
		while (((temp & 1) == 0) && n < Integer.SIZE) {
			temp = temp >> 1;
			n++;
		}
		return n;
	}
}
