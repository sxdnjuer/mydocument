package AIalgorithm;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Cost {
	// public Cost(int i, int j, int k, int d) {
	// value = i;
	// start = j;
	// heur = k;
	// dir = d;
	// }

	Cost() {
	}

	public int value;// ��ʼ��Ŀ��ڵ�Ļ���
	public int start;// ��ʼ����ǰ�ڵ�Ļ���
	public int heur;// ����ʽ����ǰ�ڵ㵽Ŀ��ڵ�Ļ��ѣ�
	public int dir;// ����
	public int row;// ������
	public int col;// ������
}

public class Astar {
	// ------------- �����ǳ������� --------------//
	public static final int Left = 0; // ����
	public static final int Right = 1; // ����
	public static final int Up = 2; // ����
	public static final int Down = 3; // ����

	public static final int Empty = 0; // �հ�
	public static final int Fence = 1; // �ϰ�
	public static final int Player = 2; // С��
	public static final int Box = 3; // ����
	static int[][] grid = new int[8][8];
	Cost[][] cost = new Cost[8][8];
	List<Cost> openlist = new LinkedList<Cost>();
	List<Cost> closelist = new LinkedList<Cost>();
	static int play_row;
	static int play_col;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				grid[i][j] = scan.nextInt();
				if (grid[i][j] == 2) {
					play_row = i;
					play_col = j;
				}
			}
		}
		Astar astar = new Astar();
		astar.Initial();
		astar.Findbox();
		astar.FindRoute();
		astar.PrintRoute(play_row, play_col);
	}

	public void Initial() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				cost[i][j] = new Cost();
				cost[i][j].row = i;
				cost[i][j].col = j;
			}
		}
	}

	public void Findbox() {
		boolean unfind = true;
		for (int i = 0; i < 8 && unfind; i++) {
			for (int j = 0; j < 8; j++) {
				if (grid[i][j] == Box) {
					System.out.println(i + " " + j);
					Astaralgorithm(i, j);
					unfind = false;
					break;
				}
			}
		}
	}

	public void FindRoute() {// �ҳ���С�˵����ӵ����·��
		while (openlist.contains(cost[play_row][play_col]) == false) {// ��openlist�в�����С��ʱ������Ѱ��·��
			int min = Integer.MAX_VALUE;
			int row = -1;
			int col = -1;
			Iterator<Cost> it = openlist.iterator();
			while (it.hasNext()) {
				Cost temp = it.next();
				if (min > temp.value) {
					min = temp.value;
					row = temp.row;
					col = temp.col;
				}
			}
			System.out.println("row=" + row + "col=" + col + "min=" + min);
			Astaralgorithm(row, col);
		}
	}

	public void PrintRoute(int row, int col) {
		if (grid[row][col] == Box) {
			return;
		}
		if (cost[row][col].dir == Left) {
			System.out.println("Left= " + Left);
			PrintRoute(row, col - 1);
		} else if (cost[row][col].dir == Right) {
			System.out.println("Right=" + Right);
			PrintRoute(row, col + 1);
		} else if (cost[row][col].dir == Up) {
			System.out.println("Up=" + Up);
			PrintRoute(row - 1, col);
		} else {
			System.out.println("Down=" + Down);
			PrintRoute(row + 1, col);
		}
	}

	public void Astaralgorithm(int row, int col) {
		closelist.add(cost[row][col]);
		if (openlist.contains(cost[row][col])) {
			openlist.remove(cost[row][col]);
		}
		for (int i = 0; i < 4; i++) {
			if (i == 0) {
				LeftEmpty(row, col);// ��ߵĿո�
			} else if (i == 1) {
				RightEmpty(row, col);// �ұߵĿո�
			} else if (i == 2) {
				DownEmpty(row, col);// �±ߵĿո�
			} else {
				UpEmpty(row, col);// �ϱߵĿո�
			}
		}
	}

	public void LeftEmpty(int row, int col) {
		if (col - 1 >= 0 && grid[row][col - 1] != Fence// �ж��Ƿ�Ϊ �ϰ�
				&& openlist.contains(cost[row][col - 1]) == false// �ж�openlist�в������ýڵ�
				&& closelist.contains(cost[row][col - 1]) == false) {// �ж�closelist�в������ýڵ�
			cost[row][col - 1].start = cost[row][col].start + 1;
			cost[row][col - 1].heur = Math.abs(play_row - row)
					+ Math.abs(play_col - (col - 1));
			cost[row][col - 1].value = cost[row][col - 1].start
					+ cost[row][col - 1].heur;
			cost[row][col - 1].dir = Right;// ������
			openlist.add(cost[row][col - 1]);
		}
	}

	public void RightEmpty(int row, int col) {
		if (col + 1 <= 7 && grid[row][col + 1] != Fence// �ж��Ƿ�Ϊ �ϰ�
				&& openlist.contains(cost[row][col + 1]) == false// �ж�openlist�в������ýڵ�
				&& closelist.contains(cost[row][col + 1]) == false) {// �ж�closelist�в������ýڵ�
			cost[row][col + 1].start = cost[row][col].start + 1;
			cost[row][col + 1].heur = Math.abs(play_row - row)
					+ Math.abs(play_col - (col + 1));
			cost[row][col + 1].value = cost[row][col + 1].start
					+ cost[row][col + 1].heur;
			cost[row][col + 1].dir = Left;// ������
			openlist.add(cost[row][col + 1]);
		}
	}

	public void DownEmpty(int row, int col) {
		if (row + 1 <= 7 && grid[row + 1][col] != Fence// �ж��Ƿ�Ϊ �ϰ�
				&& openlist.contains(cost[row + 1][col]) == false// �ж�openlist�в������ýڵ�
				&& closelist.contains(cost[row + 1][col]) == false) {// �ж�closelist�в������ýڵ�
			cost[row + 1][col].start = cost[row][col].start + 1;
			cost[row + 1][col].heur = Math.abs(play_row - (row + 1))
					+ Math.abs(play_col - col);
			cost[row + 1][col].value = cost[row + 1][col].start
					+ cost[row + 1][col].heur;
			cost[row + 1][col].dir = Up;// ������
			openlist.add(cost[row + 1][col]);
		}
	}

	public void UpEmpty(int row, int col) {
		if (row - 1 >= 0 && grid[row - 1][col] != Fence// �ж��Ƿ�Ϊ �ϰ�
				&& openlist.contains(cost[row - 1][col]) == false// �ж�openlist�в������ýڵ�
				&& closelist.contains(cost[row - 1][col]) == false) {// �ж�closelist�в������ýڵ�
			cost[row - 1][col].start = cost[row][col].start + 1;
			cost[row - 1][col].heur = Math.abs(play_row - (row - 1))
					+ Math.abs(play_col - col);
			cost[row - 1][col].value = cost[row - 1][col].start
					+ cost[row - 1][col].heur;
			cost[row - 1][col].dir = Down;// ������
			openlist.add(cost[row - 1][col]);
		}
	}

}
/*
 * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 1 1 1 0 0 0 0 0 0 0 1 2 0 0 0 0 0 0 1 0
 * 0 0 0 3 0 0 1 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0
 */
