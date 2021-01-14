package leo.class21;

/**
 * @author Leo
 * @ClassName CoinsWayNoLimit
 * @DATE 2021/1/13 5:21 下午
 * @Description
 *
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的方法数
 */
public class CoinsWayNoLimit {

    static class Recursion {
        public static int coinWays(int[] arr, int aim) {
            if (arr == null || arr.length == 0 || aim == 0) {
                return 0;
            }
            return process(arr, 0, aim);
        }

        private static int process(int[] arr, int i, int rest) {
            if (i == arr.length) {
                return rest == 0 ? 1 : 0;
            }
            int ans = 0;
            for (int z = 0; z * arr[i] <= rest; z++) {
                ans += process(arr, i + 1, rest - z * arr[i]);
            }
            return ans;
        }
    }

    static class Recursion1 {
        public static int coinWays(int[] arr, int aim) {
            if (arr == null || arr.length == 0) {
                return aim == 0 ? 1 : 0;
            }
            return process(arr, aim, 0);
        }

        private static int process(int[] arr, int rest, int i) {
            if (i == arr.length) {
                return rest == 0 ? 1 : 0;
            }
            int ans = 0;
            for (int z = 0; z * arr[i] <= rest; z++) {
                ans += process(arr, rest - z*arr[i], i + 1);
            }
            return ans;
        }
    }

    static class Dp1 {
        public static int coinWays(int[] arr, int aim) {
            if (arr == null || arr.length == 0) {
                return aim == 0 ? 1 : 0;
            }
            int n = arr.length;
            int[][] dp = new int[n + 1][aim + 1];
            dp[n][0] = 1;
            for (int i = n-1; i >= 0; i--) {
                for (int rest = 0; rest <= aim; rest++) {
                    int ans = 0;
                    for (int z = 0; z * arr[i] <= rest; z++) {
                        ans += dp[i + 1][rest - z * arr[i]];
                    }
                    dp[i][rest] = ans;
                }
            }
            return dp[0][aim];
        }
    }
    static class Dp {
        public static int coinWays(int[] arr, int aim) {
            if (arr == null || arr.length == 0) {
                return aim == 0 ? 1 : 0;
            }
            int n = arr.length;
            int[][] dp = new int[n + 1][aim + 1];
            dp[n][0] = 1;
            for (int i = n - 1; i >= 0; i--) {
                for (int rest = 0; rest <= aim; rest++) {
                    int ans = 0;
                    for (int z = 0; z * arr[i] <= rest; z++) {
                        ans += dp[i + 1][rest - z * arr[i]];
                    }
                    dp[i][rest] = ans;
                }
            }

            return dp[0][aim];
        }
    }

    /**
     * 观察严格以来表结构,简化枚举过程
     */
    static class OptimizationDp {
        public static int coinWays(int[] arr, int aim) {
            if (arr == null || arr.length == 0) {
                return aim == 0 ? 1 : 0;
            }
            int n = arr.length;
            int[][] dp = new int[n + 1][aim + 1];
            dp[n][0] = 1;
            for (int i = n - 1; i >= 0; i--) {
                for (int rest = 0; rest <= aim; rest++) {
                    dp[i][rest] = dp[i + 1][rest];
                    if (rest - arr[i] >= 0) {
                        dp[i][rest] += dp[i][rest - arr[i]];
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
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
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
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = OptimizationDp.coinWays(arr, aim);
            int ans2 = Dp.coinWays(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
