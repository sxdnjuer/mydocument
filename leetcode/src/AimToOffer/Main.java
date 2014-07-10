package AimToOffer;

import java.util.Scanner;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine();
		int temp[] = new int[line.length()];
		int tag = 0;
		for (int i = tag; i < line.length(); i++) {
			temp[i] = Integer.parseInt(String.valueOf(line.charAt(i)));
			// System.out.println(temp[i]);
		}
		Main main = new Main();
		int result = main.maxnunber(temp);
		System.out.println(result);
	}

	public int maxnunber(int[] temp) {
		if (temp.length == 0)
			return 0;
		int max = temp[0];
		int maxtemp = temp[0];
		int tag = 0;
		for (int i = tag; i < temp.length - 1; i++) {
			if (temp[i] < temp[i + 1]) {
				maxtemp = maxtemp * 10 + temp[i + 1];
				if (maxtemp > max) {
					max = maxtemp;
				}
			} else
				maxtemp = temp[i + 1];
		}
		return max;
	}
}
