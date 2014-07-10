import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

class Pos {
	public Pos(int i, int j) {
		row = i;
		col = j;
	}

	Pos() {
	}

	public int row;
	public int col;
}

public class ComputerManage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
		// Scanner scan = new Scanner(System.in);
		// int m = scan.nextInt();
		// int n = scan.nextInt();
		// int p = scan.nextInt();
		// int q = scan.nextInt();
		// scan.nextLine();
		// List<String> list = new ArrayList<String>();
		//
		// // Map<String, String> map = new LinkedHashMap<String, String>();
		// List<String> comlist = new ArrayList<String>();
		// List<String> perlist = new ArrayList<String>();
		// for (int i = 0; i < m + n; i++) {
		// list.add(scan.nextLine());
		// }
		// for (int i = 0; i < p+q; i++) {
		// String line = scan.nextLine();
		// String[] num = line.split(" ");
		// comlist.add(num[0]);
		// perlist.add(num[1]);
		//
		// }
		// ComputerManage cm = new ComputerManage();
		// cm.manage(list, comlist,perlist, m, n, p, q);
	}

	public void manage(List<String> list, List<String> comlist,
			List<String> perlist, int m, int n, int p, int q) {

		List<String> newlist = new ArrayList<String>();
		Map<String, String> map = new TreeMap<String, String>();
		for (int i = 0; i < m; i++) {
			if (list.get(i).length() > 10 || newlist.contains(list.get(i))) {
				System.out.println("add computer error:" + list.get(i));
			} else {
				newlist.add(list.get(i));
			}
		}
		for (int i = m; i < m + n; i++) {
			if (newlist.contains(list.get(i))) {
				newlist.remove(newlist.indexOf(list.get(i)));
			} else {
				System.out.println("remove computer error:" + list.get(i));
			}
		}
		// Iterator<String> it = map.keySet().iterator();
		for (int i = 0; i < p; i++) {
			if (newlist.contains(comlist.get(i))
					&& !map.containsKey(comlist.get(i))) {
				map.put(comlist.get(i), perlist.get(i));
			} else {
				System.out.println("assign new computer error:"
						+ comlist.get(i) + " " + perlist.get(i));
			}
		}

		for (int i = p; i < p + q; i++) {
			if (map.containsKey(comlist.get(i))
					&& map.get(comlist.get(i)).equals(perlist.get(i))) {
				map.remove(comlist.get(i));
			} else {
				System.out.println("send back computer error:" + comlist.get(i)
						+ " " + perlist.get(i));
			}
		}
		System.out.println(map.size());// 输出分配电脑的总共数量
		// 这里将map.entrySet()转换成list
		List<Map.Entry<String, String>> maplist = new ArrayList<Map.Entry<String, String>>(
				map.entrySet());
		// 然后通过比较器来实现排序
		Collections.sort(maplist, new Comparator<Map.Entry<String, String>>() {
			// 升序排序
			public int compare(Entry<String, String> o1,
					Entry<String, String> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}

		});

		for (Map.Entry<String, String> mapping : maplist) {
			System.out.println(mapping.getValue() + "_" + mapping.getKey());
		}
	}

	public static void test() {
		Pos[] pos = new Pos[3];
		pos[0] = new Pos();
		pos[0].row = 0;
		pos[0].col = 0;
	}
}
/*
 * 3 2 2 1 SN001 SN002 SN003 SN003 SN004 SN001 12345678 SN002 12345678 SN002
 * 12345678
 */
/*
 * 4 2 4 0 SN001 SN002 SN003 SN003 SN004 SN005 SN001 12345678 SN002 12345678
 * SN003 12345679 SN002 23456
 */