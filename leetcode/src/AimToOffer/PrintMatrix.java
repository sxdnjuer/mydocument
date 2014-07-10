package AimToOffer;

import java.util.Scanner;

public class PrintMatrix {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int rows = scan.nextInt();
		int columns = scan.nextInt();
		int[][] matrix = new int[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				matrix[i][j] = scan.nextInt();
			}
		}
		PrintMatrix pm = new PrintMatrix();
		pm.PrintMatrixClockwisely(matrix, rows, columns);
	}

	private void PrintMatrixClockwisely(int[][] numbers, int rows, int columns) {
		if (numbers == null || rows <= 0 || columns <= 0)
			return;
		int start = 0;
		while(rows>start*2&&columns>start*2){
			PrintMatrixInCicle(numbers, rows, columns, start);
			start++;
		}
		
	}

	private void PrintMatrixInCicle(int[][] numbers, int rows, int columns,
			int start) {
		int endx = columns -start -1;//终止列号
		int endy = rows - start -1;//终止行号
		//第一步，打印一行
		for(int i=start ;i<=endx;i++){
			Print(numbers[start][i]);
		}
		if(endy>start){
			for(int i = start+1 ; i<=endy;i++){
				Print(numbers[i][endy]);
			}
		}
		if(endy>start&&endx>start){
			for(int i= endy-1 ; i>=start;i--){
				Print(numbers[endy][i]);
			}
		}
		if(endx>start&&endy-1>start){
			for(int i=endy-1 ; i>start;i--){
				Print(numbers[i][start]);
			}
		}
	}
	
	private void Print(int singlenumber){
		System.out.print(singlenumber+" ");
	}
}
