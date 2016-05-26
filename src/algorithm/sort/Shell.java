package algorithm.sort;

//http://wuchong.me/blog/2014/02/09/algorithm-sort-summary/

public class Shell {
	// 不稳定，平均时间O(nlogn)，额外空间O(1)，n小时较好
	public static int[] shellSort(int[] nums) {
		int gap = nums.length / 2;

		while (gap > 0) {
			helper(nums, gap);
			gap = gap / 2;
		}
		return nums;
	}

	public static void helper(int[] nums, int gap) {
		for (int i = 0; i < gap; i++) {
			for (int j = i + gap; j < nums.length; j += gap) {
				if (nums[j] < nums[j - gap]) {// 前面是有序的序列，因此只要和前面序列的最后一个比较就可以
					int temp = nums[j];
					int k = j - gap;
					while (k >= 0 && nums[k] > temp) {// 从这一列的最后后往前，依次和temp比较，比它大的就往后移动
						nums[k + gap] = nums[k];
						k -= gap;
					}
					nums[k + gap] = temp;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = { 8, 6, 4, 2, 4, 6, 1, 11, 33, 22, 55, 44, 77, 99, 7, 5 };
		int gap = 5;
		helper(nums, gap);
	}
}
