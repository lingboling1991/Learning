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
		int t_start = left;

		int l_start = left;
		int l_stop = mid;
		int r_start = mid + 1;
		int r_stop = right;

		while (l_start <= l_stop && r_start <= r_stop) {
			if (nums[l_start] <= nums[r_start])
				tmp[t_start++] = nums[l_start++];
			else
				tmp[t_start++] = nums[r_start++];
		}

		while (l_start <= l_stop)
			tmp[t_start++] = nums[l_start++];
		while (r_start <= r_stop)
			tmp[t_start++] = nums[r_start++];

		//merge的结果，先在tmp里保存，再全抄回nums
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
