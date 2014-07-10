package TreeProblem;

public class Convert_BinaryTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test1();

//		StringBuffer sb = new StringBuffer("Hello ");
//		StringBuffer str = new StringBuffer("Hi");
//		System.out.println("Before change, sb = " + sb);
//		System.out.println("Before change, str = " + str);
//		changeData(str, sb);
//		System.out.println("After changeData(n), sb = " + sb);
//		System.out.println("After change, str = " + str);
	}

	public static void changeData(StringBuffer str, StringBuffer strBuf) {
		str = strBuf ;
		str.append("World!");
	}

	public static void Test1() {
		BinaryTree bt = new BinaryTree();
		BinaryTreeNode pNode1 = bt.CreateBinaryTreeNode(6);
		BinaryTreeNode pNode2 = bt.CreateBinaryTreeNode(4);
		BinaryTreeNode pNode3 = bt.CreateBinaryTreeNode(8);
		BinaryTreeNode pNode4 = bt.CreateBinaryTreeNode(3);
		BinaryTreeNode pNode5 = bt.CreateBinaryTreeNode(5);
		BinaryTreeNode pNode6 = bt.CreateBinaryTreeNode(7);
		BinaryTreeNode pNode7 = bt.CreateBinaryTreeNode(9);
		bt.ConnectTreeNodes(pNode1, pNode2, pNode3);
		bt.ConnectTreeNodes(pNode2, pNode4, pNode5);
		bt.ConnectTreeNodes(pNode3, pNode6, pNode7);
		System.out.println(Convert(pNode1).value);
	}

	public static BinaryTreeNode Convert(BinaryTreeNode pRoot) {
		BinaryTreeNode pLastNode = null;
		pLastNode = ConvertNode(pRoot, pLastNode);
		BinaryTreeNode pHeadNode = pLastNode;
		while (pHeadNode != null && pHeadNode.pLeft != null) {
			pHeadNode = pHeadNode.pLeft;
			System.out.println(pHeadNode.value);
		}
		return pHeadNode;
	}

	// pLastNode已排好的双向链表中的最后一个结点
	public static BinaryTreeNode ConvertNode(BinaryTreeNode pNode,
			BinaryTreeNode pLastNode) {
		if (pNode == null) {
			return pLastNode;
		}
		BinaryTreeNode pCurrent = pNode;
		if (pCurrent.pLeft != null) {
			pLastNode = ConvertNode(pCurrent.pLeft, pLastNode);
		}
		pCurrent.pLeft = pLastNode;
		if (pLastNode != null) {
			pLastNode.pRight = pCurrent;
		}
		pLastNode = pCurrent;
		if (pCurrent.pRight != null) {
			pLastNode = ConvertNode(pCurrent.pRight, pLastNode);
		}
		return pLastNode;
	}
}
