package leo.class19;

/**
 * @author Leo
 * @ClassName LongestCommonSubsequence
 * @DATE 2021/1/8 1:57 下午
 * @Description
 * 样本对应模型
 * 样本对应模型,只讨论当前的结尾如何组织可能性
 *
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 * 若这两个字符串没有公共子序列，则返回 0。
 *
 * 链接：https://leetcode-cn.com/problems/longest-common-subsequence
 */
public class LongestCommonSubsequence {

    static class Recursion {
        public static int longestCommonSubsequence(String s1, String s2) {
            if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
                return 0;
            }
            char[] c1 = s1.toCharArray();
            char[] c2 = s2.toCharArray();
            //尝试
            return process(c1, c2, c1.length - 1, c2.length - 1);
        }

        /**
         * 功能描述 : c1[0...i]与c2[0...j] 的最长公共子序列多少
         * @author Leo
         * @date 2021/1/8 3:14 下午
         * @param c1
         * @param c2
         * @param i c1下标
         * @param j c2下标
         * @return int
         */
        public static int process(char[] c1, char[] c2, int i, int j) {
            if (i == 0 && j == 0) {
                return c1[0] == c2[0] ? 1 : 0;
            } else if (i == 0) {
                if (c1[i] == c2[j]) {
                    return 1;
                }else{
                    return process(c1, c2, i, j - 1);
                }
            } else if (j == 0) {
                if (c1[i] == c2[j]) {
                    return 1;
                }else {
                    return process(c1, c2, i - 1, j);
                }
            }else {
                //i!=0&&j!=0
                //可能考虑j
                int p1 = process(c1, c2, i - 1, j);
                //可能考虑i
                int p2 = process(c1, c2, i, j - 1);
                //如果两边相等,不考虑i和j
                int p3 = c1[i] == c2[j] ? 1 + process(c1, c2, i - 1, j - 1) : 0;
                return Math.max(p1, Math.max(p2, p3));
            }
        }
    }

    static class Dp {
        public static int longestCommonSubsequence(String s1, String s2) {
            if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
                return 0;
            }
            char[] c1 = s1.toCharArray();
            char[] c2 = s2.toCharArray();
            int n = c1.length;
            int m = c2.length;
            int[][] dp = new int[n][m];
            dp[0][0] = c1[0] == c2[0] ? 1 : 0;
            for (int j = 1; j < c2.length; j++) {
                dp[0][j] = c1[0] == c2[j] ? 1 : dp[0][j - 1];
            }
            for (int i = 1; i < c1.length; i++) {
                dp[i][0] = c1[i] == c2[0] ? 1 : dp[i - 1][0];
            }
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    int p1 = dp[i - 1][j];
                    int p2 = dp[i][j - 1];
                    int p3 = c1[i] == c2[j] ? 1 + dp[i - 1][j - 1] : 0;
                    dp[i][j] = Math.max(p1, Math.max(p2, p3));
                }
            }
            return dp[n - 1][m - 1];
        }
    }
}
