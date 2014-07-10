package onLineProblem;
//链表的插入排序算法
import java.util.Scanner;

public class Insertion_Sort_List {

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
		Insertion_Sort_List sl = new Insertion_Sort_List();
		ListNode sortNode = sl.sortList(head);
		while (sortNode != null) {
			System.out.print(sortNode.val + " ");
			sortNode = sortNode.next;
		}
	}
	public ListNode sortList(ListNode head) {
		ListNode temp = head;
		while (temp != null) {
			temp = temp.next;
		}

		head = insertionSortList(head);// 调整结点的位置

		return head;
	}
	
	public ListNode insertionSortList(ListNode head) {
		if(head==null) return null;
		if(head.next==null) return head;
		ListNode curNode = head;
		while(curNode.next!=null){
			ListNode flagNode = head;
			if(curNode.next.val<head.val){
				ListNode temp = curNode.next;
				curNode.next = curNode.next.next; 
				temp.next = head;
				head = temp;
			}
			else{
				while(flagNode.next.val<=curNode.next.val&&flagNode!=curNode){
					flagNode = flagNode.next;
				}
				if(flagNode==curNode){
					curNode = curNode.next;
				}
				else{
					ListNode temp = curNode.next;
					curNode.next = curNode.next.next;
					temp.next = flagNode.next;
					flagNode.next = temp;
				}
			}
		}
		
		return head;
    }

}
