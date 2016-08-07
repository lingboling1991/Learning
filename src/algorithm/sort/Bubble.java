package algorithm.sort;

public class Bubble {
	// 平均时间复杂度O(n^2)，辅助空间O(1)，稳定
	public static void bubble(int[] nums) {
		if (nums == null || nums.length == 0)
			return;

		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = nums.length - 1; j > i; j--) {
				if (nums[j] < nums[j - 1]) {
					int tmp = nums[j];
					nums[j] = nums[j - 1];
					nums[j - 1] = tmp;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] src = {3, 1, 5, 7, 2, 4, 8, 6, 1};

		bubble(src);

		System.out.println("a");
	}
}
