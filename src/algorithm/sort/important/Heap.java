package algorithm.sort.important;

//http://www.cnblogs.com/jetpie/p/3971382.html

public class Heap {
	// 解决top k问题，时间复杂度最好最坏和平均都是O(nlogn)，额外空间O(1)，不稳定

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
			//为了最后结果是升序的
			int x = nums[i];
			nums[i] = nums[0];
			nums[0] = x;

			heapsize--;

			heapify(nums, 0);
		}
	}

	protected static void build(int[] nums) {
		for (int i = parent(heapsize - 1); i >= 0; i--)
			// 从最后一个节点的
			heapify(nums, i);
	}

	protected static void heapify(int[] nums, int i) {
		// 建一个大顶堆（即根元素是最小的）
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
