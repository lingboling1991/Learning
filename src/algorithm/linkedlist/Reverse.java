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
		ListNode p = null;
		ListNode q = head;
		while (q.next != null) {
			ListNode r = q.next;
			q.next = p;
			p = q;
			q = r;
		}
		q.next = p;
		return q;
	}
}
