package TreeProblem;

public class IsBalancedTree {

	int depth = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	public static void test() {
		BinaryTree bt = new BinaryTree();
		BinaryTreeNode pNode1 = bt.CreateBinaryTreeNode(1);
		BinaryTreeNode pNode2 = bt.CreateBinaryTreeNode(2);
		BinaryTreeNode pNode3 = bt.CreateBinaryTreeNode(3);
		BinaryTreeNode pNode4 = bt.CreateBinaryTreeNode(4);
		BinaryTreeNode pNode5 = bt.CreateBinaryTreeNode(5);
		BinaryTreeNode pNode6 = bt.CreateBinaryTreeNode(6);
		BinaryTreeNode pNode7 = bt.CreateBinaryTreeNode(7);
		BinaryTreeNode pNode8 = bt.CreateBinaryTreeNode(8);
		BinaryTreeNode pNode9 = bt.CreateBinaryTreeNode(9);
		bt.ConnectTreeNodes(pNode1, pNode2, pNode3);
		bt.ConnectTreeNodes(pNode2, pNode4, pNode5);
		bt.ConnectTreeNodes(pNode3, pNode6, pNode7);
		bt.ConnectTreeNodes(pNode5, null, pNode8);
		bt.ConnectTreeNodes(pNode8, null, pNode9);
		IsBalancedTree dep = new IsBalancedTree();
		System.out.println(IsBalanced(pNode1 , dep));

	}

	public static boolean IsBalanced(BinaryTreeNode root, IsBalancedTree dep) {
		if (root == null)
			return true;
		IsBalancedTree left = new IsBalancedTree();
		IsBalancedTree right = new IsBalancedTree();
		if (IsBalanced(root.pLeft, left) && IsBalanced(root.pRight, right)) {
			if(left.depth-right.depth>=-1&&left.depth-right.depth<=1){
				dep.depth = left.depth>right.depth?left.depth+1:right.depth+1;
				return true;
			}
		}
		return false;
	}
}
