package class09_15;

// 本题为leetcode原题
// 测试链接：https://leetcode.com/problems/friend-circles/
// 可以直接通过
public class Code01_FriendCircles {

	public static int findCircleNum(int[][] M) {
		int N = M.length;
		UnionFind unionFind = new UnionFind(N);
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (M[i][j] == 1) {
					unionFind.union(i, j);
				}
			}
		}
		return unionFind.sets();
	}

	public static class UnionFind {
		private int[] parent;
		private int[] size;
		private int[] help;
		private int sets;

		public UnionFind(int N) {
			parent = new int[N];
			size = new int[N];
			help = new int[N];
			sets = N;
			for (int i = 0; i < N; i++) {
				parent[i] = i;
				size[i] = 1;
			}
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

		public void union(int i, int j) {
			int f1 = find(i);
			int f2 = find(j);
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

}
