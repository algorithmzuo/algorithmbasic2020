package class42;

import java.util.Arrays;

public class Code02_PostOfficeProblem {

	public static int minDis1(int[] arr, int num) {
		if (arr == null || arr.length < 2 || num < 1) {
			return 0;
		}
		// record[L][R]表示如果arr[L...R]上只建立一个邮局，总距离最小是多少？
		int[][] record = getRecord(arr);
		int N = arr.length;
		int[][] dp = new int[N][num + 1];
		// dp[...][0] 0个邮局的时候如何如何
		// dp[0][...] 0
		for (int i = 0; i < N; i++) {
			// 0...i 1
			dp[i][1] = record[0][i];
		}
		for (int i = 1; i < N; i++) {
			for (int j = 2; j <= Math.min(num, i); j++) {
				// dp[i][j]
				// 枚举最后一个邮局负责的范围，K...i
				// i..i
				// i-1..i
				// i-2..i
				// 1....i
				// 0....i 单列
				// 0...k-1 j-1个邮局 +
				dp[i][j] = record[0][i];
				for (int k = i; k > 0; k--) { // 1 .... i
					dp[i][j] = Math.min(dp[i][j], dp[k - 1][j - 1] + record[k][i]);
				}
			}
		}
		return dp[N - 1][num];
	}

	public static int minDis2(int[] arr, int num) {
		if (arr == null || arr.length < 2 || num < 1) {
			return 0;
		}
		// record[L][R]表示如果arr[L...R]上只建立一个邮局，总距离最小是多少？
		int[][] record = getRecord(arr);
		int N = arr.length;
		int[][] dp = new int[N][num + 1];
		// dp[...][0]
		// dp[0][...] 0..0 0
		// choose[0][..] 0
		// choose[i][j] 当时在求dp[i][j]的时候，
		// 最右的邮局，如果是在负责k...i这一段的时候，取得的最优解，请把choose[i][j] = k
		int[][] choose = new int[N][num + 1];
		for (int i = 0; i < N; i++) {
			// 0..i 1个邮局 0...i 0
			dp[i][1] = record[0][i];
		}
		for (int i = 1; i < N; i++) {
			for (int j = Math.min(num, i); j >= 2; j--) {
				int down = choose[i - 1][j];
				int up = j == Math.min(num, i) ? i : choose[i][j + 1];
				dp[i][j] = record[0][i];
				for (int k = Math.max(1, down); k <= Math.min(up, i); k++) {
					if (dp[k - 1][j - 1] + record[k][i] < dp[i][j]) {
						dp[i][j] = dp[k - 1][j - 1] + record[k][i];
						choose[i][j] = k;
					}
				}
			}
		}
		return dp[N - 1][num];
	}

	public static int[][] getRecord(int[] arr) {
		int N = arr.length;
		int[][] record = new int[N][N];
		for (int L = 0; L < N; L++) {
			for (int R = L + 1; R < N; R++) {
				record[L][R] = record[L][R - 1] + arr[R] - arr[(L + R) >> 1];
			}
		}
		return record;
	}

	public static int minDistances1(int[] arr, int num) {
		if (arr == null || num < 1 || arr.length < num) {
			return 0;
		}
		int[][] w = new int[arr.length + 1][arr.length + 1];
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				w[i][j] = w[i][j - 1] + arr[j] - arr[(i + j) / 2];
			}
		}
		int[][] dp = new int[num][arr.length];
		for (int j = 0; j != arr.length; j++) {
			dp[0][j] = w[0][j];
		}
		for (int i = 1; i < num; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				dp[i][j] = Integer.MAX_VALUE;
				for (int k = 0; k <= j; k++) {
					dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + w[k + 1][j]);
				}
			}
		}
		return dp[num - 1][arr.length - 1];
	}

	public static int minDistances2(int[] arr, int num) {
		if (arr == null || num < 1 || arr.length < num) {
			return 0;
		}
		int[][] w = new int[arr.length + 1][arr.length + 1];
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				w[i][j] = w[i][j - 1] + arr[j] - arr[(i + j) / 2];
			}
		}
		int[][] dp = new int[num][arr.length];
		int[][] s = new int[num][arr.length];
		for (int j = 0; j != arr.length; j++) {
			dp[0][j] = w[0][j];
			s[0][j] = 0;
		}
		int minK = 0;
		int maxK = 0;
		int cur = 0;
		for (int i = 1; i < num; i++) {
			for (int j = arr.length - 1; j > i; j--) {
				minK = s[i - 1][j];
				maxK = j == arr.length - 1 ? arr.length - 1 : s[i][j + 1];
				dp[i][j] = Integer.MAX_VALUE;
				for (int k = minK; k <= maxK; k++) {
					cur = dp[i - 1][k] + w[k + 1][j];
					if (cur <= dp[i][j]) {
						dp[i][j] = cur;
						s[i][j] = k;
					}
				}
			}
		}
		return dp[num - 1][arr.length - 1];
	}

	// for test
	public static int[] getSortedArray(int len, int range) {
		int[] arr = new int[len];
		for (int i = 0; i != len; i++) {
			arr[i] = (int) (Math.random() * range);
		}
		Arrays.sort(arr);
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int[] arr = { 1,3,8,10,12 };
		int num = 3;
		System.out.println(minDis1(arr, num));
		System.out.println(minDistances1(arr, num));
		System.out.println(minDistances2(arr, num));

		int times = 100; // test time
		int len = 1000; // test array length
		int range = 2000; // every number in [0,range)
		int p = 50; // post office number max
		long time1 = 0; // method1 all run time
		long time2 = 0;// method2 all run time
		long time3 = 0;
		long start = 0;
		long end = 0;
		int res1 = 0;
		int res2 = 0;
		int res3 = 0;
		for (int i = 0; i != times; i++) {
			int office = (int) (Math.random() * p) + 1;
			arr = getSortedArray(len, range);
			start = System.currentTimeMillis();
			res1 = minDistances1(arr, office);
			end = System.currentTimeMillis();
			time1 += end - start;
			start = System.currentTimeMillis();
			res2 = minDis2(arr, office);
			end = System.currentTimeMillis();
			time2 += end - start;

			start = System.currentTimeMillis();
			res3 = minDis1(arr, office);
			end = System.currentTimeMillis();
			time3 += end - start;
			if (res1 != res2 || res1 != res3) {
				printArray(arr);
				break;
			}
			if (i % 10 == 0) {
				System.out.print(". ");
			}
		}
		System.out.println();
		System.out.println("method1 all run time(ms): " + time1);
		System.out.println("method2 all run time(ms): " + time2);
		System.out.println("method3 all run time(ms): " + time3);

	}

}
