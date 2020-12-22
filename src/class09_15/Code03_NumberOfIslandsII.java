package class09_15;

import java.util.ArrayList;
import java.util.List;

// 本题为leetcode原题
// 测试链接：https://leetcode.com/problems/number-of-islands-ii/
// 所有方法都可以直接通过
public class Code03_NumberOfIslandsII {

	public static List<Integer> numIslands2(int m, int n, int[][] positions) {
		UnionFind uf = new UnionFind(m, n);
		List<Integer> ans = new ArrayList<>();
		for (int[] position : positions) {
			ans.add(uf.connect(position[0], position[1]));
		}
		return ans;
	}

	public static class UnionFind {
		private int[] parent;
		private int[] size;
		private int[] help;
		private final int row;
		private final int col;
		private int sets;

		public UnionFind(int m, int n) {
			row = m;
			col = n;
			sets = 0;
			int len = row * col;
			parent = new int[len];
			size = new int[len];
			help = new int[len];
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

		private void union(int r1, int c1, int r2, int c2) {
			if (r1 < 0 || r1 == row || r2 < 0 || r2 == row || c1 < 0 || c1 == col || c2 < 0 || c2 == col) {
				return;
			}
			int i1 = index(r1, c1);
			int i2 = index(r2, c2);
			if (size[i1] == 0 || size[i2] == 0) {
				return;
			}
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

		public int connect(int i, int j) {
			int index = index(i, j);
			if (size[index] == 0) {
				parent[index] = index;
				size[index] = 1;
				sets++;
				union(i - 1, j, i, j);
				union(i + 1, j, i, j);
				union(i, j - 1, i, j);
				union(i, j + 1, i, j);
			}
			return sets;
		}

	}

}
