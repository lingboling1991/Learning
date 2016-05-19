package temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {

	public static List<Integer> topKFrequent(int[] nums, int k) {
		int maxFreq = 0;

		HashMap<Integer, Integer> count = new HashMap<>();// num,freq
		for (int i = 0; i < nums.length; i++) {
			if (count.containsKey(nums[i]))
				count.put(nums[i], count.get(nums[i]) + 1);
			else
				count.put(nums[i], 1);

			if (count.get(nums[i]) > maxFreq)
				maxFreq = count.get(nums[i]);
		}

		List<List<Integer>> buckets = new ArrayList<>(maxFreq + 1);
		for (int i = 0; i <= maxFreq; i++) {
			buckets.add(new ArrayList<>());
		}

		for (int num : count.keySet()) {
			int freq = count.get(num);
			buckets.get(freq).add(num);
		}

		List<Integer> res = new ArrayList<Integer>();
		for (int i = buckets.size() - 1; i >= 0 && k > 0; i--) {
			if (buckets.get(i) != null && k > 0) {
				res.addAll(buckets.get(i));
				k -= buckets.get(i).size();
			}
		}

		return res;
	}

	public static void main(String[] args) {
		int[] nums = {9, 8, 7, 6, 5, 4, 3, 2, 1, 9, 8, 7, 6, 5, 4, 3, 9, 8, 7,
				6};
		List<Integer> res = topKFrequent(nums, 2);
		System.out.println(res.toString());
	}
}