package algorithm.backtracking;

import algorithm.util.Tools;

import java.util.ArrayList;
import java.util.List;

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
		}// �����Ϳ��Ե�����չʾ�Ӽ���
		List<Integer> cur = new ArrayList<>();
		helper(n, cur, 0);

		res.add(new ArrayList<Integer>());// �ռ�Ҳ��

		return res;
	}

	public static void helper(int[] n, List<Integer> cur, int level) {
		if (level == n.length) {
			return;
		}

		for (int i = level; i < n.length; i++) {
			cur.add(n[i]);// ע������
			List<Integer> tmp = Tools.copyList(cur);
			res.add(tmp);
			helper(n, cur, i + 1);// �����������i������level����������
			cur.remove(cur.size() - 1);
		}
	}

	public static void main(String[] args) {
		int[] n = {1, 2, 3, 2, 1, 5, 8, 6, 9, 5, 3, 7};
		List<List<Integer>> res = subsets(n);
		System.out.println("as");
	}
}
