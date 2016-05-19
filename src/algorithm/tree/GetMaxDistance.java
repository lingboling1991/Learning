package algorithm.tree;

import algorithm.util.TreeNode;

public class GetMaxDistance {

	public static int maxDistance = 0;

	public static void getMaxDistance(TreeNode root) {
		if (root == null) {
			return;
		}
		if (root.left != null) {
			getMaxDistance(root.left);
			root.maxLeft = Math.max(root.left.maxLeft, root.left.maxRight) + 1;
		}
		if (root.right != null) {
			getMaxDistance(root.right);
			root.maxRight = Math.max(root.right.maxLeft, root.right.maxRight) + 1;
		}
		if (root.maxLeft + root.maxRight > maxDistance)
			maxDistance = root.maxLeft + root.maxRight;// 这里是边数，不是层数，所以不用+1
	}

}
