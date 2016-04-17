package temp;

import java.util.Scanner;

public class CopyOfMain {
	public static int[][] combo;
	public static int x;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		while (in.hasNext()) {
			int n = in.nextInt();
			int v = in.nextInt();

			int[] values = new int[n];
			int[] weight = new int[n];
			combo = new int[n][v + 1];

			for (int i = 0; i < n; i++) {
				weight[i] = in.nextInt();
				values[i] = in.nextInt();
			}

			int res = zeroOne(values, weight, v);
			System.out.println(res);

			int z = combo[0][v];
			String s = String.valueOf(z);
			char[] c = s.toCharArray();

			if (c.length == 1 && c[0] == '0') {
				System.out.println("NO");
			}

			for (int i = 0; i < c.length; i++) {
				if (c[i] != '0') {
					System.out.println(c[i] + " ");
				}
			}
		}
	}

	public static int zeroOne(int[] values, int[] weight, int capicity) {
		int total = values.length;
		int[][] tab = new int[total][capicity + 1];// tab[尝试到第几件物品了，这里是他的编号][包中剩余体积0-15]

		x = 0;

		// 初始化：剩余体积从0号物品的体积，到背包的最大容积，在这个范围里的包中商品总价值都等于第一件物品的价值
		// 倒着一行一行来填写的
		for (int i = weight[0]; i <= capicity; i++) {
			tab[total - 1][i] = values[0];
			combo[total - 1][i] += weight[0] * Math.pow(10, x);
		}
		x++;

		// 从1号物品开始，遍历每一件物品
		for (int i = 1; i < total; i++) {
			// 每件物品，从它的体积，到背包的最大容积，看这些情况下是装进去好还是不装进去好
			for (int j = weight[i]; j <= capicity; j++) {
				if (values[i] + tab[total - i][j - weight[i]] > tab[total - i][j]) {// 装进去好
					combo[total - 1 - i][j] += weight[i] * Math.pow(10, x);
				} else {
					combo[total - 1 - i][j] += combo[total - i][j];
				}
				tab[total - 1 - i][j] = Math.max(tab[total - i][j], values[i]
						+ tab[total - i][j - weight[i]]);
			}
			x += 1;
		}

		return tab[0][capicity];
	}

}