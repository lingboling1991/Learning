package algorithm.sort.important;

public class Merge {
	// 平均时间复杂度O(nlogn)，辅助空间O(n)，稳定
	public static void mergeSort(int[] nums) {
		sort(nums, 0, nums.length - 1);
	}

	public static void sort(int[] nums, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			sort(nums, left, mid);
			sort(nums, mid + 1, right);
			merge(nums, left, mid, right);
		}
	}

	public static void merge(int[] nums, int left, int mid, int right) {
		int[] tmp = new int[nums.length];
		int tstart = left;

		int lstart = left;
		int lstop = mid;
		int rstart = mid + 1;
		int rstop = right;

		while (lstart <= lstop && rstart <= rstop) {
			if (nums[lstart] <= nums[rstart])
				tmp[tstart++] = nums[lstart++];
			else
				tmp[tstart++] = nums[rstart++];
		}

		while (lstart <= lstop)
			tmp[tstart++] = nums[lstart++];
		while (rstart <= rstop)
			tmp[tstart++] = nums[rstart++];

		while (left <= right) {
			nums[left] = tmp[left];
			left++;
		}
	}

	public static void main(String[] args) {
		int[] nums = {7, 6, 9, 4, 8, 3, 2, 5, 1};
		mergeSort(nums);
		System.out.println("a");
	}
}
