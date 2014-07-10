package onLineProblem;

//leetcode
/*题目大意：给定n个二维平面上的点，求出这n个点当中最多有多少个点在同一直线上。
 * 本人解题思路（每次取定一个点p1，然后求出p1与其他的n-1个点的斜率，并统计斜率相同的数目，取最大值即可）
 1. 计算出每两个点之间的斜率，保存到double[][] k中
 当斜率为无穷大时，记为Integer.MAX_VALUE
 当两个点为同一个点时，记为Integer.MIN_VALUE
 2. slope保存i点与其它各点的斜率，不包括与其相同的点
 对slope进行快速排序，计算出与i点在同一直线上的最多的点数
 即找出slope出相同最多的那个数
 强调点：快速排序必须牢记，特别注意细节
 这里要注意3个地方：
 1）如果点的个数小于3个，则最大数目为点的个数；
 2）考虑重复点的情况，重复点是无法计算斜率的；（即相同点）
 3）考虑直线与y轴平行时，斜率为无穷大的情况。
 */
import java.util.Scanner;

class Point {
	int x;
	int y;

	Point() {
		x = 0;
		y = 0;
	}

	Point(int a, int b) {
		x = a;
		y = b;
	}
}

public class MaxPointsOnALine {

	double[][] k;// 斜率k

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		Point[] point = new Point[n];
		for (int i = 0; i < n; i++) {
			point[i] = new Point(scan.nextInt(), scan.nextInt());
		}
		// double[] temp = new double[n];
		// for (int i = 0; i < n; i++) {
		// temp[i] = scan.nextDouble();
		// }
		MaxPointsOnALine mp = new MaxPointsOnALine();
		System.out.println(mp.maxPoints(point));
		// mp.QuickSort(temp, 0, n - 1);
		// for (int i = 0; i < n; i++) {
		// System.out.println(temp[i]);
		// }
	}

	public int maxPoints(Point[] points) {
		if (points == null || points.length == 0)
			return 0;
		if (points.length == 1)
			return 1;
		int length = points.length;
		// System.out.println(length);
		k = new double[length][length];
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				if (points[i].x != points[j].x) {
					k[i][j] = (double) (points[i].y - points[j].y)
							/ (double) (points[i].x - points[j].x);
				} else if (points[i].y != points[j].y) {
					k[i][j] = Integer.MAX_VALUE;// 斜率无穷大，即x值相等，y值不等
				} else {
					k[i][j] = Integer.MIN_VALUE;// 两个点为同一个点
				}
				k[j][i] = k[i][j];// 对称原则
				// System.out.println("k[" + i + "][" + j + "]" + k[i][j]);
			}
		}
		double[] slope = new double[length - 1];// slope保存i点与其它各点的斜率，不包括与其相同的点
		int count = 0;
		int maxpoint = 0;
		int max_temp = 0;// 即与i点相同的点的个数
		int temp = 1;// 初始时有一个点（已去掉与其相同的点）
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (i != j) {
					if (k[i][j] == Integer.MIN_VALUE) {
						max_temp++;
					} else {
						slope[count] = k[i][j];// slope为与i不同的其他点的斜率，count为其数量
						// System.out.println(i+"slope["+count+"]"+slope[count]);
						count++;
					}
				}
			}
			System.out.println("count=" + count);
			if (count >= 1) {
				QuickSort(slope, 0, count - 1);
				// for (int k = 0; k < slope.length; k++) {
				// System.out.print(slope[k] + "slope ");
				// }
				// System.out.println("QuickSort");
				temp = Maximum(slope, 0, count - 1);
			}
			System.out.println("temp=" + temp);
			if (maxpoint < temp + max_temp) {
				maxpoint = temp + max_temp;
			}
			// System.out.println("max_temp="+max_temp);
			count = 0;
			max_temp = 0;
		}
		return maxpoint;
	}

	public void QuickSort(double[] num, int start, int end) {
		if (start >= end) {
			return;
		}
		int i = start;
		int j = end - 1;
		int flag = end;
		double temp;
		while (i <= j) {
			while (i <= end && num[i] < num[flag])
				i++;
			while (j >= start && num[j] > num[flag])
				j--;
			if (i <= j) {
				temp = num[i];
				num[i] = num[j];
				num[j] = temp;
				i++;
				j--;
			}
			// System.out.print("i=" + i + "j=" + j);
		}

		temp = num[i];
		// System.out.print(num[i]);
		num[i] = num[flag];
		num[flag] = temp;
		// System.out.println("i="+i);
		QuickSort(num, start, i - 1);
		QuickSort(num, i + 1, end);
	}

	public int Maximum(double[] num, int start, int end) {
		System.out.println("num.length" + num.length);
		if (end - start == 0 || num == null)
			return 2;// 即num中只有一个斜率，即有两个点，所以返回2
		int temp = 2;
		int max = 1;
		// for (int i = 0; i < num.length; i++) {
		// System.out.print(num[i] + " ");
		// }
		for (int i = 1; i <= end; i++) {
			if (num[i] == num[i - 1]) {
				temp++;
			} else {
				if (temp > max) {
					max = temp;
				}
				temp = 2;
			}
		}
		if (temp > max)
			max = temp;
		System.out.println("max=" + max);
		return max;
	}

}
/*
 * 5 1 2 2 3 4 6 7 8 8 9
 */
