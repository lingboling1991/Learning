package algorithm.linkedlist;

import algorithm.util.ListNode;

public class Reverse {
	public static ListNode reverseInPairs(ListNode head) {
		ListNode dummy = new ListNode(0);
		ListNode p = head;
		ListNode pre = dummy;// ��סҪ��4��ָ������ǣ����������Ǻ���ѭ���︳ֵ��

		dummy.next = p;

		while (p != null && p.next != null) {
			ListNode q = p.next;
			ListNode r = p.next.next;

			pre.next = q;
			q.next = p;
			p.next = r;

			pre = p;
			p = r;
		}
		return dummy.next;
	}

	public static ListNode reverse(ListNode head) {
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
		ListNode cur = head;

		if (cur == null || cur.next == null)
			return cur;
		ListNode next = cur.next;
		cur.next = null;
		ListNode rest = reverseRecursive(next);
		next.next = cur;
		return rest;
	}
}
