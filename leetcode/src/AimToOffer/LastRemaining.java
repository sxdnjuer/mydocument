package AimToOffer;

import java.util.Scanner;


public class LastRemaining {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		// ListNode list = new ListNode(2);
		int n = scan.nextInt();
		int m = scan.nextInt();
		ListNode head = new ListNode(0);
		ListNode temp = head;
		for (int i = 1; i < n; i++) {
			ListNode list = new ListNode(i);
			temp.next = list;
			temp = list;
		}
		temp.next = head;
		System.out.println(LastOne(head, m).val);
	}

	public static ListNode LastOne(ListNode head, int m) {
		if (head == null || m < 1)
			return null;
		ListNode temp = head;
		if (m == 1) {
			while (temp.next != head) {
				temp = temp.next;
			}
			return temp;
		}
		while (head.next != null) {
			for (int i = 0; i < m - 1; i++) {
				temp = head;
				head = head.next;
			}
			temp.next = head.next;
			head = temp.next;
			if (head.val == temp.val) {
				head.next = null;
			}
		}
		return head;
	}

}
