package algorithm.tree;

import algorithm.util.TreeNode;

public class ValidateBST {
	public static void isValid(TreeNode root) {
		boolean res = help(root, null, null);
	}

	public static boolean help(TreeNode x, Integer low, Integer high) {
		if (x == null)
			return true;

		return (low == null || x.val > low) && (high == null || x.val < high) && help(x.left, low, x.val)
				&& help(x.right, x.val, high);
	}
}
