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
		int[][] tab = new int[total][capicity + 1];// tab[���Ե��ڼ�����Ʒ�ˣ����������ı��][����ʣ�����0-15]

		x = 0;

		// ��ʼ����ʣ�������0����Ʒ�������������������ݻ����������Χ��İ�����Ʒ�ܼ�ֵ�����ڵ�һ����Ʒ�ļ�ֵ
		// ����һ��һ������д��
		for (int i = weight[0]; i <= capicity; i++) {
			tab[total - 1][i] = values[0];
			combo[total - 1][i] += weight[0] * Math.pow(10, x);
		}
		x++;

		// ��1����Ʒ��ʼ������ÿһ����Ʒ
		for (int i = 1; i < total; i++) {
			// ÿ����Ʒ�������������������������ݻ�������Щ�������װ��ȥ�û��ǲ�װ��ȥ��
			for (int j = weight[i]; j <= capicity; j++) {
				if (values[i] + tab[total - i][j - weight[i]] > tab[total - i][j]) {// װ��ȥ��
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