package algorithm.tree;

import algorithm.util.TreeNode;

public class Convert2Linkedlist {

	public static TreeNode head, tail;

	public static void traversal(TreeNode node) {
		if (node == null)
			return;
		if (node.left != null)
			traversal(node.left);
		changeNode(node);
		if (node.right != null)
			traversal(node.right);
	}

	public static void changeNode(TreeNode node) {
		// ��ʼʱ��˫���������޽ڵ㣬head��tail��Ϊnull
		if (head == null) {
			head = node;
			tail = node;
		} else {
			// ����node����ָ��ָ��ǰtail,�ٽ���ǰtail����ָ��ָ����node�����tail����
			node.left = tail;
			tail.right = node;
			tail = node;
		}
	}

	// ͷ�������ӡ
	public static void printHead() {
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.right;
		}
	}

	// β�ڵ���ǰ��ӡ
	public static void printTail() {
		while (tail != null) {
			System.out.print(tail.val + " ");
			tail = tail.left;
		}
	}
}
