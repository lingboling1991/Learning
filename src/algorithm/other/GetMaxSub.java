package algorithm.other;

import java.util.ArrayList;
import java.util.List;

public class GetMaxSub {

	// ���Ӷ�O(n^2)
	public static List<Integer> getMaxSub(List<Integer> nums) {
		int len = nums.size();
		List<List<Integer>> dp_final = new ArrayList<List<Integer>>();// ÿ��ֵԪ�ش洢һ��List����ʾ�������i���ṩ���������
		List<Integer> dp0 = new ArrayList<>();
		dp0.add(nums.get(0));
		dp_final.add(0, dp0);
		int max_length = dp0.size();
		int max_index = 0;// �����dps�е�λ��

		for (int i = 1; i < len; i++) {// �Ӵ���ֹ����i����
			ArrayList<Integer> dpi = new ArrayList<>();
			int max_by_j = 0;// ��������i������j��β�������еİ����£��ܴճ�����������еĳ���
			int mark = -1;// ��������i��֮�����ܴճ�������У�����֮ǰ��mark���ֽ�β�������еİ��������
			for (int j = i - 1; j >= 0; j--) {
				if (nums.get(i) > nums.get(j)) {
					if (dp_final.get(j).size() + 1 > max_by_j) {
						max_by_j = dp_final.get(j).size() + 1;
						mark = j;
					}
				}
			}
			dpi.add(nums.get(i));
			if (mark >= 0) {// ƴ��һ�£��������ҵ���ǰ�������Ӵ�ƴ��ȥ
				dpi.addAll(dp_final.get(mark));
			}
			dp_final.add(i, dpi);
			if (dpi.size() > max_length) {
				max_length = dpi.size();
				max_index = i;
			}
		}

		return dp_final.get(max_index);// �����ǵ����

		/*
		 * for (int i = dps.get(max_index).size() - 1; i >= 0; i--) { if (i ==
		 * 0) { System.out.print(dps.get(max_index).get(i)); } else {
		 * System.out.print(dps.get(max_index).get(i) + " "); } }
		 * System.out.println();
		 */
	}

	// ���Ӷ�O(nlogn)
	public static int getMaxSubLength(int[] nums) {
		int i, j, n, top, temp;
		int[] stack = new int[nums.length];
		n = nums.length;

		top = 0;
		// ��һ��Ԫ�ؿ���Ϊ0
		stack[0] = -1;
		for (i = 0; i < n; i++) {// ���︴�ӶȻ���O(n)
			temp = nums[i];
			// ��ջ��Ԫ�ش�������ջ
			if (temp > stack[top]) {
				stack[++top] = temp;
			} else {
				int low = 1, high = top;
				int mid;
				// ���ּ���ջ�б�temp��ĵ�һ����������ĸ��Ӷ���O(logn)
				while (low <= high) {
					mid = (low + high) / 2;
					if (temp > stack[mid])
						low = mid + 1;
					else
						high = mid - 1;
				}
				// ��temp�滻
				stack[low] = temp;
			}
		}
		return top;// ����ע��Ҫ���ص�ֻ�ǳ��ȣ�����û��Ҫ��עstack���鵽�׳���ʲô����
	}

	public static void main(String[] args) {

		int[] t = {1, 3, 5, 7, 9, 2, 4, 6, 8};

		List<Integer> nums = new ArrayList<>();
		for (int i = 0; i < t.length; i++) {
			nums.add(t[i]);
		}

		List<Integer> res = getMaxSub(nums);
	}
}
