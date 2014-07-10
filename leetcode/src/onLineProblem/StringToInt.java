package onLineProblem;

import java.util.Scanner;

public class StringToInt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		StringToInt sti = new StringToInt();
		System.out.println(sti.atoi(str));
	}
	
	public int atoi(String str){
		int result = 0;
		if(str==null||str.length()==0){
			return 0;
		}
		boolean negative = false;
		int length = str.length();
		int i = 0;
		while(str.charAt(i)==' '){
			i++;
		}
		if(str.charAt(i)=='+'){
			negative = false;
			result = (int)stringtoint(str.substring(i+1, length), negative);
		}
		else if(str.charAt(i)=='-'){
			negative = true;
//			if(length==1){
//				return 0;
//			}
//			else 
				result = (int)stringtoint(str.substring(i+1, length), negative);
		}
		else{
			result = (int)stringtoint(str.substring(i,length), negative);
		}
		return result;
	}
	
	public long stringtoint(String str, boolean negative){
		if(str==null||str.length()==0){
			return 0;
		}
		int i = 0;
		long result = 0;
		int flag = (negative==true)?-1:1;
		while(i<str.length()){
			if(str.charAt(i)>='0'&&str.charAt(i)<='9'){
				result = result*10+flag*(str.charAt(i)-'0');
				if((negative==true&&result<0x80000000)||(negative==false&&result>0x7FFFFFFF)){
					if(negative==false){
						return 0x7FFFFFFF;
					}
					else
						return 0x80000000;
				}
				i++;
			}
			else
				return result;
		}
		return result;
	}

}
