package onLineProblem;

//leetcode
/*��Ŀ���⣺����n����άƽ���ϵĵ㣬�����n���㵱������ж��ٸ�����ͬһֱ���ϡ�
 * ���˽���˼·��ÿ��ȡ��һ����p1��Ȼ�����p1��������n-1�����б�ʣ���ͳ��б����ͬ����Ŀ��ȡ���ֵ���ɣ�
 1. �����ÿ������֮���б�ʣ����浽double[][] k��
 ��б��Ϊ�����ʱ����ΪInteger.MAX_VALUE
 ��������Ϊͬһ����ʱ����ΪInteger.MIN_VALUE
 2. slope����i�������������б�ʣ�������������ͬ�ĵ�
 ��slope���п������򣬼������i����ͬһֱ���ϵ����ĵ���
 ���ҳ�slope����ͬ�����Ǹ���
 ǿ���㣺������������μǣ��ر�ע��ϸ��
 ����Ҫע��3���ط���
 1�������ĸ���С��3�����������ĿΪ��ĸ�����
 2�������ظ����������ظ������޷�����б�ʵģ�������ͬ�㣩
 3������ֱ����y��ƽ��ʱ��б��Ϊ�����������
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

	double[][] k;// б��k

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
					k[i][j] = Integer.MAX_VALUE;// б������󣬼�xֵ��ȣ�yֵ����
				} else {
					k[i][j] = Integer.MIN_VALUE;// ������Ϊͬһ����
				}
				k[j][i] = k[i][j];// �Գ�ԭ��
				// System.out.println("k[" + i + "][" + j + "]" + k[i][j]);
			}
		}
		double[] slope = new double[length - 1];// slope����i�������������б�ʣ�������������ͬ�ĵ�
		int count = 0;
		int maxpoint = 0;
		int max_temp = 0;// ����i����ͬ�ĵ�ĸ���
		int temp = 1;// ��ʼʱ��һ���㣨��ȥ��������ͬ�ĵ㣩
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (i != j) {
					if (k[i][j] == Integer.MIN_VALUE) {
						max_temp++;
					} else {
						slope[count] = k[i][j];// slopeΪ��i��ͬ���������б�ʣ�countΪ������
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
			return 2;// ��num��ֻ��һ��б�ʣ����������㣬���Է���2
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
