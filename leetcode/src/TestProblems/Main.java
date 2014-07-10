package TestProblems;

import java.util.Scanner;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long dt = System.currentTimeMillis();
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		float temp = 0;
		float result = Integer.MAX_VALUE;

		int flag = 0;
		int i = 1;

		while (i <= n) {
			int first = scan.nextInt();
			String op = scan.next();
			int second = scan.nextInt();
			temp = calculate(first, second, op);
			if(temp<0)
				temp = -temp;
			if (temp<result) {
				result = temp;
				flag = i;
			}
			i++;
		}
		long dt1 = System.currentTimeMillis();
		System.out.println(dt1 - dt);
		System.out.println(flag);

	}

	public static float calculate(int first, int second, String op) {
		float temp = 0;
		if (op == "/") {
			temp = (float) first / second ;
		} else if (op == "+") {
			temp =  first + second;
		} else if (op == "-") {
			temp =  first - second;
		} else {
			temp =  first * second;
		}
		return temp-9;
	}

}
