package algorithm.tree;

import algorithm.util.TreeNode;

public class ValidateBalanced {

	public static boolean isValid(TreeNode root) {
		return maxDepth(root) != -1;
	}

	public static int maxDepth(TreeNode root) {
		if (root == null)
			return 0;

		int L = maxDepth(root.left);// 自底向上去判断，并不是说要先遍历到最后一个节点再做操作，
		// 而是在递归的同时去判断，这样自然而然就会先判断靠下的，再利用下面已有的结论来判断靠上的
		if (L == -1)
			return -1;

		int R = maxDepth(root.right);
		if (R == -1)
			return -1;

		return Math.abs(L - R) > 1 ? -1 : Math.max(L, R) + 1;
	}
}
