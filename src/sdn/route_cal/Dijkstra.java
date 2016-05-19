package sdn.route_cal;

public class Dijkstra {
	private static int M = 10000; // ��·��ͨ

	public static void main(String[] args) {
		int[][] weight1 = {// �ڽӾ���
				{0, 3, 2000, 7, M}, {3, 0, 4, 2, M}, {M, 4, 0, 5, 4},
				{7, 2, 5, 0, 6}, {M, M, 4, 6, 0}};

		int[][] weight2 = {{0, 10, M, 30, 100}, {M, 0, 50, M, M},
				{M, M, 0, M, 10}, {M, M, 20, 0, 60}, {M, M, M, M, 0}};

		int start = 0;
		// int[] shortPath = dijkstra(weight2, start);
		int[][] stop = getEachStop(weight2, start);

		for (int i = 0; i < stop.length; i++) {
			for (int j = 0; j < stop.length; j++) {
				System.out.println(stop[i][j]);
			}
		}
		System.out.println();

		// for (int i = 0; i < shortPath.length; i++)
		// System.out.println("��" + start + "������" + i + "����̾���Ϊ��"
		// + shortPath[i]);
	}

	public static int[] dijkstra(int[][] weight, int start) {
		// ����һ������ͼ��Ȩ�ؾ��󣬺�һ�������start����0��ţ�������������У�
		// ����һ��int[] ���飬��ʾ��start���������·������
		int n = weight.length; // �������
		int[] shortPath = new int[n]; // ����start��������������·��
		String[] path = new String[n]; // ����start�������������·�����ַ�����ʾ
		for (int i = 0; i < n; i++)
			path[i] = start + "-->" + i;
		int[] visited = new int[n]; // ��ǵ�ǰ�ö�������·���Ƿ��Ѿ����,1��ʾ�����

		// ��ʼ������һ�������Ѿ����
		shortPath[start] = 0;
		visited[start] = 1;

		for (int count = 1; count < n; count++) { // Ҫ����n-1������
			int k = -1; // ѡ��һ�������ʼ����start�����δ��Ƕ���
			int dmin = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				if (visited[i] == 0 && weight[start][i] < dmin) {
					dmin = weight[start][i];
					k = i;
				}
			}

			// ����ѡ���Ķ�����Ϊ��������·�����ҵ�start�����·������dmin
			shortPath[k] = dmin;
			visited[k] = 1;

			// ��kΪ�м�㣬������start��δ���ʸ���ľ���
			for (int i = 0; i < n; i++) {
				if (visited[i] == 0
						&& weight[start][k] + weight[k][i] < weight[start][i]) {
					weight[start][i] = weight[start][k] + weight[k][i];
					path[i] = path[k] + "-->" + i;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			System.out.println("��" + start + "������" + i + "�����·��Ϊ��" + path[i]);
		}

		System.out.println("=====================================");
		return shortPath;
	}

	public static int[][] getEachStop(int[][] weight, int start) {
		// ����һ������ͼ��Ȩ�ؾ��󣬺�һ�������sktart����0��ţ�������������У�
		// ����һ��int[] ���飬��ʾ��start���������·������
		int n = weight.length; // �������
		int[] shortPath = new int[n]; // ����start��������������·��
		String[] path = new String[n]; // ����start�������������·�����ַ�����ʾ
		for (int i = 0; i < n; i++)
			path[i] = start + "-->" + i;
		int[] visited = new int[n]; // ��ǵ�ǰ�ö�������·���Ƿ��Ѿ����,1��ʾ�����

		// ��ʼ������һ�������Ѿ����
		shortPath[start] = 0;
		visited[start] = 1;

		for (int count = 1; count < n; count++) { // Ҫ����n-1������
			int k = -1; // ѡ��һ�������ʼ����start�����δ��Ƕ���
			int dmin = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				if (visited[i] == 0 && weight[start][i] < dmin) {
					dmin = weight[start][i];
					k = i;
				}
			}

			// ����ѡ���Ķ�����Ϊ��������·�����ҵ�start�����·������dmin
			shortPath[k] = dmin;
			visited[k] = 1;

			// ��kΪ�м�㣬������start��δ���ʸ���ľ���
			for (int i = 0; i < n; i++) {
				if (visited[i] == 0
						&& weight[start][k] + weight[k][i] < weight[start][i]) {
					weight[start][i] = weight[start][k] + weight[k][i];
					path[i] = path[k] + "-->" + i;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			System.out.println("��" + start + "������" + i + "�����·��Ϊ��" + path[i]);
		}

		int[][] path_1 = new int[n][n];// ���Լ���ӵ�һ����ά���飬��һ���±��ʾĿ�꽻�������ڶ�����ʾһ·�ϵĸ����ڵ�
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				path_1[i][j] = M;

		for (int i = 0; i < n; i++) {
			String[] tmp = path[i].split("-->");
			for (int j = 0; j < tmp.length; j++) {
				// path_1[i][j] = tmp[j].getBytes()[0] - 48;
				path_1[i][j] = Integer.valueOf(tmp[j]);
			}
		}

		return path_1;
	}

}
