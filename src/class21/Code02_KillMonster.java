package class21;

public class Code02_KillMonster {

	public static double right1(int N, int M, int K) {
		if (N < 1 || M < 1 || K < 1) {
			return 0;
		}
		long all = (long) Math.pow(M + 1, K);
		long kill = process1(N, M, K);
		return (double) ((double) kill / (double) all);
	}

	public static long process1(int N, int M, int K) {
		if (K == 0) {
			return N <= 0 ? 1 : 0;
		}
		long ways = 0;
		for (int i = 0; i <= M; i++) {
			ways += process1(N - i, M, K - 1);
		}
		return ways;
	}

	public static double right2(int N, int M, int K) {
		if (N < 1 || M < 1 || K < 1) {
			return 0;
		}
		long all = (long) Math.pow(M + 1, K);
		long kill = process2(N, M, K);
		return (double) ((double) kill / (double) all);
	}

	public static long process2(int N, int M, int K) {
		if (K == 0) {
			return N <= 0 ? 1 : 0;
		}
		if (N <= 0) {
			return (long) (Math.pow(M + 1, K));
		}
		return process2(N, M, K - 1) + process2(N - 1, M, K) - process2(N - M - 1, M, K - 1);
	}

	// M = 5
	// 以下为斜率优化改进枚举行为的动态规划
	// dp[8][5] = dp[8..3][4]
	// dp[9][5] = dp[9..4][4] = dp[9][4] + dp[8][5] - dp[3][4]
	// 可以推出
	// dp[i][j] = dp[i][j-1] + dp[i-1][j] - dp[i - M - 1][j-1]
	public static double dp(int N, int M, int K) {
		if (N < 1 || M < 1 || K < 1) {
			return 0;
		}
		long[][] dp = new long[N + 1][K + 1];
		// 特别注意：dp[0][j]既表示原含义，也表示M+1的j次方的值
		dp[0][0] = 1;
		for (int j = 1; j <= K; j++) {
			dp[0][j] = dp[0][j - 1] * (M + 1);
		}
		for (int j = 1; j <= K; j++) {
			for (int i = 1; i <= N; i++) {
				dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
				if (i - M - 1 < 0) {
					dp[i][j] -= dp[0][j - 1];
				} else {
					dp[i][j] -= dp[i - M - 1][j - 1];
				}
			}
		}
		return (double) ((double) dp[N][K] / (double) dp[0][K]);
	}

	public static void main(String[] args) {
		int NMax = 10;
		int MMax = 10;
		int KMax = 10;
		int testTime = 200;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int N = (int) (Math.random() * NMax);
			int M = (int) (Math.random() * MMax);
			int K = (int) (Math.random() * KMax);
			double ans1 = right1(N, M, K);
			double ans2 = right2(N, M, K);
			double ans3 = dp(N, M, K);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("Oops!");
				break;
			}
		}
		System.out.println("测试结束");
	}

}
