package class23;

public class Code01_SplitSumClosed {

	public static int right(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int sum = 0;
		for (int num : arr) {
			sum += num;
		}
		return process(arr, 0, sum >> 1);
	}

	public static int process(int[] arr, int i, int rest) {
		if (i == arr.length) {
			return 0;
		} else {
			int p1 = process(arr, i + 1, rest);
			int p2 = 0;
			if (arr[i] <= rest) {
				p2 = arr[i] + process(arr, i + 1, rest - arr[i]);
			}
			return Math.max(p1, p2);
		}
	}

	public static int dp(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int sum = 0;
		for (int num : arr) {
			sum += num;
		}
		sum >>= 1;
		int N = arr.length;
		boolean[][] dp = new boolean[N][sum + 1];
		for (int i = 0; i < N; i++) {
			dp[i][0] = true;
		}
		if (arr[0] <= sum) {
			dp[0][arr[0]] = true;
		}
		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= sum; j++) {
				dp[i][j] = dp[i - 1][j];
				if (j - arr[i] >= 0) {
					dp[i][j] |= dp[i - 1][j - arr[i]];
				}
			}
		}
		for (int j = sum; j >= 1; j--) {
			if (dp[N - 1][j]) {
				return j;
			}
		}
		return 0;
	}

	public static int[] randomArray(int len, int value) {
		int[] arr = new int[len];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * value);
		}
		return arr;
	}

	public static void printArray(int[] arr) {
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int maxLen = 20;
		int maxValue = 50;
		int testTime = 10000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * maxLen);
			int[] arr = randomArray(len, maxValue);
			int ans1 = right(arr);
			int ans2 = dp(arr);
			if (ans1 != ans2) {
				printArray(arr);
				System.out.println(ans1);
				System.out.println(ans2);
				System.out.println("Oops!");
				break;
			}
		}
		System.out.println("测试结束");
	}

}
