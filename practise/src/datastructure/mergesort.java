package datastructure;

import java.util.Scanner;
//��·�鲢����
public class mergesort {
	public static void main(String args[])
	{
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
		int i1=0,j1=0,i2=0,j2=0;//i1,ji,i2,j2�ֱ�Ϊ��·�鲢��ÿһ·����ʼλ�ú���ֹλ��
		int size = 1;//sizeΪÿ�ι鲢�ĳ��ȣ��ֱ�Ϊ1,2,4,8....
		while(size<n)
		{
			i1=0;
			while(i1<n-size)
			{
				i2=i1+size;
				j1=i2-1;
				if(n-i2<size)
				{
					j2=n-1;
				}
				else
					j2=j1+size;
				merge(numbers,i1,j1,i2,j2,size);
				i1=i1+2*size;
			}
			size=2*size;
		}
		for(int i=0;i<n;i++)
		{
			System.out.print(numbers[i]+" ");
		}
	}

	private static void merge(int[] numbers, int i1, int j1, int i2, int j2, int size) {
		// TODO Auto-generated method stub
		int temp[] = new int[2*size];//ÿ�ΰѹ鲢�õ���������ʱ����temp[]�У�
									//�䳤��Ϊ2*size��������temp�����������������Ӧλ��
		int k=0;
		int m=i1;
		while(i1<=j1&&i2<=j2)
		{
			if(numbers[i1]<numbers[i2])
			{
				temp[k++]=numbers[i1++];
			}
			else
			{
				temp[k++]=numbers[i2++];
			}
		}
		while(i1<=j1)
		{
			temp[k++]=numbers[i1++];
		}
		while(i2<=j2)
		{
			temp[k++]=numbers[i2++];
		}
		for(int j=m;j<m+k;j++)
		{
			numbers[j]=temp[j-m];
		}
	}
}
