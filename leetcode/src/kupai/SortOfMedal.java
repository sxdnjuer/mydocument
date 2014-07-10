package kupai;


/*
 * ÿ�ΰ��˻��ڼ䣬��Ҷ��ǳ���ע���ư������������
 
�������Ǽ��轱�ư���������򣬰����ȼ��Ӹߵ������£� 
1������ �����������ǰ�棻
2������ �����������ǰ�棻
3��ͭ�� �����������ǰ�棻
4�������������������޷��������Σ����Թ������Ƶ��ֵ������С�
 
���Ǽ���������Ʋ�����20���ַ������ֽ�����������100���Ҵ��ڵ���0��
sample:
5
China 32 28 34
England 12 34 22
France 23 33 2
Japan 12 34 25
Rusia 23 33 2
�����
China 32 28 34
France 23 33 2
Rusia 23 33 2
Japan 12 34 25
England 12 34 22
 * */
import java.util.Scanner;
public class SortOfMedal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		String[] country = new String[n];
		int[] first = new int[n];
		int[] second = new int[n];
		int[] third = new int[n];
		scan.nextLine();
		for (int i = 0; i < n; i++) {
			String line = scan.nextLine();
			String[] num = line.split(" ");
			country[i] = num[0];
			first[i] = Integer.parseInt(num[1]);
			second[i] = Integer.parseInt(num[2]);
			third[i] = Integer.parseInt(num[3]);
		}
		SortOfMedal som = new SortOfMedal();
		som.sortfirst(country, first, second, third, n);
		som.sortsecond(country, first, second, third, n);
		som.thirdsecond(country, first, second, third, n);
		som.country(country, first, second, third, n);
		for (int i = 0; i < n; i++) {
			System.out.println(country[i]+" "+first[i]+" "+second[i]+" "+third[i]);
		}
		
	}

	public void sortfirst(String[] country, int[] first, int[] second, int[] third,
			int n) {
		for (int i = 0; i < n; i++) {
			int max = first[i];
			int flag = i;
			for (int j = i + 1; j < n; j++) {
				if (first[j] > max) {
					max = first[j];
					flag = j;
				}
			}
			if(flag!=i){
				swap(first , i , flag);
				swap(second, i ,flag);
				swap(third, i ,flag);
				swap_country(country , i , flag);
			}
		}
	}
	public void sortsecond(String[] country, int[] first, int[] second, int[] third,
			int n){
		int start = 0; 
		int end = 0;
		for(int i = 1;i<n;i++){
			if(first[i]==first[i-1]){
				end++;
			}
			else{
				if(start!=end){
					firstequal(country, first, second, third , start , end);
				}
				end++;
				start = end;
			}
		}
	}
	
	public void firstequal(String[] country, int[] first, int[] second, int[] third,int start , int end){
		for(int i= start ; i<=end;i++){
			int max = second[i];
			int flag = i;
			for (int j = i + 1; j <= end; j++) {
				if (second[j] > max) {
					max = second[j];
					flag = j;
				}
			}
			if(flag!=i){
				swap(first , i , flag);
				swap(second, i ,flag);
				swap(third, i ,flag);
				swap_country(country , i , flag);
			}
		}
	}
	
	public void thirdsecond(String[] country, int[] first, int[] second, int[] third,
			int n){
		int start = 0; 
		int end = 0;
		for(int i = 1;i<n;i++){
			if(second[i]==second[i-1]){
				end++;
			}
			else{
				if(start!=end){
					thirdequal(country, first, second, third , start , end);
				}
				end++;
				start = end;
			}
		}
	}
	public void secondequal(String[] country, int[] first, int[] second, int[] third,int start , int end){
		for(int i= start ; i<=end;i++){
			int max = third[i];
			int flag = i;
			for (int j = i + 1; j <=end; j++) {
				if (third[j] > max) {
					max = third[j];
					flag = j;
				}
			}
			if(flag!=i){
				swap(first , i , flag);
				swap(second, i ,flag);
				swap(third, i ,flag);
				swap_country(country , i , flag);
			}
		}
	}
	public void country(String[] country, int[] first, int[] second, int[] third,
			int n){
		int start = 0; 
		int end = 0;
		for(int i = 1;i<n;i++){
			if(third[i]==third[i-1]){
				end++;
			}
			else{
				if(start!=end){
					thirdequal(country, first, second, third , start , end);
				}
				end++;
				start = end;
			}
		}
	}
	public void thirdequal(String[] country, int[] first, int[] second, int[] third,int start , int end){
		for(int i= start ; i<=end;i++){
			String min = country[i];
			
			int flag = i;
			for (int j = i + 1; j <=end; j++) {
				if (compareTo(country[j], min)==false) {
					min = country[j];
					flag = j;
				}
			}
			if(flag!=i){
				swap(first , i , flag);
				swap(second, i ,flag);
				swap(third, i ,flag);
				swap_country(country , i , flag);
			}
		}
	}
	public boolean compareTo(String str1 , String str2){
		int len1 = str1.length();
		int len2 = str2.length();
		for(int k = 0;k<Math.min(len1, len2);k++){
			if(str1.charAt(k)>str2.charAt(k)){
				return true;
			}
			else if(str1.charAt(k)<str2.charAt(k)){
				return false;
			}
		}
		if(len1>=len2)return true;
		else return false;
	}
	
	
	public void swap(int[] array , int i ,int j){
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}
	public void swap_country(String[] array ,int i ,int j){
		String temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}

}
