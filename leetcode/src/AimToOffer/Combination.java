package AimToOffer;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

//�ַ������������
public class Combination {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String string = scan.next();
		Stack<Character> result = new Stack<Character>();
		System.out.println("�ַ�����ȫ����Ϸ���0");
//		Allcombine(string, result);
		System.out.println("�ַ�����ȫ����Ϸ���1");
		for (int i = 1; i <= string.length(); i++) {
			Allcombineone(string, i, result);// �ַ�����ȫ�����
		}
		System.out.println("�ַ�����ȫ����Ϸ���2");
//		Allcombinetwo(string);
		System.out.println("�ַ�����ȫ������");
//		permutation(string.toCharArray(), 0);

	}

	public static void Allcombine(String string, Stack<Character> result) {
		if (string.length() == 0) {
			Iterator<Character> it = result.iterator();
			while (it.hasNext()) {
				System.out.print(it.next());
			}
			System.out.println();
			return;
		}
		// if (string.length() == 0 || string == null) {
		// return;
		// }

		result.push(string.charAt(0));
		Allcombine(string.substring(1, string.length()), result);
		result.pop();
//		System.out.println("�ַ�������" + string.length());
		Allcombine(string.substring(1, string.length()), result);

	}

	public static void Allcombineone(String string, int number,
			Stack<Character> result) {
		if (number == 0) {
			Iterator<Character> it = result.iterator();
			while (it.hasNext()) {
				System.out.print(it.next());
			}
			System.out.println();
			return;
		}
		if (string.length() == 0 || string == null) {
			return;
		}
		result.push(string.charAt(0));
		Allcombineone(string.substring(1, string.length()), number - 1, result);
		result.pop();
		Allcombineone(string.substring(1, string.length()), number, result);

	}

	// ��1��N-1�ö����Ʊ�ʾ����1�������λ�õ���ĸ�������ǳ��ã���Ҫ�õ���λ����
	public static void Allcombinetwo(String str) {
		int N = str.length();
		int num = (int) Math.pow(2.0, N);// Cn0+Cn1+Cn2+......+Cnn=2��n�η�
		for (int i = 1; i < num; i++) {
			for (int j = 0; j < N; j++) {
				if (((i >> j) & 1) != 0)
					System.out.print(str.charAt(j));
			}
			System.out.println();
		}
	}

	// ȫ����
	public static void permutation(char[] str, int start) {
		if (str == null || str.length == 0) {
			return;
		}
		if (start == str.length) {
			System.out.println(str);
			return;
		}
		for (int i = start; i < str.length; i++) {
			if (isExist(str, start, i)) {//�����������������ظ���ĸ�µ�ȫ����
				char temp1 = str[i];
				str[i] = str[start];
				str[start] = temp1;
				permutation(str, start + 1);
				char temp2 = str[i];
				str[i] = str[start];
				str[start] = temp2;
			}
		}
	}
	//�ж�str[i]��(start��i-1)�ķ�Χ���Ƿ���ֹ�
	public static boolean isExist(char[] str , int start , int i){
		for(int j =start;j<i;j++ ){
			if(str[j]==str[i])
				return false;
		}
		return true;
	}
}
