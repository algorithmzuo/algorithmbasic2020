package class46;

// 本题测试链接 : https://leetcode.com/problems/burst-balloons/
public class Code01_BurstBalloons {

	public static int maxCoins1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		if (arr.length == 1) {
			return arr[0];
		}
		int N = arr.length;
		int[] help = new int[N + 2];
		help[0] = 1;
		help[N + 1] = 1;
		for (int i = 0; i < N; i++) {
			help[i + 1] = arr[i];
		}
		return process(help, 1, N);
	}

	// 打爆arr[L..R]范围上的所有气球，返回最大的分数
	// 假设arr[L-1]和arr[R+1]一定没有被打爆
	public static int process(int[] arr, int L, int R) {
		if (L == R) {// 如果arr[L..R]范围上只有一个气球，直接打爆即可
			return arr[L - 1] * arr[L] * arr[R + 1];
		}
		// 最后打爆arr[L]的方案，和最后打爆arr[R]的方案，先比较一下
		int max = Math.max(arr[L - 1] * arr[L] * arr[R + 1] + process(arr, L + 1, R),
				arr[L - 1] * arr[R] * arr[R + 1] + process(arr, L, R - 1));
		// 尝试中间位置的气球最后被打爆的每一种方案
		for (int i = L + 1; i < R; i++) {
			max = Math.max(max, arr[L - 1] * arr[i] * arr[R + 1] + process(arr, L, i - 1) + process(arr, i + 1, R));
		}
		return max;
	}

	public static int maxCoins2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		if (arr.length == 1) {
			return arr[0];
		}
		int N = arr.length;
		int[] help = new int[N + 2];
		help[0] = 1;
		help[N + 1] = 1;
		for (int i = 0; i < N; i++) {
			help[i + 1] = arr[i];
		}
		int[][] dp = new int[N + 2][N + 2];
		for (int i = 1; i <= N; i++) {
			dp[i][i] = help[i - 1] * help[i] * help[i + 1];
		}
		for (int L = N; L >= 1; L--) {
			for (int R = L + 1; R <= N; R++) {
				int ans = help[L - 1] * help[L] * help[R + 1] + dp[L + 1][R];
				ans = Math.max(ans, help[L - 1] * help[R] * help[R + 1] + dp[L][R - 1]);
				for (int i = L + 1; i < R; i++) {
					ans = Math.max(ans, help[L - 1] * help[i] * help[R + 1] + dp[L][i - 1] + dp[i + 1][R]);
				}
				dp[L][R] = ans;
			}
		}
		return dp[1][N];
	}

}
