package FiveAlgorithm;

import java.util.Scanner;



//Dynamic Program
//最长递增子序列
public class LongestIncreasingSubsequence {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan  = new Scanner(System.in);
		int n  = scan.nextInt();
		int[] array = new int[n+1];
		array[0] = 0;
		for(int i = 1;i<n+1;i++){
			array[i] = scan.nextInt();
		}
		LongestIncreasingSubsequence lic = new LongestIncreasingSubsequence();
		System.out.println(lic.lic(array));
	}
	
	public int lic(int[] num){
		int[] lis = new int[num.length];
		lis[0] = 0;
		for(int i=1;i<num.length;i++){
			lis[i] = 1;
			for(int j =1;j<i;j++){
				if(num[i]>num[j]&&lis[j]+1>lis[i]){
					lis[i] = lis[j]+1;
				}
			}
		}
		return max(lis);
	}
	
	public int max(int[] lis){
		int temp = lis[0];
		for(int i = 1;i<lis.length;i++){
			if(lis[i]>temp)
				temp = lis[i];
		}
		return temp;
	}

}
