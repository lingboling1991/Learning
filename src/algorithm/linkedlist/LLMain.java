package algorithm.linkedlist;

import algorithm.util.ListNode;

public class LLMain {
	public static void main(String[] args) {
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		ListNode d = new ListNode(4);
		ListNode e = new ListNode(5);
		ListNode f = new ListNode(6);

		a.next = b;
		b.next = c;
		c.next = d;
		d.next = e;
		// e.next = f;

		// ListNode res = AddTwoNum.add(a, d);
		ListNode res = Reverse.reverse(a);

		System.out.println("a");
	}
}
