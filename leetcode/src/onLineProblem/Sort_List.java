package onLineProblem;

//LeetCode Sort_List
/*������Ŀ������򣨽����ʱ���������£�
 ���ڵ���������������������洢��������Ŀ�������Ƚϣ�������һЩ��Ҫע���ϸ�ڣ�
 1��֧���ѡȡ�����ڲ���������ʵ�K��Ԫ�أ����ÿ��ѡ��֧��ʱ����ȡ�������ǲ��������ͷָ�롣

 2����������ʽ�����ڲ��ܴӵ������ĩβ��ǰ���������ʹ������ָ��ֱ���ǰ�������Ĳ���ʵЧ��

 ��ʵ�ϣ����Կ��Բ���һ�˱����ķ�ʽ����С��Ԫ�طŵ����������ߡ����巽��Ϊ��

 1)��������ָ��pslow��pfast������pslowָ�������ͷ��㣬pfastָ������ͷ������һ�����;

 2)ʹ��pfast����������ÿ����һ����֧��С��Ԫ�أ�����pslow=pslow->next��Ȼ���pslow�������ݽ�����

 3���������ݷ�ʽ��ֱ�ӽ�����������ָ��ָ��Ĳ��֣����ؽ�������ڵ㱾��
 */

/*Ӧ��ʹ�ù鲢�ķ����������������
 * �Ƚ�������зָƽ��ÿ�ζ��ֳ����ݣ���ֻʣ��һ�����ʱ�����кϲ���
 * �ϲ���˼�뼴����������������кϲ���ע��ռ临�Ӷ�ΪO(1)����������֮ǰ�ķ���O(n);
 * �ϲ�����������������ÿ������next��
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
		// quickSort(head, length);//�ı����ֵ
		head = mergeSort(head);// ��������λ��
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

	// �Ƚ�������зָ�ٽ��кϲ�
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

	// ������ƽ���ֳ�������midΪ�ڶ�������Ŀ�ʼ���
	public ListNode getMiddle(ListNode head) {
		ListNode first_end = null, fast = null, mid = null;
		fast = mid = head;
		while (fast != null && fast.next != null) {// ���ÿ���ָ����������м�λ�ò�������1��Ϊ2
			fast = fast.next.next;
			first_end = mid;
			mid = mid.next;
		}
		first_end.next = null;
		return mid;
	}

	// ����������������кϲ����ռ临�Ӷ�ΪO(1)
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
