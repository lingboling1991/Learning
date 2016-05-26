package algorithm.sort;

//http://www.cricode.com/3212.html

public class Insert {
	// 稳定，平均时间O(n^2)，额外空间O(1)，大部分已排序时较好
	public static int[] directInsert(int[] src) {
		int tmp;
		for (int i = 1; i < src.length; i++) {// 这时，src[i]左边的数组已经被排好序了
			for (int j = 0; j < i; j++) {// src[j]从左往右，逐个来和src[i]比较，看谁恰好比src[i]大
				if (src[i] < src[j]) {// 这里是<而不是<=，保证了算法的稳定性
					tmp = src[i];
					for (int k = i; k != j; k--) {// 从i开始，从右往左，逐位往右挪一个
						src[k] = src[k - 1];
					}
					src[j] = tmp;
					break;
				}
			}
		}
		return src;
	}

	public static int biSearch(int[] nums, int L, int R, int target) {
		if (L == R) {
			return nums[L];
		}

		int M = (L + R) / 2;
		int res;

		if (target > nums[M]) {
			res = biSearch(nums, M + 1, R, target);
		} else {
			res = biSearch(nums, L, M, target);
		}
		return res;
	}

	public static int biSearch_book(int[] nums, int L, int R, int target) {
		while (L < R) {
			int M = (L + R) / 2;
			if (target > nums[M])
				L = M + 1;
			else
				R = M;
		}

		return (L == R && nums[L] == target) ? L : -1;
	}

	public static void main(String[] args) {
		int[] src = { 3, 1, 5, 7, 2, 4, 8, 6 };

		int[] res = directInsert(src);
		//
		// for (int j = 0; j < res.length; j++) {
		// System.out.println(res[j]);
		// }

		System.out.println(biSearch(res, 0, res.length - 1, 3));
	}
}
