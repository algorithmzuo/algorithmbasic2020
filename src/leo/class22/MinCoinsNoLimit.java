package leo.class22;

import class22.Code02_MinCoinsNoLimit;

/**
 * @author Leo
 * @ClassName MinCoinsNoLimit
 * @DATE 2021/1/15 1:39 下午
 * @Description
 *
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的最少货币数
 */
public class MinCoinsNoLimit {

    static class Recursion{
        public static int minCoins(int[] arr, int aim) {
            return process(arr, aim, 0);
        }

        private static int process(int[] arr, int rest, int i) {
            if (i == arr.length) {
                return rest == 0 ? 0 : Integer.MAX_VALUE;
            }
            int ans = Integer.MAX_VALUE;
            for (int z = 0; z * arr[i] <= rest; z++) {
                int next = process(arr, rest - z * arr[i], i + 1);
                if (next != Integer.MAX_VALUE) {
                    ans = Math.min(ans, next + z);
                }
            }
            return ans;
        }
    }

    static class Recursion1 {
        public static int minCoins(int[] arr, int aim) {
            return process(arr, 0, aim);
        }
        private static int process(int[] arr, int i, int rest) {
            if (i == arr.length) {
                return rest == 0 ? 0 : Integer.MAX_VALUE;
            }
            int min = Integer.MAX_VALUE;
            for (int z = 0; z * arr[i] <= rest; z++) {
                int next = process(arr, i + 1, rest - z * arr[i]);
                if (next != Integer.MAX_VALUE) {
                    min = Math.min(min, next + z);
                }
            }
            return min;
        }
    }
    static class Dp1{
        public static int minCoins(int[] arr, int aim) {
            int n = arr.length;
            int[][] dp = new int[n + 1][aim + 1];
            dp[n][0] = 0;
            for (int i = 1; i <= aim; i++) {
                dp[n][i] = Integer.MAX_VALUE;
            }
            for (int i = n - 1; i >= 0; i--) {
                for (int rest = 0; rest <= aim; rest++) {
                    int min = Integer.MAX_VALUE;
                    for (int z = 0; z * arr[i] <= rest; z++) {
                        int next = dp[i + 1][rest - z * arr[i]];
                        if (next != Integer.MAX_VALUE) {
                            min = Math.min(min, next + z);
                        }
                    }
                    dp[i][rest] = min;
                }
            }
            return dp[0][aim];
        }
    }
    static class OptDp1 {
        public static int minCoins(int[] arr, int aim) {
            int n = arr.length;
            int[][] dp = new int[n + 1][aim + 1];
            dp[n][0] = 0;
            for (int i = 1; i <= aim; i++) {
                dp[n][i] = Integer.MAX_VALUE;
            }
            for (int i = n - 1; i >= 0; i--) {
                for (int rest = 0; rest <= aim; rest++) {
                    dp[i][rest] = dp[i + 1][rest];
                    if (rest - arr[i] >= 0 && dp[i][rest - arr[i]] != Integer.MAX_VALUE) {
                        dp[i][rest] = Math.min(dp[i][rest], dp[i][rest - arr[i]] + 1);
                    }
                }
            }
            return dp[0][aim];
        }
    }

    static class Dp {
        public static int minCoins(int[] arr, int aim) {
            if (aim == 0) {
                return 0;
            }
            int n = arr.length;
            int[][] dp = new int[n + 1][aim + 1];
            dp[n][0] = 0;
            for (int i = 1; i <= aim; i++) {
                dp[n][i] = Integer.MAX_VALUE;
            }
            for (int i = n - 1; i >= 0; i--) {
                for (int rest = 0; rest <= aim; rest++) {
                    int ans = Integer.MAX_VALUE;
                    for (int z = 0; z * arr[i] <= rest; z++) {
                        int next = dp[i + 1][rest - z * arr[i]];
                        if (next != Integer.MAX_VALUE) {
                            ans = Math.min(ans, next + z);
                        }
                    }
                    dp[i][rest] = ans;
                }
            }
            return dp[0][aim];
        }
    }

    static class OptDp{
        public static int minCoins(int[] arr, int aim) {
            if (aim == 0) {
                return 0;
            }
            int n = arr.length;
            int[][] dp = new int[n + 1][aim + 1];
            dp[n][0] = 0;
            for (int i = 1; i <= aim; i++) {
                dp[n][i] = Integer.MAX_VALUE;
            }
            for (int i = n - 1; i >= 0; i--) {
                for (int rest = 0; rest <= aim; rest++) {
                    dp[i][rest] = dp[i + 1][rest];
                    if (rest - arr[i] >= 0 && dp[i][rest - arr[i]] != Integer.MAX_VALUE) {
                        dp[i][rest] = Math.min(dp[i][rest], dp[i][rest - arr[i]] + 1);
                    }
                }
            }
            return dp[0][aim];


        }
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = Recursion1.minCoins(arr, aim);
            int ans2 = Dp1.minCoins(arr, aim);
            int ans3 = OptDp1.minCoins(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println("-----");
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("功能测试结束");
    }

}
