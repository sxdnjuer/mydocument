package onLineProblem;

import java.util.Scanner;

public class WildcardMatching {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		String p = scan.next();
		WildcardMatching wm = new WildcardMatching();
		System.out.println(wm.isMatch(s, p));
	}

	public boolean isMatch(String s, String p) {
		if (s.length() == 0 && p.length() == 0) {
			return true;
		} else if (s.length() == 0 || p.length() == 0) {
			return false;
		}
		
		if (s.charAt(0) == p.charAt(0) || p.charAt(0) == '?') {
			return isMatch(s.substring(1, s.length()),
					p.substring(1, p.length()));
		} else if (p.charAt(0) == '*') {
			if (p.length() == 1) {
				return true;
			} else {
				int i = 1;
				int j = 0;
				boolean ismatch = false;
				//在p中寻找第一个不等于'?'和'*'的字符
				for(;i<p.length();i++){
					if(p.charAt(i)=='?'||p.charAt(i)=='*'){
						continue;
					}
					else{
						break;
					}
				}
				if(i==p.length()){
					return true;
				}
				for(;j<s.length();j++){
					if(s.charAt(j)==p.charAt(i)){
						ismatch = isMatch(s.substring(j+1, s.length()),
								p.substring(i+1, p.length()));
						if(ismatch){
							return ismatch;
						}
					}
				}
			}
		} 
		return false;

	}

}
