package algorithm.sort;

import java.util.ArrayList;
import java.util.List;

//http://blog.csdn.net/fg2006/article/details/6677465
//http://www.cnblogs.com/kkun/archive/2011/11/23/bucket_sort.html

public class Bucket {
	// 已知其区间，例如[1..10]，学生的分数[0...100]等，如果比较数据分散，可以用函数取映射作为sorted数组的下标
	// 如果有重复的数字,则需要 List<int>数组，这里举的例子没有重复的数字
	public static List<Integer> bucket(int[] unsorted) {
		int max = unsorted[0];
		for (int i = 1; i < unsorted.length; i++) {
			max = Math.max(max, unsorted[i]);
		}

		Integer[] sorted = new Integer[max + 1];// 这里是桶有冗余，可以用二维数组表示一串容量固定的小桶，而小桶里还需要排序
		for (int i = 0; i < unsorted.length; i++) {
			sorted[unsorted[i]] = unsorted[i];// f(x)映射，List<>防重复，都在这里
		}

		List<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < sorted.length; i++) {
			if (sorted[i] != null) {
				res.add(sorted[i]);
			}
		}

		return res;
	}

	public static void main(String[] args) {
		int[] src = { 9, 8, 4, 3, 2, 1, 7, 6, 5 };
		System.out.println(bucket(src).toString());
	}
}
