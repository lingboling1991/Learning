package algorithm.tree;

import algorithm.util.TreeNode;

public class ValidateBalanced {

	public static boolean isValid(TreeNode root) {
		return maxDepth(root) != -1;
	}

	public static int maxDepth(TreeNode root) {
		if (root == null)
			return 0;

		int L = maxDepth(root.left);// �Ե�����ȥ�жϣ�������˵Ҫ�ȱ��������һ���ڵ�����������
		// �����ڵݹ��ͬʱȥ�жϣ�������Ȼ��Ȼ�ͻ����жϿ��µģ��������������еĽ������жϿ��ϵ�
		if (L == -1)
			return -1;

		int R = maxDepth(root.right);
		if (R == -1)
			return -1;

		return Math.abs(L - R) > 1 ? -1 : Math.max(L, R) + 1;
	}
}
