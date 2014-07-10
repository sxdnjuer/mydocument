package kupai;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StringtoInteger {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String line = scan.next();
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("Ling", 0);
		map.put("Yi", 1);
		map.put("Er", 2);
		map.put("San", 3);
		map.put("Si", 4);
		map.put("Wu", 5);
		map.put("Liu", 6);
		map.put("Qi", 7);
		map.put("Ba", 8);
		map.put("Jiu", 9);
		map.put("Shi", 10);
		map.put("Bai", 100);
		map.put("Qian", 1000);
		map.put("Wan", 10000);
		StringtoInteger si = new StringtoInteger();
		System.out.println(si.sti(line, map));
		
	}
	public int sti(String num,Map<String,Integer> map){
		List<String> list = new LinkedList<String>();
		int result = 0;
		int n = num.length();
		String temp = "";
		for(int i = 0;i<n;i++){
			if(num.charAt(i)>='A'&&num.charAt(i)<='Z'){
				if(!temp.equals(""))
					list.add(temp);
				temp = "";
			}
			temp = temp+num.charAt(i);
		}
		list.add(temp);
		for(int i = 1 ;i<list.size();i++){
			if(map.get(list.get(i))>9&&map.get(list.get(i-1))<=9&&map.get(list.get(i-1))>0){
				result = result+map.get(list.get(i-1))*map.get(list.get(i));
			}
		}
		if(map.get(list.get(list.size()-1))<=9)
			result = result+map.get(list.get(list.size()-1));
		return result;
	}

}
