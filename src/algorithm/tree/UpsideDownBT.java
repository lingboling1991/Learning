package algorithm.tree;

import algorithm.util.TreeNode;

public class UpsideDownBT {
	public static TreeNode UpsideDownBinaryTree(TreeNode root) {
		return dfsBottomUp(root, null);
	}

	private static TreeNode dfsBottomUp(TreeNode p, TreeNode parent) {
		if (p == null)
			return parent;
		TreeNode root = dfsBottomUp(p.left, p);
		p.left = (parent == null) ? parent : parent.right;
		p.right = parent;
		return root;
	}
}
