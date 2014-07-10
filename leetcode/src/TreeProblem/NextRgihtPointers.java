package TreeProblem;

import java.util.LinkedList;
import java.util.Queue;

public class NextRgihtPointers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NextRgihtPointers nrp = new NextRgihtPointers();
		nrp.Test1();

	}

	public void Test1() {
		BinaryTree bt = new BinaryTree();
		// TODO Auto-generated method stub
		BinaryTreeNode pNode1 = bt.CreateBinaryTreeNode(1);
		BinaryTreeNode pNode2 = bt.CreateBinaryTreeNode(2);
		BinaryTreeNode pNode3 = bt.CreateBinaryTreeNode(0);
		BinaryTreeNode pNode4 = bt.CreateBinaryTreeNode(4);
		BinaryTreeNode pNode5 = bt.CreateBinaryTreeNode(5);
		BinaryTreeNode pNode6 = bt.CreateBinaryTreeNode(6);
		BinaryTreeNode pNode7 = bt.CreateBinaryTreeNode(7);
		bt.ConnectTreeNodes(pNode1, pNode2, pNode3);
		bt.ConnectTreeNodes(pNode2, pNode4, pNode5);
		bt.ConnectTreeNodes(pNode3, pNode6, pNode7);
		connect(pNode1);
		System.out.println(pNode1.pLeft.pNext.value);
	}

	public void connect(BinaryTreeNode root) {
		Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
		if (root == null)
			return;
		queue.add(root);
		int count = 1;
		while (queue.size() != 0) {
			root = queue.remove();
			System.out.println(root.value);
			count++;
			if (root.pLeft != null)
				queue.add(root.pLeft);
			if (root.pRight != null)
				queue.add(root.pRight);
			if ((count & (count - 1)) == 0)//判断一个数是否为2的整数次方
				root.pNext = null;
			else {
				root.pNext = queue.peek();
			}
//			System.out.println(count);
		}

	}

}
