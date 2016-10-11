package algorithm.interview.netease.youdao;

public class Monkey {
	public static void main(String[] args) {
		// amIRight(6148914691236517202L);
		System.out.println(check(50));
	}

	public static long check(int n) {
		long[] left = new long[n];
		left[n - 1] = 1;

		for (int i = n - 1; i > 0; i--) {
			if (i % 2 == 0) {
				left[i - 1] = (left[i] + 1) * 2;
			} else {
				left[i - 1] = (left[i] + 3) * 2;
			}
		}

		return left[0];
	}
}
