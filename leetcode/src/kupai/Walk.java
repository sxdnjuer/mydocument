package kupai;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Walk {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		List<Integer> list = new ArrayList<Integer>();
		String line = scan.nextLine();
		if (line.length() == 0) {
			System.out.println("YES");
		} else {
			String[] num = line.split(" ");
			for (int i = 0; i < num.length; i++) {
				list.add(Integer.parseInt(num[i]));
			}
			Walk walk = new Walk();
			System.out.println(walk.canwalk(list));

		}
	}

	public String canwalk(List<Integer> list) {
		for (int i = 0; i < list.size(); i = i + 2) {
			int location = list.get(i) * 100;
			int length = list.get(i + 1);
			int temp = location % 65;
			if ((temp + length) > 65) {
				return "NO";
			}
		}
		return "YES";
	}

}
