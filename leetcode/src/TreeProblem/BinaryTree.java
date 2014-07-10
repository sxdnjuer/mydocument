package TreeProblem;


class BinaryTreeNode {
	int value;
	int nMaxLeft;  //��������������
	int nMaxRight; //��������������
	BinaryTreeNode pLeft;
	BinaryTreeNode pRight;
	BinaryTreeNode pNext;
}
public class BinaryTree {
	public BinaryTreeNode CreateBinaryTreeNode(int value) {
		BinaryTreeNode pNode = new BinaryTreeNode();
		pNode.value = value;
		pNode.pLeft = null;
		pNode.pRight = null;
		return pNode;
	}

	public void ConnectTreeNodes(BinaryTreeNode pParent, BinaryTreeNode pLeft,
			BinaryTreeNode pRight) {
		if (pParent != null) {
			pParent.pLeft = pLeft;
			pParent.pRight = pRight;
		}
	}
}
