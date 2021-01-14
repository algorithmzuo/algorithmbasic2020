package leo.class21;

import class21.Code02_CoinsWayEveryPaperDifferent;

/**
 * @author Leo
 * @ClassName CoinsWayEveryPaperDifferent
 * @DATE 2021/1/13 4:46 下午
 * @Description
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，只有一张
 * 返回组成aim的方法数
 */
public class CoinsWayEveryPaperDifferent {

    static class Recursion {
        public static int coinWays(int[] arr, int aim) {
            return process(arr, aim, 0);
        }

        private static int process(int[] arr, int rest, int i) {
            if (rest < 0) {
                return 0;
            }
            if (i == arr.length) {
                return rest == 0 ? 1 : 0;
            }else{
                return process(arr, rest, i + 1) + process(arr, rest - arr[i], i + 1);
            }
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
            if (rest < 0) {
                return 0;
            }
            if (i == arr.length) {
                return rest == 0 ? 1 : 0;
            }
            return process(arr, rest, i + 1) + process(arr, rest - arr[i], i + 1);

        }

    }

    static class dp1 {
        public static int coinWays(int[] arr, int aim) {
            if (arr == null || arr.length == 0) {
                return aim == 0 ? 1 : 0;
            }
            int n = arr.length;
            int[][] dp = new int[n + 1][aim + 1];
            dp[n][0] = 1;
            for (int i = n - 1; i >= 0; i--) {
                for (int rest = 0; rest <= aim; rest++) {
                    dp[i][rest] = dp[i + 1][rest] + (rest - arr[i] >= 0 ? dp[i + 1][rest - arr[i]] : 0);
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
                    dp[i][rest] = dp[i + 1][rest] + (rest - arr[i] >= 0 ? dp[i + 1][rest - arr[i]] : 0);
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
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = Recursion1.coinWays(arr, aim);
            int ans2 = dp1.coinWays(arr, aim);
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
