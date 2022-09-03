package class21;

/**
 * arr是货币组,其中都是正数，再给定一个正数aim，每一张都认为是一张货币，即使面值相同
 * 返回组成aim的方法数
 */
public class Code02_CoinsWayEveryPaperDifferent_Study {
    public static int coinWays(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    private static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        } else {
            return process(arr, index + 1, rest - arr[index]) + process(arr, index + 1, rest);
        }
    }

    private static int dp(int[] arr, int aim) {
        return 0;
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int result1 = coinWays(arr, aim);
        }
    }

    private static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    private void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
