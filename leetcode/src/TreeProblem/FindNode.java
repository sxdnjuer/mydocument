package TreeProblem;

public class FindNode {
	static int result;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTree bt = new BinaryTree();
		BinaryTreeNode pNode1 = bt.CreateBinaryTreeNode(5);
		BinaryTreeNode pNode2 = bt.CreateBinaryTreeNode(3);
		BinaryTreeNode pNode3 = bt.CreateBinaryTreeNode(7);
		BinaryTreeNode pNode4 = bt.CreateBinaryTreeNode(2);
		BinaryTreeNode pNode5 = bt.CreateBinaryTreeNode(4);
		BinaryTreeNode pNode6 = bt.CreateBinaryTreeNode(4);
		BinaryTreeNode pNode7 = bt.CreateBinaryTreeNode(8);
		bt.ConnectTreeNodes(pNode1, pNode2, pNode3);
		bt.ConnectTreeNodes(pNode2, pNode4, pNode5);
		bt.ConnectTreeNodes(pNode3, pNode6, pNode7);
		FindNode fn = new FindNode();
		boolean isfind = fn.findnode(pNode1, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
		if(isfind){
			System.out.println(isfind);
		}
		else{
			System.out.println(isfind);
			System.out.println(result);
		}
	}
	
	public boolean findnode(BinaryTreeNode root , int min ,int max, int value){
		if(root==null){
			if(min==Integer.MIN_VALUE) result = max;
			else if(max == Integer.MAX_VALUE) result = min;
			else if((max-value)>(value-min)){
				result = min;
			}
			else{
				result = max;
			}
			return false;
		}
		if(root.value>value){
			return findnode(root.pLeft , min , root.value , value);
		}
		else if(root.value<value){
			return findnode(root.pRight ,root.value, max , value);
		}
		else 
			return true;
	}
	
	

}
