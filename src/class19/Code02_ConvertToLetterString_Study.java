package class19;

public class Code02_ConvertToLetterString_Study {

    /**
     * str只含有数字字符0～9
     * 返回有多少种转化方案
     *
     * @param str
     * @return
     */
    private static int ways1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    private static int process(char[] chars, int index) {
        //base case
        // 当走到index == chars.length时，说明前面的排序都是正确的，此时要返回1
        if (chars.length == index) {
            return 1;
        }
        // 如果说，当有字符单独为0时，那么说明前面的排序是有问题的，因此返回0
        if (chars[index] == '0') {
            return 0;
        }
        // 这里进行单字符串转换
        int ways = process(chars, index + 1);
        // 多字符，最对一次性移动两位
        if (index + 1 < chars.length && ((chars[index] - '0') * 10 + chars[index + 1] - '0') < 27) {
            ways += process(chars, index + 2);
        }
        return ways;
    }

    /**
     * 从右往左动态规划
     * @param s
     * @return
     */
    private static int dp1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int N = chars.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >=0; i--) {
            if (chars[i] != '0') {
                // 这里进行单字符串转换
                int ways = dp[i + 1];
                // 多字符，最对一次性移动两位
                if (i + 1 < chars.length && ((chars[i] - '0') * 10 + chars[i + 1] - '0') < 27) {
                    ways += dp[i + 2];
                }
                dp[i] = ways;
            }
        }
        return dp[0];

    }


    /**
     * 一维表，之类只有一个可变参数index(从左往右填写)
     *
     * @param str
     * @return
     */
    private static int dp2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] strs = str.toCharArray();
        int N = strs.length;
        if (strs[0] == '0') {
            return 0;
        }
        int[] dp = new int[N];
        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            if (strs[i] == '0') {
                // 这里需要分情况判断，如果该位置是0字符，那么肯定不能单独存在，因此他的前一个字符一定存在，而且只能是'1'或者'2'
                // 同时这里也需要[0~i-2]也是可以拼成字符的，不然也不成立
                // 这里为什么只有在'0'字符的情况下需要校验，因为当数字不为
                if (strs[i - 1] == '0' || strs[i - 1] > '2' || (i - 2 > 0 && dp[i - 2] == 0)) {
                    return 0;
                } else {
                    dp[i] = i - 2 >= 0 ? dp[i - 2] : 1;
                }
            } else {
                dp[i] = dp[i - 1];
                if (strs[i - 1] != '0' && (strs[i - 1] - '0') * 10 + strs[i] - '0' < 27) {
                    dp[i] += i - 2 >= 0 ? dp[i - 2] : 1;
                }
            }
        }
        return dp[N - 1];
    }

    private static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        int N = 30;
        int testTime = 10000;
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            String s = randomString(len);
            int ans0 = ways1(s);
            int ans1 = dp2(s);
            int ans2 = dp1(s);
            if (ans0 != ans1 || ans1 != ans2) {
                System.out.println(s);
                System.out.println(ans0);
                System.out.println(ans1);
                System.out.println("oop");
                System.out.println("-----------");
            }
        }
        System.out.println("finish");
    }
}
