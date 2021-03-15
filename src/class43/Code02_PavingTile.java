package class43;

public class Code02_PavingTile {

	/*
	 * 2*M铺地的问题非常简单，这个是解决N*M铺地的问题
	 */

	public static int ways1(int N, int M) {
		if (N < 1 || M < 1 || ((N * M) & 1) != 0) {
			return 0;
		}
		if (N == 1 || M == 1) {
			return 1;
		}
		int[] pre = new int[M]; // pre代表-1行的状况
		for (int i = 0; i < pre.length; i++) {
			pre[i] = 1;
		}
		return process(pre, 0, N);
	}

	// pre 表示level-1行的状态
	// level表示，正在level行做决定
	// N 表示一共有多少行  固定的
	// level-2行及其之上所有行，都摆满砖了
	// level做决定，让所有区域都满，方法数返回s
	public static int process(int[] pre, int level, int N) {
		if (level == N) { // base case
			for (int i = 0; i < pre.length; i++) {
				if (pre[i] == 0) {
					return 0;
				}
			}
			return 1;
		}
		
		// 没到终止行，可以选择在当前的level行摆瓷砖
		int[] op = getOp(pre);
		return dfs(op, 0, level, N);
	}

	// op[i] == 0 可以考虑摆砖
	// op[i] == 1 只能竖着向上
	public static int dfs(int[] op, int col, int level, int N) {
		 // 在列上自由发挥，玩深度优先遍历，当col来到终止列，i行的决定做完了
		// 轮到i+1行，做决定
		if (col == op.length) {
			return process(op, level + 1, N);
		}
		int ans = 0;
		// col位置不横摆
		ans += dfs(op, col + 1, level, N); // col位置上不摆横转
		//  col位置横摆, 向右
		if (col + 1 < op.length && op[col] == 0 && op[col + 1] == 0) {
			op[col] = 1;
			op[col + 1] = 1;
			ans += dfs(op, col + 2, level, N);
			op[col] = 0;
			op[col + 1] = 0;
		}
		return ans;
	}

	public static int[] getOp(int[] pre) {
		int[] cur = new int[pre.length];
		for (int i = 0; i < pre.length; i++) {
			cur[i] = pre[i] ^ 1;
		}
		return cur;
	}

	public static int ways2(int N, int M) {
		if (N < 1 || M < 1 || ((N * M) & 1) != 0) {
			return 0;
		}
		if (N == 1 || M == 1) {
			return 1;
		}
		int big = N > M ? N : M;
		int small = big == N ? M : N;
		// big * small
		int sn = 1 << small;
		int limit = sn - 1; // 全满状态
		int[] dp = new int[sn];
		dp[limit] = 1; // -1行全是满的
		// dp     -1 行的状况
		// dp[0000] 0
		// dp[0001] 0
		// dp[1111] 1
		int[] cur = new int[sn]; // 当前行, 要算出所有状态下的解
		for (int level = 0; level < big; level++) {
			for (int status = 0; status < sn; status++) {
				if (dp[status] != 0) { // 状态出现了
					// 0...1100
					// 1...0011
					// 0...1111
					// 0...0011
					int op = (~status) & limit;
					dfs(dp[status], op, 0, small - 1, cur);
				}
			}
			for (int i = 0; i < sn; i++) {
				dp[i] = 0;
			}
			int[] tmp = dp;
			dp = cur;
			cur = tmp;
		}
		return dp[limit];
	}

	public static void dfs(int way, int op, int index, int end, int[] cur) {
		if (index == end) {
			cur[op] += way;
		} else {
			dfs(way, op, index + 1, end, cur);
			if (((3 << index) & op) == 0) { // 11 << index  可以放砖
				dfs(way, op | (3 << index), index + 1, end, cur);
			}
		}
	}

	public static void main(String[] args) {
		int N = 8;
		int M = 8;
		System.out.println(ways1(N, M));
		System.out.println(ways2(N, M));
	}

}
