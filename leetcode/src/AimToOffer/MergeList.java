package AimToOffer;

import java.util.Scanner;

public class MergeList {

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
		MergeList ml = new MergeList();
		ListNode pMergehead = ml.Merge(l1, l2);
		while (pMergehead != null) {
			System.out.print(pMergehead.val + " ");
			pMergehead = pMergehead.next;
		}
	}

	public ListNode Merge(ListNode phead1, ListNode phead2) {
		if (phead1 == null)
			return phead2;
		else if (phead2 == null)
			return phead1;
		ListNode pMergeHead = null;
		if (phead1.val < phead2.val) {
			pMergeHead = phead1;
			pMergeHead.next = Merge(phead1.next, phead2);
		} else {
			pMergeHead = phead2;
			pMergeHead.next = Merge(phead1, phead2.next);
		}
		return pMergeHead;
	}

}
