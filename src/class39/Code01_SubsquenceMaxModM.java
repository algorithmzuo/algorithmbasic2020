package class39;

import java.util.HashSet;
import java.util.TreeSet;

// 给定一个非负数组arr，和一个正数m。 返回arr的所有子序列中累加和%m之后的最大值。
public class Code01_SubsquenceMaxModM {

	public static int max1(int[] arr, int m) {
		HashSet<Integer> set = new HashSet<>();
		process(arr, 0, 0, set);
		int max = 0;
		for (Integer sum : set) {
			max = Math.max(max, sum % m);
		}
		return max;
	}

	public static void process(int[] arr, int index, int sum, HashSet<Integer> set) {
		if (index == arr.length) {
			set.add(sum);
		} else {
			process(arr, index + 1, sum, set);
			process(arr, index + 1, sum + arr[index], set);
		}
	}

	public static int max2(int[] arr, int m) {
		int sum = 0;
		int N = arr.length;
		for (int i = 0; i < N; i++) {
			sum += arr[i];
		}
		boolean[][] dp = new boolean[N][sum + 1];
		for (int i = 0; i < N; i++) {
			dp[i][0] = true;
		}
		dp[0][arr[0]] = true;
		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= sum; j++) {
				dp[i][j] = dp[i - 1][j];
				if (j - arr[i] >= 0) {
					dp[i][j] |= dp[i - 1][j - arr[i]];
				}
			}
		}
		int ans = 0;
		for (int j = 0; j <= sum; j++) {
			if (dp[N - 1][j]) {
				ans = Math.max(ans, j % m);
			}
		}
		return ans;
	}

	public static int max3(int[] arr, int m) {
		int N = arr.length;
		// 0...m-1
		boolean[][] dp = new boolean[N][m];
		for (int i = 0; i < N; i++) {
			dp[i][0] = true;
		}
		dp[0][arr[0] % m] = true;
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < m; j++) {
				// dp[i][j] T or F
				dp[i][j] = dp[i - 1][j];
				int cur = arr[i] % m;
				if (cur <= j) {
					dp[i][j] |= dp[i - 1][j - cur];
				} else {
					dp[i][j] |= dp[i - 1][m + j - cur];
				}
			}
		}
		int ans = 0;
		for (int i = 0; i < m; i++) {
			if (dp[N - 1][i]) {
				ans = i;
			}
		}
		return ans;
	}

	// 如果arr的累加和很大，m也很大
	// 但是arr的长度相对不大
	public static int max4(int[] arr, int m) {
		if (arr.length == 1) {
			return arr[0] % m;
		}
		int mid = (arr.length - 1) / 2;
		TreeSet<Integer> sortSet1 = new TreeSet<>();
		process4(arr, 0, 0, mid, m, sortSet1);
		TreeSet<Integer> sortSet2 = new TreeSet<>();
		process4(arr, mid + 1, 0, arr.length - 1, m, sortSet2);
		int ans = 0;
		for (Integer leftMod : sortSet1) {
			ans = Math.max(ans, leftMod + sortSet2.floor(m - 1 - leftMod));
		}
		return ans;
	}

	// 从index出发，最后有边界是end+1，arr[index...end]
	public static void process4(int[] arr, int index, int sum, int end, int m, TreeSet<Integer> sortSet) {
		if (index == end + 1) {
			sortSet.add(sum % m);
		} else {
			process4(arr, index + 1, sum, end, m, sortSet);
			process4(arr, index + 1, sum + arr[index], end, m, sortSet);
		}
	}

	public static int[] generateRandomArray(int len, int value) {
		int[] ans = new int[(int) (Math.random() * len) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (int) (Math.random() * value);
		}
		return ans;
	}

	public static void main(String[] args) {
		int len = 10;
		int value = 100;
		int m = 76;
		int testTime = 500000;
		System.out.println("test begin");
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(len, value);
			int ans1 = max1(arr, m);
			int ans2 = max2(arr, m);
			int ans3 = max3(arr, m);
			int ans4 = max4(arr, m);
			if (ans1 != ans2 || ans2 != ans3 || ans3 != ans4) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish!");

	}

}
