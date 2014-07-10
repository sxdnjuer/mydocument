package AimToOffer;

import java.util.Scanner;

public class FindNumbersWithSum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine();
		String[] numbers = line.split(" ");
		int[] num = new int[numbers.length];
		for(int i=0;i<numbers.length;i++){
			num[i] = Integer.parseInt(numbers[i]);
		}
		int sum = scan.nextInt();
		System.out.println(findnumbers(num,sum));
	}
	public static String findnumbers(int[] num,int sum){
		if(num.length==0||num==null){
			return "NULL";
		}
		int i = 0;
		int j = num.length-1;
		String result = "";
		while(i<j){
			if(num[i]+num[j]<sum){
				i++;
			}
			else if(num[i]+num[j]>sum){
				j--;
			}
			else{
				break;
			}
		}
		if(i==j){
			result = "not found";
		}
		else{
			result = num[i]+" "+num[j];
		}
		return result;
	}

}
