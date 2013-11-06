package ACM;
//寻找最大回文子串
import java.util.Scanner;

public class palindrome {
	public static void main(String []args){
		String str = null;
		Scanner scan = new Scanner(System.in);
		str = scan.next();
		char s[] = str.toCharArray();
		int length = str.length();
		int count = 0,temp = 0,count1 = 0,temp1 = 0;
		int location = 0 , m = 0 , location1 = 0, m1 = 0;
		for(int i = 1 ; i<length ; i++) //AOEOA型
		{
			if((length-i)<=i){m = length - i;}
			else{m = i;}
			for(int k = 1 ; k<m; k++)
			{
				if(s[i-k]==s[i+k]){
					count++;
					System.out.println("s[i-k]="+s[i-k]+", s[i+k]="+s[i+k]+", count="+count+", i="+i+", i-k="+(i-k));
					continue;
				}
				else{ break;}
			}
			if(count>temp){
				 temp=count;
				 location=i;
				 System.out.println("temp="+temp+"location="+location);
	
			}	
			count=0;
		}
		for(int i = 1 ; i<length ; i++)//AOEEOA型
		{
			if((length-i)<=i){m1 = length - i;}
			else{m1 = i;}
			for(int k = 1 ; k<m1; k++)
			{
				if(s[i-k+1]==s[i+k]){
					count1++;
					System.out.println("s[i]="+s[i-k+1]+", s[i+k]="+s[i+k]+", count1="+count1+", i="+i+", i-k="+(i-k));
					continue;
				}
				else{ break;}
			}
			if(count1>temp1){
				 temp1=count1;
				 location1=i;
				 System.out.println("temp1="+temp1+"location1="+location1);
	
			}	
			count1=0;
		}
		
		if(temp>=temp1){
			for(int j = (location-temp) ; j<=(location+temp) ; j++){
				System.out.print(s[j]);
			}
		}
		else{
			
			for(int j = (location1-temp1+1) ; j<=(location1+temp1) ; j++){
				System.out.print(s[j]);
			}
		}
	}
}
