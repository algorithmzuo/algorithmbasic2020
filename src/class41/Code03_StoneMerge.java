package class41;

// 四边形不等式：合并石子问题
public class Code03_StoneMerge {

	public static int[] sum(int[] arr) {
		int N = arr.length;
		int[] s = new int[N + 1];
		s[0] = 0;
		for (int i = 0; i < N; i++) {
			s[i + 1] = s[i] + arr[i];
		}
		return s;
	}

	public static int w(int[] s, int l, int r) {
		return s[r + 1] - s[l];
	}

	public static int min1(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int N = arr.length;
		int[] s = sum(arr);
		return process1(0, N - 1, s);
	}

	public static int process1(int L, int R, int[] s) {
		if (L == R) {
			return 0;
		}
		int next = Integer.MAX_VALUE;
		for (int leftEnd = L; leftEnd < R; leftEnd++) {
			next = Math.min(next, process1(L, leftEnd, s) + process1(leftEnd + 1, R, s));
		}
		return next + w(s, L, R);
	}

	public static int min2(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int N = arr.length;
		int[] s = sum(arr);
		int[][] dp = new int[N][N];
		// dp[i][i] = 0
		for (int L = N - 2; L >= 0; L--) {
			for (int R = L + 1; R < N; R++) {
				int next = Integer.MAX_VALUE;
				// dp(L..leftEnd)  + dp[leftEnd+1...R]  + 累加和[L...R]
				for (int leftEnd = L; leftEnd < R; leftEnd++) {
					next = Math.min(next, dp[L][leftEnd] + dp[leftEnd + 1][R]);
				}
				dp[L][R] = next + w(s, L, R);
			}
		}
		return dp[0][N - 1];
	}

	public static int min3(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int N = arr.length;
		int[] s = sum(arr);
		int[][] dp = new int[N][N];
		int[][] best = new int[N][N];
		for (int i = 0; i < N - 1; i++) {
			best[i][i + 1] = i;
			dp[i][i + 1] = w(s, i, i + 1);
		}
		for (int L = N - 3; L >= 0; L--) {
			for (int R = L + 2; R < N; R++) {
				int next = Integer.MAX_VALUE;
				int choose = -1;
				for (int leftEnd = best[L][R - 1]; leftEnd <= best[L + 1][R]; leftEnd++) {
					int cur = dp[L][leftEnd] + dp[leftEnd + 1][R];
					if (cur <= next) {
						next = cur;
						choose = leftEnd;
					}
				}
				best[L][R] = choose;
				dp[L][R] = next + w(s, L, R);
			}
		}
		return dp[0][N - 1];
	}

	public static int[] randomArray(int len, int maxValue) {
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * maxValue);
		}
		return arr;
	}

	public static void main(String[] args) {
		int N = 15;
		int maxValue = 100;
		int testTime = 1000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * N);
			int[] arr = randomArray(len, maxValue);
			int ans1 = min1(arr);
			int ans2 = min2(arr);
			int ans3 = min3(arr);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("Oops!");
			}
		}
		System.out.println("测试结束");
	}

}
