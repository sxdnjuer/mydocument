package TreeProblem;

public class DepthOfTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	public static void test(){
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
		System.out.println(depth(pNode1));
		System.out.println(depthoftree(pNode1));
	}
	
	public static int depth(BinaryTreeNode root){
		if(root==null)
			return 0 ;
		int left = 0 , right = 0;
		if(root.pLeft!=null){
			left = depth(root.pLeft);
		}
		if(root.pRight!=null){
		    right = depth(root.pRight);
		}
		return (left>right)?left+1:right+1;
		
	}
	public static int depthoftree(BinaryTreeNode root){
		if(root==null)
			return 0 ;
		int left = depthoftree(root.pLeft)+1;
		int right = depthoftree(root.pRight)+1;
		return left>right?left:right;
	}
	
}
