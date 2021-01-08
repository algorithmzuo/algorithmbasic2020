package leo.class19;

import java.io.BufferedReader;
import java.util.function.IntPredicate;

/**
 * @author Leo
 * @ClassName Knapsack
 * @DATE 2021/1/6 9:43 上午
 * @Description 背包问题
 * 范围模型
 */
public class Knapsack {


    static class Recursion {

        /**
         * 功能描述 : 在不超重的情况下,能过得到的最大价值
         * @author Leo
         * @date 2021/1/7 4:38 下午
         * @param w 货物的重量
         * @param v 货物的价值
         * @param bag 背包的容量
         * @return int
         */
        public static int maxValue(int[] w, int[] v, int bag) {
            if (w == null || v == null || w.length != v.length || w.length == 0) {
                return 0;
            }
            return process(w, v, 0, bag);
        }

        /**
         * 功能描述 : 返回最大价值
         * @author Leo
         * @date 2021/1/7 4:40 下午
         * @param w 货物的重量
         * @param v 货物的价值
         * @param i 当前考虑到了i号货物
         * @param bag 背包的容量
         * @return int
         */
        private static int process(int[] w, int[] v, int i, int bag) {
            if (bag < 0) {
                return -1;
            }
            if (i == w.length) {
                return 0;
            }
            //不要当前的货
            int p1 = process(w, v, i + 1, bag);
            //要当前的货

            int next = process(w, v, i + 1, bag - w[i]);
            int p2 = 0;
            //防止超出bag容量
            if (next != -1) {
                p2 = v[i] + next;
            }
            return Math.max(p1, p2);
        }
    }

    static class RecursionDP {
        public static int maxValue(int[] w, int[] v, int bag) {
            if (w == null || v == null || w.length != v.length || w.length == 0) {
                return 0;
            }
            int n = w.length;
            int[][] dp = new int[n + 1][bag + 1];
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= bag; j++) {
                    dp[i][j] = -1;
                }
            }
            int value = process(w, v, 0, bag, dp);
            return value;


        }

        private static int process(int[] w, int[] v,int i, int bag, int[][] dp) {

            if (bag < 0) {
                return -3;
            }
            if (i == w.length) {
                return 0;
            }
            if (dp[i][bag] != -1) {
                return dp[i][bag];
            }

            int ans = 0;

            int p1 = process(w, v, i + 1, bag, dp);
            int next = process(w, v, i + 1, bag - w[i], dp);
            int p2 = 0;
            if (next != -3) {
                p2 = v[i] + next;
            }
            ans = Math.max(p1, p2);
            dp[i][bag] = ans;
            return ans;
        }
    }

    static class Dp {
        public static int maxValue(int[] w, int[] v, int bag) {
            if (w == null || v == null || w.length != v.length || w.length == 0) {
                return 0;
            }
            int n = w.length;
            int[][] dp = new int[n + 1][bag + 1];
            for (int i = n - 1; i >= 0; i--) {
                for (int rest = 0; rest <= bag; rest++) {
                    int p1 = dp[i + 1][rest];
                    int p2 = rest - w[i] < 0 ? -1 : v[i] + dp[i + 1][rest - w[i]];
                    dp[i][rest] = Math.max(p1, p2);
                }
            }
            return dp[0][bag];
        }
    }

    static class Recursion1 {
        public static int maxValue(int[] w, int[] v, int bag) {
            if (w == null || v == null || w.length != v.length || w.length == 0) {
                return 0;
            }
            return process(w, v, 0, bag);
        }

        private static int process(int[] w, int[] v, int i, int rest) {
            if (rest < 0) {
                return -1;
            }
            if (i == w.length) {
                return 0;
            }
            //不要当前货
            int p1 = process(w, v, i + 1, rest);
            //要当前货
            int next = process(w, v, i + 1, rest - w[i]);
            int p2 = 0;
            if (next != -1) {
                p2 = v[i] + next;
            }
            return Math.max(p1, p2);

        }
    }

    static class Dp1 {
        public static int maxValue(int[] w, int[] v, int bag) {
            if (w == null || v == null || w.length != v.length || w.length == 0) {
                return 0;
            }
            int n = w.length;
            int[][] dp = new int[n + 1][bag + 1];
            //int[n] dp == 0;
            for (int i = n - 1; i >= 0; i--) {
                for (int rest = 0; rest <= bag; rest++) {
                    int p1 = dp[i + 1][rest];
                    int p2 = rest - w[i] < 0 ? -1 : v[i] + dp[i + 1][rest - w[i]];
                    dp[i][rest] = Math.max(p1, p2);
                }
            }
            return dp[0][bag];

        }
    }

    public static void main(String[] args) {

        int maxSize = 20;
        int range = 30;
        int maxBag = 30;
        int testTime = 1000;
        System.out.println("start");
        for (int i = 0; i < testTime; i++) {
            int size = (int) (maxSize * Math.random());
            int bag = (int) (maxBag * Math.random());
            int[] weights = createRandomArray(size, range);
            int[] values = createRandomArray(size, range);
            int r = Recursion1.maxValue(weights, values, bag);
            int rDp = RecursionDP.maxValue(weights, values, bag);
            int dp = Dp1.maxValue(weights, values, bag);
            if (r != rDp || rDp != dp || r != dp) {
                System.out.println("r = " + r);
                System.out.println("rDp = " + rDp);
                System.out.println("dp = " + dp);
                break;
            }
        }
        System.out.println("end");

    }


    public static int[] createRandomArray(int size, int range) {
        if (size == 0) {
            return null;
        }
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (range * Math.random());
        }
        return arr;
    }


}
