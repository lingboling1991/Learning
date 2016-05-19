package algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import algorithm.util.Tools;

public class Subsets {

	public static List<List<Integer>> res = new ArrayList<List<Integer>>();

	public static List<List<Integer>> subsets(int[] n) {
		if (n.length == 0)
			return res;

		// Arrays.sort(n);
		for (int i = 0; i < n.length - 1; i++) {
			for (int j = 0; j < n.length - i - 1; j++) {
				if (n[j] < n[j + 1]) {
					Tools.switchList(n, j, j + 1);
				}
			}
		}// 这样就可以倒序来展示子集了
		List<Integer> cur = new ArrayList<>();
		helper(n, cur, 0);

		res.add(new ArrayList<Integer>());// 空集也算

		return res;
	}

	public static void helper(int[] n, List<Integer> cur, int level) {
		if (level == n.length) {
			return;
		}

		for (int i = level; i < n.length; i++) {
			cur.add(n[i]);// 注意这里
			List<Integer> tmp = Tools.copyList(cur);
			res.add(tmp);
			helper(n, cur, i + 1);// 还有这里，是用i而不是level来进行运算
			cur.remove(cur.size() - 1);
		}
	}

	public static void main(String[] args) {
		int[] n = { 1, 2, 3, 2, 1, 5, 8, 6, 9, 5, 3, 7 };
		List<List<Integer>> res = subsets(n);
		System.out.println("as");
	}
}
