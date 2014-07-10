package TreeProblem;

import java.util.Scanner;

public class ReconstructBinaryTree {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String preorder = scan.nextLine();
		String inorder = scan.nextLine();
		ReconstructBinaryTree rbt = new ReconstructBinaryTree();
		BinaryTreeNode root = rbt.reconstruct(preorder, inorder, preorder.length());
		rbt.postorder(root);
	}
	
	public BinaryTreeNode reconstruct(String preorder , String inorder , int length){
		if(preorder==null||inorder==null||preorder.length()==0||inorder.length()==0){
			return null;
		}
		BinaryTreeNode root = new BinaryTreeNode();
		root.value = preorder.charAt(0);
		root.pLeft = null;
		root.pRight = null;
		int i = 0;
		while(inorder.charAt(i)!=preorder.charAt(0)){
			i++;
		}
		if(preorder.length()>1){
			root.pLeft = reconstruct(preorder.substring(1,i+1),inorder.substring(0,i),i);
		}
		if(preorder.length()>i+1)
			root.pRight = reconstruct(preorder.substring(i+1,length),inorder.substring(i+1,length),length-i-1);
		return root;
	}
	
	public void postorder(BinaryTreeNode root){
		if(root.pLeft!=null)
			postorder(root.pLeft);
		if(root.pRight!=null)
			postorder(root.pRight);
		System.out.println(root.value-'0');
	}
	

}
