package class09_14;

import java.util.ArrayList;
import java.util.List;

import class09_14.Code05_UnionFind.UnionSet;

public class Code06_IslandProblem {

	public static int solve1(int[][] board) {
		int islands = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 1) {
					islands++;
					infect(board, i, j);
				}
			}
		}
		return islands;
	}

	public static void infect(int[][] board, int i, int j) {
		if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != 1) {
			return;
		}
		board[i][j] = 2;
		infect(board, i - 1, j);
		infect(board, i + 1, j);
		infect(board, i, j - 1);
		infect(board, i, j + 1);
	}

	public static int solve2(int[][] board) {
		int row = board.length;
		int col = board[0].length;
		Dot[][] dots = new Dot[row][col];
		List<Dot> dotList = new ArrayList<>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				dots[i][j] = new Dot();
				dotList.add(dots[i][j]);
			}
		}
		UnionSet<Dot> us = new UnionSet<>(dotList);
		for (int j = 1; j < col; j++) {
			if (board[0][j - 1] == 1 && board[0][j] == 1) {
				us.union(dots[0][j - 1], dots[0][j]);
			}
		}
		for (int i = 1; i < row; i++) {
			if (board[i - 1][0] == 1 && board[i][0] == 1) {
				us.union(dots[i - 1][0], dots[i][0]);
			}
		}
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				if (board[i][j] == 1) {
					if (board[i][j - 1] == 1) {
						us.union(dots[i][j - 1], dots[i][j]);
					}
					if (board[i - 1][j] == 1) {
						us.union(dots[i - 1][j], dots[i][j]);
					}
				}
			}
		}
		return us.sets();
	}

	public static class Dot {

	}

	public static int solve3(int[][] board) {
		int row = board.length;
		int col = board[0].length;
		UnionFind uf = new UnionFind(board);
		for (int j = 1; j < col; j++) {
			if (board[0][j - 1] == 1 && board[0][j] == 1) {
				uf.union(0, j - 1, 0, j);
			}
		}
		for (int i = 1; i < row; i++) {
			if (board[i - 1][0] == 1 && board[i][0] == 1) {
				uf.union(i - 1, 0, i, 0);
			}
		}
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				if (board[i][j] == 1) {
					if (board[i][j - 1] == 1) {
						uf.union(i, j - 1, i, j);
					}
					if (board[i - 1][j] == 1) {
						uf.union(i - 1, j, i, j);
					}
				}
			}
		}
		return uf.sets();
	}

	public static class UnionFind {
		private int[] parent;
		private int[] size;
		private int[] help;
		private int col;
		private int sets;

		public UnionFind(int[][] board) {
			col = board[0].length;
			sets = 0;
			int row = board.length;
			int len = row * col;
			parent = new int[len];
			size = new int[len];
			help = new int[len];
			for (int r = 0; r < row; r++) {
				for (int c = 0; c < col; c++) {
					if (board[r][c] == 1) {
						int i = index(r, c);
						parent[i] = i;
						size[i] = 1;
						sets++;
					}
				}
			}
		}

		private int index(int r, int c) {
			return r * col + c;
		}

		private int find(int i) {
			int hi = 0;
			while (i != parent[i]) {
				help[hi++] = i;
				i = parent[i];
			}
			for (hi--; hi >= 0; hi--) {
				parent[help[hi]] = i;
			}
			return i;
		}

		public void union(int r1, int c1, int r2, int c2) {
			int i1 = index(r1, c1);
			int i2 = index(r2, c2);
			int f1 = find(i1);
			int f2 = find(i2);
			if (f1 != f2) {
				if (size[f1] >= size[f2]) {
					size[f1] += size[f2];
					parent[f2] = f1;
				} else {
					size[f2] += size[f1];
					parent[f1] = f2;
				}
				sets--;
			}
		}

		public int sets() {
			return sets;
		}

	}

	public static int[][] generateRandomMatrix(int row, int col) {
		int[][] board = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				board[i][j] = Math.random() < 0.5 ? 1 : 0;
			}
		}
		return board;
	}

	public static int[][] copy(int[][] board) {
		int row = board.length;
		int col = board[0].length;
		int[][] ans = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				ans[i][j] = board[i][j];
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int row = 1000;
		int col = 1000;
		int[][] board1 = generateRandomMatrix(row, col);
		int[][] board2 = copy(board1);
		int[][] board3 = copy(board1);

		long start = 0;
		long end = 0;
		start = System.currentTimeMillis();
		System.out.println(solve1(board1));
		end = System.currentTimeMillis();
		System.out.println((end - start) + " ms");
		System.out.println("===================");

		start = System.currentTimeMillis();
		System.out.println(solve2(board2));
		end = System.currentTimeMillis();
		System.out.println((end - start) + " ms");
		System.out.println("===================");

		start = System.currentTimeMillis();
		System.out.println(solve3(board3));
		end = System.currentTimeMillis();
		System.out.println((end - start) + " ms");
		System.out.println("===================");

		row = 10000;
		col = 10000;
		board1 = generateRandomMatrix(row, col);
		board3 = copy(board1);

		start = System.currentTimeMillis();
		System.out.println(solve1(board1));
		end = System.currentTimeMillis();
		System.out.println((end - start) + " ms");
		System.out.println("===================");

		start = System.currentTimeMillis();
		System.out.println(solve3(board3));
		end = System.currentTimeMillis();
		System.out.println((end - start) + " ms");
		System.out.println("===================");

	}

}
