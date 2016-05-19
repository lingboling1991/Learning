package algorithm.sort.important;

public class Quick {
	// 不稳定，平均时间O(nlogn)，额外空间O(nlogn)。通常是用于排序的最佳选择。因为，基于比较的排序，最快也只能达到O（nlogn）
	public static void quick(int[] nums) {
		helper(nums, 0, nums.length - 1);
	}

	public static void helper(int[] nums, int left, int right) {
		if (left < right) {
			int key = nums[left];// 基准数
			int lp = left;// 左指针
			int rp = right;// 右指针

			while (lp < rp) {
				while (nums[rp] >= key && lp < rp)
					// 这里先移动rp，再移动lp，不然会排错
					rp -= 1;
				while (nums[lp] <= key && lp < rp)
					lp += 1;
				if (lp != rp) {
					int t = nums[lp];
					nums[lp] = nums[rp];
					nums[rp] = t;
				}
			}
			// 因为是把比key小的往前放，比key大的往后放，那么最后lp==rp的时候，lp就应该指着中间位置，这时再把key交换回中间
			int t = nums[left];
			nums[left] = nums[lp];
			nums[lp] = t;

			// 注意这时lp和rp位置没变，只是指着的值变了
			// 再对左右区间递归执行，直至各区间只有一个数
			helper(nums, left, lp - 1);
			helper(nums, rp + 1, right);// 这里只是为了方便理解，此时lp和rp应该是在同一个位置的
		}
	}

	public static void main(String[] args) {
		int[] nums = {1, 2, 0, 2, 2, 2, 0, 0, 2, 1, 1};
		quick(nums);
		System.out.println("a");
	}
}
