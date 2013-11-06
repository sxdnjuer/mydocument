package datastructure;
//直接插入排序
//时间复杂度 最好O(n) 最差O(n2)
import java.util.Scanner;

public class insertsort {
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
		for(int i=1;i<n;i++)                      //将待排的那个数与前面已排好的序列进行一一从后往前比较
		{
			int temp = numbers[i];
			int j = i-1;
			while(j>=0&&numbers[j]>temp)
			{
				numbers[j+1]=numbers[j];
				j--;
			}
			numbers[j+1]=temp;
		}
		for(int i = 0;i<n;i++)
		{
			System.out.print(numbers[i]+" ");
		}
	}
}
