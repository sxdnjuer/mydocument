package kupai;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Deletenumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n = 15;
		List<Integer> list = new LinkedList<Integer>();
		for(int i = 0;i<15;i++){
			list.add(scan.nextInt());
		}
		Deletenumber dn = new Deletenumber();
		List<Integer> result= dn.deletenum(list, n);
		Iterator<Integer> it= result.iterator();
		
		while(it.hasNext()){
			System.out.print(it.next()+" ");
		}
	}
	
	public List<Integer> deletenum(List<Integer> list , int len){
		if(list==null||list.size()==0)return null;
		int hasthree = 1;
		int temp = 0;
		while(hasthree>0){
			hasthree = 0;
			int start = list.size()-1;
			int end = list.size()-1;
			int i;
			for(i=len-1;i>0;i--){
				if(list.get(i)==list.get(i-1)){
					start--;
				}
				else{
					if(end-start+1>=3){	
						for(int j = end;j>=start;j--){
							list.remove(j);
						}
						hasthree = 1;
						temp = len;
						len = len - (end-start+1);
					}
					start--;
					end = start;
				}
				temp = len;
			}
			if(i==0){
				if(end-start+1>=3){
					for(int j = end;j>=start;j--){
						list.remove(j);
					}
				}
			}
		}
		return list;
	}

}
