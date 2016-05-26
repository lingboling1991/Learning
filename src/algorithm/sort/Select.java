package algorithm.sort;

public class Select {
	// ���ȶ���ƽ��ʱ��O(n^2)������ռ�O(1)��nСʱ�Ϻ�
	public static void select(int[] nums) {
		for (int i = 0; i < nums.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[j] < nums[min])
					min = j;
			}
			if (min != i) {
				int tmp = nums[i];
				nums[i] = nums[min];
				nums[min] = tmp;
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = {3, 1, 9, 7, 2, 4, 9, 6};
		select(nums);
	}

}
