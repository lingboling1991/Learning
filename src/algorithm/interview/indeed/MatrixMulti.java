package algorithm.interview.indeed;

import java.util.Scanner;

public class MatrixMulti {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int k = in.nextInt();

		int[][] a = new int[n][m];
		int[][] b = new int[m][k];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				a[i][j] = in.nextInt();
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < k; j++) {
				b[i][j] = in.nextInt();
			}
		}
		printMatrix(a, b);
	}

	static void printMatrix(int[][] a, int[][] b) {
		int c[][] = new int[a.length][b[0].length];

		int x, i, j;
		for (i = 0; i < a.length; i++) {
			for (j = 0; j < b[0].length; j++) {
				int temp = 0;
				for (x = 0; x < b.length; x++) {
					temp += a[i][x] * b[x][j];

				}
				c[i][j] = temp;

			}
		}

		for (int m = 0; m < a.length; m++) {
			for (int n = 0; n < b[0].length; n++) {
				System.out.print(c[m][n] + " ");
			}
			System.out.println();
		}
	}


}
