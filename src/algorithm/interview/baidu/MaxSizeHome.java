package algorithm.interview.baidu;

/**
 * Created by lenovo on 2016-9-27.
 */
public class MaxSizeHome {
	public int numIslands(char[][] grid) {
		int result = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1') {
					result++;
					dfs(grid, i, j);
				}
			}
		}
		return result;
	}

	private void dfs(char[][] grid, int i, int j) {
		if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
			return;
		}
		grid[i][j] = '2';
		dfs(grid, i, j + 1);
		dfs(grid, i + 1, j);
		dfs(grid, i, j - 1);
		dfs(grid, i - 1, j);
	}

	private static int num;

	public static int bigHomes(int[][] grid) {
		int result = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					num = 0;
					dfs(grid, i, j);
					result = Math.max(result, num);
				}
			}
		}
		return result;
	}

	private static void dfs(int[][] grid, int i, int j) {
		if (i < 0 || i <= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
			return;
		}
		num++;
		grid[i][j] = 2;
		dfs(grid, i, j + 1);
		dfs(grid, i + 1, j);
		dfs(grid, i, j - 1);
		dfs(grid, i - 1, j);
	}
}
