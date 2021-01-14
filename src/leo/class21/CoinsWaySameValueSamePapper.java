package leo.class21;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Leo
 * @ClassName CoinsWaySameValueSamePapper
 * @DATE 2021/1/14 11:07 上午
 * @Description
 *
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 认为值相同的货币没有任何不同，
 * 返回组成aim的方法数
 */
public class CoinsWaySameValueSamePapper {

    static class Recursion{
        static class Info {
            int[] count;
            int[] coins;
            public Info(int[] coins,int[] count){
                this.coins = coins;
                this.count = count;
            }
        }
        private static Info getInfo(int[] arr) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                if (map.containsKey(arr[i])) {
                    map.put(arr[i], map.get(arr[i]) + 1);
                }else{
                    map.put(arr[i], 1);
                }
            }
            int[] count = new int[map.size()];
            int[] coins = new int[map.size()];
            int i = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                coins[i] = entry.getKey();
                count[i++] = entry.getValue();
            }
            return new Info(coins, count);
        }

        public static int coinsWay(int[] arr, int aim) {
            if (arr == null || arr.length == 0) {
                return aim == 0 ? 1 : 0;
            }
            Info info = getInfo(arr);
            return process(info.coins, info.count, aim, 0);
        }

        private static int process(int[] coins, int[] count, int rest, int i) {
            if (rest < 0) {
                return 0;
            }
            if (i == coins.length) {
                return rest == 0 ? 1 : 0;
            }
            int ans = 0;
            for (int z = 0; coins[i] * z <= rest && z <= count[i]; z++) {
                ans += process(coins, count, rest - z * coins[i], i + 1);
            }
            return ans;
        }
    }

    static class Dp {
        static class Info {
            int[] coins;
            int[] count;

            public Info(int[] coins, int[] count) {
                this.coins = coins;
                this.count = count;
            }
        }
        private static Info getInfo(int[] arr) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                if (map.containsKey(arr[i])) {
                    map.put(arr[i], map.get(arr[i]) + 1);
                }else{
                    map.put(arr[i], 1);
                }
            }
            int[] coins = new int[map.size()];
            int[] count = new int[map.size()];
            int i = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                coins[i] = entry.getKey();
                count[i++] = entry.getValue();
            }
            return new Info(coins, count);
        }

        public static int coinsWay(int[] arr, int aim) {
            if (arr == null || arr.length == 0) {
                return aim == 0 ? 1 : 0;
            }
            Info info = getInfo(arr);
            int n = info.coins.length;
            int[] coins = info.coins;
            int[] count = info.count;
            int[][] dp = new int[n + 1][aim + 1];
            dp[n][0] = 1;
            for (int i = n - 1; i >= 0; i--) {
                for (int rest = 0; rest <= aim; rest++) {
                    int ans = 0;
                    for (int z = 0; coins[i] * z <= rest && z <= count[i]; z++) {
                        ans += dp[i + 1][rest - z * coins[i]];
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
        static class Info{
            int[] coins;
            int[] count;

            public Info(int[] coins, int[] count) {
                this.coins = coins;
                this.count = count;
            }
        }

        private static Info getInfo(int[] arr) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                if (map.containsKey(arr[i])) {
                    map.put(arr[i], map.get(arr[i]) + 1);
                }else{
                    map.put(arr[i], 1);
                }
            }
            int[] coins = new int[map.size()];
            int[] count = new int[map.size()];
            int i = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                coins[i] = entry.getKey();
                count[i++] = entry.getValue();
            }
            return new Info(coins, count);
        }


        public static int coinsWay(int[] arr, int aim) {
            if (arr == null || arr.length == 0) {
                return aim == 0 ? 1 : 0;
            }
            Info info = getInfo(arr);
            int[] coins = info.coins;
            int[] count = info.count;
            int n = coins.length;
            int[][] dp = new int[n + 1][aim + 1];
            dp[n][0] = 1;
            for (int i = n - 1; i >= 0; i--) {
                for (int rest = 0; rest <= aim; rest++) {
                    dp[i][rest] = dp[i + 1][rest];
                    if (rest - coins[i] >= 0) {
                        dp[i][rest] += dp[i][rest - coins[i]];
                    }
                    if (rest - coins[i] * (count[i] + 1) >= 0) {
                        dp[i][rest] -= dp[i + 1][rest - coins[i] * (count[i] + 1)];
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
        int maxLen = 10;
        int maxValue = 20;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = OptimizationDp.coinsWay(arr, aim);
            int ans2 = Dp.coinsWay(arr, aim);
            if (ans1 != ans2 ) {
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
