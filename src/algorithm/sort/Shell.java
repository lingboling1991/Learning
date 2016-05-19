package algorithm.sort;

//http://wuchong.me/blog/2014/02/09/algorithm-sort-summary/

public class Shell {
	// ���ȶ���ƽ��ʱ��O(nlogn)������ռ�O(1)��nСʱ�Ϻ�
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
				if (nums[j] < nums[j - gap]) {// ǰ������������У����ֻҪ��ǰ�����е����һ���ȽϾͿ���
					int temp = nums[j];
					int k = j - gap;
					while (k >= 0 && nums[k] > temp) {// ����һ�е�������ǰ�����κ�temp�Ƚϣ�������ľ������ƶ�
						nums[k + gap] = nums[k];
						k -= gap;
					}
					nums[k + gap] = temp;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = {8, 6, 4, 2, 4, 6, 1, 11, 33, 22, 55, 44, 77, 99, 7, 5};
		int gap = 5;
		helper(nums, gap);
	}
}
