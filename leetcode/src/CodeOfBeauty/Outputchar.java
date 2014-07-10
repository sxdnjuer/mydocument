package CodeOfBeauty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Outputchar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			String input = scan.nextLine();
			String result = compute(input);
			System.out.println(result);
		}
	}

	public static String compute(String num) {
		String result = "";
		// Set<Character> set = new TreeSet<Character>();
		List<Character> set = new ArrayList<Character>();
		for (int i = 0; i < num.length(); i++) {
			set.add(num.charAt(i));
		}
		Collections.sort(set);
		Map<Character, Integer> map = new TreeMap<Character, Integer>();
		Iterator<Character> it = set.iterator();
		// char t=' ';

		for (int i = 0; i < num.length(); i++) {
			char temp = it.next();
			// char temp = set.get(i);
//			System.out.println(temp);
			if ((temp >= '0' && temp <= '9') || (temp >= 'a' && temp <= 'z')) {
				if (map.containsKey(temp)) {
					map.put(temp, map.get(temp) + 1);
				} else {
					map.put(temp, 1);
				}
			} else
				return "<invalid input string>";
		}

		while (map.size() != 0) {
//			System.out.println("map.size()" + map.size());
			char t = 'A';
			Iterator<Character> itmap = map.keySet().iterator();
			int size = map.size();
			for (int i = 0; i < size; i++) {
				t = itmap.next();
				if (map.get(t) == 1) {
					itmap.remove();
					// map.remove(t);
					// System.out.println("map"+map.get(t));
				} else {
					map.put(t, map.get(t) - 1);
				}
//				System.out.println("t=" + t);
				result = result + t;
			}
		}
		return result;
	}
}
