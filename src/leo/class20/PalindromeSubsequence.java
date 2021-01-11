package leo.class20;

import java.util.function.IntPredicate;

/**
 * @author Leo
 * @ClassName PalindromeSubsequence
 * @DATE 2021/1/11 10:12 上午
 * @Description 最长回文子序列
 * https://leetcode-cn.com/problems/longest-palindromic-subsequence/
 * 子序列可以不连续
 * 子串必须连续
 * 范围尝试模型特别在意开头和结尾.
 */
public class PalindromeSubsequence {

    /**
     * 一个字符串的逆序最长子序列,就是这个字符串的最长回文子序列
     */
    static class Reverse {

    }

    static class Recursion {
        public static int longestPalindromeSubseq(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            char[] chars = s.toCharArray();
            return f(chars, 0, chars.length - 1);
        }

        /**
         * 功能描述 : 求一个字符串从l到r范围中的最长回文子序列长度
         * @author Leo
         * @date 2021/1/11 10:21 上午
         * @param str
         * @param l
         * @param r
         * @return int
         */
        public static int f(char[] str, int l, int r) {
            if (l == r) {
                return 1;
            }
            if (l == r - 1) {
                return str[l] == str[r] ? 2 : 1;
            }
            int p1 = f(str, l + 1, r);
            int p2 = f(str, l, r - 1);
            int p3 = f(str, l + 1, r - 1);
            int p4 = str[l] == str[r] ? (2 + f(str, l + 1, r - 1)) : 0;
            return Math.max(Math.max(p1, p2), Math.max(p3, p4));
        }
    }

    static class Dp {
        public static int longestPalindromeSubseq(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            char[] str = s.toCharArray();
            int n = str.length;
            int[][] dp = new int[n][n];
            dp[n - 1][n - 1] = 1;
            for (int i = 0; i < n - 1; i++) {
                dp[i][i] = 1;
                dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
            }
            //从底网上填
            for (int l = n - 3; l >= 0; l--) {
                for (int r = l + 2; r < n; r++) {
                    int p1 = dp[l + 1][r];
                    int p2 = dp[l][r - 1];
                    int p3 = dp[l + 1][r - 1];
                    int p4 = str[l] == str[r] ? (2 + dp[l + 1][r - 1]) : 0;
                    dp[l][r] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
                }
            }
            return dp[0][n - 1];
        }

        public static int longestPalindromeSubseqMajorization(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            char[] str = s.toCharArray();
            int n = str.length;
            int[][] dp = new int[n][n];
            dp[n - 1][n - 1] = 1;
            for (int i = 0; i < n - 1; i++) {
                dp[i][i] = 1;
                dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
            }
            //从底网上填
            for (int l = n - 3; l >= 0; l--) {
                for (int r = l + 2; r < n; r++) {
                    dp[l][r] = Math.max(dp[l][r - 1], dp[l + 1][r]);
                    if (str[l] == str[r]) {
                        dp[l][r] = Math.max(dp[l][r], 2 + dp[l + 1][r - 1]);
                    }
                }
            }
            return dp[0][n - 1];
        }
    }


    public static void main(String[] args){
        int a = Dp.longestPalindromeSubseq("a");
        System.out.println(a);
    }
}
