package algorithm.sort;

public class Bubble {
	// 稳定，平均时间O(n^2)，额外空间O(1)，n小时较好
	public static void bubble(int[] nums) {
		for (int i = 0; i < nums.length - 1; i++) {// 数组里有n个数就要比较n-1次
			for (int j = 0; j < nums.length - 1 - i; j++) {// 每次循环,都要比较n-i-1次；每次j<nums.length-1-i这里要-i,
				if (nums[j] > nums[j + 1]) {// 把最大的沉到最右边
					int tmp = nums[j];
					nums[j] = nums[j + 1];
					nums[j + 1] = tmp;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] src = { 3, 1, 5, 7, 2, 4, 8, 6, 1 };

		bubble(src);

		System.out.println("a");
	}
}
