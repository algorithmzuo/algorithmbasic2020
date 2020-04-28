package class12;

public class Code09_CoinsWay {

	// arr中都是正数且无重复值，返回组成aim的方法数
	public static int ways(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		return process(arr, 0, aim);
	}

	// 如果自由使用arr[index...]的面值，组成rest这么多钱，返回方法数 （1 , 6）
	public static int process(int[] arr, int index, int rest) {
		if (index == arr.length) { // 无面值的时候
			return rest == 0 ? 1 : 0;
		}
		// 有面值的时候
		int ways = 0;
		// arr[index] 当钱面值
		for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
			ways += process(arr, index + 1, rest - zhang * arr[index]);
		}
		return ways;
	}

	public static int waysdp(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		int N = arr.length;
		int[][] dp = new int[N + 1][aim + 1];
		dp[N][0] = 1;
		for (int i = N - 1; i >= 0; i--) { // 大顺序 从下往上
			for (int rest = 0; rest <= aim; rest++) {
				dp[i][rest] = dp[i + 1][rest];
				if (rest - arr[i] >= 0) {
					dp[i][rest] += dp[i][rest - arr[i]];
				}
			}
		}
		return dp[0][aim];
	}

	public static void main(String[] args) {
		int[] arr = { 5, 2, 3, 1 };
		int sum = 350;
		System.out.println(ways(arr, sum));
		System.out.println(waysdp(arr, sum));
	}

}
