package class45;

public class Code02_RemoveBoxes {

	public int removeBoxes(int[] boxes) {
		int N = boxes.length;
		int[][][] dp = new int[N][N][N];
		return process(boxes, 0, N - 1, 0, dp);
	}

	// boxes[L....R]，前面还跟着K个boxes[L]
	// 前面的包袱和L...R所有的数都消掉，最好得分是什么
	public static int process(int[] boxes, int L, int R, int K, int[][][] dp) {
		if (L > R) {
			return 0;
		}
		if (dp[L][R][K] != 0) {
			return dp[L][R][K];
		}
		if (L == R) {
			dp[L][R][K] = (K + 1) * (K + 1);
			return dp[L][R][K];
		}
		while (L < R && boxes[L] == boxes[L + 1]) {
			L++;
			K++;
		}
		int ans = (K + 1) * (K + 1) + process(boxes, L + 1, R, 0, dp);
		for (int m = L + 1; m <= R; m++) {
			if (boxes[L] == boxes[m]) {
				ans = Math.max(ans, process(boxes, L + 1, m - 1, 0, dp) + process(boxes, m, R, K + 1, dp));
			}
		}
		dp[L][R][K] = ans;
		return ans;
	}

}
