package TreeProblem;

/**
 * 此题甚难。后来看了参考才做出来的。

基本的思路就是，在递归中计算包含该root的最大值并更新至max[0]（Java无法按引用传，就只能建立一个数组or something）。

包含该root的最大值有如下几种可能：1.root本身；2.root和左子树中一条路径；3.root和右子树中一条路径；4.左子树一条路径和root和右子树一条路径。其中取最大就可更新至max[0]

其中1，2，3可用来计算上一级的root的最大值，所以要传回去。

最终，对于最上层的root来说，数内的最大路径不一定要经过根，但由于每个节点都遍历到，其最大值已经存在max[0]里面了。
下面我用一个全局变量maxnumber代替max[0];
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
