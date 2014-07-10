package onLineProblem;

import java.util.Scanner;

public class DecodeWays {
	static int count = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String s = scan.nextLine();
		DecodeWays dw = new DecodeWays();
		 dw.numDecoding(s);
		System.out.println(dw.numDecodings(s));
//		 System.out.println(count);
	}
//递归的做法
	public void numDecoding(String s) {
		if (s == null || s.length() == 0) {
			count++;
			return;
		}
		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				if (s.charAt(0) == '0') {
					break;
				} else {
					numDecoding(s.substring(1, s.length()));
				}
			}
			if (i == 1 && s.length() >= 2) {
				if (s.charAt(0) == '0' || s.charAt(0) >= '3'|| (s.charAt(0) == '2' && s.charAt(1) >= '7')) {
					break;
				} else {
					numDecoding(s.substring(2, s.length()));
				}
			}
		}
		return;
	}
//动态规划的做法
	public int numDecodings(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int[] temp = new int[s.length()];
		for(int i=0;i<s.length();i++){
			temp[i]=0;
		}
		if (check(s.charAt(0))==1)
			temp[0] = 1;
		else
			temp[0] = 0;
		if (s.length() == 1) {
			return temp[0];
		}
		
		temp[1] = (check(s.charAt(0))&check(s.charAt(1)))+check(s.charAt(0),s.charAt(1));
		for (int i = 2; i < s.length(); i++) {
			if(check(s.charAt(i))==1){
				temp[i] += temp[i-1];
			}
			if(check(s.charAt(i-1),s.charAt(i))==1){
				temp[i] += temp[i-2];
			}
		}
		for(int i = 0;i<s.length();i++){
			System.out.println(temp[i]);
		}
		return temp[s.length() - 1];
	}
	
	public int check(char c){
		return (c!='0')?1:0;
	}
	public int check(char c1 , char c2){
		return (c1=='1'||(c1=='2'&&c2<='6'))?1:0;
	}
	
}
