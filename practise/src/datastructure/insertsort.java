package datastructure;
//ֱ�Ӳ�������
//ʱ�临�Ӷ� ���O(n) ���O(n2)
import java.util.Scanner;

public class insertsort {
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("������Ҫ�������");
		String input = scan.nextLine();
		String[] inputs = input.split(" ");
		int n = inputs.length;
		int[] numbers = new int[n]; 
		for(int i=0;i<n;i++)
		{
			numbers[i]=Integer.parseInt(inputs[i]);
		}
		for(int i=1;i<n;i++)                      //�����ŵ��Ǹ�����ǰ�����źõ����н���һһ�Ӻ���ǰ�Ƚ�
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
