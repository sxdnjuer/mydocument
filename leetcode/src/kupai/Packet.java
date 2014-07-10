package kupai;

import java.util.Scanner;

public class Packet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int m = scan.nextInt();
		int n = scan.nextInt();
		int packet = n - m;
		Packet pac = new Packet();
		System.out.println(pac.packetmoney(packet));
	}
	
	public String packetmoney(int packet){
		if(packet>100||packet<0) return null;
		String result = "";
		int count_50 = 0;
		int count_20 = 0;
		int count_10 = 0;
		int count_5 = 0;
		int count_1 = 0;
		while((packet-50)>=0){
			count_50++;
			packet = packet - 50;
		}
		while((packet-20)>=0){
			count_20++;
			packet = packet-20;
		}
		while((packet-10)>=0){
			count_10++;
			packet = packet-10;
		}
		while((packet-5)>=0){
			count_5++;
			packet = packet-5;
		}
		while((packet-1)>=0){
			count_1++;
			packet = packet-1;
		}
		result = result+count_50+count_20+count_10+count_5+count_1;
		return result;
	}

}
