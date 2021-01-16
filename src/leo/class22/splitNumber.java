package leo.class22;

/**
 * @author Leo
 * @ClassName splitNumber
 * @DATE 2021/1/14 10:02 下午
 * @Description
 *
 * 给定一个正数1，裂开的方法有一种，(1)
 * 给定一个正数2，裂开的方法有两种，(1和1)、(2)
 * 给定一个正数3，裂开的方法有三种，(1、1、1)、(1、2)、(3)
 * 给定一个正数4，裂开的方法有五种，(1、1、1、1)、(1、1、2)、(1、3)、(2、2)、 (4)
 * 给定一个正数n，求裂开的方法数。 动态规划优化状态依赖的技巧
 */
public class splitNumber {
    static class Recursion {
        public static int number(int n) {
            if (n <= 0) {
                return 0;
            }
            return process(n, 1);
        }

        private static int process(int rest, int pre) {
            if (rest == 0) {
                return 1;
            }
            if (rest <= pre) {
                return rest - pre == 0 ? 1 : 0;
            }
            int ans = 0;
            for (int j = pre; j <= rest; j++) {
                ans += process(rest - j, j);
            }
            return ans;
        }

    }

    static class Recursion1{
        public static int number(int n) {
            if (n <= 0) {
                return 0;
            }
            return process(n, 1);
        }

        private static int process(int rest, int pre) {
            if (rest == 0) {
                return 1;
            }
            if (pre >= rest) {
                return rest - pre == 0 ? 1 : 0;
            }
            int ans = 0;
            for (int i = pre; i <= rest; i++) {
                ans += process(rest - i, i);
            }
            return ans;
        }
    }

    static class Dp1{
        public static int number(int n) {
            if (n <= 0) {
                return 0;
            }
            int[][] dp = new int[n + 1][n + 1];
            for (int i = 0; i <= n; i++) {
                dp[i][i] = 1;
                dp[i][0] = 1;
            }
            for (int pre = n-1; pre >=0; pre--) {
                for (int rest = pre+1; rest <= n; rest++) {
                    int ans = 0;
                    for (int i = pre; i <= rest; i++) {
                        ans += dp[i][rest - i];
                    }
                    dp[pre][rest] = ans;
                }
            }
            return dp[0][n];
        }
    }

    static class OptDp1{
        public static int number(int n) {
            if (n <= 0) {
                return 0;
            }
            int[][] dp = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                dp[i][i] = 1;
                dp[i][0] = 1;
            }
            for (int pre = n - 1; pre > 0; pre--) {
                for (int rest = pre + 1; rest <= n; rest++) {
                    //下面的格子
                    dp[pre][rest] = dp[pre+1][rest];
                    //左侧的格子
                    dp[pre][rest] += dp[pre][rest - pre];
                }
            }
            return dp[1][n];
        }
    }
    static class Dp{
        public static int number(int n) {
            if (n <= 0) {
                return 0;
            }
            int[][] dp = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                dp[i][i] = 1;
                dp[i][0] = 1;
            }
            for (int pre = n - 1; pre > 0; pre--) {
                for (int rest = pre + 1; rest <= n; rest++) {
                    int ans = 0;
                    for (int j = pre; j <= rest; j++) {
                        ans+= dp[j][rest - j];
                    }
                    dp[pre][rest] = ans;
                }
            }
            return dp[1][n];
        }
    }

    static class OptDp{
        public static int number(int n) {
            if (n <= 0) {
                return 0;
            }
            int[][] dp = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                dp[i][i] = 1;
                dp[i][0] = 1;
            }
            for (int pre = n - 1; pre > 0; pre--) {
                for (int rest = pre + 1; rest <= n; rest++) {
                    //下面的格子
                    dp[pre][rest] = dp[pre+1][rest];
                    //左侧的格子
                    dp[pre][rest] += dp[pre][rest - pre];

                }
            }
            return dp[1][n];
        }
    }
    public static void main(String[] args){
        System.out.println( Recursion1.number(14));
        System.out.println(Dp1.number(14));
        System.out.println(OptDp1.number(14));
    }

}
