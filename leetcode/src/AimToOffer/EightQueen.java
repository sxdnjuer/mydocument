package AimToOffer;

public class EightQueen {
	static int count = 0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String string = "01234567";
		long start  = System.currentTimeMillis();
		permutation(string.toCharArray(), 0);
		System.out.println(count);
		long end  = System.currentTimeMillis();
		System.out.println(end-start);
				
	}

	public static void permutation(char[] str, int start) {
		if (str == null || str.length == 0) {
			return;
		}
		if (start == str.length) {
			if(isEightQueen(str)){
				count++;
			}
			return;
		}
		for (int i = start; i < str.length; i++) {
			// if (isExist(str, start, i)) {//加上这个条件变成有重复字母下的全排列
			char temp1 = str[i];
			str[i] = str[start];
			str[start] = temp1;
			permutation(str, start + 1);
			char temp2 = str[i];
			str[i] = str[start];
			str[start] = temp2;
		}
		// }
	}
	
	public static boolean isEightQueen(char[] str){
		for(int i=0;i<str.length;i++){
			for(int j = i+1;j<str.length;j++){
				int temp = str[j]-str[i];
//				System.out.println(temp);
				if(temp<0){
					temp = - temp;
				}
				if((j-i)==temp){
					return false;
				}
			}
		}
		return true;
	}
}
