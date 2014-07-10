package TreeProblem;


class BinaryTreeNode {
	int value;
	int nMaxLeft;  //左子树的最大距离
	int nMaxRight; //右子树的最大距离
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
