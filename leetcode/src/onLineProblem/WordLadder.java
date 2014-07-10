package onLineProblem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;


public class WordLadder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet<String> dict = new HashSet<String>();
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		for (int i = 0; i < n; i++) {
			dict.add(scan.next());
		}
		String start = scan.next();
		String end = scan.next();
		WordLadder wl = new WordLadder();
		System.out.println(wl.ladderLength(start, end, dict));
	}

	public int ladderLength(String start, String end, HashSet<String> dict) {
		Queue<String> queue = new LinkedList<String>();// Queue is a interface
		Map<String, Integer> map = new HashMap<String, Integer>();
		queue.add(start);
		map.put(start, 1);
		dict.add(end);
		while (!queue.isEmpty()) {
			String temp = queue.poll();
//			System.out.println(temp);
			for (int i = 0; i < temp.length(); i++) {
				for(char j = 'a' ; j<='z';j++){
					StringBuilder strbud = new StringBuilder(temp);
					strbud.setCharAt(i, j);
					if(strbud.toString().equals(temp))continue;
					if(dict.contains(strbud.toString())&&!map.containsKey(strbud.toString())){
						queue.add(strbud.toString());
						map.put(strbud.toString(),map.get(temp)+1);
					}
				}
			}
		}
		if(map.containsKey(end)){
			return map.get(end);
		}
		return 0;
	}

}
