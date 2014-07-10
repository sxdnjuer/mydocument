package TreeProblem;

import java.util.ArrayList;
import java.util.Iterator;

public class ValidateBinarySearchTree {

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
		ValidateBinarySearchTree vb = new ValidateBinarySearchTree();
//		System.out.println(vb.isValidBST(pNode1));
		System.out.println(vb.isValidBST_Two(pNode1, Integer.MIN_VALUE, Integer.MAX_VALUE));
	}

	public boolean isValidBST_Two(BinaryTreeNode root, int min, int max) {
		if (root == null)
			return true;
		if (root.value <= min || root.value >= max) {
			return false;
		}
		return isValidBST_Two(root.pLeft, min, root.value)
				&& isValidBST_Two(root.pRight, root.value, max);
	}

	// 中序遍历，再判断数列是否递增
	public boolean isValidBST(BinaryTreeNode root) {
		ArrayList<Integer> alist = new ArrayList<Integer>();
		preorder(root, alist);
		Iterator<Integer> it = alist.iterator();
		int current = Integer.MIN_VALUE;
		while (it.hasNext()) {
			int next = it.next();
			if (current >= next) {
				return false;
			}
			current = next;
		}
		return true;
	}

	public void preorder(BinaryTreeNode root, ArrayList<Integer> alist) {
		if (root == null)
			return;
		// if(root.pLeft!=null)
		preorder(root.pLeft, alist);
		alist.add(root.value);
		// if(root.pRight!=null)
		preorder(root.pRight, alist);
	}
	public boolean isSameTree(BinaryTreeNode p, BinaryTreeNode q) {
        if(p==null&&q==null)
            return true;
        if((p==null&&q!=null)||(p!=null&&q==null))
            return false;
        if(p.value!=q.value){
            return false;
        }
        else{
        	return isSameTree(p.pLeft, q.pLeft)&&isSameTree(p.pRight, q.pRight);
        }
    }
}
