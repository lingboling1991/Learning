package algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

import algorithm.util.TreeNode;

public class MaxMinDepth {

	public static int maxDepth(TreeNode root) {
		if (root == null)
			return 0;

		return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
	}

	public static int minDepth(TreeNode root) {
		if (root == null)
			return 0;

		return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
	}

	public static int minDepthII(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		int res = 1;
		queue.offer(root);
		TreeNode rightmost = root;// 这里用rightmost来标记最右节点，它的左儿子或者右儿子就是下一层的最右节点
		while (queue.size() != 0) {
			TreeNode tmp = queue.poll();
			if (tmp.left == null && tmp.right == null)
				break;// 这里只要遇到一个叶子结点，就停止遍历，这样后面的就都不用看了，节省了时间
			if (tmp.left != null)
				queue.offer(tmp.left);
			if (tmp.right != null)
				queue.offer(tmp.right);
			if (tmp == rightmost) {
				res += 1;// 顺利到达最右边，且最右边这个结点还有子节点，那么层数可以+1
				rightmost = (tmp.right == null) ? tmp.left : tmp.right;
			}
		}
		return res;
	}
}
