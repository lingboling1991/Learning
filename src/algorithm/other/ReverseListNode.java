package algorithm.other;

import algorithm.util.ListNode;

public class ReverseListNode {
	// http://blog.csdn.net/beiyetengqing/article/details/7596554
	public static ListNode reverseNonRecursive(ListNode head) {
		ListNode pre = null;
		ListNode cur = head;

		while (cur != null) {
			ListNode next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		return pre;
	}

	public static ListNode reverseRecursive(ListNode head) {
		ListNode current = head;

		if (current == null || current.next == null)
			return current;
		ListNode nextNode = current.next;
		current.next = null;
		ListNode reverseRest = reverseRecursive(nextNode);
		nextNode.next = current;
		return reverseRest;
	}

	public static void main(String[] args) {
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		ListNode d = new ListNode(4);

		a.next = b;
		b.next = c;
		c.next = d;
		d.next = null;

		ListNode res = reverseRecursive(a);
		System.out.println("a");
	}
}
