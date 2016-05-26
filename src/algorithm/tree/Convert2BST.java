package algorithm.tree;

import algorithm.util.ListNode;
import algorithm.util.TreeNode;

public class Convert2BST {
	public static ListNode list;

	public static TreeNode fromSortedArray(int[] n) {
		int start = 0, end = n.length - 1;
		int mid = (start + end) / 2;

		TreeNode res = new TreeNode(n[mid]);
		res.left = helpA(n, start, mid - 1);
		res.right = helpA(n, mid + 1, end);

		return res;
	}

	public static TreeNode helpA(int[] n, int start, int end) {
		int mid = (start + end) / 2;
		TreeNode res = new TreeNode(0);

		if (start > end) {
			// res.val = n[mid];
			return null;// ע��������start > end�Ļ���
			// ��ô��˵�����������û���κ������ˣ�Ҳ����û������½ڵ�Ŀ�����
		}
		res.val = n[mid];
		res.left = helpA(n, start, mid - 1);
		res.right = helpA(n, mid + 1, end);
		return res;
	}

	public static TreeNode fromSortedListNode(ListNode head) {
		ListNode p = head;
		int count = 0;
		while (p != null) {
			p = p.next;
			count += 1;
		}
		list = head;
		TreeNode res = helpLN(0, count - 1);
		return res;
	}

	public static TreeNode helpLN(int start, int end) {
		if (start > end)
			return null;

		int mid = (start + end) / 2;

		TreeNode left = helpLN(start, mid - 1);// �����൱���ȵݹ�mid - 1 - start��

		TreeNode root = new TreeNode(0);
		root.val = list.val;
		list = list.next;// ��Ϊ֮ǰ�еݹ飬�ȵ�����������ʱ��list�Ѿ�ǡ�õ����м�λ����

		TreeNode right = helpLN(mid + 1, end);

		root.left = left;
		root.right = right;
		return root;
	}
}
