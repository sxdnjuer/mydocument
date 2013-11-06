package datastructure;
//简单选择排序
//时间复杂度 O(n2)
//不稳定算法
import java.util.Scanner;

public class selectsort {
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
		for(int i=0;i<n-1;i++)                //每次选择待排序数列中最小的一个，与队头元素进行交换，放在最前面
		{
			int min = numbers[i];
			int k = i;
			for(int j=i+1;j<n;j++)
			{
				if(numbers[j]<min)
				{
					min = numbers[j];
					k = j;
				}
			}
			int temp = numbers[k];
			numbers[k] = numbers[i];
			numbers[i] = temp;
		}
		for(int i = 0;i<n;i++)
		{
			System.out.print(numbers[i]+" ");
		}
	}
}
