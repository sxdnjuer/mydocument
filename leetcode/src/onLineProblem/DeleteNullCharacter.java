package onLineProblem;

import java.util.Scanner;

public class DeleteNullCharacter {
	int i = 0;
	DeleteNullCharacter(){
		System.out.println("DeleteNullCharacter"+i);
		i++;
	}
//	int i = 1;
//	DeleteNullCharacter(int i){
//		this.i = i;
//	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		DeleteNullCharacter dnc = new DeleteNullCharacter();
		System.out.println(dnc.deletenullchar(str));
	}
	
	public String deletenullchar(String str){
		StringBuilder s = new StringBuilder(str);
		int i = 0;
		int n = 0;
		while(i<str.length()){
			s.setCharAt(i-n, s.charAt(i));
			if(str.charAt(i)==' '){
				n++;
			}
			i++;
		}
		return s.substring(0, str.length()-n);
	}
	

}
