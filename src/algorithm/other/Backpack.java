package algorithm.other;

public class Backpack {
	// www.cnblogs.com/daoluanxiaozi/archive/2012/05/06/2486105.html
	// ��ס��������

	public static int zeroOne(int[] values, int[] weight, int capicity) {
		int total = values.length;
		int[][] tab = new int[total][capicity];// tab[���Ե��ڼ�����Ʒ�ˣ����������ı��][����ʣ�����0-15]

		// ��ʼ����ʣ�������0����Ʒ�������������������ݻ����������Χ��İ�����Ʒ�ܼ�ֵ�����ڵ�һ����Ʒ�ļ�ֵ
		// ����һ��һ������д��
		for (int i = weight[0]; i < capicity; i++) {
			tab[total - 1][i] = values[0];
		}

		// ��1����Ʒ��ʼ������ÿһ����Ʒ
		for (int i = 1; i < total; i++) {
			// ÿ����Ʒ�������������������������ݻ�������Щ�������װ��ȥ�û��ǲ�װ��ȥ��
			for (int j = weight[i]; j < capicity; j++) {
				tab[total - 1 - i][j] = Math.max(tab[total - i][j], values[i]
						+ tab[total - i][j - weight[i]]);
			}
		}

		return tab[0][capicity - 1];
	}

	// public static int complete(int[] values, int[] weight, int capicity){
	// int[] tab=new int[]
	// }

	public static void main(String[] args) {
		int[] values = {12, 3, 10, 3, 6};
		int[] weight = {5, 4, 7, 2, 6};
		System.out.println(zeroOne(values, weight, 15));
	}
}
