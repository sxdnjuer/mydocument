package TreeProblem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SumRoottoLeafnumber {
	BinaryTree bt = new BinaryTree();
	int totalresult = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SumRoottoLeafnumber srtln = new SumRoottoLeafnumber();
		srtln.Test1();
	}

	private void Test1() {
		BinaryTreeNode pNode1 = bt.CreateBinaryTreeNode(9);
		BinaryTreeNode pNode2 = bt.CreateBinaryTreeNode(2);
		BinaryTreeNode pNode3 = bt.CreateBinaryTreeNode(3);
		BinaryTreeNode pNode4 = bt.CreateBinaryTreeNode(4);
		BinaryTreeNode pNode5 = bt.CreateBinaryTreeNode(5);
		BinaryTreeNode pNode6 = bt.CreateBinaryTreeNode(6);
		BinaryTreeNode pNode7 = bt.CreateBinaryTreeNode(7);
		bt.ConnectTreeNodes(pNode1, pNode2, pNode3);
		bt.ConnectTreeNodes(pNode2, pNode4, pNode5);
		bt.ConnectTreeNodes(pNode3, pNode6, pNode7);
		List<String> list = new ArrayList<String>();
		sum(pNode1, 0);
		System.out.println("totalreuslt = " + totalresult);
		sumNumbers(pNode1, "", list);
		Iterator<String> iterator = list.iterator();
		int total = 0;
		while (iterator.hasNext()) {
			total = total + Integer.parseInt(iterator.next());
		}
		System.out.println("total = " + total);
	}

	private void sumNumbers(BinaryTreeNode pNode, String temp,
			List<String> result) {
		if (pNode == null)
			return;
		temp = temp + pNode.value;
		if (pNode.pLeft == null && pNode.pRight == null) {
			result.add(temp);
			// System.out.println(temp);
			return;
		}
		if (pNode.pLeft != null) {
			sumNumbers(pNode.pLeft, temp, result);
		}
		if (pNode.pRight != null) {
			sumNumbers(pNode.pRight, temp, result);
		}
	}

	private void sum(BinaryTreeNode pNode, int temp) {
		if (pNode == null)
			return;
		temp = temp * 10 + pNode.value;
		if (pNode.pLeft == null && pNode.pRight == null) {
			totalresult = totalresult + temp;
		}
		if (pNode.pLeft != null) {
			sum(pNode.pLeft, temp);
		}
		if (pNode.pRight != null) {
			sum(pNode.pRight, temp);
		}
		return;
	}
}
