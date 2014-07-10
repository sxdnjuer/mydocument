package TreeProblem;

/**
 * �������ѡ��������˲ο����������ġ�

������˼·���ǣ��ڵݹ��м��������root�����ֵ��������max[0]��Java�޷������ô�����ֻ�ܽ���һ������or something����

������root�����ֵ�����¼��ֿ��ܣ�1.root����2.root����������һ��·����3.root����������һ��·����4.������һ��·����root��������һ��·��������ȡ���Ϳɸ�����max[0]

����1��2��3������������һ����root�����ֵ������Ҫ����ȥ��

���գ��������ϲ��root��˵�����ڵ����·����һ��Ҫ��������������ÿ���ڵ㶼�������������ֵ�Ѿ�����max[0]�����ˡ�
��������һ��ȫ�ֱ���maxnumber����max[0];
 */
public class MaxPathSum {
	BinaryTree bt = new BinaryTree();
	int maxnumber = Integer.MIN_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MaxPathSum mps = new MaxPathSum();
//		int min = Integer.MAX_VALUE;
//		System.out.println("min= "+min);
		mps.Test1();
	}

	public void Test1() {
		BinaryTreeNode pNode1 = bt.CreateBinaryTreeNode(1);
		BinaryTreeNode pNode2 = bt.CreateBinaryTreeNode(2);
		// BinaryTreeNode pNode3 = bt.CreateBinaryTreeNode(0);
		// BinaryTreeNode pNode4 = bt.CreateBinaryTreeNode(4);
		// BinaryTreeNode pNode5 = bt.CreateBinaryTreeNode(5);
		// BinaryTreeNode pNode6 = bt.CreateBinaryTreeNode(6);
		// BinaryTreeNode pNode7 = bt.CreateBinaryTreeNode(7);
		bt.ConnectTreeNodes(pNode1, pNode2, null);
		// bt.ConnectTreeNodes(pNode2, pNode4, pNode5);
		// bt.ConnectTreeNodes(pNode3, pNode6, pNode7);
		maxPathSum(pNode1,maxnumber);
		System.out.println(maxnumber);
	}

	public int maxPathSum(BinaryTreeNode pNode , int max) {
		maxnumber = max;
		if(pNode == null)
			return 0 ;
		int lsum = maxPathSum(pNode.pLeft,maxnumber);
		int rsum = maxPathSum(pNode.pRight,maxnumber);
		int csum = Math.max(pNode.value , Math.max(pNode.value+lsum,pNode.value+rsum));
		maxnumber = Math.max(maxnumber,Math.max(csum,lsum+pNode.value+rsum));
		return csum;
	}

}
