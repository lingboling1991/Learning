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

		maxSum = Math.max(root.val + left + right, maxSum);// ����󳤶�ʱ����������������+���ڵ�+����������˶�Ҫ�ӽ�ȥ
		int ret = root.val + Math.max(left, right);// ����ʱ����Ϊһ֦���صģ������������ֻ��ѡһ��

		return ret > 0 ? ret : 0;// ����ע�⣬�������ֵС��0�Ļ����ͷ���0����ʾ�������ߵ�ֵ
	}
}
