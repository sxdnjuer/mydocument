package onLineProblem;

import java.util.Arrays;
import java.util.Scanner;

public class WordSearch {
	boolean result;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int m = scan.nextInt();
		int n = scan.nextInt();
		scan.nextLine();
		char[][] board = new char[m][n];
		for (int i = 0; i < m; i++) {
			String line  = scan.nextLine();
			char[] temp = line.toCharArray();
//			System.out.println(temp.length);
			for(int j = 0;j<n;j++){
				board[i][j] = temp[j];
			}
		}
		String word = scan.nextLine();
//		System.out.println("board[0].length="+board[0].length);
		WordSearch ws = new WordSearch();
		System.out.println(ws.exist(board, word));
	}

	public boolean exist(char[][] board, String word) {
		boolean[][] flag = new boolean[board.length][board[0].length];
//		StringBuilder temp = new StringBuilder("");
		for(int i = 0;i<board.length&&result==false;i++){
			for(int j = 0;j<board[0].length;j++){
//				temp.append(board[i][j]);
//				flag[i][j] = true;
				dfs(board, flag, word, 0, i, j);
				if(result){
					break;
				}
//				temp.delete(0, temp.length());
//				flag[i][j] = false;
			}
		}
		return result;
	}
//该方法比较好，利用dfs,当board[i][j]==word.charAt(depth)时，进行递归搜索，若不相等，则直接退出
	public void dfs(char[][] board, boolean[][] flag, String word,int depth,int i,int j){
		if (word == null || word.length() == 0) {
			return ;
		}
		if(result)
			return;
		if(depth == word.length())
		{
			result = true;
			return;
		}
		if(i<0||j<0||i==board.length||j==board[0].length){
			return;
		}
		if(flag[i][j])return;
		if(board[i][j]==word.charAt(depth)){
			flag[i][j] = true;
			dfs(board , flag , word , depth+1 , i+1 , j);
			dfs(board , flag , word , depth+1 , i-1 , j);
			dfs(board , flag , word , depth+1 , i , j+1);
			dfs(board , flag , word , depth+1 , i , j-1);
			flag[i][j] = false;
		}
		
	}
	
//该方法超时，因为将每个组合都列出来，直到遇到与word相同
	public boolean recursion(char[][] board, boolean[][] flag, String word,
			StringBuilder temp, int i, int j) {
		if (word == null || word.length() == 0) {
			return false;
		}

		if (word.equals(temp.toString())) {
			result = true;
			return result;
		}
		for (int k = 0; k < 4; k++) {
			if (result == true) {
				break;
			}
			if (k == 0) {
				if ((i + 1) < board.length && j < board[0].length
						&& flag[i + 1][j] == false) {
					temp.append(board[i+1][j]);
					flag[i+1][j] = true;
					recursion(board, flag, word, temp, i + 1, j);
					temp.deleteCharAt(temp.length()-1);
					flag[i+1][j] = false;
				}
			} else if (k == 1) {
				if ((i - 1) >= 0 && j < board[0].length
						&& flag[i - 1][j] == false) {
					temp.append(board[i-1][j]);
					flag[i-1][j] = true;
					recursion(board, flag, word, temp, i - 1, j);
					temp.deleteCharAt(temp.length()-1);
					flag[i-1][j] = false;
				}
			} else if (k == 2) {
				if (i < board[0].length && (j + 1) < board[0].length
						&& flag[i][j + 1] == false) {
					temp.append(board[i][j+1]);
					flag[i][j+1] = true;
					recursion(board, flag, word, temp, i, j + 1);
					temp.deleteCharAt(temp.length()-1);
					flag[i][j+1] = false;
				}
			} else if (k == 3) {
				if (i < board[0].length && (j - 1) >= 0
						&& flag[i][j - 1] == false) {
					temp.append(board[i][j-1]);
					flag[i][j-1] = true;
					recursion(board, flag, word, temp, i, j - 1);
					temp.deleteCharAt(temp.length()-1);
					flag[i][j-1] = false;
				}
			}
		}
		return result;
	}

}
