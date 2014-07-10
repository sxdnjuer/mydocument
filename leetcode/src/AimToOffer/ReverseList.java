package AimToOffer;

import java.util.Scanner;

public class ReverseList {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String LineA = scan.nextLine();
		String[] InputA = LineA.split(" ");
		int[] A = new int[InputA.length];
		for (int i = 0; i < InputA.length; i++) {
			A[i] = Integer.parseInt(InputA[i]);
			// System.out.print(" "+num[i]);
		}
		ListNode head = new ListNode(A[0]);
		ListNode phead = head;
		for (int i = 1; i < InputA.length; i++) {
			ListNode temp = new ListNode(A[i]);
			head.next = temp;
			head = head.next;
		}
		head.next = null;
		ListNode pPrev = null;
		ReverseList rl = new ReverseList();
		// while (phead != null) {
		// System.out.print(" " + phead.val);
		// phead = phead.next;
		// }
		ListNode prv = rl.reverseNode(pPrev, phead);
		while (prv != null) {
			System.out.print(" " + prv.val);
			prv = prv.next;
		}
		ListNode ln = phead;
		ln.next = new ListNode(6);
		ListNode result = change(ln);
		while (result != null) {
			System.out.println("ln=" + result.val);
			result = result.next;
		}
	}
//递归逆转单链表
	public ListNode reverseNode(ListNode pPrev, ListNode pNode) {
		ListNode pReverseHead = null;
		// pReverseHead = pNode;
		ListNode pNext = pNode.next;
		
		pNode.next = pPrev;
		pPrev = pNode;
		if (pNext == null) {
			pReverseHead = pNode;
			// print(pNode);
			return pReverseHead;
		}
		pNode = pNext;
		pReverseHead = reverseNode(pPrev, pNode);
		return pReverseHead;
		// System.out.println();
		// return pReverseHead;
	}
//非递归逆转单链表
	public ListNode reverseNode1(ListNode pPrev, ListNode pNode) {
		ListNode pReverseHead = null;
		// pReverseHead = pNode;
		while (pNode != null) {
			ListNode pNext = pNode.next;
			if (pNext == null) {
				pReverseHead = pNode;		
			}
			pNode.next = pPrev;
			pPrev = pNode;
			pNode = pNext;
			// pReverseHead = reverseNode(pPrev , pNode);
		}
		// print(pNode);
		return pReverseHead;
		// System.out.println();
		// return pReverseHead;
	}

	public void print(ListNode prvhead) {
		while (prvhead != null) {
			System.out.println(prvhead.val + "prvhead.val ");
			prvhead = prvhead.next;
		}
	}

	public static ListNode  change(ListNode p) {
		ListNode q = p;
		ListNode k = p.next;
		p.next = null;
		p=k;
//		p.val = 9;
		p.next = q;
		return p;
	
	}

}

// class ListNode{
// int value;
// ListNode next;
// ListNode(int x) {
// value = x;
// next = null;
// }
// }
// 包内已经定义过了，因此这边无需再定义