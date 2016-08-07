package algorithm.sort.important;

//http://www.codeceo.com/article/10-sort-algorithm-interview.html

public class Quick {
	// 最好和平均时间复杂度O(nlogn)，最坏O(n^2)，辅助空间O(logn)~~O(n)，基于比较的排序的时间复杂度最优就是O(nlogn)，不稳定
	public static void quick(int[] nums) {
		quickHelp(nums, 0, nums.length - 1);
	}

	private static void quickHelp(int[] nums, int left, int right) {
		//结果获得升序序列
		if (left < right) {
			int flag = nums[left];//是值，不是坐标
			int lp = left;
			int rp = right;
			while (lp < rp) {
				//注意这里是rp先动，不然结果会错，说明参考上面网址
				while (nums[rp] >= flag && lp < rp)
					rp -= 1;
				while (nums[lp] <= flag && lp < rp)
					lp += 1;
				if (lp != rp) {
					int t = nums[lp];
					nums[lp] = nums[rp];
					nums[rp] = t;
				}
			}

			int t = nums[left];
			nums[left] = nums[lp];
			nums[lp] = t;

			//此时，lp和rp相等
			quickHelp(nums, left, lp - 1);
			quickHelp(nums, rp + 1, right);
		}
	}

	public static void main(String[] args) {
		int[] nums = {1, 2, 0, 2, 2, 2, 0, 0, 2, 1, 1};
		quick(nums);
		System.out.println("a");
	}
}
