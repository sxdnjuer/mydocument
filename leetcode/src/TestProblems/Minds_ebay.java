package TestProblems;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Minds_ebay {
	
	public static void main(String args[]) {
		String[] arr = { "Slow", "Medium", "Fast", "Extreme" };
		List<String> ls = Arrays.asList(arr);
//		Collections.sort(ls);
		Collections.sort(ls, Minds_ebay.comparatorX());
		int index = Collections.binarySearch(ls, "Extreme",Minds_ebay.comparatorX());
//		System.out.println(ls);
		System.out.println(ls + " :Extreme at " + index);
	}

	public static Comparator<String> comparatorX(){
		return new Comparator<String>(){
			public int compare(String str1 ,String str2){
				StringBuilder sb1 =  new StringBuilder(str1);
				StringBuilder sb2 =  new StringBuilder(str2);
				System.out.println(sb2.reverse().toString());
				System.out.println(sb1.reverse().toString());
				System.out.println(sb2.reverse().toString().compareTo(sb1.reverse().toString()));
				return sb2.reverse().toString().compareTo(sb1.reverse().toString());
			}
		};
	}
}
