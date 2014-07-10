package onLineProblem;

/*˼·��
 1. ÿһվ�Ĵ���Ϊgas-cost, Ҳ���������һվ��ʼ�ۼӴ��ۺ����Ǵ���0��
 	һ��ʼ����һ��O��n^2���Ľⷨ����ʱ
 2. �������վ�Ĵ��ۺʹ���0���������·�߱ض����ڡ�����ܴ��ۡ�=0,
 	�����0��ʼ����ۺͣ�������ۺ�С��0�����Ǵӱ�վ���߱�վ֮ǰ��ĳһ������
 	����0��վ��ʼ���ش���һվ��֮���վ��ʼ������������վ�ض�����O��n��
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

	// ʱ�临�Ӷ�O(n2)����ʱ
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
	//ʱ�临�Ӷ� O(n)
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
