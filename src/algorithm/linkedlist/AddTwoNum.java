package algorithm.linkedlist;

import algorithm.util.ListNode;

public class AddTwoNum {
	public static ListNode add(ListNode a, ListNode b) {
		ListNode res = new ListNode(0);
		ListNode p = res;
		int isMoreThanTen = 0;
		while (a != null || b != null) {// 两个有一个不为null就继续
			int aVal = (a == null) ? 0 : a.val;
			int bVal = (b == null) ? 0 : b.val;

			int x = aVal + bVal + isMoreThanTen;
			if (x >= 10) {
				x = x % 10;
				isMoreThanTen = 1;
			} else {
				isMoreThanTen = 0;
			}
			p.next = new ListNode(x);
			p = p.next;

			a = (a == null) ? null : a.next;
			b = (b == null) ? null : b.next;
		}
		if (isMoreThanTen == 1) {
			p.next = new ListNode(isMoreThanTen);
		}
		return res.next;
	}

}
