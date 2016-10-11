package algorithm.interview.netease.lede;

import java.math.BigInteger;
import java.util.Scanner;

public class PickBalls {
	public static void main(String[] args) {
		int res = 0;

		Scanner cin = new Scanner(System.in);
		int N = cin.nextInt();
		int M = cin.nextInt();

		//方案1：回溯，遍历所有可能的排序，然后得到数量
		/*int[] balls = new int[N];
		for (int i = 0; i < N; i++) {
			balls[i] = 4;
		}

		if (M > 2 * N) {
			M = 4 * N - M;
		}

		System.out.println(help(balls, M, res, N, 0));*/

		//方案2：数学方案
		int i, j;
		int MAXN = 1000;
		BigInteger[][] C = new BigInteger[MAXN + 1][MAXN + 1];
		for (i = 0; i <= MAXN; i++) {
			C[0][i] = BigInteger.ZERO;
			C[i][0] = BigInteger.ONE;
		}
		for (i = 1; i <= MAXN; i++)
			for (j = 1; j <= MAXN; ++j)
				C[i][j] = C[i - 1][j].add(C[i - 1][j - 1]);

		BigInteger result = BigInteger.ZERO;

		int x0, x1, x2, x3, x4;
		for (x4 = 0; x4 <= N; x4++) {
			for (x3 = 0; x3 <= N; x3++) {
				for (x2 = 0; x2 <= N; x2++) {
					x1 = M - 4 * x4 - 3 * x3 - 2 * x2;
					x0 = N - x4 - x3 - x2 - x1;
					if (x1 >= 0 && x0 >= 0) {
						BigInteger a = (x4 == 0 ? BigInteger.ZERO : C[N][x4]);
						BigInteger b = (x3 == 0 ? BigInteger.ZERO : C[N - x4][x3]);
						BigInteger c = (x2 == 0 ? BigInteger.ZERO : C[N - x4 - x3][x2]);
						BigInteger d = (x1 == 0 ? BigInteger.ZERO : C[N - x4 - x3 - x2][x1]);
						BigInteger x = a.add(b);
						BigInteger y = c.add(d);

						result = result.add(x.add(y));
					}
				}
			}
		}
		System.out.println(result);
	}

	public static int help(int[] balls, int rest, int res, int N, int curColor) {
		if (rest == 0) {
			res++;
			return res;
		}
		for (int i = curColor; i < N; i++) {
			if (balls[i] != 0) {
				balls[i]--;
				res = help(balls, rest - 1, res, N, i);
				balls[i]++;
			}

		}
		return res;
	}
}
