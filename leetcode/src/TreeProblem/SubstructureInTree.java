package TreeProblem;


public class SubstructureInTree {
	BinaryTree bt = new BinaryTree();
	/**
	 * @param args
	 * @author SHENGXD
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SubstructureInTree ssit = new SubstructureInTree();
		ssit.Test1();

	}

	private void Test1() {
		BinaryTreeNode pNodeA1 = bt.CreateBinaryTreeNode(8);
		BinaryTreeNode pNodeA2 = bt.CreateBinaryTreeNode(8);
		BinaryTreeNode pNodeA3 = bt.CreateBinaryTreeNode(7);
		BinaryTreeNode pNodeA4 = bt.CreateBinaryTreeNode(9);
		BinaryTreeNode pNodeA5 = bt.CreateBinaryTreeNode(2);
		BinaryTreeNode pNodeA6 = bt.CreateBinaryTreeNode(4);
		BinaryTreeNode pNodeA7 = bt.CreateBinaryTreeNode(7);

		bt.ConnectTreeNodes(pNodeA1, pNodeA2, pNodeA3);
		bt.ConnectTreeNodes(pNodeA2, pNodeA4, pNodeA5);
		bt.ConnectTreeNodes(pNodeA5, pNodeA6, pNodeA7);

		BinaryTreeNode pNodeB1 = bt.CreateBinaryTreeNode(8);
		BinaryTreeNode pNodeB2 = bt.CreateBinaryTreeNode(9);
		BinaryTreeNode pNodeB3 = bt.CreateBinaryTreeNode(2);

		bt.ConnectTreeNodes(pNodeB1, pNodeB2, pNodeB3);
		System.out.println(HasSubTree(pNodeA1, pNodeB1));
	}

	private boolean HasSubTree(BinaryTreeNode pNodeA1, BinaryTreeNode pNodeB1) {
		boolean result = false;
		if (pNodeA1 != null && pNodeB1 != null) {
			if (pNodeA1.value == pNodeB1.value) {
				System.out.println(pNodeA1.value);
				result = DoesTree1HaveTree2(pNodeA1, pNodeB1);
				System.out.println("result="+result);
			}
			if (result == false) {
				result = HasSubTree(pNodeA1.pLeft, pNodeB1);
			}
			if (result == false)
				result = HasSubTree(pNodeA1.pRight, pNodeB1);
		}
		System.out.println("result="+result);
		return result;
	}

	private boolean DoesTree1HaveTree2(BinaryTreeNode pNodeA1,
			BinaryTreeNode pNodeB1) {
		if (pNodeB1 == null)
			return true;
		if (pNodeA1 == null)
			return false;
		if (pNodeA1.value != pNodeB1.value) {
			return false;
		}
		System.out.println(pNodeA1.value+" "+ pNodeB1.value);
		return DoesTree1HaveTree2(pNodeA1.pLeft, pNodeB1.pLeft)
				&& DoesTree1HaveTree2(pNodeA1.pRight, pNodeB1.pRight);
	}

	
}
