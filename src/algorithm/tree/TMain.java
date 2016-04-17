package algorithm.tree;

import algorithm.util.TreeNode;

public class TMain {
	public static void main(String[] args) {
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(3);
		TreeNode d = new TreeNode(-4);
		TreeNode e = new TreeNode(5);

		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;

		int maxPathSum = MaxPathSum.maxPathSum(a);
		TreeNode upsideDown = UpsideDownBT.UpsideDownBinaryTree(a);

		int max = MaxMinDepth.maxDepth(a);
		int min = MaxMinDepth.minDepthII(a);

		boolean res = ValidateBalanced.isValid(a);

		int[] n = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
		TreeNode root = Convert2BST.fromSortedArray(n);

		System.out.println(max + " " + min + " " + res + " " + maxPathSum);
	}

}
