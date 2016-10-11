package algorithm.interview.hulu;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by lenovo on 2016-9-27.
 */
public class HuluwaMoving {
	//未完成版本，回退时应去掉已经探索过的地块，还有s需要区分，是因为走无可走->回溯->++，还是后退寻找新的路->继续->--
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		int N = cin.nextInt();
		cin.nextLine();
		for (int i = 0; i < N; i++) {
			String tmpString = cin.nextLine();
			String[] strings = tmpString.split(" ");
			int n = Integer.valueOf(strings[0]);
			int m = Integer.valueOf(strings[1]);
			int L = Integer.valueOf(strings[2]);
			int s = Integer.valueOf(strings[3]);

			String[][] map = new String[m][n];

			for (int j = 0; j < m; j++) {
				String x = cin.nextLine();
				String[] xs = x.split("");
				for (int k = 0; k < n; k++) {
					map[j][k] = xs[k];
				}
			}

			Set<String> touched = new HashSet<>();
			Set<Integer> pack = new HashSet<>();

			touched.add(String.valueOf(m - 1) + ":" + String.valueOf(0));
			moveUp(map, m - 1, 0, m, n, L, s, touched, pack);
			moveRight(map, m - 1, 0, m, n, L, s, touched, pack);

			System.out.println("Test Case " + (i + 1) + ": " + touched.size());
//			System.out.println("sss");
		}
	}

	//sm == start_m, y dir; sn == start_n, x dir;
	public static void moveUp(String[][] map, int sm, int sn, int m, int n, int L, int s, Set<String> touched, Set<Integer> pack) {
		if (map[sm][sn].equals("-")) {
			return;
		}
		//using ladder
		for (int i = 1; i <= L && sm - i >= 0; i++) {// < or <=, think again
			if (map[sm - i][sn].equals("#") &&
					!touched.contains(String.valueOf(sm - i) + ":" + String.valueOf(sn))) {

				touched.add(String.valueOf(sm - i) + ":" + String.valueOf(sn));
				moveLeft(map, sm - i, sn, m, n, L, s, touched, pack);
				moveUp(map, sm - i, sn, m, n, L, s, touched, pack);
				moveRight(map, sm - i, sn, m, n, L, s, touched, pack);
				touched.remove(String.valueOf(sm - i) + ":" + String.valueOf(sn));
			}
		}
	}

	public static void moveDown(String[][] map, int sm, int sn, int m, int n, int L, int s, Set<String> touched, Set<Integer> pack) {
		if (map[sm][sn].equals("-")) {
			return;
		}
		//using ladder
		for (int i = 1; i <= L && sm + i < m; i++) {// < or <=, think again
			if (map[sm + i][sn].equals("#") && !touched.contains(String.valueOf(sm + i) + ":" + String.valueOf(sn))) {
				touched.add(String.valueOf(sm + i) + ":" + String.valueOf(sn));
				moveLeft(map, sm + i, sn, m, n, L, s, touched, pack);
				moveDown(map, sm + i, sn, m, n, L, s, touched, pack);
				moveRight(map, sm + i, sn, m, n, L, s, touched, pack);
				touched.remove(String.valueOf(sm + i) + ":" + String.valueOf(sn));
			}
		}
	}

	public static void moveLeft(String[][] map, int sm, int sn, int m, int n, int L, int s, Set<String> touched, Set<Integer> pack) {
		if (map[sm][sn].equals("-")) {
			return;
		}
		//need and able to jump
		if (sn - 1 >= 0 &&
				map[sm][sn - 1].equals("-") &&
				sn - 2 >= 0 &&
				map[sm][sn - 2].equals("#") &&
				s > 0 &&
				!touched.contains(String.valueOf(sm) + ":" + String.valueOf(sn - 2))) {

			s--;
			touched.add(String.valueOf(sm) + ":" + String.valueOf(sn - 2));
			moveUp(map, sm, sn - 2, m, n, L, s, touched, pack);
			moveLeft(map, sm, sn - 2, m, n, L, s, touched, pack);
			moveDown(map, sm, sn - 2, m, n, L, s, touched, pack);

			if (s == 0 &&
					sm + 1 < m && map[sm + 1][sn - 2].equals("-") &&
					sm - 1 >= 0 && map[sm - 1][sn - 2].equals("-") &&
					sn - 1 < n && map[sm][sn - 1].equals("-") &&
					sn - 3 >= 0 && map[sm][sn - 3].equals("-")) {

				pack.add(touched.size());
			}
			touched.remove(String.valueOf(sm) + ":" + String.valueOf(sn - 2));
		} else if (sn - 1 >= 0 &&
				map[sm][sn - 1].equals("#") &&
				!touched.contains(String.valueOf(sm) + ":" + String.valueOf(sn - 1))) {

			touched.add(String.valueOf(sm) + ":" + String.valueOf(sn - 1));
			moveUp(map, sm, sn - 1, m, n, L, s, touched, pack);
			moveLeft(map, sm, sn - 1, m, n, L, s, touched, pack);
			moveDown(map, sm, sn - 1, m, n, L, s, touched, pack);
			touched.remove(String.valueOf(sm) + ":" + String.valueOf(sn - 1));
		}
	}

	public static void moveRight(String[][] map, int sm, int sn, int m, int n, int L, int s, Set<String> touched, Set<Integer> pack) {
		if (map[sm][sn].equals("-")) {
			return;
		}
		//need and able to jump
		if (sn + 1 < n &&
				map[sm][sn + 1].equals("-") &&
				sn + 2 < n &&
				map[sm][sn + 2].equals("#") &&
				s > 0 &&
				!touched.contains(String.valueOf(sm) + ":" + String.valueOf(sn + 2))) {

			s--;
			touched.add(String.valueOf(sm) + ":" + String.valueOf(sn + 2));
			moveUp(map, sm, sn + 2, m, n, L, s, touched, pack);
			moveRight(map, sm, sn + 2, m, n, L, s, touched, pack);
			moveDown(map, sm, sn + 2, m, n, L, s, touched, pack);

			if (s == 0 &&
					sm + 1 < m && map[sm + 1][sn + 2].equals("-") &&
					sm - 1 >= 0 && map[sm - 1][sn + 2].equals("-") &&
					sn - 1 < n && map[sm][sn + 1].equals("-") &&
					sn - 3 >= 0 && map[sm][sn + 3].equals("-")) {

				pack.add(touched.size());
			}
			touched.remove(String.valueOf(sm) + ":" + String.valueOf(sn + 2));

		} else if (sn + 1 < n &&
				map[sm][sn + 1].equals("#") &&
				!touched.contains(String.valueOf(sm) + ":" + String.valueOf(sn + 1))) {

			touched.add(String.valueOf(sm) + ":" + String.valueOf(sn + 1));
			moveUp(map, sm, sn + 1, m, n, L, s, touched, pack);
			moveRight(map, sm, sn + 1, m, n, L, s, touched, pack);
			moveDown(map, sm, sn + 1, m, n, L, s, touched, pack);
			touched.remove(String.valueOf(sm) + ":" + String.valueOf(sn + 1));
		}
	}
}
