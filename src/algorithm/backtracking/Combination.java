package algorithm.backtracking;

import algorithm.util.Tools;

import java.util.*;

public class Combination {
	// https://siddontang.gitbooks.io/leetcode-solution/content/backtracking/combination.html

	public static HashSet<Integer> containedSet = new HashSet<>();

	public static List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();

		if (n <= 0 || k >= n)
			return res;

		List<Integer> cur = new ArrayList<Integer>();
		combineHelper(res, cur, n, k, 1);
		return res;
	}

	public static void combineHelper(List<List<Integer>> res,
	                                 List<Integer> cur, int n, int k, int level) {
		if (cur.size() == k) {// ע��������cur.size()������level���ж��Ƿ��˱߽�
			List<Integer> tmp = Tools.copyList(cur);
			res.add(tmp);
			return;
		}

		for (int i = level; i <= n; i++) {
			cur.add(i);
			combineHelper(res, cur, n, k, i + 1);
			cur.remove(cur.size() - 1);
		}
	}

	public static List<List<Integer>> combineSum(int[] n, int k) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();

		if (n.length == 0 || k <= 0)
			return res;

		List<Integer> cur = new ArrayList<Integer>();
		Arrays.sort(n);// ������Ҫ��������Ϊ���Ҫ������
		combineSumHelper(res, cur, n, k, 0);

		List<List<Integer>> fin = new ArrayList<List<Integer>>();
		for (List<Integer> list : res) {
			Collections.sort(list);
			int uniCode = isContained(list);
			if (uniCode != -1) {
				containedSet.add(uniCode);
				fin.add(list);
			}
		}
		return fin;
	}

	public static void combineSumHelper(List<List<Integer>> res,
	                                    List<Integer> cur, int[] n, int k, int level) {
		if (k < 0) {
			return;
		} else if (k == 0) {
			List<Integer> tmp = Tools.copyList(cur);
			res.add(tmp);
			return;
		}

		for (int i = level; i < n.length; i++) {
			cur.add(n[i]);
			k -= n[i];
			combineSumHelper(res, cur, n, k, level + 1);// ����ĳ�level�����Ǳ�ʾ�����ظ�ʹ��ĳ������
			cur.remove(cur.size() - 1);
			k += n[i];
		}
	}

	public static int isContained(List<Integer> cur) {
		int uniCode = 0;
		for (int i = 0; i < cur.size(); i++) {
			uniCode += cur.get(i) * Math.pow(10, i);
		}

		return containedSet.contains(uniCode) ? -1 : uniCode;
	}

	public static void main(String[] args) {
		List<List<Integer>> res = combine(5, 3);

		// int[] n = { 1, 2, 3, 4, 5, 6 };
		// List<List<Integer>> res = combineSum(n, 5);

		System.out.println("a");
	}
}
