package algorithm.sort.important;

//http://www.cnblogs.com/jetpie/p/3971382.html

public class Heap {
	// ��top K������ʹ�ñȽ�Ƶ�������ȶ���ƽ��ʱ��O(nlogn)������ռ�O(1)����������ӻ�������

	static int heapsize;

	public static void main(String[] args) {
		int[] nums = {9, 7, 3, 1, 6, 8, 2, 4, 5};
		heapsize = nums.length;
		heap(nums);

		System.out.println("a");
	}

	public static void heap(int[] nums) {
		build(nums);
		for (int i = nums.length - 1; i >= 1; i--) {
			// �����ķŵ����ȥ�������ͱ�ɽ���������
			int x = nums[i];
			nums[i] = nums[0];
			nums[0] = x;

			heapsize--;

			heapify(nums, 0);
		}
	}

	protected static void build(int[] nums) {
		for (int i = parent(heapsize - 1); i >= 0; i--)
			// �����һ���ڵ�ĸ��ڵ㿪ʼ�������Ͻ��в���
			heapify(nums, i);
	}

	protected static void heapify(int[] nums, int i) {
		// ���ѻ�����float down�������Ǽ�����nums[l]��nums[r]Ϊ���ڵ�������������ѣ�
		// �Ѷ��ߵĸ��ڵ���Լ���ȣ��������ķ����棬���Ų���������ĳ���㣬���ٶ��������������д���
		int l = left(i);
		int r = right(i);
		int largest = i;

		if (l < heapsize && nums[l] > nums[i])
			largest = l;

		if (r < heapsize && nums[r] > nums[largest])
			largest = r;

		if (largest != i) {
			int x = nums[i];
			nums[i] = nums[largest];
			nums[largest] = x;

			heapify(nums, largest);
		}

	}

	protected static int parent(int i) {
		return (i - 1) / 2;
	}

	protected static int left(int i) {
		return 2 * i + 1;
	}

	protected static int right(int i) {
		return 2 * i + 2;
	}
}
