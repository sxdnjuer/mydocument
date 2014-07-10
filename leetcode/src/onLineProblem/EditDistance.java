package onLineProblem;

import java.util.Scanner;

public class EditDistance {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String word1 = scan.nextLine();
		String word2 = scan.nextLine();
		EditDistance ed = new EditDistance();
		System.out.println(ed.minDistance(word1, word2));
	}

	public int minDistance(String word1, String word2) {
		int d[][] = new int[word1.length() + 1][word2.length() + 1];
		for (int i = 0; i < word1.length() + 1; i++) {
			d[i][0] = i;
		}

		for (int j = 0; j < word2.length() + 1; j++) {
			d[0][j] = j;
		}

		for (int i = 1; i < word1.length() + 1; i++) {
			for (int j = 1; j < word2.length() + 1; j++) {
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					d[i][j] = d[i - 1][j - 1];
				} else {
					d[i][j] = min(d[i - 1][j], d[i][j - 1], d[i - 1][j - 1]) + 1;
				}
			}
		}
		return d[word1.length()][word2.length()];
	}

	public int min(int a, int b, int c) {
		int temp = (b > c) ? c : b;
		return (a > temp) ? temp : a;
	}
}
