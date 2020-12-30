package class20;

import java.util.TreeSet;

/*
 * 给定一个整型数组arr，请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
 * 返回最接近的情况下，较小集合的累加和（较大集合的累加和一定是所有数累加和减去较小集合的累加和）
 * 为了方便起见，假设arr中没有负数，其实也可以有
 * 但是处理起来会比较麻烦，而且有没有负数都不影响算法流程的理解
 * */
public class Code02_SplitSumClosed {

	public static int right(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int sum = 0;
		for (int num : arr) {
			sum += num;
		}
		TreeSet<Integer> ans = new TreeSet<>();
		process(arr, 0, 0, 0, ans, sum >> 1);
		return ans.last();
	}

	public static void process(int[] arr, int i, int sum, int picks, TreeSet<Integer> ans, int limit) {
		if (i == arr.length) {
			if (sum <= limit) {
				ans.add(sum);
			}
		} else {
			process(arr, i + 1, sum, picks, ans, limit);
			process(arr, i + 1, sum + arr[i], picks + 1, ans, limit);
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
