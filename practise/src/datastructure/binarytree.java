package datastructure;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//�������Ĺ��죻��������������������������
//�õ���list�е�linkedlist������ɲ�list��API
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
		
		System.out.println("���������");  
        bt.preOrderTraverse(root);  
        System.out.println();  
        
        System.out.println("���������");  
        bt.inOrderTraverse(root);  
        System.out.println();  
        
        System.out.println("���������");  
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
		// ��һ�������ֵ����ת��ΪNode�ڵ�
		for (int nodeindex = 0; nodeindex < n; nodeindex++) {
			nodelist.add(new Node(numbers[nodeindex]));
		}
		// ��ǰlastParentIndex-1�����ڵ㰴�ո��ڵ��뺢�ӽڵ�����ֹ�ϵ����������
		for (int parentIndex = 0; parentIndex < n / 2 - 1; parentIndex++) {
			// ����
			nodelist.get(parentIndex).leftChild = nodelist
					.get(parentIndex * 2 + 1);
			// �Һ���
			nodelist.get(parentIndex).rightChild = nodelist
					.get(parentIndex * 2 + 2);
		}
		// ���һ�����ڵ�:��Ϊ���һ�����ڵ����û���Һ��ӣ����Ե����ó�������
		int lastparentindex = n / 2 - 1;
		// ����
		nodelist.get(lastparentindex).leftChild = nodelist
				.get(lastparentindex * 2 + 1);
		// �Һ���,�������ĳ���Ϊ�����Ž����Һ���
		if (n % 2 == 1) {
			nodelist.get(lastparentindex).rightChild = nodelist
					.get(lastparentindex * 2 + 2);
		}
	}
	/** 
     * ������� 
     *  
     * �����ֲ�ͬ�ı����ṹ����һ���ģ�ֻ���Ⱥ�˳��һ������ 
     *  
     * @param node 
     *            �����Ľڵ� 
     */  
	public void preOrderTraverse(Node node) {
		if (node != null) {
			System.out.print(node.data + " ");
			preOrderTraverse(node.leftChild);
			preOrderTraverse(node.rightChild);
		}
	}
	/** 
     * ������� 
     *  
     * �����ֲ�ͬ�ı����ṹ����һ���ģ�ֻ���Ⱥ�˳��һ������ 
     *  
     * @param node 
     *            �����Ľڵ� 
     */  
	public void inOrderTraverse(Node node) {
		if (node != null) {
			inOrderTraverse(node.leftChild);
			System.out.print(node.data + " ");
			inOrderTraverse(node.rightChild);
		}
	}
	/** 
     * ������� 
     *  
     * �����ֲ�ͬ�ı����ṹ����һ���ģ�ֻ���Ⱥ�˳��һ������ 
     *  
     * @param node 
     *            �����Ľڵ� 
     */  
    public void postOrderTraverse(Node node) {  
        if (node == null)  
            return;  
        postOrderTraverse(node.leftChild);  
        postOrderTraverse(node.rightChild);  
        System.out.print(node.data + " ");  
    }  
}
