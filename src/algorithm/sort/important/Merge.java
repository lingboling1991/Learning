package algorithm.sort.important;

public class Merge {
	// �ȶ���ƽ��ʱ��O(nlogn)������ռ�O(n)��n��ʱЧ�ʽϸߣ�����Ҫ���ڴ�
	public static void mergeSort(int[] nums) {
		sort(nums, 0, nums.length - 1);
	}

	public static void sort(int[] nums, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			// ��·�鲢��������������Sort����·�鲢��������д���Sort�Ϳ�����
			sort(nums, left, mid);
			sort(nums, mid + 1, right);
			merge(nums, left, mid, right);
		}
	}

	public static void merge(int[] nums, int left, int mid, int right) {
		int[] tmp = new int[nums.length];
		int tstart = left;

		// ����ֻ��Ϊ�˱��������д��ʱ����Ҫ��ô����
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
