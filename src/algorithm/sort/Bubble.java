package algorithm.sort;

public class Bubble {
	// �ȶ���ƽ��ʱ��O(n^2)������ռ�O(1)��nСʱ�Ϻ�
	public static void bubble(int[] nums) {
		for (int i = 0; i < nums.length - 1; i++) {// ��������n������Ҫ�Ƚ�n-1��
			for (int j = 0; j < nums.length - 1 - i; j++) {// ÿ��ѭ��,��Ҫ�Ƚ�n-i-1�Σ�ÿ��j<nums.length-1-i����Ҫ-i,
				if (nums[j] > nums[j + 1]) {// �����ĳ������ұ�
					int tmp = nums[j];
					nums[j] = nums[j + 1];
					nums[j + 1] = tmp;
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
