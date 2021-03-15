package class46;

public class Code01_RestoreWays {

	/*
	 * 整型数组arr长度为n(3 <= n <= 10^4)，最初每个数字是<=200的正数且满足如下条件： 1. 0位置的要求：arr[0] <=
	 * arr[1] 2. n-1位置的要求：arr[n-1] <= arr[n-2] 3. 中间i位置的要求：arr[i] <= max(arr[i-1],
	 * arr[i+1]) 但是在arr有些数字丢失了，比如k位置的数字之前是正数，丢失之后k位置的数字为0。 请你根据上述条件，
	 * 计算可能有多少种不同的arr可以满足以上条件。 比如 [6,0,9] 只有还原成 [6,9,9]满足全部三个条件，所以返回1种。 [6,9,9] 达标
	 * 
	 * [ ...... 0 0]
	 */

	public static int ways0(int[] arr) {
		return process0(arr, 0);
	}

	public static int process0(int[] arr, int index) {
		if (index == arr.length) {
			return isValid(arr) ? 1 : 0;
		} else {
			if (arr[index] != 0) {
				return process0(arr, index + 1);
			} else {
				int ways = 0;
				for (int v = 1; v < 201; v++) {
					arr[index] = v;
					ways += process0(arr, index + 1);
				}
				arr[index] = 0;
				return ways;
			}
		}
	}

	public static boolean isValid(int[] arr) {
		if (arr[0] > arr[1]) {
			return false;
		}
		if (arr[arr.length - 1] > arr[arr.length - 2]) {
			return false;
		}
		for (int i = 1; i < arr.length - 1; i++) {
			if (arr[i] > Math.max(arr[i - 1], arr[i + 1])) {
				return false;
			}
		}
		return true;
	}

	public static int ways1(int[] arr) {
		int N = arr.length;
		if (arr[N - 1] != 0) {
			return process1(arr, N - 1, arr[N - 1], 2);
		} else {
			int ways = 0;
			for (int v = 1; v < 201; v++) {
				ways += process1(arr, N - 1, v, 2);
			}
			return ways;
		}
	}

	// 如果i位置的数字变成了v,
	// 并且arr[i]和arr[i+1]的关系为s，
	// s==0，代表arr[i] < arr[i+1] 右大
	// s==1，代表arr[i] == arr[i+1]
	// s==2，代表arr[i] > arr[i+1] 右小
	// 返回0...i范围上有多少种有效的转化方式？
	public static int process1(int[] arr, int i, int v, int s) {
		if (i == 0) { // 0...i 只剩一个数了，0...0
			return ((s == 0 || s == 1) && (arr[i] == 0 || v == arr[i])) ? 1 : 0;
		}
		// i > 0
		if (arr[i] != 0 && v != arr[i]) {
			return 0;
		}
		// i>0 并且 i位置的数真的可以变成V，
		int ways = 0;
		if (s == 0 || s == 1) { // [i - 1] [i] <= 右
			for (int pre = 1; pre < 201; pre++) {
				ways += process1(arr, i - 1, pre, pre < v ? 0 : (pre == v ? 1 : 2));
			}
		} else { // s == 2 i > 右 i-1 >= i > 右
			for (int pre = v; pre < 201; pre++) {
				ways += process1(arr, i - 1, pre, pre == v ? 1 : 2);
			}
		}
		return ways;
	}

