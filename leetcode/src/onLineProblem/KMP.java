package onLineProblem;

import java.util.Scanner;

public class KMP {
	public static void main(String[] args) {
		System.out.println("«Î ‰»Îƒ£ Ω¥Æ");
		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine();
		KMP kmp = new KMP();
		int[] next = kmp.findnext(line);
		for (int i = 0; i < next.length; i++) {
			System.out.print(next[i] + " ");
		}
	}

	private int[] findnext(String str) {
		int[] next = new int[str.length()];
		next[0] = -1;
		int k = -1, j = 0;
		while (j != str.length() - 1) {
			if (k != -1 && str.charAt(k) != str.charAt(j)) {
				k = next[k];
				continue;
			}
			++j;
			++k;
			if (str.charAt(k) == str.charAt(j))
				next[j] = next[k];
			else
				next[j] = k;
		}
		return next;
	}
}
