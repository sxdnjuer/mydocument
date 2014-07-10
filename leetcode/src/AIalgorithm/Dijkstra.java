package AIalgorithm;

import java.util.Scanner;
//Dijkstra�㷨�������鱾���ݽṹ���㷨����java��247ҳ������
//Dijkstra�㷨��̰���㷨����ð���
public class Dijkstra {

	static boolean[][] connected = new boolean[7][7];//����ͼ�ĸ����Ƿ�����
	static int[][] edge = new int[7][7];//����ͼ���ߵ�ֵ
	boolean[] known = new boolean[7];//�ڵ��Ƿ񱻼���
	int[] distance = new int[7];//��ʼ�ڵ㵽���ڵ����̾���

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		// connected[0][0] = scan.nextBoolean();
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				connected[i][j] = scan.nextBoolean();
				edge[i][j] = scan.nextInt();
			}
		}
		Dijkstra dij = new Dijkstra();
		int start = scan.nextInt();
		dij.Initial(start);
		dij.ShortestRoute(start);
		dij.Print();
	}

	public void Initial(int start) {
		for (int i = 0; i < 7; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		distance[start] = 0;
	}

	public void ShortestRoute(int start) {
		known[start] = true;
		for (int i = 0; i < 7; i++) {
			if (connected[start][i]) {
				if (distance[start] + edge[start][i] < distance[i])
					distance[i] = distance[start] + edge[start][i];
			}
		}
		int min = Integer.MAX_VALUE;
		int newadd = -1;
		for (int i = 0; i < 7; i++) {
			if (known[i] == false) {
				if (distance[i] < min) {
					min = distance[i];
					newadd = i;
				}
			}
		}
		
		boolean alladded = true;// �Ƿ�ȫ���Ѽ��뵽known��
		for (int i = 0; i < 7; i++) {
			if (known[i] == false) {
				alladded = false;
			}
		}
		if (alladded) {
			return;
		} else {
			ShortestRoute(newadd);
		}

	}

	public void Print() {
		for (int i = 0; i < 7; i++) {
			System.out.print(distance[i] + " ");
		}
	}

}
/*
Input:
false 0 true 2 false 0 true 1 false 0 false 0 false 0
false 0 false 0 false 0 true 3 true 5 false 0 false 0
true 4 false 0 false 0 false 0 false 0 true 5 false 0
false 0 false 0 true 2 false 0 true 2 true 8 true 4
false 0 false 0 false 0 false 0 false 0 false 0 true 6
false 0 false 0 false 0 false 0 false 0 false 0 false 0 
false 0 false 0 false 0 false 0 false 0 true 1 false 0
0
Output:
0 2 3 1 3 6 5 
 */