package kupai;

import java.util.Scanner;

public class Gesture {
	
	public static void main(String[] args) {
	
		int a=-67,b=116,c=78; 
		int d=~a | b & c;

		System.out.println(d);
		String[] s = new String[10];
		System.out.println(s[0]);
		Scanner scan = new Scanner(System.in);
		
		int x1 = scan.nextInt();
		int y1 = scan.nextInt();
		int x2 = scan.nextInt();
		int y2 = scan.nextInt();
		Gesture gs = new Gesture();
		System.out.println(gs.gestruedetector(x1, y1, x2, y2));
	}

	public String gestruedetector(int x1, int y1, int x2, int y2) {
		
		int x = x1 - x2;
		int y = y1 - y2;
		if (x == 0 && y == 0) {
			return "CLICK";
		}
		if (Math.abs(y) > Math.abs(x) && y2 > y1) {
			return "DOWN";
		}
		if (Math.abs(y) > Math.abs(x) && y1 > y2) {
			return "UP";
		}
		if (Math.abs(y) <= Math.abs(x) && x2 > x1) {
			return "RIGHT";
		}
		if (Math.abs(y) <= Math.abs(x) && x1 > x2) {
			return "LEFT";
		}
		return null;
	}
}
