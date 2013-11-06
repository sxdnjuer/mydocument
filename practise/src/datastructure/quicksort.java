package datastructure;
//快速排序算法（java版）
import java.util.Scanner;

public class quicksort {
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入要排序的数");
		String input = scan.nextLine();
		String[] inputs = input.split(" ");
		int n = inputs.length;
		int[] numbers = new int[n]; 
		for(int i=0;i<n;i++)
		{
			numbers[i]=Integer.parseInt(inputs[i]);
		}
		int p = 0 , r = n-1;
		sort(numbers,p,r);
		for(int j = 0; j<n;j++)
		{
			System.out.print(numbers[j]+" ");
		}
	}

	private static void sort(int[] numbers, int p, int r) {
		// TODO Auto-generated method stub
		if(p<r){
			int q = partition(numbers,p,r);
			sort(numbers , p ,q-1);
			sort(numbers , q+1 , r);
		}
	}

	private static int partition(int[] numbers, int p, int r) {
		// TODO Auto-generated method stub
		int i = p-1;
		int temp = 0;
		for (int j = p;j<r;j++)
		{
			if(numbers[j]<=numbers[r])
			{
				i=i+1;
				temp = numbers[j];
				numbers[j]=numbers[i];
				numbers[i] = temp;
			}
		}
		temp = numbers[r];
		numbers[r]=numbers[i+1];
		numbers[i+1] = temp;
		return i+1;
	}
}
