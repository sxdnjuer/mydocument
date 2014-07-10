package onLineProblem;

import java.util.Scanner;

public class Candy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[] ratings = new int[n];
		for (int i = 0; i < n; i++) {
			ratings[i] = scan.nextInt();
		}
		Candy can = new Candy();
		System.out.println(can.candy(ratings));
	}

	public int candy(int[] ratings) {
		int[] num = new int[ratings.length];
		int id = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < ratings.length; i++) {
			if (ratings[i] <= min) {
				min = ratings[i];
				id = i;
			}
		}
		num[id] = 1;
		for (int i = 1; i <= id; i++) {
			if (ratings[id - i] > ratings[id - i + 1]) {
				num[id - i] = num[id - i + 1] + 1;
			} else if (ratings[id - i] < ratings[id - i + 1]) {
				num[id - i] = 1;
				if (num[id - i + 1] == 1) {
					num[id - i + 1] = 2;
					int j = 1;
					while (ratings[id - i + j] < ratings[id - i + j + 1]) {
						if (num[id - i + j + 1] > num[id - i + j]) {
							break;
						} else {
							num[id - i + j + 1] = num[id - i + j + 1] + 1;
						}
						j++;
					}
				}
			} else {
				num[id - i] = 1;
			}
		}
		for (int i = 1; i < ratings.length - id; i++) {
			if (ratings[id + i] > ratings[id + i - 1]) {
				num[id + i] = num[id + i - 1] + 1;
			} else if (ratings[id + i] < ratings[id + i - 1]) {
				num[id + i] = 1;
				System.out.println("id+i-1="+(id+i-1));
				System.out.println("ratings[id + i - 1]="+ratings[id + i - 1]);
				if (num[id + i - 1] == 1) {
					num[id + i - 1] = 2;
					System.out.println("id+i-1="+(id+i-1));
					int j = 1;
					while (ratings[id + i - j] < ratings[id + i - j - 1]) {
						System.out.println("j=" + j);
						if (num[id + i - j - 1] > num[id + i - j]) {
							break;
						} else {
							num[id + i - j - 1] = num[id + i - j - 1] + 1;
						}
						j++;
					}
					
				}
			} else {
				num[id + i] = 1;
			}
		}
		int result = 0;
		for (int i = 0; i < num.length; i++) {
			System.out.println("i=" + num[i]);
			result = result + num[i];
		}
		return result;
	}

}
