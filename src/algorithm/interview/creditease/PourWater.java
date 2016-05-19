package algorithm.interview.creditease;

import java.util.Arrays;
import java.util.HashSet;

public class PourWater {
	public static HashSet<Integer> hashSet;

	public static void plan(int m, int n) {
		int[] cups = new int[n];
		hashSet = new HashSet<>();
		pour(m, n, m, 0, cups);
		System.out.println(hashSet.size());
	}

	public static void pour(int m, int n, int left, int cup_no, int[] cups) {
		if (left == 0) {
			int[] t = new int[cups.length];
			for (int i = 0; i < cups.length; i++) {
				t[i] = cups[i];
			}
			Arrays.sort(t);
			int mark = 0;
			for (int i = 0; i < t.length; i++) {
				mark += t[i] * Math.pow(10, i);
			}
			hashSet.add(mark);
			return;
		}

		for (int j = cup_no; j < n; j++) {
			cups[j] += 1;
			left--;
			pour(m, n, left, j, cups);
			cups[j] -= 1;
			left++;
		}
	}

	public static void main(String[] args) {
		plan(3, 3);
	}
}
