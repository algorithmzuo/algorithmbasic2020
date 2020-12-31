package class18;

public class Code01_RobotWalk {

	public static int ways1(int N, int M, int K, int P) {
		if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
			return 0;
		}
		return walk(N, M, K, P);
	}

	public static int walk(int N, int cur, int rest, int P) {
		if (rest == 0) {
			return cur == P ? 1 : 0;
		}
		if (cur == 1) {
			return walk(N, 2, rest - 1, P);
		}
		if (cur == N) {
			return walk(N, N - 1, rest - 1, P);
		}
		return walk(N, cur + 1, rest - 1, P) + walk(N, cur - 1, rest - 1, P);
	}

	public static int waysCache(int N, int M, int K, int P) {
		if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
			return 0;
		}
		int[][] dp = new int[N + 1][K + 1];
		for (int row = 0; row <= N; row++) {
			for (int col = 0; col <= K; col++) {
				dp[row][col] = -1;
			}
		}
		return walkCache(N, M, K, P, dp);
	}

	public static int walkCache(int N, int cur, int rest, int P, int[][] dp) {
		if (dp[cur][rest] != -1) {
			return dp[cur][rest];
		}
		if (rest == 0) {
			dp[cur][rest] = cur == P ? 1 : 0;
			return dp[cur][rest];
		}
		if (cur == 1) {
			dp[cur][rest] = walkCache(N, 2, rest - 1, P, dp);
			return dp[cur][rest];
		}
		if (cur == N) {
			dp[cur][rest] = walkCache(N, N - 1, rest - 1, P, dp);
			return dp[cur][rest];
		}
		dp[cur][rest] = walkCache(N, cur + 1, rest - 1, P, dp) + walkCache(N, cur - 1, rest - 1, P, dp);
		return dp[cur][rest];
	}

	public static int ways2(int N, int M, int K, int P) {
		if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
			return 0;
		}
		int[][] dp = new int[K + 1][N + 1];
		dp[0][P] = 1;
		for (int i = 1; i <= K; i++) {
			for (int j = 1; j <= N; j++) {
				if (j == 1) {
					dp[i][j] = dp[i - 1][2];
				} else if (j == N) {
					dp[i][j] = dp[i - 1][N - 1];
				} else {
					dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
				}
			}
		}
		return dp[K][M];
	}

	public static int ways3(int N, int M, int K, int P) {
		if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
			return 0;
		}
		int[] dp = new int[N + 1];
		dp[P] = 1;
		for (int i = 1; i <= K; i++) {
			int leftUp = dp[1];
			for (int j = 1; j <= N; j++) {
				int tmp = dp[j];
				if (j == 1) {
					dp[j] = dp[j + 1];
				} else if (j == N) {
					dp[j] = leftUp;
				} else {
					dp[j] = leftUp + dp[j + 1];
				}
				leftUp = tmp;
			}
		}
		return dp[M];
	}

	public static int ways4(int N, int M, int K, int P) {
		if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
			return 0;
		}
		return process(N, 0, P, M, K);
	}

	public static int process(int N, int i, int j, int M, int K) {
		if (i == K) {
			return j == M ? 1 : 0;
		}
		if (j == 1) {
			return process(N, i + 1, j + 1, M, K);
		}
		if (j == N) {
			return process(N, i + 1, j - 1, M, K);
		}
		return process(N, i + 1, j + 1, M, K) + process(N, i + 1, j - 1, M, K);
	}

	public static int ways5(int N, int M, int K, int P) {
		if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
			return 0;
		}
		int[][] dp = new int[K + 1][N + 1];
		dp[K][M] = 1;
		for (int i = K - 1; i >= 0; i--) {
			for (int j = 1; j <= N; j++) {
				if (j == 1) {
					dp[i][j] = dp[i + 1][j + 1];
				} else if (j == N) {
					dp[i][j] = dp[i + 1][j - 1];
				} else {
					dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j - 1];
				}
			}
		}
		return dp[0][P];
	}

	public static void main(String[] args) {
		System.out.println(ways1(7, 4, 9, 5));
		System.out.println(ways2(7, 4, 9, 5));
		System.out.println(ways3(7, 4, 9, 5));
		System.out.println(ways4(7, 4, 9, 5));
		System.out.println(ways5(7, 4, 9, 5));
	}

}
