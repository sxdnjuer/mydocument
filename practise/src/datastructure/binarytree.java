package datastructure;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//二叉树的构造；先序遍历；中序遍历；后续遍历
//用到了list中的linkedlist，具体可查list的API
public class binarytree {
	private static List<Node> nodelist = null;
	private static int n, numbers[];

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		String num[] = input.split(" ");
		n = num.length;
		numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(num[i]);
		}
		binarytree bt = new binarytree();
		bt.createBinaryTree();
		Node root = nodelist.get(0);
		
		System.out.println("先序遍历：");  
        bt.preOrderTraverse(root);  
        System.out.println();  
        
        System.out.println("中序遍历：");  
        bt.inOrderTraverse(root);  
        System.out.println();  
        
        System.out.println("后序遍历：");  
        bt.postOrderTraverse(root);  
        System.out.println(); 
        
        
	}

	public static class Node {
		Node leftChild;
		Node rightChild;
		int data;

		Node(int newdata) {
			leftChild = null;
			rightChild = null;
			data = newdata;
		}
	}

	public void createBinaryTree() {
		nodelist = new LinkedList<Node>();
		// 将一个数组的值依次转换为Node节点
		for (int nodeindex = 0; nodeindex < n; nodeindex++) {
			nodelist.add(new Node(numbers[nodeindex]));
		}
		// 对前lastParentIndex-1个父节点按照父节点与孩子节点的数字关系建立二叉树
		for (int parentIndex = 0; parentIndex < n / 2 - 1; parentIndex++) {
			// 左孩子
			nodelist.get(parentIndex).leftChild = nodelist
					.get(parentIndex * 2 + 1);
			// 右孩子
			nodelist.get(parentIndex).rightChild = nodelist
					.get(parentIndex * 2 + 2);
		}
		// 最后一个父节点:因为最后一个父节点可能没有右孩子，所以单独拿出来处理
		int lastparentindex = n / 2 - 1;
		// 左孩子
		nodelist.get(lastparentindex).leftChild = nodelist
				.get(lastparentindex * 2 + 1);
		// 右孩子,如果数组的长度为奇数才建立右孩子
		if (n % 2 == 1) {
			nodelist.get(lastparentindex).rightChild = nodelist
					.get(lastparentindex * 2 + 2);
		}
	}
	/** 
     * 先序遍历 
     *  
     * 这三种不同的遍历结构都是一样的，只是先后顺序不一样而已 
     *  
     * @param node 
     *            遍历的节点 
     */  
	public void preOrderTraverse(Node node) {
		if (node != null) {
			System.out.print(node.data + " ");
			preOrderTraverse(node.leftChild);
			preOrderTraverse(node.rightChild);
		}
	}
	/** 
     * 中序遍历 
     *  
     * 这三种不同的遍历结构都是一样的，只是先后顺序不一样而已 
     *  
     * @param node 
     *            遍历的节点 
     */  
	public void inOrderTraverse(Node node) {
		if (node != null) {
			inOrderTraverse(node.leftChild);
			System.out.print(node.data + " ");
			inOrderTraverse(node.rightChild);
		}
	}
	/** 
     * 后序遍历 
     *  
     * 这三种不同的遍历结构都是一样的，只是先后顺序不一样而已 
     *  
     * @param node 
     *            遍历的节点 
     */  
    public void postOrderTraverse(Node node) {  
        if (node == null)  
            return;  
        postOrderTraverse(node.leftChild);  
        postOrderTraverse(node.rightChild);  
        System.out.print(node.data + " ");  
    }  
}
