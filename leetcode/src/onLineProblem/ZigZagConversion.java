package onLineProblem;

import java.util.Scanner;

public class ZigZagConversion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("请输入字符串和行数");
		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine();
		int nRows = scan.nextInt();
		ZigZagConversion zzc = new ZigZagConversion();
		zzc.convert(line, nRows);

	}

	public String convert(String s, int nRows) {
		int length = s.length();
		if(s.length()==0||s == null){
			return null;
		}
		if (length <= nRows||nRows == 1) {
			return s;
		}
		String result = null;
		for (int i = 0; i < nRows; i++) {
			int location = 2 * (nRows - 1);
			int count = 2;
			result = result+s.charAt(i);
			System.out.print(s.charAt(i) + " ");
			int step = 1;
			if (i == 0) {
				while (location < length) {
					result = result+s.charAt(location);
					System.out.print(s.charAt(location) + " ");
					count = count + 2;
					location = count * (nRows - 1);
				}
			} else if (i == nRows - 1) {
				location = count * (nRows - 1)+i;
				while (location < length) {
					result = result+s.charAt(location);
					System.out.print(s.charAt(location) + " ");
					count = count + 2;
					location = count * (nRows - 1)+i;
					
				}
			} else {
				while (location < length) {
					if (step == 1) {
						location = location - i;
						step = step + 1;
						result = result+s.charAt(location);
						System.out.print(s.charAt(location) + " ");
						location = location + 2 * i;
						continue;
					}
					if (step == 2) {
						result = result+s.charAt(location);
						System.out.print(s.charAt(location) + " ");
						step = 1;
						count = count + 2;
						location = count * (nRows - 1);
					}
					// System.out.printlns(s.charAt(location));
				}
			}
		}
		return result;
	}
}
