package class41;

public class Code03_SplitArrayLargestSum {

	public static int splitArray1(int[] nums, int m) {
		return process(nums, 0, m);
	}

	public static int process(int[] arr, int index, int part) {
		if (index == arr.length) {
			return 0;
		}
		if (part == 0) {
			return -1;
		}
		int first = 0;
		int min = Integer.MAX_VALUE;
		for (int end = index; arr.length - end >= part; end++) {
			first += arr[end];
			int next = process(arr, end + 1, part - 1);
			if (next != -1) {
				min = Math.min(min, Math.max(first, next));
			}
		}
		return min;
	}

	public static int splitArray2(int[] nums, int M) {
		int N = nums.length;
		int[] help = new int[nums.length + 1];
		for (int i = 0; i < N; i++) {
			help[i + 1] = help[i] + nums[i];
		}
		int[][] dp = new int[N][M + 1];
		for (int i = 0; i < N; i++) {
			dp[i][1] = help[i + 1] - help[0];
		}
		for (int i = 1; i < Math.min(N, M); i++) {
			dp[i][i + 1] = Math.max(dp[i - 1][i], nums[i]);
		}
		for (int i = 2; i < N; i++) {
			for (int j = 2; j <= Math.min(i, M); j++) {
				dp[i][j] = Integer.MAX_VALUE;
				for (int k = i; k >= j - 1; k--) {
					dp[i][j] = Math.min(dp[i][j], Math.max(dp[k - 1][j - 1], help[i + 1] - help[k]));
				}
			}
		}
		return dp[N - 1][M];
	}

	public static int splitArray3(int[] nums, int M) {
		int N = nums.length;
		int[] help = new int[nums.length + 1];
		for (int i = 0; i < N; i++) {
			help[i + 1] = help[i] + nums[i];
		}
		int[][] dp = new int[N][M + 1];
		int[][] best = new int[N][M + 1];
		for (int i = 0; i < N; i++) {
			dp[i][1] = help[i + 1] - help[0];
		}
		for (int i = 1; i < Math.min(N, M); i++) {
			dp[i][i + 1] = Math.max(dp[i - 1][i], nums[i]);
			best[i][i + 1] = i;
		}
		for (int i = 2; i < N; i++) {
			for (int j = Math.min(i, M); j >= 2; j--) {
				dp[i][j] = Integer.MAX_VALUE;
				int left = best[i - 1][j];
				int right = j + 1 > M ? i : best[i][j + 1];
				for (int k = left; k <= right; k++) {
					int curAns = Math.max(dp[k - 1][j - 1], help[i + 1] - help[k]);
					if (dp[i][j] > curAns) {
						dp[i][j] = curAns;
						best[i][j] = k;
					}
				}
			}
		}
		return dp[N - 1][M];
	}

	public static int splitArray4(int[] nums, int M) {
		long sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		long l = 0;
		long r = sum;
		long ans = 0;
		while (l <= r) {
			long mid = (l + r) / 2;
			long cur = getNeedParts(nums, mid);
			if (cur <= M) {
				ans = mid;
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return (int) ans;
	}

	public static int getNeedParts(int[] arr, long aim) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > aim) {
				return Integer.MAX_VALUE;
			}
		}
		int parts = 1;
		int all = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (all + arr[i] > aim) {
				parts++;
				all = arr[i];
			} else {
				all += arr[i];
			}
		}
		return parts;
	}

}
