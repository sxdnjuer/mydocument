package ACM;

import java.util.Scanner;

public class sticks {
	public static void main(String []args){
		int [] result = new int[10000];
		int n = 1,line = 0, count = 0;
		Scanner scan  = new Scanner(System.in);
		while(true){
			int temp = 0 , total = 0;
			count=scan.nextInt();
			n = count;
			if( n == 0 ){break;}
			int [] number = new int[n];
			for(int i = 0;i<n;i++){
				number[i] = scan.nextInt();
				total = total + number[i];
				if(temp < number[i]){
					temp = number[i];
				}
			}
			while(total%temp != 0){
				temp++;
			}
			result[line]=temp;
			line++;
		}
		for(int j =0 ; j<line;j++){
			System.out.println(result[j]);
		}
	}
}
