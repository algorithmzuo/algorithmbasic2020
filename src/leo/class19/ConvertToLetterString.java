package leo.class19;

import java.time.OffsetDateTime;

/**
 * @author Leo
 * @ClassName ConvertToLetterString
 * @DATE 2021/1/7 5:19 下午
 * @Description
 * 从左往右的尝试模型1
 * 规定1和A对应、2和B对应、3和C对应...
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 */
public class ConvertToLetterString {

    static class Recursion {
        public static int number(String s) {
            if (s.length() == 0 || s == null) {
                return 0;
            }
            return process(0, s.toCharArray());
        }

        private static int process(int i, char[] str) {
            if (i == str.length) {
                return 1;
            }
            if (str[i] == '0') {
                return 0;
            }
            //当前位置单转
            int ans = process(i + 1, str);
            //入股不越界,当前位置与下一个位置的和<=26,也计入次数,下一个位置从i+2算
            if (i + 1 < str.length && (str[i] - '0') * 10 + (str[i + 1] - '0') <= 26) {
                ans += process(i + 2, str);
            }
            return ans;
        }
    }
    static class Dp {
        public static int number(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int n = s.length();
            int[] dp = new int[n + 1];
            dp[n] = 1;
            char[] str = s.toCharArray();
            for (int i = n - 1; i >= 0; i--) {
                if (str[i] != '0') {
                    int ans = dp[i + 1];
                    //入股不越界,当前位置与下一个位置的和<=26,也计入次数,下一个位置从i+2算
                    if (i + 1 < str.length && (str[i] - '0') * 10 + (str[i + 1] - '0') <= 26) {
                        ans += dp[i + 2];
                    }
                    dp[i] = ans;
                }
            }
            return dp[0];

        }
    }

    static class Recursion1 {
        public static int number(String s) {
            if (s == null || s.length() == 0) {

                return 0;
            }
            return process(0, s.toCharArray());
        }

        private static int process(int i, char[] str) {
            if (i == str.length) {
                return 1;
            }
            if (str[i] == '0') {
                return 0;
            }
            //不要当前字符
            int ans = process(i + 1, str);
            //要当前字符
            if (i + 1 < str.length && (str[i] - '0') * 10 + (str[i + 1] - '0') < 27) {
                ans += process(i + 2, str);
            }
            return ans;
        }
    }

    static class Dp1 {
        public static int number(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int n = s.length();
            int[] dp = new int[n + 1];
            dp[n] = 1;
            char[] str = s.toCharArray();
            for (int i = n - 1; i >= 0; i--) {
                if (str[i] != '0') {
                    int ans = dp[i + 1];
                    if (i + 1 < str.length && (str[i] - '0') * 10 + (str[i + 1] - '0') < 27) {
                        ans += dp[i + 2];
                    }
                    dp[i] = ans;
                }

            }
            return dp[0];
        }
    }

    public static void main(String[] args){
        int testTime = 10000;
        System.out.println("start");
        int maxStrLength = 100;
        for (int i = 0; i < testTime; i++) {
            int length = (int) (maxStrLength * Math.random());
            String s = createRandomString(length);
            int r = Recursion1.number(s);
            int dp = Dp1.number(s);
            if (r != dp) {
                System.out.println("r = " + r);
                System.out.println("dp = " + dp);
                break;
            }
        }
        System.out.println("end");


    }

    public static String createRandomString(int length) {
        if (length == 0) {
            return Math.random() < 0.5 ? null : "";
        }
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < length; i++) {
            str.append((int)(Math.random() * 10));
        }
        return str.toString();
    }
}
