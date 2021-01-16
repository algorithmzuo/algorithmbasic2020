package leo.class22;

/**
 * @author Leo
 * @ClassName KillMonster
 * @DATE 2021/1/14 9:06 下午
 * @Description
 *
 * 给定3个参数，N，M，K
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次在[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 */
public class KillMonster {

    static class Recursion{
        public static double kill(int n, int m, int k) {
            if (n < 1 || m < 1 || k < 1) {
                return 0;
            }
            long ans = process(n, m, k);
            return (double) ans / Math.pow(m + 1, k);
        }

        private static long process(int hp, int m, int rest) {
            if (rest == 0) {
                return hp <= 0 ? 1 : 0;
            }
            if (hp <= 0) {
                return (long) Math.pow(m + 1, rest);
            }
            int ans = 0;
            for (int i = 0; i <= m; i++) {
                ans += process(hp - i, m, rest - 1);
            }
            return ans;

        }

    }

    static class Recursion1 {
        public static double kill(int n, int m, int k) {
            if (n < 1 || m < 1 || k < 1) {
                return 0;
            }
            long kill = process(n, m, k);
            return (double) kill / Math.pow(m + 1, k);
        }

        private static long process(int hp, int m, int rest) {
            if (rest <= 0) {
                return hp <= 0 ? 1 : 0;
            }
            if (hp <=0) {
                return (long) Math.pow(m + 1, rest);
            }
            long ans = 0;
            for (int i = 0; i <= m; i++) {
                ans += process(hp - i, m, rest - 1);
            }
            return ans;
        }
    }
    static class Dp1 {
        public static double kill(int n, int m, int k) {
            if (n < 1 || m < 1 || k < 1) {
                return 0;
            }
            long[][] dp = new long[n + 1][k + 1];
            dp[0][0] = 1;
            for (int hp = 1; hp <= n; hp++) {
                for (int rest = 1; rest <= k; rest++) {
                    dp[0][rest] = (long) Math.pow(m + 1, rest);
                    long ans = 0;
                    for (int i = 0; i <= m; i++) {
                        if (hp - i > 0) {
                            ans += dp[hp - i][rest - 1];
                        }else{
                            ans += Math.pow(m + 1, rest - 1);
                        }
                    }
                    dp[hp][rest] = ans;
                }
            }
            return (double)(dp[n][k] / Math.pow(m+1, k));
        }
    }
    static class Dp {
        public static double kill(int n, int m, int k) {
            if (n < 1 || m < 1 || k < 1) {
                return 0;
            }
            long[][] dp = new long[n + 1][k + 1];
            dp[0][0] = 1;
            for (int hp = 1; hp <= n; hp++) {
                for (int rest = 1; rest <= k; rest++) {
                    dp[0][rest] = (long) Math.pow(m + 1, rest);
                    long ans = 0;
                    for (int i = 0; i <= m; i++) {
                        if (hp - i <= 0) {
                            ans += (long) Math.pow(m + 1, rest - 1);
                        } else {
                            ans += dp[hp - i][rest - 1];
                        }
                    }
                    dp[hp][rest] = ans;
                }
            }
            return dp[n][k] / Math.pow(m + 1, k);
        }
    }
    static class OptDp1{
        public static double kill(int n, int m, int k) {
            if (n < 1 || m < 1 || k < 1) {
                return 0;
            }
            long[][] dp = new long[n + 1][k + 1];
            dp[0][0] = 1;
            for (int hp = 1; hp <= n; hp++) {
                for (int rest = 1; rest <= k; rest++) {
                    dp[0][rest] = (long) Math.pow(m + 1, rest);
                    dp[hp][rest] = dp[hp - 1][rest] + dp[hp][rest - 1];
                    if (hp - m - 1 > 0) {
                        dp[hp][rest] -= dp[hp - m - 1][rest - 1];
                    }else{
                        dp[hp][rest] -= Math.pow(m + 1, rest - 1);
                    }
                }
            }
            return dp[n][k] / Math.pow(m + 1, k);
        }
    }

    static class OptDp{
        public static double kill(int n, int m, int k) {
            if (n < 1 || m < 1 || k < 1) {
                return 0;
            }
            long[][] dp = new long[n + 1][k + 1];
            dp[0][0] = 1;
            for (int hp = 1; hp <= n; hp++) {
                for (int rest = 1; rest <= k; rest++) {
                    dp[0][rest] = (long) Math.pow(m + 1, rest);
                    dp[hp][rest] = dp[hp][rest - 1] + dp[hp-1][rest];
                    if (hp - m - 1 >=0) {
                        dp[hp][rest] -= dp[hp - m - 1][rest - 1];
                    }else{
                        dp[hp][rest] -= (long) Math.pow(m + 1, rest - 1);
                    }

                }
            }
            return dp[n][k] / Math.pow(m + 1, k);
        }
    }

    public static void main(String[] args){
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = Recursion1.kill(N, M, K);
            double ans2 = Dp1.kill(N, M, K);
            double ans3 = OptDp1.kill(N, M, K);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");

    }
}
