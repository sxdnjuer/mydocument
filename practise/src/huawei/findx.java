package huawei;

import java.util.Scanner;

public class findx {

	public static void main(String[] args) {
		int[] number = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		// int[] number1 =
		// {1,10,100,1000,10000,100000,1000000,10000000,100000000};
		// int[] number2 = {0,0,20,200,2000,20000,200000,2000000,20000000};
		// int[] number3 = {0,0,0,30,300,3000,30000,300000,3000000};
		// int[] number4 = {0,0,0,4,40,400,4000,40000,400000};
		// int[] number5 = {0,0,0,0,5,50,500,5000,50000};
		// int[] number6 = {0,0,0,0,0,6,60,600,6000};
		// int[] number7 = {0,0,0,0,0,0,7,70,700};
		// int[] number8 = {0,0,0,0,0,0,0,8,80};
		// int[] number9 = {0,0,0,0,0,0,0,0,9};
		int total = 0;
		int x = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println("ÇëÊäÈëxµÄÖµ");
		x=scan.nextInt();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					if (i == 0) {
						total = number[0] + number[1];
						if (j == 0) {
							total = total + number[2];
							if (k == 0) {
								total = total + number[3];	
								if(total==x){System.out.println(i+","+j+","+k);}
							}
							if (k == 1) {
								total = total - number[3];
								if(total==x){System.out.println(i+j+k);}
							}
							if (k == 2) {
								total = total * 10 + number[3];
								if(total==x){System.out.println(i+j+k);}
							}
						}
						if (j == 1) {
							total = total - number[2];
							if (k == 0) {
								total = total + number[3];	
								if(total==x){System.out.println(i+j+k);}
							}
							if (k == 1) {
								total = total - number[3];
								if(total==x){System.out.println(i+j+k);}
							}
							if (k == 2) {
								total = total * 10 + number[3];
								if(total==x){System.out.println(i+j+k);}
							}
						}
						if (j == 2) {
							total = total * 10 + number[2];
							if (k == 0) {
								total = total + number[3];	
								if(total==x){System.out.println(i+j+k);}
							}
							if (k == 1) {
								total = total - number[3];
								if(total==x){System.out.println(i+j+k);}
							}
							if (k == 2) {
								total = total * 10 + number[3];
								if(total==x){System.out.println(i+j+k);}
							}
						}
					}
					if (i == 1) {
						total = number[0] - number[1];
						if (j == 0) {
							total = total + number[2];
							if (k == 0) {
								total = total + number[3];	
								if(total==x){System.out.println(i+j+k);}
							}
							if (k == 1) {
								total = total - number[3];
								if(total==x){System.out.println(i+j+k);}
							}
							if (k == 2) {
								total = total * 10 + number[3];
								if(total==x){System.out.println(i+j+k);}
							}
						}
						if (j == 1) {
							total = total - number[2];
							if (k == 0) {
								total = total + number[3];	
								if(total==x){System.out.println(i+j+k);}
							}
							if (k == 1) {
								total = total - number[3];
								if(total==x){System.out.println(i+j+k);}
							}
							if (k == 2) {
								total = total * 10 + number[3];
								if(total==x){System.out.println(i+j+k);}
							}
						}
						if (j == 2) {
							total = total * 10 + number[2];
							if (k == 0) {
								total = total + number[3];				
								if(total==x){System.out.println(i+j+k);}
							}
							if (k == 1) {
								total = total - number[3];
								if(total==x){System.out.println(i+j+k);}
							}
							if (k == 2) {
								total = total * 10 + number[3];
								if(total==x){System.out.println(i+j+k);}
							}
						}
					}
					if (i == 2) {
						total = number[0] * 10 + number[1];
						if (j == 0) {
							total = total + number[2];
							if (k == 0) {
								total = total + number[3];				
								if(total==x){System.out.println(i+j+k);}
							}
							if (k == 1) {
								total = total - number[3];
								if(total==x){System.out.println(i+j+k);}
							}
							if (k == 2) {
								total = total * 10 + number[3];
								if(total==x){System.out.println(i+j+k);}
							}
						}
						if (j == 1) {
							total = total - number[2];
							if (k == 0) {
								total = total + number[3];				
								if(total==x){System.out.println(i+j+k);}
							}
							if (k == 1) {
								total = total - number[3];
								if(total==x){System.out.println(i+j+k);}
							}
							if (k == 2) {
								total = total * 10 + number[3];
								if(total==x){System.out.println(i+j+k);}
							}
						}
						if (j == 2) {
							total = total * 10 + number[2];
							if (k == 0) {
								total = total + number[3];				
								if(total==x){System.out.println(i+j+k);}
							}
							if (k == 1) {
								total = total - number[3];
								if(total==x){System.out.println(i+j+k);}
							}
							if (k == 2) {
								total = total * 10 + number[3];
								if(total==x){System.out.println(i+j+k);}
							}
						}
					}
				}
			}
		}

	}
}
