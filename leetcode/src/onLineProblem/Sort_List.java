package onLineProblem;

//LeetCode Sort_List
/*单链表的快速排序（结果超时）方法如下：
 由于单链表不能像数组那样随机存储，和数组的快排序相比较，还是有一些需要注意的细节：
 1、支点的选取，由于不能随机访问第K个元素，因此每次选择支点时可以取待排序那部分链表的头指针。

 2、遍历量表方式，由于不能从单链表的末尾向前遍历，因此使用两个指针分别向前向后遍历的策略实效，

 事实上，可以可以采用一趟遍历的方式将较小的元素放到单链表的左边。具体方法为：

 1)定义两个指针pslow，pfast，其中pslow指向单链表的头结点，pfast指向单链表头结点的下一个结点;

 2)使用pfast遍历单链表，每遇到一个比支点小的元素，就令pslow=pslow->next，然后和pslow进行数据交换。

 3、交换数据方式，直接交换链表数据指针指向的部分，不必交换链表节点本身。
 */

/*应该使用归并的方法对链表进行排序
 * 先将链表进行分割，平均每次都分成两份，当只剩下一个结点时，进行合并；
 * 合并的思想即对两个有序链表进行合并，注意空间复杂度为O(1)，而不是我之前的方法O(n);
 * 合并两个有序链表即调整每个结点的next域；
 */
import java.util.Scanner;

//class ListNode{
//	int value;
//	ListNode next;
//	ListNode(int value){
//		this.value = value;
//	}
//}
public class Sort_List {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int i = 1;
		ListNode temp = new ListNode(scan.nextInt());
		ListNode head = temp;
		while (i < n) {
			temp.next = new ListNode(scan.nextInt());
			temp = temp.next;
			i++;
		}
		Sort_List sl = new Sort_List();
		ListNode sortNode = sl.sortList(head);
		while (sortNode != null) {
			System.out.print(sortNode.val + " ");
			sortNode = sortNode.next;
		}
	}

	public ListNode sortList(ListNode head) {
		int length = 0;
		ListNode temp = head;
		while (temp != null) {
			temp = temp.next;
			length++;
		}
		// System.out.println("length=" + length);
		// quickSort(head, length);//改变结点的值
		head = mergeSort(head);// 调整结点的位置
		// System.out.println("end_head.val=" + head.val);
		return head;
	}

	public void quickSort(ListNode head, int length) {
		if (length == 0 || length == 1) {
			return;
		}
		ListNode flag = head;
		ListNode pSlow = head;
		ListNode pFast = head.next;
		int temp = 0;
		int pfast_count = 1;
		int pslow_count = 0;
		while (pfast_count < length) {
			if (pFast.val <= flag.val) {
				pslow_count++;
				pSlow = pSlow.next;
				temp = pSlow.val;
				pSlow.val = pFast.val;
				pFast.val = temp;
			}
			pFast = pFast.next;
			pfast_count++;
		}
		temp = pSlow.val;
		pSlow.val = flag.val;
		flag.val = temp;
		// System.out.println("pslow_count=" + pslow_count);
		quickSort(flag, pslow_count);
		quickSort(pSlow.next, length - pslow_count - 1);
	}

	// 先将链表进行分割，再进行合并
	public ListNode mergeSort(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode l2 = getMiddle(head);
		ListNode l1 = head;
		l1 = mergeSort(l1);
		l2 = mergeSort(l2);
		head = mergeList(l1, l2);
		return head;
	}

	// 将链表平均分成两个，mid为第二个链表的开始结点
	public ListNode getMiddle(ListNode head) {
		ListNode first_end = null, fast = null, mid = null;
		fast = mid = head;
		while (fast != null && fast.next != null) {// 利用快慢指针找链表的中间位置并将链表1分为2
			fast = fast.next.next;
			first_end = mid;
			mid = mid.next;
		}
		first_end.next = null;
		return mid;
	}

	// 对两个有序链表进行合并，空间复杂度为O(1)
	public ListNode mergeList(ListNode p1, ListNode p2) {
		if (p1 == null && p2 == null)
			return null;
		if (p1 == null)
			return p2;
		if (p2 == null)
			return p1;
		ListNode minNode;
		ListNode mergehead;
		if (p1.val < p2.val) {
			mergehead = p1;
			minNode = p1;
			p1 = p1.next;
		} else {
			mergehead = p2;
			minNode = p2;
			p2 = p2.next;
		}
		while (p1 != null && p2 != null) {
			if (p1.val < p2.val) {
				minNode.next = p1;
				p1 = p1.next;
				minNode = minNode.next;
			} else {
				minNode.next = p2;
				p2 = p2.next;
				minNode = minNode.next;
			}
		}
		if (p1 != null) {
			minNode.next = p1;
		} else {
			minNode.next = p2;
		}
		return mergehead;
	}

}
