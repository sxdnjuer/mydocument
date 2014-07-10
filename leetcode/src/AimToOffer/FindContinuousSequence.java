package AimToOffer;

import java.util.Scanner;

public class FindContinuousSequence {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int sum = scan.nextInt();
		findconseq(sum);
	}
	
	public static void findconseq(int sum){
		int start = 1;
		int end = 2;
		int temp = start+end;
		int middle = (sum+1)/2;
		while(start<middle){
			if(temp<sum){
				end = end+1;
				temp = temp+end;
			}
			else if(temp>sum){
				temp = temp-start;
				start = start+1;
			}
			else {
				for(int i=start ; i<=end ; i++){
					System.out.print(i+" ");
				}
				System.out.println();
				end = end+1;
				temp = temp+end;
			}
			
		}
	}

}
