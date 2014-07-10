package TestProblems;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MS_SendSentence {
	private static Map<String, String> map;
	private static int n;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		String results[] = new String[T];
		for (int i = 0; i < T; i++) {
			n = scan.nextInt();
			int m = scan.nextInt();
			String line = null;
			 map = new HashMap<String, String>();
			for (int j = 0; j < m; j++) {
				map.put(scan.next(), scan.next());
			}
			scan.nextLine();
			line = scan.nextLine();
			
			String result = null;
			result = calculate(line);
			results[i] = result;
		}
		for(int i = 0;i<T;i++){
			int temp = i+1;
			System.out.println("Case #"+temp+": "+results[i]);
		}
	}
	public static String findvalue(String value) {
		int count = 1;
		while(map.containsKey(value)&&count<n){
			value = map.get(value);
			count++;
		}
		
		return value;
	}

	public static String calculate(String line) {
		String result = "";
		String[] lines = line.split(" ");
		int length = lines.length;
		for (int i = 0; i < length; i++) {
			result = result + findvalue(lines[i]) + " ";
		}
		return result;
	}

}
//
//3
//4 3
//ship sheep
//sinking thinking
//thinking sinking
//the ship is sinking
//10 5
//tidy tiny
//tiger liar
//tired tire
//tire bear
//liar bear
//a tidy tiger is tired
//4 3
//ship sheep
//sheep thinking
//thinking ship
//the ship is thinking