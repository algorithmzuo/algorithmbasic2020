package class19;

// 这个问题leetcode上可以直接测
// 链接：https://leetcode.com/problems/longest-common-subsequence/
public class Code04_LongestCommonSubsequence {

	public static int longestCommonSubsequence1(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
			return 0;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		// 尝试
		return process1(str1, str2, str1.length - 1, str2.length - 1);
	}

	public static int process1(char[] str1, char[] str2, int i, int j) {
		if (i == 0 && j == 0) {
			return str1[i] == str2[j] ? 1 : 0;
		} else if (i == 0) {
			if (str1[i] == str2[j]) {
				return 1;
			} else {
				return process1(str1, str2, i, j - 1);
			}
		} else if (j == 0) {
			if (str1[i] == str2[j]) {
				return 1;
			} else {
				return process1(str1, str2, i - 1, j);
			}
		} else { // i != 0 && j != 0
			int p1 = process1(str1, str2, i - 1, j);
			int p2 = process1(str1, str2, i, j - 1);
			int p3 = str1[i] == str2[j] ? (1 + process1(str1, str2, i - 1, j - 1)) : 0;
			return Math.max(p1, Math.max(p2, p3));
		}
	}

	public static int longestCommonSubsequence2(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
			return 0;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int N = str1.length;
		int M = str2.length;
		int[][] dp = new int[N][M];
		dp[0][0] = str1[0] == str2[0] ? 1 : 0;
		for (int j = 1; j < M; j++) {
			dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
		}
		for (int i = 1; i < N; i++) {
			dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
		}
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				int p1 = dp[i - 1][j];
				int p2 = dp[i][j - 1];
				int p3 = str1[i] == str2[j] ? (1 + dp[i - 1][j - 1]) : 0;
				dp[i][j] = Math.max(p1, Math.max(p2, p3));
			}
		}
		return dp[N - 1][M - 1];
	}

}
