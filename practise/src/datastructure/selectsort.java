package datastructure;
//��ѡ������
//ʱ�临�Ӷ� O(n2)
//���ȶ��㷨
import java.util.Scanner;

public class selectsort {
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
		for(int i=0;i<n-1;i++)                //ÿ��ѡ���������������С��һ�������ͷԪ�ؽ��н�����������ǰ��
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
