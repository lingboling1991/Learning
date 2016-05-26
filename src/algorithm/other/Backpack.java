package algorithm.other;

public class Backpack {
	// www.cnblogs.com/daoluanxiaozi/archive/2012/05/06/2486105.html
	// 记住表格的意义

	public static int zeroOne(int[] values, int[] weight, int capicity) {
		int total = values.length;
		int[][] tab = new int[total][capicity];// tab[尝试到第几件物品了，这里是他的编号][包中剩余体积0-15]

		// 初始化：剩余体积从0号物品的体积，到背包的最大容积，在这个范围里的包中商品总价值都等于第一件物品的价值
		// 倒着一行一行来填写的
		for (int i = weight[0]; i < capicity; i++) {
			tab[total - 1][i] = values[0];
		}

		// 从1号物品开始，遍历每一件物品
		for (int i = 1; i < total; i++) {
			// 每件物品，从它的体积，到背包的最大容积，看这些情况下是装进去好还是不装进去好
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
		int[] values = { 12, 3, 10, 3, 6 };
		int[] weight = { 5, 4, 7, 2, 6 };
		System.out.println(zeroOne(values, weight, 15));
	}
}
