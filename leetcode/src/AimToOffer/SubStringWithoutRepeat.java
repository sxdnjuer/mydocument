package AimToOffer;

import java.util.Scanner;
//KMP�㷨������Ľⷨ��ʱ
//ԭ�����ڲ�����һ���ַ��Ƿ����ַ�����ʱ��Ӧ����һ��exist�����ʾ�����ж��Ƿ���ǰ����ִ���
//�����ڣ������whileѭ����Ѱ�����򲻽���whileѭ��
//������Ľⷨ������һ���ַ��Ƿ�����ڵ�ǰ�ִ��У���������Ѱ�������ʱ
public class SubStringWithoutRepeat {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("�����������Ŀ������");
		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine();
		String[] input = line.split(" ");
		SubStringWithoutRepeat sswr = new SubStringWithoutRepeat();
		int result = sswr.findLongest(input);
//		String s = null;
//		int flag = s.length();
//		System.out.println(flag);
		System.out.println("result=" + result);
	}

	public int findLongest(String[] num) {
		if (num == null) {
			return -1;
		}
		int first = 0;
		int last = 0;
		int longest = 1;
		int temp = 0;
		int move = 0;
		while (last != num.length - 1) {
			move = first;
			while(!num[last+1].equals(num[move])&&move!=last+1){
				move++;
			}
			if(move!=last+1)
				first=move+1;
//			System.out.println("first=" + first + "move=" + move);
//			int repeat = contain(num,first ,last + 1);
//			if (repeat == -1) {
//				temp = temp + 1;
			last++;
//			} else {
//				first = first + repeat + 1;
			temp = last - first + 1;
//			}
			if (temp > longest) {
				longest = temp;
			}
//			System.out.println("repeat=" + repeat + "temp=" + temp);
		}
		return longest;
	}

//	public int contain(String[] num,int start, int next) {
//		int i;
//		for (i = start; i < next; i++) {
////			System.out.println("num[i]" + num[i] + " num[next]" + num[next]);
//			if (num[next].equals(num[i])) {
//				return i;
//			}
//		}
//		return -1;
//	}

}
//
//public int lengthOfLongestSubstring(String s) {
//    if(s.length()==0) {
//		return -1;
//	}
//	int first = 0;
//	int last = 0;
//	int longest = 1;
//	int temp = 0;
//	int move = 0;
//	while (last != s.length() - 1) {
//		move = first;
//		while(s.charAt(last+1)!=s.charAt(move)&&move!=last+1){
//			move++;
//		}
//		if(move!=last+1)
//			first=move+1;
//		System.out.println("first=" + first + "move=" + move);
//		last++;
//		temp = last - first + 1;
//		if (temp > longest) {
//			longest = temp;
//		}
//	}
//	return longest;
//}
//
//public int contain(String num, int start, int next) {
//	int i;
//	for (i = start; i < next; i++) {
//		if (num.charAt(next)==num.charAt(i)){
//			return i;
//		}
//	}
//	return -1;
//}
