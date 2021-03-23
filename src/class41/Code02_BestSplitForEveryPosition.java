package class41;

public class Code02_BestSplitForEveryPosition {

	public static int[] bestSplit1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return new int[0];
		}
		int N = arr.length;
		int[] ans = new int[N];
		ans[0] = 0;
		for (int range = 1; range < N; range++) {
			for (int s = 0; s < range; s++) {
				int sumL = 0;
				for (int L = 0; L <= s; L++) {
					sumL += arr[L];
				}
				int sumR = 0;
				for (int R = s + 1; R <= range; R++) {
					sumR += arr[R];
				}
				ans[range] = Math.max(ans[range], Math.min(sumL, sumR));
			}
		}
		return ans;
	}

	// 求原来的数组arr中，arr[L...R]的累加和
	public static int sum(int[] sum, int L, int R) {
		return sum[R + 1] - sum[L];
	}

	public static int[] bestSplit2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return new int[0];
		}
		int N = arr.length;
		int[] ans = new int[N];
		ans[0] = 0;
		int[] sum = new int[N + 1];
		for (int i = 0; i < N; i++) {
			sum[i + 1] = sum[i] + arr[i];
		}
		for (int range = 1; range < N; range++) {
			for (int s = 0; s < range; s++) {
				int sumL = sum(sum, 0, s);
				int sumR = sum(sum, s + 1, range);
				ans[range] = Math.max(ans[range], Math.min(sumL, sumR));
			}
		}
		return ans;
	}

	public static int[] bestSplit3(int[] arr) {
		if (arr == null || arr.length == 0) {
			return new int[0];
		}
		int N = arr.length;
		int[] ans = new int[N];
		ans[0] = 0;
		// arr =   {5, 3, 1, 3}
		//          0  1  2  3
		// sum ={0, 5, 8, 9, 12}
		//       0  1  2  3   4
		// 0~2 ->  sum[3] - sum[0]
		// 1~3 ->  sum[4] - sum[1]
		int[] sum = new int[N + 1];
		for (int i = 0; i < N; i++) {
			sum[i + 1] = sum[i] + arr[i];
		}
		// 最优划分
		// 0~range-1上，最优划分是左部分[0~best]  右部分[best+1~range-1]
		int best = 0;
		for (int range = 1; range < N; range++) {
			while (best + 1 < range) {
				int before = Math.min(sum(sum, 0, best), sum(sum, best + 1, range));
				int after = Math.min(sum(sum, 0, best + 1), sum(sum, best + 2, range));
				// 注意，一定要是>=，只是>会出错
				// 课上会讲解
				if (after >= before) {
					best++;
				} else {
					break;
				}
			}
			ans[range] = Math.min(sum(sum, 0, best), sum(sum, best + 1, range));
		}
		return ans;
	}

	public static int[] randomArray(int len, int max) {
		int[] ans = new int[len];
		for (int i = 0; i < len; i++) {
			ans[i] = (int) (Math.random() * max);
		}
		return ans;
	}

	public static boolean isSameArray(int[] arr1, int[] arr2) {
		if (arr1.length != arr2.length) {
			return false;
		}
		int N = arr1.length;
		for (int i = 0; i < N; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int N = 20;
		int max = 30;
		int testTime = 1000000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * N);
			int[] arr = randomArray(len, max);
			int[] ans1 = bestSplit1(arr);
			int[] ans2 = bestSplit2(arr);
			int[] ans3 = bestSplit3(arr);
			if (!isSameArray(ans1, ans2) || !isSameArray(ans1, ans3)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("测试结束");
	}

}
