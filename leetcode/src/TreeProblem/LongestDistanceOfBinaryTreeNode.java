package TreeProblem;

public class LongestDistanceOfBinaryTreeNode {

	static int max = 0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTree bt = new BinaryTree();
		BinaryTreeNode pNode1 = bt.CreateBinaryTreeNode(1);
		BinaryTreeNode pNode2 = bt.CreateBinaryTreeNode(2);
		BinaryTreeNode pNode3 = bt.CreateBinaryTreeNode(3);
		BinaryTreeNode pNode4 = bt.CreateBinaryTreeNode(4);
		BinaryTreeNode pNode5 = bt.CreateBinaryTreeNode(5);
		BinaryTreeNode pNode6 = bt.CreateBinaryTreeNode(6);
		BinaryTreeNode pNode7 = bt.CreateBinaryTreeNode(7);
		BinaryTreeNode pNode8 = bt.CreateBinaryTreeNode(8);
		bt.ConnectTreeNodes(pNode1, pNode2, pNode3);
		bt.ConnectTreeNodes(pNode2, pNode4, pNode5);
		bt.ConnectTreeNodes(pNode3, pNode6, pNode7);
		bt.ConnectTreeNodes(pNode5, null, pNode8);
		LongestDistanceOfBinaryTreeNode ldobt = new LongestDistanceOfBinaryTreeNode();
		ldobt.FindMaxLen(pNode1);
		System.out.println(max);
	}
	
	public void FindMaxLen(BinaryTreeNode root){
		if(root==null)
			return;
		if(root.pLeft==null){
			root.nMaxLeft = 0;
		}
		if(root.pRight==null){
			root.nMaxRight = 0;
		}
		if(root.pLeft!=null){
			FindMaxLen(root.pLeft);
		}
		if(root.pRight!=null){
			FindMaxLen(root.pRight);
		}
		if(root.pLeft!=null){
			if(root.pLeft.nMaxLeft>root.pRight.nMaxRight)
				root.nMaxLeft = root.pLeft.nMaxLeft+1;
			else
				root.nMaxLeft = root.pLeft.nMaxRight+1;
		}
		if(root.pRight!=null){
			if(root.pRight.nMaxLeft>root.pRight.nMaxRight)
				root.nMaxRight = root.pRight.nMaxLeft+1;
			else
				root.nMaxRight = root.pRight.nMaxRight+1;
		}
		if(root.nMaxLeft+root.nMaxRight>max){
			max = root.nMaxLeft+root.nMaxRight;
		}
	}
}
