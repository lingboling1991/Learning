package algorithm.tree;

import algorithm.util.TreeNode;

public class TMain {
	public static void main(String[] args) {
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(3);
		TreeNode d = new TreeNode(4);
		TreeNode e = new TreeNode(5);
		TreeNode f = new TreeNode(6);
		TreeNode g = new TreeNode(7);

		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		c.left = f;
		c.right = g;

		// 二叉排序树转换为双向链表
		Convert2Linkedlist.traversal(a);
		Convert2Linkedlist.printHead();

		// 树中价值最大的路径，https://leetcode.com/problems/binary-tree-maximum-path-sum/
		int maxPathSum = MaxPathSum.maxPathSum(a);
		TreeNode upsideDown = UpsideDownBT.UpsideDownBinaryTree(a);

		// 二叉树最大最小深度
		int max = MaxMinDepth.maxDepth(a);
		int min = MaxMinDepth.minDepthII(a);

		// 是否是平衡二叉树
		boolean res = ValidateBalanced.isValid(a);

		// 数组转化成二叉排序树
		int[] n = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
		TreeNode root = Convert2BST.fromSortedArray(n);

		System.out.println(max + " " + min + " " + res + " " + maxPathSum);
	}

}
