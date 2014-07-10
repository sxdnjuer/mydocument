package onLineProblem;

import java.util.Arrays;
import java.util.Scanner;

public class LongestPalindromic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println("请输入字符串");
		
		Scanner scan = new Scanner(System.in);
//		int n = scan.nextInt();
//		int[] result = new int[n];
//		scan.nextLine();
		LongestPalindromic lp = new LongestPalindromic();
//		for(int i = 0;i<n;i++){
			String str = scan.nextLine();
//			result[i] = lp.manacher(str);
//		}
//		for(int i = 0;i<n;i++){
//			System.out.println(result[i]);
//		}

		
		 String result = lp.Research(str);
		System.out.println(lp.Palindrome(str));
		System.out.println(lp.longestPalindrome(str));
		System.out.println(lp.manacher(str));
		// System.out.println("the longest palindromic is " + result);

	}

	private String Research(String s) {
		if (s == null || s.length() == 0)
			return null;
		int longest = 0;
		int start = 0;
		for (int i = 0; i < s.length(); i++) {
			int result = SinglePalin(s, i);
			System.out.println("result =" + result);
			if (result > longest) {
				longest = result;
				start = i;
			}
			if (longest > s.length() - i - 1) {
				break;
			}
		}

		return s.substring(start, start + longest);
	}

	private int SinglePalin(String s, int first) {
		int start = first;
		int end = s.length() - 1;
		int result = 0;
		int temp[] = new int[s.length()];
		int flag = 0;
		for (int i = first; i < s.length(); i++) {
			if (s.charAt(first) == s.charAt(i)) {
				temp[flag] = i;
				flag++;
			}
		}
		flag--;
		while (end > start && flag != 0) {
			if (s.charAt(end) != s.charAt(start)) {
				end = temp[flag];
				start = first;
				flag--;
			} else {
				start++;
				end--;
			}
		}
		if (first == start) {
			result = 1;
		} else if (start == end) {
			result = (start - first) * 2 + 1;
		} else {
			result = (start - first) * 2;
		}
		return result;
	}

	/*
	 * @time 2014.5.19 动态规划求最长回文字串
	 */
	String Palindrome(String str) {
		int n = str.length();
		boolean table[][] = new boolean[n][n];
		// Arrays.fill(table,false);
		int begin = 0;
		int longest = 0;
		for (int i = 0; i < n; i++) {
			table[i][i] = true;
			begin = i;
			longest = 1;
		}
		for (int i = 0; i < n - 1; i++) {
			if (str.charAt(i) == str.charAt(i + 1)) {
				table[i][i + 1] = true;
				begin = i;
				longest = 2;
			}
		}
		for (int len = 3; len <= n; len++) {
			for (int i = 0; i <= n - len; i++) {
				if ((str.charAt(i) == str.charAt(i + len - 1))
						&& table[i + 1][i + len - 2]) {
					table[i][i + len - 1] = true;
					begin = i;
					longest = len;
				}
			}
		}
		return str.substring(begin, begin + longest);
	}

	/*
	 * @time 2014.5.19 使用中心对称的方法求最长回文子串 其中中心对称点有2N-1个，如abbbc，即可看成a#b#b#b#c;
	 */
	String longestPalindrome(String str) {
		int longest = 0;
		int half = 0;
		int start = 0;
		int flag = 0;
		for (int i = 0; i < str.length(); i++) {
			int temp = expandAroundCenter(str, i);
			int temp2 = expandAroundCenter2(str, i);
			if (longest < 2 * temp + 1) {
				longest = 2 * temp + 1;
				half = temp;
				start = i;
				flag = 0;
			}

			if (longest < 2 * temp2) {
				longest = 2 * temp2;
				half = temp2;
				start = i;
				flag = 1;
			}
		}
		return (flag == 0) ? str.substring(start - half, start + half + 1)
				: str.substring(start - half + 1, start + half + 1);
	}

	int expandAroundCenter(String str, int begin) {
		int len = 0;
		int i = 1;
		while ((begin - i) >= 0 && (begin + i) < str.length()) {
			if (str.charAt(begin - i) == str.charAt(begin + i)) {
				len = i;
				i++;
			} else {
				break;
			}
		}
		return len;
	}

	int expandAroundCenter2(String str, int begin) {
		int len = 0;
		int i = 0;
		while ((begin - i) >= 0 && (begin + 1 + i) < str.length()) {
			if (str.charAt(begin - i) == str.charAt(begin + 1 + i)) {
				i++;
				len = i;
			} else {
				break;
			}
		}
		return len;
	}

	public String manacher(String s) {
		StringBuilder str = new StringBuilder();
		str.append('#');
		for (int i = 0; i < s.length(); i++) {// 将原始串进行改变，即每一个字符间加上#号
			str.append(s.charAt(i));
			str.append('#');
		}
		int maxlen = 0;
		int id = 0;// 最长回文串的中心位置
		int[] p = new int[str.length()];
		for (int i = 0; i < str.length(); i++) {
			if (maxlen > i) {
				p[i] = Math.min(p[2 * id - i], p[id] - (i - id));
			} else {
				p[i] = 1;
			}
			while (i+p[i]<str.length()&&i-p[i]>=0&&str.charAt(i + p[i]) == str.charAt(i - p[i])) {
				++p[i];
			}
			if (p[i] > p[id]) {
				id = i;
				maxlen = p[i] + i;
			}
//			System.out.println("maxlen="+maxlen+" p[id]="+p[id]+" id="+id);
		}
		String temp = str.substring(id-p[id]+1,id+p[id]);
		String result = "";
		for(int i = 0;i<temp.length();i++){
			if(temp.charAt(i)!='#'){
				result = result+temp.charAt(i);
			}
		}
		return result;
	}
}
