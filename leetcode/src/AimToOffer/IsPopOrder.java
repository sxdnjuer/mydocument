package AimToOffer;

import java.util.Scanner;
import java.util.Stack;

public class IsPopOrder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String pushorder = scan.nextLine();
		String poporder = scan.nextLine();
		IsPopOrder ipo = new IsPopOrder();
		boolean result = ipo.ispoporder(pushorder, poporder);
		System.out.println(result);
	}

	public boolean ispoporder(String pushorder, String poporder) {
		if (pushorder.length() != poporder.length())
			return false;
		if (pushorder == null || pushorder.length() == 0)
			return false;
		boolean result = false;
		Stack<Character> stack = new Stack<Character>();
		int n = 0;
		int i = 0;
		while (i != poporder.length()) {
			if (stack.size() == 0||stack.peek() != poporder.charAt(i)) {
				if(n==poporder.length()){
					break;
				}
				stack.push(pushorder.charAt(n));
				n++;
			} else {
				stack.pop();
				i++;
			}
		}
		if (stack.size() != 0) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}

}
