package class42;

// leetcode测试链接：https://leetcode.com/problems/super-egg-drop
// 方法1和方法2会超时
// 方法3勉强通过
// 方法4打败100%
// 方法5打败100%，方法5是在方法4的基础上做了进一步的常数优化
public class Code02_ThrowChessPiecesProblem {

	public static int superEggDrop1(int kChess, int nLevel) {
		if (nLevel < 1 || kChess < 1) {
			return 0;
		}
		return Process1(nLevel, kChess);
	}

	// rest还剩多少层楼需要去验证
	// k还有多少颗棋子能够使用
	// 一定要验证出最高的不会碎的楼层！但是每次都是坏运气。
	// 返回至少需要扔几次？
	public static int Process1(int rest, int k) {
		if (rest == 0) {
			return 0;
		}
		if (k == 1) {
			return rest;
		}
		int min = Integer.MAX_VALUE;
		for (int i = 1; i != rest + 1; i++) { // 第一次扔的时候，仍在了i层
			min = Math.min(min, Math.max(Process1(i - 1, k - 1), Process1(rest - i, k)));
		}
		return min + 1;
	}

	public static int superEggDrop2(int kChess, int nLevel) {
		if (nLevel < 1 || kChess < 1) {
			return 0;
		}
		if (kChess == 1) {
			return nLevel;
		}
		int[][] dp = new int[nLevel + 1][kChess + 1];
		for (int i = 1; i != dp.length; i++) {
			dp[i][1] = i;
		}
		for (int i = 1; i != dp.length; i++) {
			for (int j = 2; j != dp[0].length; j++) {
				int min = Integer.MAX_VALUE;
				for (int k = 1; k != i + 1; k++) {
					min = Math.min(min, Math.max(dp[k - 1][j - 1], dp[i - k][j]));
				}
				dp[i][j] = min + 1;
			}
		}
		return dp[nLevel][kChess];
	}

	public static int superEggDrop3(int kChess, int nLevel) {
		if (nLevel < 1 || kChess < 1) {
			return 0;
		}
		if (kChess == 1) {
			return nLevel;
		}
		int[][] dp = new int[nLevel + 1][kChess + 1];
		for (int i = 1; i != dp.length; i++) {
			dp[i][1] = i;
		}
		int[][] best = new int[nLevel + 1][kChess + 1];
		for (int i = 1; i != dp[0].length; i++) {
			dp[1][i] = 1;
			best[1][i] = 1;
		}
		for (int i = 2; i < nLevel + 1; i++) {
			for (int j = kChess; j > 1; j--) {
				int ans = Integer.MAX_VALUE;
				int bestChoose = -1;
				int down = best[i - 1][j];
				int up = j == kChess ? i : best[i][j + 1];
				for (int first = down; first <= up; first++) {
					int cur = Math.max(dp[first - 1][j - 1], dp[i - first][j]);
					if (cur <= ans) {
						ans = cur;
						bestChoose = first;
					}
				}
				dp[i][j] = ans + 1;
				best[i][j] = bestChoose;
			}
		}
		return dp[nLevel][kChess];
	}

	public static int superEggDrop4(int kChess, int nLevel) {
		if (nLevel < 1 || kChess < 1) {
			return 0;
		}
		int[] dp = new int[kChess];
		int res = 0;
		while (true) {
			res++;
			int previous = 0;
			for (int i = 0; i < dp.length; i++) {
				int tmp = dp[i];
				dp[i] = dp[i] + previous + 1;
				previous = tmp;
				if (dp[i] >= nLevel) {
					return res;
				}
			}
		}
	}

	public static int superEggDrop5(int kChess, int nLevel) {
		if (nLevel < 1 || kChess < 1) {
			return 0;
		}
		int bsTimes = log2N(nLevel) + 1;
		if (kChess >= bsTimes) {
			return bsTimes;
		}
		int[] dp = new int[kChess];
		int res = 0;
		while (true) {
			res++;
			int previous = 0;
			for (int i = 0; i < dp.length; i++) {
				int tmp = dp[i];
				dp[i] = dp[i] + previous + 1;
				previous = tmp;
				if (dp[i] >= nLevel) {
					return res;
				}
			}
		}
	}

	public static int log2N(int n) {
		int res = -1;
		while (n != 0) {
			res++;
			n >>>= 1;
		}
		return res;
	}

	public static void main(String[] args) {
		int maxN = 500;
		int maxK = 30;
		int testTime = 1000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int N = (int) (Math.random() * maxN) + 1;
			int K = (int) (Math.random() * maxK) + 1;
			int ans2 = superEggDrop2(K, N);
			int ans3 = superEggDrop3(K, N);
			int ans4 = superEggDrop4(K, N);
			int ans5 = superEggDrop5(K, N);
			if (ans2 != ans3 || ans4 != ans5 || ans2 != ans4) {
				System.out.println("出错了!");
			}
		}
		System.out.println("测试结束");
	}

}
