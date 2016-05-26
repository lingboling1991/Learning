package algorithm.tree;

import algorithm.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

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
		TreeNode rightmost = root;// ������rightmost��������ҽڵ㣬��������ӻ����Ҷ��Ӿ�����һ������ҽڵ�
		while (queue.size() != 0) {
			TreeNode tmp = queue.poll();
			if (tmp.left == null && tmp.right == null)
				break;// ����ֻҪ����һ��Ҷ�ӽ�㣬��ֹͣ��������������ľͶ����ÿ��ˣ���ʡ��ʱ��
			if (tmp.left != null)
				queue.offer(tmp.left);
			if (tmp.right != null)
				queue.offer(tmp.right);
			if (tmp == rightmost) {
				res += 1;// ˳���������ұߣ������ұ������㻹���ӽڵ㣬��ô��������+1
				rightmost = (tmp.right == null) ? tmp.left : tmp.right;
			}
		}
		return res;
	}
}
