package TestProblems;

public class runtime_alibaba {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int result = new runtime_alibaba().f(30);
		System.out.println("result" + result);
		int array[][] = new int[][] { new int[] { 5, 6, 2 },
				new int[] { 1, 2, 3, 4, 5 }, new int[] { 1, 2 } };
//		System.out.println(array[2][2]);
//		int x = 127;
//		short y =-9;
//		int z = x+y;
//		System.out.println(z);
	}

	static int f(int x) {
		int s = 0;
		while (x-- > 0) {
			s += f(x);
			System.out.println(s);
		}
		return Math.max(s, 1);
	}
}
