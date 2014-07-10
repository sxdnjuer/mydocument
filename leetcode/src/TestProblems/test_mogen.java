package TestProblems;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

public class test_mogen {

	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test_mogen tm =new test_mogen();
//		BigDecimal amount = new BigDecimal(100.00);
//		double rate =1.6f;
//		double total = tm.ca(amount,rate,12);
//		System.out.println(total);
//		
//		Character a = new Character("c");
//		Byte b =Byte.parseByte(121);
//		
//		Long alog = 9876543210L;
//		Integer c = new Integer("9876543220");
//		Boolean d = Boolean.parseBoolean(true);
//		int a = 9 ,b = 2;
//		float f;
//		f=a/b;
//		System.out.println(f);
//		f=f/2;
//		System.out.println(f);
//		f=a+b/f;
//		System.out.println(f);
//		int a = 0;
//		if(a>=0);
//		if(a==0);
//		System.out.println("1");
//		else System.out.println("2");
//		System.out.println("3");
		
//		int c=1;
//		boolean b = false;
//		if(b=true){
//			System.out.println("true");
//		}
//		else{
//			System.out.println("false");
//		}
//		
		double d = -27;
//		System.out.println(Math.ceil(d));//向上取整，保留小数点
//		System.out.println(Math.round(d));//四舍五入，没有小数点
//		System.out.println(Math.abs(d));//取绝对值,根据值类型返回
//		System.out.println(Math.floor(d));//向下取整，保留小数点
		Byte abyte = Byte.parseByte("121");
		System.out.println(abyte);
//		Character a = new Character('C');
//		Long a = 9876543210L;
////		Boolean b = Boolean.parseBoolean(true);
//		Integer i = new Integer("9876543220");
//		int a = 0;
//		if(a>=0);
//			if(a==0);
//			System.out.print(" 1 ");
//			else System.out.print(" 2 ");
//			System.out.print(" 2");
//		}
		int x = 5 , y=0;
		try{
			try{
				System.out.println(x);
				System.out.println(x/y);
				System.out.println(y);
			}
			catch(ArithmeticException ex){
				System.out.println("Inner Catch1");
				throw ex;
			}
			catch(RuntimeException ex){
				System.out.println("Inner Catch2");
				throw ex;
			}
			finally{
				System.out.println("Inner Finally");
			}
		}
		catch(Exception ex){
			System.out.println("Outer Catch");
		}
	}
//	 int ca(BigDecimal amount , double rate , long months)throws IOException{
////		 return(int)(amount * rate* months /100);
//	 }

}
