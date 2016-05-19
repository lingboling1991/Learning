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
		// 初始时，双向链表中无节点，head及tail均为null
		if (head == null) {
			head = node;
			tail = node;
		} else {
			// 将新node的左指针指向当前tail,再将当前tail的右指针指向新node，最后将tail后移
			node.left = tail;
			tail.right = node;
			tail = node;
		}
	}

	// 头结点向后打印
	public static void printHead() {
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.right;
		}
	}

	// 尾节点向前打印
	public static void printTail() {
		while (tail != null) {
			System.out.print(tail.val + " ");
			tail = tail.left;
		}
	}
}
