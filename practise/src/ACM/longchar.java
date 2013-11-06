package ACM;

import java.util.Scanner;
//递归算法求最长连续字符
public class longchar {
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		char num[] = input.toCharArray();
		int n = num.length;
		longchar lc = new longchar();
		int result = lc.getnum(num,1,0,0,n-1);
		System.out.println("result="+result);
	}
	private int getnum(char num[],int count,int Max,int i,int end)
	{
		if(i==end+1)
		{
			return Max;
		}
		if(i==end)
		{
			if(count>Max)
				Max = count;
			return Max;
		}
		else if(num[i+1]==num[i])
		{
			count++;
		}
		else
		{
			if(count>Max)
				Max = count;
			count = 1;
		}
		i++;
		return getnum(num,count,Max,i,end);
	}
}
