package onLineProblem;

import java.util.Scanner;

public class Reverse_Integer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan =new Scanner(System.in);
		int number = scan.nextInt();
		int result = new Reverse_Integer().reverseint(number);
		System.out.println("the reverse result is "+result);
	}
	private int reverseint(int number){
		int result = 0 , temp = 0;
		if(number == 0)
			return result;
		if(number<0)
			temp = -number;
		else
			temp = number;
		while(temp>=10){
			result=(result+temp%10)*10;
			temp = temp/10;
		}
		result = result+temp;
		if(number<0)
			result = -result;
		return result;
	}

}
