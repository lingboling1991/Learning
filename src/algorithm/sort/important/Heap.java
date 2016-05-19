package algorithm.sort.important;

//http://www.cnblogs.com/jetpie/p/3971382.html

public class Heap {
	// 在top K问题中使用比较频繁，不稳定，平均时间O(nlogn)，额外空间O(1)，这里的例子基于最大堆

	static int heapsize;

	public static void main(String[] args) {
		int[] nums = { 9, 7, 3, 1, 6, 8, 2, 4, 5 };
		heapsize = nums.length;
		heap(nums);

		System.out.println("a");
	}

	public static void heap(int[] nums) {
		build(nums);
		for (int i = nums.length - 1; i >= 1; i--) {
			// 把最大的放到最后去，这样就变成降序排列了
			int x = nums[i];
			nums[i] = nums[0];
			nums[0] = x;

			heapsize--;

			heapify(nums, 0);
		}
	}

	protected static void build(int[] nums) {
		for (int i = parent(heapsize - 1); i >= 0; i--)
			// 从最后一个节点的父节点开始，逐级往上进行操作
			heapify(nums, i);
	}

	protected static void heapify(int[] nums, int i) {
		// 最大堆化，“float down”，就是假设以nums[l]和nums[r]为根节点的子树都是最大堆，
		// 把二者的根节点和自己相比，最后把最大的放上面，如果挪动了左或右某个点，就再对它所在子树进行处理
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
