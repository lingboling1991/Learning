package algorithm.interview.perfect;

import java.util.ArrayList;
import java.util.List;

public class maxUpriseSub {
	public static void main(String[] args) {
		int[] t = { 1, 3, 5, 7, 9, 2, 4, 6, 8 };
		List<Integer> nums = new ArrayList<>();
		for (int i = 0; i < t.length; i++) {
			nums.add(t[i]);
		}

		List<Integer> res = getMaxUpriseSub(nums);

	}

	public static List<Integer> getMaxUpriseSub(List<Integer> nums) {
		int len = nums.size();
		List<List<Integer>> dp_final = new ArrayList<List<Integer>>();// 每个值元素存储一个List，表示截至序号i所提供的最长子序列
		ArrayList<Integer> dp0 = new ArrayList<>();
		dp0.add(nums.get(0));
		dp_final.add(0, dp0);
		int max_length = dp0.size();
		int max_index = 0;// 最长串在dps中的位置

		for (int i = 1; i < len; i++) {// 子串截止到第i个数
			ArrayList<Integer> dpi = new ArrayList<>();
			int max_by_j = 0;// 截至数字i，在由j结尾的子序列的帮助下，能凑出来的最长子序列的长度
			int mark = -1;// 截至数字i，之所以能凑出最长子序列，是在之前由mark数字结尾的子序列的帮助下完成
			for (int j = i - 1; j >= 0; j--) {
				if (nums.get(i) > nums.get(j)) {
					if (dp_final.get(j).size() + 1 > max_by_j) {
						max_by_j = dp_final.get(j).size() + 1;
						mark = j;
					}
				}
			}
			dpi.add(nums.get(i));
			if (mark >= 0) {// 拼接一下，把上面找到的前面的最大子串拼进去
				dpi.addAll(dp_final.get(mark));
			}
			dp_final.add(i, dpi);
			if (dpi.size() > max_length) {
				max_length = dpi.size();
				max_index = i;
			}
		}

		return dp_final.get(max_index);// 这里是倒序的

		/*
		 * for (int i = dps.get(max_index).size() - 1; i >= 0; i--) { if (i ==
		 * 0) { System.out.print(dps.get(max_index).get(i)); } else {
		 * System.out.print(dps.get(max_index).get(i) + " "); } }
		 * System.out.println();
		 */
	}
}
