package algorithm.sort.important;

public class Merge {
	// 稳定，平均时间O(nlogn)，额外空间O(n)，n大时效率较高，但需要大内存
	public static void mergeSort(int[] nums) {
		sort(nums, 0, nums.length - 1);
	}

	public static void sort(int[] nums, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			// 二路归并排序里面有两个Sort，多路归并排序里面写多个Sort就可以了
			sort(nums, left, mid);
			sort(nums, mid + 1, right);
			merge(nums, left, mid, right);
		}
	}

	public static void merge(int[] nums, int left, int mid, int right) {
		int[] tmp = new int[nums.length];
		int tstart = left;

		// 这里只是为了表达清晰，写的时候不需要这么复杂
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
		int[] nums = { 7, 6, 9, 4, 8, 3, 2, 5, 1 };
		mergeSort(nums);
		System.out.println("a");
	}
}
