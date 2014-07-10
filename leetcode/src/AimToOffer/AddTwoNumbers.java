package AimToOffer;

import java.util.Scanner;
//注意几种特殊情况
//当l1还剩下节点，而l2没有的时候的情况，特别注意最后有进位的时候
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class AddTwoNumbers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String LineA = scan.nextLine();
		String LineB = scan.nextLine();
		String[] InputA = LineA.split(" ");
		String[] InputB = LineB.split(" ");
		int[] A = new int[InputA.length];
		int[] B = new int[InputB.length];
		for (int i = 0; i < InputA.length; i++) {
			A[i] = Integer.parseInt(InputA[i]);
			// System.out.print(" "+num[i]);
		}
		for (int i = 0; i < InputB.length; i++) {
			B[i] = Integer.parseInt(InputB[i]);
			// System.out.print(" "+num[i]);
		}
		ListNode head = new ListNode(A[0]);
		ListNode l1 = head;
		for (int i = 1; i < InputA.length; i++) {
			ListNode temp = new ListNode(A[i]);
			head.next = temp;
			head = head.next;
		}
		head.next = null;
		// while(l1!=null){
		// System.out.println("l1="+l1.val);
		// l1 = l1.next;
		// }
		ListNode head2 = new ListNode(B[0]);
		ListNode l2 = head2;
		for (int i = 1; i < InputB.length; i++) {
			ListNode temp = new ListNode(B[i]);
			head2.next = temp;
			head2 = head2.next;
		}
		head2.next = null;
		AddTwoNumbers atn = new AddTwoNumbers();
		ListNode result = atn.addtwoNum(l1, l2);
		while (result != null) {
			System.out.println("result=" + result.val);
			result = result.next;
		}
	}

	public ListNode addtwoNum(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null)
			return null;
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		ListNode result = null;
		ListNode head = result;
		int carry = 0;
		while (l1 != null && l2 != null) {
			int temp = l1.val + l2.val + carry;
			carry = 0;
			if (temp >= 10) {
				temp = temp - 10;
				carry = 1;
			}
			ListNode ln = new ListNode(temp);
			if (result == null) {
				result = ln;
				head = result;
			} else {
				head.next = ln;
				head = head.next;
			}
			l1 = l1.next;
			l2 = l2.next;

		}
		while (l1 != null) {
			int temp = l1.val + carry;
			carry = 0;
			if (temp >= 10) {
				temp = temp - 10;
				carry = 1;
			}
			ListNode ln = new ListNode(temp);
			head.next = ln;
			head = head.next;
			l1 = l1.next;
		}
		while (l2 != null) {
			int temp = l2.val + carry;
			carry = 0;
			if (temp >= 10) {
				temp = temp - 10;
				carry = 1;
			}
			ListNode ln = new ListNode(temp);
			head.next = ln;
			head = head.next;
			l2 = l2.next;
		}
		if(carry == 1){
			ListNode ln = new ListNode(1);
			head.next = ln;
			head = head.next;
		}
		head.next = null;
		return result;
	}
}
