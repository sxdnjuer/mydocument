package onLineProblem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class MergeKSortedList {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int i = 0;
		ArrayList<ListNode> lists = new ArrayList<ListNode>();
		while (i < n) {
			int k = scan.nextInt();
			int[] num = new int[k];
			int j = 0;
			while (j < k) {
				num[j] = scan.nextInt();
				j++;
			}
			ListNode head = new ListNode(num[0]);
			constructlist(head, num);
			lists.add(head);
			i++;
		}
		ListNode list = mergeKLists(lists);
		while (list != null) {
			System.out.print(list.val + " ");
			list = list.next;
		}

	}

	public static void constructlist(ListNode node, int[] num) {
		int i = 1;
		while (i < num.length) {
			node.next = new ListNode(num[i]);
			node = node.next;
			i++;
		}
	}

	public static ListNode mergeKLists(ArrayList<ListNode> lists) {
		int size = lists.size();
		System.out.println("size=" + size);
		if (size == 0 || lists == null)
			return null;
		int i = 0;
		int NULL = 0;
		int min = 0;
		ListNode node = new ListNode(0);
		ListNode head = node;
		while (NULL != size) {
			int temp = Integer.MAX_VALUE;
			i = 0;
			NULL = 0;
			min = 0;
			while (i != size) {
				if (lists.get(i) != null && lists.get(i).val < temp) {
					temp = lists.get(i).val;
					min = i;
				} else if (lists.get(i) == null) {
					NULL++;
				}
				i++;
			}
			if (NULL != size) {
				lists.set(min, lists.get(min).next);
				node.next = new ListNode(temp);
				node = node.next;
			}
		}
		return head.next;
	}

}

// 4
// 3 1 4 8
// 4 0 2 4 6
// 5 1 6 8 9 10
// 2 3 5

