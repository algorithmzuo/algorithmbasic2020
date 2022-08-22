package class20;

/**
 * 求字串的最长的回文字串有多长
 * 一个字符串反转下，在求两个字串中的公共子序列的长度，该长度就是该字符串的最长回文字段
 */
public class Code01_PalindromeSubsequence_Study {
    public static int lpslI(String s){
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        return f(chars,0,chars.length-1);
    }

    /***
     * 一个字符产有四种情况需要考虑，主要是判断字符串的两边字符是否都为回文字符，然后取最大值
     * base case char[l]==char[r] ? 1 : 0;
     *           char[l]==char[r-1] ? 2 : 1;
     * @param chars
     * @param l
     * @param r
     * @return
     */
    private static int f(char[] chars, int l, int r) {
        if (l == r) {
            return 1;
        }
        if (l == r-1) {
            return chars[l] == chars[r-1] ? 2 : 1;
        }
        int p1 = f(chars,l+1,r);
        int p2 = f(chars,l,r-1);
        int p3 = f(chars,l+1,r-1);
        int p4 = chars[l] == chars[r] ? (2+f(chars,l+1,r-1)) : 0;
        return Math.max(p1,Math.max(p2,Math.max(p3,p4)));
    }

    /**
     * 傻缓存法
     * @param s
     * @return
     */
    public static int f2(char[] s) {
        int N  = s.length;
        int[][] result = new int[N][N];
        // 先定义最后一个元素
        result[N-1][N-1] = 1;
        // 根据上面递归发，我们知道basecase的情况因此，可以先设置basecase
        // 因为l > r没有意义，因此这个二维表的下半部分是没有意义的
        for (int i = 0; i < N-1; i++) {
            result[i][i] = 1;
            result[i][i+1] = s[i] == s[i+1] ? 2 : 1;
        }

        // 我们先定义好了最后一个字串，而剩下二维表的上半部分数据依赖的关系是一个元素会依赖左，左下，下三个元素
        for (int L = N -3; L >= 0 ; L--) {
            for (int R = L + 2; R < N; R++) {
                int p1 = result[L+1][R];
                int p2 = result[L][R-1];
                int p3 = result[L+1][R-1];
                int p4 = s[L] == s[R] ? (2+result[L+1][R-1]) : 0;
                result[L][R] = Math.max(p1,Math.max(p2,Math.max(p3,p4)));
            }
        }
        return result[0][N-1];
    }

    /**
     * 优化，在缓存中，一个点需要依赖它的左，左下，下和 2 + 左下四个值，那么其中，左 本身的依赖关系中又包含了左下，所以左一定是大于左下的
     * 所以这里我们可以忽略掉左下这个依赖关系
     */
    public static int f3(char[] s) {
        int N  = s.length;
        int[][] result = new int[N][N];
        // 先定义最后一个元素
        result[N-1][N-1] = 1;
        // 根据上面递归发，我们知道basecase的情况因此，可以先设置basecase
        // 因为l > r没有意义，因此这个二维表的下半部分是没有意义的
        for (int i = 0; i < N-1; i++) {
            result[i][i] = 1;
            result[i][i+1] = s[i] == s[i+1] ? 2 : 1;
        }

        // 我们先定义好了最后一个字串，而剩下二维表的上半部分数据依赖的关系是一个元素会依赖左，左下，下三个元素
        for (int L = N -3; L >= 0 ; L--) {
            for (int R = L + 2; R < N; R++) {
//                int p1 = result[L+1][R];
//                int p2 = result[L][R-1];
//                int p3 = result[L+1][R-1];
//                int p4 = s[L] == s[R] ? (2+result[L+1][R-1]) : 0;
//                result[L][R] = Math.max(p1,Math.max(p2,Math.max(p3,p4)));
                result[L][R] = Math.max(result[L+1][R],result[L][R-1]);
                if (s[L] == s[R] ) {
                    result[L][R] = Math.max(result[L][R],2+result[L+1][R-1]);
                }
            }
        }
        return result[0][N-1];
    }


}
