package algorithm.sort.important;

public class Quick {
	// ���ȶ���ƽ��ʱ��O(nlogn)������ռ�O(nlogn)��ͨ����������������ѡ����Ϊ�����ڱȽϵ��������Ҳֻ�ܴﵽO��nlogn��
	public static void quick(int[] nums) {
		helper(nums, 0, nums.length - 1);
	}

	public static void helper(int[] nums, int left, int right) {
		if (left < right) {
			int key = nums[left];// ��׼��
			int lp = left;// ��ָ��
			int rp = right;// ��ָ��
			while (lp < rp) {
				while (nums[rp] >= key && lp < rp)
					// �������ƶ�rp�����ƶ�lp����Ȼ���Ŵ�
					rp -= 1;
				while (nums[lp] <= key && lp < rp)
					lp += 1;
				if (lp != rp) {
					int t = nums[lp];
					nums[lp] = nums[rp];
					nums[rp] = t;
				}
			}
			// ��Ϊ�ǰѱ�keyС����ǰ�ţ���key�������ţ���ô���lp==rp��ʱ��lp��Ӧ��ָ���м�λ�ã���ʱ�ٰ�key�������м�
			int t = nums[left];
			nums[left] = nums[lp];
			nums[lp] = t;

			// ע����ʱlp��rpλ��û�䣬ֻ��ָ�ŵ�ֵ����
			// �ٶ���������ݹ�ִ�У�ֱ��������ֻ��һ����
			helper(nums, left, lp - 1);
			helper(nums, rp + 1, right);// ����ֻ��Ϊ�˷�����⣬��ʱlp��rpӦ������ͬһ��λ�õ�
		}
	}

	public static void main(String[] args) {
		int[] nums = {1, 2, 0, 2, 2, 2, 0, 0, 2, 1, 1};
		quick(nums);
		System.out.println("a");
	}
}
