package onLineProblem;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class TwoSum {
	public static void main(String[] args) {
		System.out.println("请输入数组和目标数字");
		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine();
		int target = scan.nextInt();
		String[] input = line.split(" ");
		int length = input.length;
		System.out.println("length" + length);
		int[] num = new int[length];
		for (int i = 0; i < length; i++) {
			num[i] = Integer.parseInt(input[i]);
			// System.out.print(" "+num[i]);
		}
		// System.out.println("length"+length);
		int[] source = new int[length];
		for (int i = 0; i < num.length; i++) {
			 source[i] = num[i];
			 
		}
		TwoSum ts = new TwoSum();
//		ts.partition(num, 0, length - 1);	
//		int[] result = ts.twoSum(num, target);
		int[] result = ts.summap(num,target);
		int reslen = result.length;
		for (int i = 0; i < reslen; i++) {
			int j;
			if(i==0){
				j = 0;
				while(j<=length-1){
					if(result[i]==source[j]){
						result[i] = j+1;
						break;
					}
					j++;
				}
			}
			else {
				j = length-1;
				while(j>=0){
					if(result[i]==source[j]){
						result[i] = j+1;
						break;
					}
					j--;
				}
			}
		}
		if(result[0]>result[1]){
		    int temp = result[0];
		    result[0] = result[1];
		    result[1] = temp;
		}
		for(int i = 0;i<reslen;i++){
			System.out.println("index"+i+"="+result[i]);
		}
	}

	public void partition(int[] num, int start, int end) {
		if (num == null)
			return;
		if (start == end) {
			return;
		}
		int first = start;
		int last = end - 1;
		int temp;
		while (first < last) {
			while (num[first] <= num[end] && first < end)
				first++;
			while (num[last] >= num[end] && last > start)
				last--;
			if (first < last) {
				temp = num[first];
				num[first] = num[last];
				num[last] = temp;
			}
		}
		if (num[first] > num[end]) {
			temp = num[end];
			num[end] = num[first];
			num[first] = temp;
		}

//		System.out.println("");
//		for (int i = 0; i < num.length; i++) {
//			System.out.print(" " + num[i]);
//		}
		if (first > start)
			partition(num, start, first - 1);
		if (first < end)
			partition(num, first + 1, end);
		// System.out.println("flag");
		return;
	}

	public int[] twoSum(int[] numbers, int target) {
		int[] result = new int[2];
		int length = numbers.length;
		int start  = 0 ; 
		int end = length - 1;
		while(start!=end){
			if(numbers[start]+numbers[end]==target){
				result[0] = numbers[start] ; 
				result[1] = numbers[end] ;
				return result;
			}
			else if(numbers[start]+numbers[end]>target){
				end--;
			}
			else if(numbers[start]+numbers[end]<target){
				start++;
			}			
		}
		return null;
	}
	
	public int[] summap(int[] numbers, int target){
		int[] result = new int[2];
		Map<Integer,Integer> map = new LinkedHashMap<Integer,Integer>();
		for(int i=0;i<numbers.length;i++){
			int temp = (target - numbers[i])*numbers[i];
			if(map.containsKey(temp)&&(numbers[i]+map.get(temp)==target)){
				result[0] = map.get(temp);
				result[1] = numbers[i];
				break;
			}
			map.put(temp,numbers[i]);
		}
//		Iterator<Integer> it = map.keySet().iterator();
//		
//		while(it.hasNext()){
//			int temp = it.next();
//			if(map.containsKey(map.get(temp))){
//				result[0] = temp;
//				result[1] = map.get(temp);
//				break;
//			}
//		}
		
		return result;
	}
	
}
