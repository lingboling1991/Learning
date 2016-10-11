package algorithm.backtracking;

/**
 * Created by lenovo on 2016-9-28.
 */
public class AllRange {
	// 全排列：http://wuchong.me/blog/2014/07/28/permutation-and-combination-realize/
	static void swap(char[] s, int a, int b) {
		char temp = s[a];
		s[a] = s[b];
		s[b] = temp;
	}

	static boolean isSwap(char[] s, int start, int end) {
		for (; start < end; start++) {
			if (s[start] == s[end])
				return false;
		}
		return true;
	}

	static void allRange(char[] str, int start, int length) {
		if (start == length - 1) {
			System.out.println(String.valueOf(str));
		} else {
			for (int i = start; i <= length - 1; i++) {
				//去重的全排列就是从第一个数字起每个数分别与它后面非重复出现的数字交换
				if (isSwap(str, start, i)) {
					swap(str, start, i);
					allRange(str, start + 1, length);
					swap(str, start, i);
				}
			}
		}
	}

	public static void main(String[] args) {
		String s = "abbc";
		allRange(s.toCharArray(), 0, s.length());
	}
}