	public static int ways2(int[] arr) {
		int N = arr.length;
		int[][][] dp = new int[N][201][3];
		// dp[0][...][...]
		// dp[0][...][2] = 0
		// dp[0][...][0] = 1
		// dp[0][...][1] = 1
		if (arr[0] != 0) {
			dp[0][arr[0]][0] = 1;
			dp[0][arr[0]][1] = 1;
		} else {
			for (int v = 1; v < 201; v++) {
				dp[0][v][0] = 1;
				dp[0][v][1] = 1;
			}
		}
		for (int i = 1; i < N; i++) {
			for (int v = 1; v < 201; v++) {
				for (int s = 0; s < 3; s++) {
					if (arr[i] == 0 || v == arr[i]) {
						if (s == 0 || s == 1) {
							for (int pre = 1; pre < 201; pre++) {
								dp[i][v][s] += dp[i - 1][pre][pre < v ? 0 : (pre == v ? 1 : 2)];
							}
						} else {
							for (int pre = v; pre < 201; pre++) {
								dp[i][v][s] += dp[i - 1][pre][pre < v ? 0 : (pre == v ? 1 : 2)];
							}
						}
					}
				}
			}
		}
		if (arr[N - 1] != 0) {
			return dp[N - 1][arr[N - 1]][2];
		} else {
			int ways = 0;
			for (int v = 1; v < 201; v++) {
				ways += dp[N - 1][v][2];
			}
			return ways;
		}
	}

	public static int ways3(int[] arr) {
		int N = arr.length;
		int[][][] dp = new int[N][201][3];
		if (arr[0] != 0) {
			dp[0][arr[0]][0] = 1;
			dp[0][arr[0]][1] = 1;
		} else {
			for (int v = 1; v < 201; v++) {
				dp[0][v][0] = 1;
				dp[0][v][1] = 1;
			}
		}
		// presum[0~V][0] -> sum0[]
		// presum[0~V][1] -> sum1[]
		// presum[0~V][2] -> sum2[]
		int[][] presum = new int[201][3];
		// presum -> dp[0][..][..] 三张表
		for (int v = 1; v < 201; v++) {
			for (int s = 0; s < 3; s++) {
				presum[v][s] = presum[v - 1][s] + dp[0][v][s];
			}
		}
		for (int i = 1; i < N; i++) {
			for (int v = 1; v < 201; v++) {
				for (int s = 0; s < 3; s++) {
					if (arr[i] == 0 || v == arr[i]) {
						if (s == 0 || s == 1) {
							// dp[i][..][..] -> dp[i-1][..][..]
							dp[i][v][s] += sum(1, v - 1, 0, presum);
							dp[i][v][s] += dp[i - 1][v][1];
							dp[i][v][s] += sum(v + 1, 200, 2, presum);
						} else {
							dp[i][v][s] += dp[i - 1][v][1];
							dp[i][v][s] += sum(v + 1, 200, 2, presum);
						}
					}
				}
			}
			for (int v = 1; v < 201; v++) {
				for (int s = 0; s < 3; s++) {
					presum[v][s] = presum[v - 1][s] + dp[i][v][s];
				}
			}
		}
		if (arr[N - 1] != 0) {
			return dp[N - 1][arr[N - 1]][2];
		} else {
			return sum(1, 200, 2, presum);
		}
	}

	public static int sum(int begin, int end, int relation, int[][] presum) {
		return presum[end][relation] - presum[begin - 1][relation];
	}

	// for test
	public static int[] generateRandomArray(int len) {
		int[] ans = new int[(int) (Math.random() * len) + 2];
		for (int i = 0; i < ans.length; i++) {
			if (Math.random() < 0.5) {
				ans[i] = 0;
			} else {
				ans[i] = (int) (Math.random() * 200) + 1;
			}
		}
		return ans;
	}

	// for test
	public static void printArray(int[] arr) {
		System.out.println("arr size : " + arr.length);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int len = 3;
		int testTime = 10;
		System.out.println("test begin");
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(len);
			int ans0 = ways0(arr);
			int ans1 = ways1(arr);
			int ans2 = ways2(arr);
			int ans3 = ways3(arr);
			if (ans0 != ans1 || ans2 != ans3 || ans0 != ans2) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
		int[] arr = generateRandomArray(100000);
		System.out.println(arr.length);
		long begin = System.currentTimeMillis();
		ways3(arr);
		long end = System.currentTimeMillis();
		System.out.println("run time : " + (end - begin) + " ms");

	}

}
