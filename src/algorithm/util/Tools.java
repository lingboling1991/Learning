package algorithm.util;

import java.util.ArrayList;
import java.util.List;

public class Tools {
	public static List<Integer> copyList(List<Integer> src) {
		List<Integer> tmp = new ArrayList<>();
		for (int j = 0; j < src.size(); j++) {
			tmp.add(src.get(j));
		}
		return tmp;
	}

	public static void switchList(int[] src, int i, int j) {
		int t = src[i];
		src[i] = src[j];
		src[j] = t;
	}
}
