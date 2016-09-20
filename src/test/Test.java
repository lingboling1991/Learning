package test;

import java.util.HashMap;

/**
 * Created by LCW on 2016-9-19.
 */

public class Test {
	public static void main(String[] args) {
		System.out.println(getNum("((())"));
	}

	public static int getNum(String s) {
		int l = 0;
		int r = 0;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') {
				l++;
			} else {
				r++;
			}

			if (r > l) {
				return -1;
			}
		}
		if (l > r) {
			return -1;
		} else {
			return l;
		}
	}

	public static int getMaxSq(int[][] map) {
		int[][] tmp = new int[map.length][map[0].length];
		int[][] touched = new int[map.length][map[0].length];
		HashMap<String, Node> hashMap = new HashMap<>();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {

				if (map[i][j] == 1) {
					if (touched[i][j] == 1) {

						hashMap.get(String.valueOf(i) + ":" + String.valueOf(j));

					} else {

						tmp[i][j] = helper(i, j - 1, map, hashMap, "left")
								+ helper(i, j + 1, map, hashMap, "right")
								+ helper(i + 1, j, map, hashMap, "down")
								+ 1;

					}
				}

			}
		}
	}

	public static int helper(int i, int j, int[][] map, HashMap<String, Node> hashMap, String to) {
		if (hashMap.containsKey(i + ":" + j)) {
			return hashMap.get(i + ":" + j).maxSq;
		}

	}

	class Node {
		int first;
		int second;
		int maxSq;
	}

}
