package onLineProblem;

/*思路：
 1. 每一站的代价为gas-cost, 也就是求从哪一站开始累加代价和总是大于0。
 	一开始用了一个O（n^2）的解法，超时
 2. 如果所有站的代价和大于0，则所求的路线必定存在。如果总代价〉=0,
 	从序号0开始求代价和，如果代价和小于0，则不是从本站或者本站之前的某一个代价
 	大于0的站开始，必从下一站即之后的站开始，而且这样的站必定存在O（n）
 */
public class Gas_Station {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] gas = new int[2];
		gas[0] = 1;
		gas[1] = 2;
		int[] cost = new int[2];
		cost[0] = 2;
		cost[1] = 1;
		Gas_Station gs = new Gas_Station();
		System.out.println(gs.canCompleteCircuit(gas, cost));
	}

	// 时间复杂度O(n2)，超时
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int[] remainder = new int[gas.length];
		for (int i = 0; i < gas.length; i++) {
			remainder[i] = gas[i] - cost[i];
			System.out.println("remainder[" + i + "]" + remainder[i]);
		}

		for (int i = 0; i < gas.length; i++) {
			int total = 0;
			int j = i;
			System.out.println("j=" + j);
			for (; j < gas.length + i; j++) {
				total = total + remainder[j % gas.length];
				if (total < 0) {
					System.out.println("total=" + total);
					break;
				}
			}
			if (j == gas.length + i) {
				return i;
			}
		}
		return -1;
	}
	//时间复杂度 O(n)
	public int canCompleteCircuit_2(int[] gas, int[] cost){
		int[] remainder = new int[gas.length];
		for (int i = 0; i < gas.length; i++) {
			remainder[i] = gas[i] - cost[i];
		}
		int sum = 0;
		int total = 0;
		int start = 0;
		for(int i=0;i<gas.length;i++){
			sum = sum+remainder[i];
			total = total+remainder[i];
			if(sum<0){
				start = (i+1)%gas.length;
				sum = 0;
			}
		}
		if(total<0)
			return -1;
		else
			return start;
	}

}
