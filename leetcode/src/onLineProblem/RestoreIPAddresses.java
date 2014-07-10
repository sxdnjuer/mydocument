package onLineProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RestoreIPAddresses {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String s = scan.nextLine();
		RestoreIPAddresses ria = new RestoreIPAddresses();
		System.out.println(Arrays.asList(ria.restoreIpAddresses(s)));
	}

	public ArrayList<String> restoreIpAddresses(String s) {
		ArrayList<String>  alist = new ArrayList<String> ();
		int[] array = new int[5];
		dfs(alist , 0, 0 , s ,array);
		return alist;
	}
	
	public void dfs(ArrayList<String> alist , int depth , int length, String s, int[] array){
		if(depth == 4){
			if(length==s.length()){
				String temp = "";
				for(int i=1;i<4;i++){
					temp = temp+s.substring(array[i-1],array[i])+".";
				}
				temp = temp+s.substring(array[3],array[4]);
				alist.add(temp);
			}
			return;
		}
		if(length+1>s.length())return;
		array[depth+1] = length+1;
		int value1 = Integer.parseInt(s.substring(array[depth],array[depth+1]));
		if(value1<=9&&value1>=0){
			dfs(alist , depth+1 , length+1,s,array);
		}
		if(length+2>s.length())return;
		array[depth+1] = length+2;
		int value2 = Integer.parseInt(s.substring(array[depth],array[depth+1]));
		if(value2<=99&&value2>=10){
			dfs(alist , depth+1 , length+2,s,array);
		}
		if(length+3>s.length())return;
		array[depth+1] = length+3;
		int value3 = Integer.parseInt(s.substring(array[depth],array[depth+1]));
		if(value3<=255&&value3>=100){
			dfs(alist , depth+1 , length+3,s,array);
		}
	}
}
