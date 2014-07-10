package TreeProblem;

import java.util.LinkedList;
import java.util.List;

public class MirrorBinaryTree {
	BinaryTree bt = new BinaryTree();

	public static void main(String[] args) {
		MirrorBinaryTree mbt = new MirrorBinaryTree();
		mbt.Test1();
	}

	private void Test1() {
		BinaryTreeNode pNode1 = bt.CreateBinaryTreeNode(8);
		BinaryTreeNode pNode2 = bt.CreateBinaryTreeNode(6);
		BinaryTreeNode pNode3 = bt.CreateBinaryTreeNode(10);
		BinaryTreeNode pNode4 = bt.CreateBinaryTreeNode(5);
		BinaryTreeNode pNode5 = bt.CreateBinaryTreeNode(7);
		BinaryTreeNode pNode6 = bt.CreateBinaryTreeNode(9);
		BinaryTreeNode pNode7 = bt.CreateBinaryTreeNode(11);
		bt.ConnectTreeNodes(pNode1, pNode2, pNode3);
		bt.ConnectTreeNodes(pNode2, pNode4, pNode5);
		bt.ConnectTreeNodes(pNode3, pNode6, pNode7);
//		MirrorRecursively(pNode1);
		MirrorRecursively1(pNode1);
		levelPrint(pNode1);

	}
//递归的方法
	private void MirrorRecursively(BinaryTreeNode pNode) {
		if (pNode == null)
			return;
		if (pNode.pLeft == null && pNode.pRight == null)
			return;
		BinaryTreeNode ptemp = pNode.pLeft;
		pNode.pLeft = pNode.pRight;
		pNode.pRight = ptemp;
		if (pNode.pLeft != null) {
			MirrorRecursively(pNode.pLeft);
		}
		if (pNode.pRight != null) {
			MirrorRecursively(pNode.pRight);
		}
	}
//非递归的方法
	private void MirrorRecursively1(BinaryTreeNode pNode) {
		List<BinaryTreeNode> list = new LinkedList<BinaryTreeNode>();
		if (pNode == null)
			return;
		list.add(pNode);
		while (list.size() > 0) {
			if (pNode.pLeft == null && pNode.pRight == null)
				continue;
			BinaryTreeNode temp = list.remove(0);
			BinaryTreeNode ptemp = temp.pLeft;
			temp.pLeft = temp.pRight;
			temp.pRight = ptemp;
//			System.out.print(temp.value + " ");
			if (temp.pLeft != null)
				list.add(temp.pLeft);
			if (temp.pRight != null)
				list.add(temp.pRight);
		}
	}

	private void levelPrint(BinaryTreeNode pNode) {
		List<BinaryTreeNode> list = new LinkedList<BinaryTreeNode>();
		if (pNode == null)
			return;
		list.add(pNode);
		while (list.size() > 0) {
			BinaryTreeNode temp = list.remove(0);
			System.out.print(temp.value + " ");
			if (temp.pLeft != null)
				list.add(temp.pLeft);
			if (temp.pRight != null)
				list.add(temp.pRight);
		}
	}
}
