package class32;

// 测试链接：https://leetcode.com/problems/range-sum-query-2d-mutable
// 但这个题是付费题目
// 提交时把类名、构造函数名从Code02_IndexTree2D改成NumMatrix
public class Code02_IndexTree2D {
	private int[][] tree;
	private int[][] nums;
	private int N;
	private int M;

	public Code02_IndexTree2D(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) {
			return;
		}
		N = matrix.length;
		M = matrix[0].length;
		tree = new int[N + 1][M + 1];
		nums = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				update(i, j, matrix[i][j]);
			}
		}
	}

	private int sum(int row, int col) {
		int sum = 0;
		for (int i = row + 1; i > 0; i -= i & (-i)) {
			for (int j = col + 1; j > 0; j -= j & (-j)) {
				sum += tree[i][j];
			}
		}
		return sum;
	}

	public void update(int row, int col, int val) {
		if (N == 0 || M == 0) {
			return;
		}
		int add = val - nums[row][col];
		nums[row][col] = val;
		for (int i = row + 1; i <= N; i += i & (-i)) {
			for (int j = col + 1; j <= M; j += j & (-j)) {
				tree[i][j] += add;
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		if (N == 0 || M == 0) {
			return 0;
		}
		return sum(row2, col2) + sum(row1 - 1, col1 - 1) - sum(row1 - 1, col2) - sum(row2, col1 - 1);
	}

}
