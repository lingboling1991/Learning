package algorithm.tree;

import algorithm.util.TreeNode;

public class MaxPathSum {
	public static int maxSum;

	public static int maxPathSum(TreeNode root) {
		maxSum = Integer.MIN_VALUE;
		findMax(root);
		return maxSum;
	}

	public static int findMax(TreeNode root) {
		if (root == null)
			return 0;
		int left = findMax(root.left);
		int right = findMax(root.right);

		maxSum = Math.max(root.val + left + right, maxSum);// 求最大长度时，最好情况是左子树+父节点+右子树，因此都要加进去
		int ret = root.val + Math.max(left, right);// 返回时是作为一枝返回的，因此左右子树只能选一个

		return ret > 0 ? ret : 0;// 这里注意，如果返回值小于0的话，就返回0，表示不加这半边的值
	}
}
