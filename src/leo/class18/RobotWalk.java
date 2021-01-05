package leo.class18;

import java.awt.event.KeyEvent;

/**
 * @author Leo
 * @ClassName RobotWalk
 * @DATE 2021/1/5 4:27 下午
 * @Description
 */
public class RobotWalk {

    static class Recursion {
        /**
         * 功能描述 :
         *      一个机器人,从start出发,
         *      走到aim,在走k步的情况下,
         *      一共有多少种走法?
         * @author Leo
         * @date 2021/1/5 4:37 下午
         * @param N 一共多少路
         * @param start 起始点
         * @param aim   目标点
         * @param k 可以走几步
         * @return int
         */
        public static int walk(int N, int start, int aim, int k) {
            if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || k < 1) {
                return -1;
            }
            return process(start, k, aim, N);
        }

        /**
         * 功能描述 :
         * 从cur出发,走了rest步后,最终到aim的方法数是多少?
         * @param cur  当前位置
         * @param rest 还有多少步
         * @param aim  目标
         * @param N    一共有多少位置
         * @return int
         * @author Leo
         * @date 2021/1/5 4:49 下午
         */
        public static int process(int cur, int rest, int aim, int N) {

            if (rest == 0) {
                return cur == aim ? 1 : 0;
            }
            if (cur == 1) {
                return process(2, rest - 1, aim, N);
            }
            if (cur == N) {
                return process(N - 1, rest - 1, aim, N);
            }
            return process(cur + 1, rest - 1, aim, N) + process(cur - 1, rest - 1, aim, N);
        }


    }
    /**
     * 从顶向下的动态规划(记忆化搜索)
     */
    static class RecursionDp {
        public static int walk(int N, int start, int aim, int k) {
            if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || k < 1) {
                return -1;
            }
            int[][] dp = new int[N + 1][k + 1];
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[i].length; j++) {
                    dp[i][j] = -1;
                }
            }
            return process(start, k, aim, N, dp);
        }

        private static int process(int cur, int rest, int aim, int n, int[][] dp) {
            if (dp[cur][rest] != -1) {
                return dp[cur][rest];
            }
            int ans = 0;
            if (rest == 0) {
                ans = cur == aim ? 1 : 0;
            } else if (cur == 1) {
                ans = process(2, rest - 1, aim, n, dp);
            } else if (cur == n) {
                ans = process(n - 1, rest - 1, aim, n, dp);
            }else {
                ans = process(cur - 1, rest - 1, aim, n, dp) + process(cur + 1, rest - 1, aim, n, dp);
            }
            dp[cur][rest] = ans;
            return ans;
        }

    }
    /**
     * 状态转移 cur为行,rest为列
     * 动态规划是结果,不是原因
     */
    static class DP{
        public static int walk(int N, int start, int aim, int k) {
            if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || k < 1) {
                return -1;
            }
            int[][] dp = new int[N + 1][k + 1];
            //第0列的值
            dp[aim][0] = 1;
            for (int rest = 1; rest <= k; rest++) {
                dp[1][rest] = dp[2][rest - 1];
                for (int cur = 2; cur < N; cur++) {
                    dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
                }
                dp[N][rest] = dp[N - 1][rest - 1];
            }
            return dp[start][k];
        }
    }


    public static class walkMain {
        public static void main(String[] args){
            int N = 5;
            int start = 2;
            int aim = 4;
            int k = 6;
            System.out.println(Recursion.walk(N, start, aim, k));
            System.out.println(RecursionDp.walk(N, start, aim, k));
            System.out.println(DP.walk(N, start, aim, k));

        }

    }
}
