package class22;

public class Code01_KillMonster {

	public static double right(int N, int M, int K) {
		if (N < 1 || M < 1 || K < 1) {
			return 0;
		}
		long all = (long) Math.pow(M + 1, K);
		long kill = process(K, M, N);
		return (double) ((double) kill / (double) all);
	}

	// 怪兽还剩hp点血
	// 每次的伤害在[0~M]范围上
	// 还有times次可以砍
	// 返回砍死的情况数！
	public static long process(int times, int M, int hp) {
		if (times == 0) {
			return hp <= 0 ? 1 : 0;
		}
		if (hp <= 0) {
			return (long) Math.pow(M + 1, times);
		}
		long ways = 0;
		for (int i = 0; i <= M; i++) {
			ways += process(times - 1, M, hp - i);
		}
		return ways;
	}

	public static double dp1(int N, int M, int K) {
		if (N < 1 || M < 1 || K < 1) {
			return 0;
		}
		long all = (long) Math.pow(M + 1, K);
		long[][] dp = new long[K + 1][N + 1];
		dp[0][0] = 1;
		for (int times = 1; times <= K; times++) {
			dp[times][0] = (long) Math.pow(M + 1, times);
			for (int hp = 1; hp <= N; hp++) {
				long ways = 0;
				for (int i = 0; i <= M; i++) {
					if (hp - i >= 0) {
						ways += dp[times - 1][hp - i];
					} else {
						ways += (long) Math.pow(M + 1, times - 1);
					}
				}
				dp[times][hp] = ways;
			}
		}
		long kill = dp[K][N];
		return (double) ((double) kill / (double) all);
	}

	public static double dp2(int N, int M, int K) {
		if (N < 1 || M < 1 || K < 1) {
			return 0;
		}
		long all = (long) Math.pow(M + 1, K);
		long[][] dp = new long[K + 1][N + 1];
		dp[0][0] = 1;
		for (int times = 1; times <= K; times++) {
			dp[times][0] = (long) Math.pow(M + 1, times);
			for (int hp = 1; hp <= N; hp++) {
				dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];
				if (hp - 1 - M >= 0) {
					dp[times][hp] -= dp[times - 1][hp - 1 - M];
				} else {
					dp[times][hp] -= Math.pow(M + 1, times - 1);
				}
			}
		}
		long kill = dp[K][N];
		return (double) ((double) kill / (double) all);
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
			double ans1 = right(N, M, K);
			double ans2 = dp1(N, M, K);
			double ans3 = dp2(N, M, K);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("Oops!");
				break;
			}
		}
		System.out.println("测试结束");
	}

}
