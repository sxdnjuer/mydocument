/**
 * 
 */
package TreeProblem;

import java.util.Scanner;

/**
 * @author SHENGXD
 * 
 */


public class ConstructBinaryTree {
	/**
	 * @param args
	 */
	static int[] preOrder;
	static int[] inOrder;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String linepre = scan.nextLine();
		String linein = scan.nextLine();
		String pre[] = linepre.split(" ");
		String in[] = linein.split(" ");
		int[] preord = new int[pre.length];
		int[] inord = new int[in.length];
		for (int i = 0; i < pre.length; i++) {
			preord[i] = Integer.parseInt(pre[i]);
		}
		for (int i = 0; i < in.length; i++) {
			inord[i] = Integer.parseInt(in[i]);
		}
		preOrder = preord;
		inOrder = inord;
		for (int i = 0; i < pre.length; i++) {
			System.out.print(" " + preOrder[i]);
		}
		ConstructBinaryTree cbt = new ConstructBinaryTree();
		// System.out.println("pre.length="+pre.length+"in.length"+in.length);
		BinaryTreeNode rootNode = new BinaryTreeNode();
		rootNode = cbt.construct(0, pre.length - 1, 0, in.length - 1);

		// rootNode.value = preOrder[0];
		cbt.postOrder(rootNode);
	}

	public BinaryTreeNode construct(int startpre, int endpre, int startin,
			int endin) {
		int rootvalue = preOrder[startpre];
		BinaryTreeNode root = new BinaryTreeNode();
		System.out.println("rootvalue=" + rootvalue);
		root.value = rootvalue;
		root.pLeft = null;
		root.pRight = null;
		System.out.println("startpre=" + startpre + " endpre=" + endpre
				+ " startin" + startin + " endin" + endin);
		if (startpre == endpre) {
			if (startin == endin && preOrder[startpre] == inOrder[startin]) {
				System.out.println("return");
				return root;
			} else {
				try {
					throw new Exception("Invalid input first");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		int rootInorder = startin;
		while (rootInorder < endin && inOrder[rootInorder] != rootvalue) {
			rootInorder++;
		}
		if (rootInorder == endin && inOrder[rootInorder] != rootvalue) {
			try {
				throw new Exception("Invalid input second");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int leftLength = rootInorder - startin;
		// int leftPreOrderEnd =
		if (leftLength > 0) {
			root.pLeft = construct(startpre + 1, startpre + leftLength,
					startin, rootInorder - 1);
		}
		if (leftLength < endpre - startpre) {
			root.pRight = construct(startpre + leftLength + 1, endpre,
					rootInorder + 1, endin);
		}
		return root;
	}

	public void postOrder(BinaryTreeNode root) {
//		System.out.print(" " + root.value);
		if (root.pLeft != null) {
			postOrder(root.pLeft);
		}
		if (root.pRight != null) {
			postOrder(root.pRight);
		}
		System.out.print(" " + root.value);
	}
}
