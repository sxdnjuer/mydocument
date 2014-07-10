package CodeOfBeauty;

import java.util.Scanner;
public class Two {
	static String  results[] = new String[10000];
	static int log = 0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int T=scan.nextInt();
		String result[] = new String[T];
		for(int i=0;i<T;i++){
			int m =scan.nextInt();
			int n = scan.nextInt();
			int p = scan.nextInt();
			combine(m,n,p);
			if(p>log){
				result[i] = "Impossible";
			}
			else{
				result[i] = results[p];
			}
			log = 0;
		}
		for(int i=0;i<T;i++){
			System.out.println(result[i]);
		}
	}
	
	public static void combine(int m,int n,int p){
		char c[] = new char[m+n];
		for(int i =0;i<m;i++){
			c[i] = '0';
		}
		for(int i = m;i<n+m;i++){
			c[i] = '1';
		}
		permutation(c,0,p);
	}
	
	public static void permutation(char[] str, int start,int p) {
		if (str == null || str.length == 0) {
			return;
		}
		if (start == str.length) {
			log++;
			if(log==p){
				results[p] = String.valueOf(str);
			}
			return;
		}
		for (int i = start; i < str.length; i++) {
			if (isExist(str, start, i)) {//加上这个条件变成有重复字母下的全排列
				char temp1 = str[i];
				str[i] = str[start];
				str[start] = temp1;
				permutation(str, start + 1,p);
				char temp2 = str[i];
				str[i] = str[start];
				str[start] = temp2;
			}
		}
	}
	public static boolean isExist(char[] str , int start , int i){
		for(int j =start;j<i;j++ ){
			if(str[j]==str[i])
				return false;
		}
		return true;
	}

}
