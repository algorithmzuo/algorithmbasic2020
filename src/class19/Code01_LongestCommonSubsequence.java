package class19;

// 这个问题leetcode上可以直接测
// 链接：https://leetcode.com/problems/longest-common-subsequence/
public class Code01_LongestCommonSubsequence {

	public static int longestCommonSubsequence1(String text1, String text2) {
		if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
			return 0;
		}
		return process(text1.toCharArray(), text2.toCharArray(), text1.length() - 1, text2.length() - 1);
	}

	// str1[0..i]与str2[0..j]最长公共子序列多长
	public static int process(char[] str1, char[] str2, int i, int j) {
		if (i == 0 && j == 0) {
			return str1[0] == str2[0] ? 1 : 0;
		}
		if (i == 0) {
			return str1[0] == str2[j] ? 1 : process(str1, str2, 0, j - 1);
		}
		if (j == 0) {
			return str1[i] == str2[0] ? 1 : process(str1, str2, i - 1, 0);
		}
		int p1 = process(str1, str2, i - 1, j);
		int p2 = process(str1, str2, i, j - 1);
		int p3 = str1[i] == str2[j] ? (process(str1, str2, i - 1, j - 1) + 1) : 0;
		return Math.max(Math.max(p1, p2), p3);
	}

	public static int longestCommonSubsequence2(String text1, String text2) {
		if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
			return 0;
		}
		char[] str1 = text1.toCharArray();
		char[] str2 = text2.toCharArray();
		int N = str1.length;
		int M = str2.length;
		int[][] dp = new int[N][M];
		dp[0][0] = str1[0] == str2[0] ? 1 : 0;
		for (int i = 1; i < N; i++) {
			dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
		}
		for (int j = 1; j < M; j++) {
			dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
		}
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				if (str1[i] == str2[j]) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
				}
			}
		}
		return dp[N - 1][M - 1];
	}

}
