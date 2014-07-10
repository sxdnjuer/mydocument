package AimToOffer;

import java.util.Scanner;

public class ReverseString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine();
		StringBuffer buffer = new StringBuffer(line);
		Reverse(buffer, 0, buffer.length() - 1);
		int start = 0;
		int end = 0;
		while (end < buffer.length()) {
			if (buffer.charAt(start) == ' ') {
				start++;
				end++;
			} else if (buffer.charAt(end) == ' ') {
				Reverse(buffer, start, end - 1);
				end++;
				start = end;
			} else {
				end++;
			}
		}
		String line2 = scan.nextLine();
		int n = scan.nextInt();
		StringBuffer buffer2 = new StringBuffer(line2);
		Reverse(buffer2 , 0 , n-1);
		Reverse(buffer2 , n , buffer2.length()-1);
		Reverse(buffer2 , 0 , buffer2.length()-1);
		System.out.println(buffer);
		System.out.println(buffer2);
	}

	public static void Reverse(StringBuffer str, int start, int end) {
		int i = start;
		int j = end;
		while (i < j) {
			char temp = str.charAt(i);
			str.setCharAt(i, str.charAt(j));
			str.setCharAt(j, temp);
			i++;
			j--;
		}
	}

}
