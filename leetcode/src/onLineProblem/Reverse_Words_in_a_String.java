package onLineProblem;

import java.util.Scanner;

public class Reverse_Words_in_a_String {
	static String s1;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String s = scan.nextLine();
		Reverse_Words_in_a_String rw = new Reverse_Words_in_a_String();
//		System.out.println(rw.reverse(s));
		System.out.println(rw.reverseWords(s));
	}

	public String reverse(String s) {
		int start = s.length() - 1;
		int end = start;
		String result = "";
		while (start >= 0) {
			end = start;
			while (start >= 0 && s.charAt(start) != ' ') {
				start--;
			}
			result = result + s.substring(start + 1, end + 1);
			if (start >= 0) {
				result = result + " ";
				start--;
			}
		}
		return result;
	}
	
	public String reverseWords(String s) {
		if(s.length()==0||s==null)
			return "";
		int i = 0;
		for(;i<s.length();i++){
			if(s.charAt(i)!=' ')
				break;
		}
		if(i==s.length())return "";
		int j = 0;
		while(s.charAt(j)==' '){
			j++;
		}	
		s = s.substring(j,s.length());
		int k = s.length()-1;
		while(s.charAt(k)==' ')
		{
			k--;
		}
		s = s.substring(0,k+1);
		String s_rev = reverseword(s);
		int start = 0;
		int end = start;
		String result = "";
		while(end<s.length()){
			if(end==s.length()-1||s_rev.charAt(end)==' '){
				if(end==s.length()-1)
					result = result+reverseword(s_rev.substring(start, end+1));
				else 
					result = result+reverseword(s_rev.substring(start, end))+" ";
				start = end+1;
			}
			while(start<s.length()&&s_rev.charAt(start)==' '){
				start++;
				end++;
			}
			end++;
		}
		return result;
		
	}
	
	public String reverseword(String s){
		StringBuffer str = new StringBuffer(s);
		str.reverse();
		return new String(str);
	}
	

}
