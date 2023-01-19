package class40;

import java.util.TreeMap;

public class Code04_AvgLessEqualValueLongestSubarray {

	// 暴力解，时间复杂度O(N^3)，用于做对数器
	public static int ways1(int[] arr, int v) {
		int ans = 0;
		for (int L = 0; L < arr.length; L++) {
			for (int R = L; R < arr.length; R++) {
				int sum = 0;
				int k = R - L + 1;
				for (int i = L; i <= R; i++) {
					sum += arr[i];
				}
				double avg = (double) sum / (double) k;
				if (avg <= v) {
					ans = Math.max(ans, k);
				}
			}
		}
		return ans;
	}

	// 想实现的解法2，时间复杂度O(N*logN)
	// 题目要求平均值<=v的最长子数组。
	// 那么我们可以转化一下，
	// 比如数组如下：7，4，3，9，6
	// v=5
	// 我们可以让每个数字减去5，得到如下转化数组：
	// 2，-1，-2，4，1
	// 原数组平均值<=5的最长子数组，就等同于：
	// 累加和<=0的最长子数组。
	// 想通这个，就好办了。
	// 然后就是在转化数组里求：
	// 子数组必须以i结尾的情况下，累加和<=0的最长子数组。
	// 代码中的sum，就是每一步从原始数组的值先转化，然后把转化后的值累加起来的前缀和。
	// 比如，转化数组
	// 2，-1，-2，4，1
	// sum依次为 : 2、1、-1、3、4
	// 那么，比如当你来到一个位置i，
	// sum就是0...i的转化前缀和，假设是100
	// 那么就找>=100且距离100最近的转化前缀和最早出现在哪。
	public static int ways2(int[] arr, int v) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		for (int i = 0; i < arr.length; i++) {
			arr[i] -= v;
		}
		TreeMap<Integer, Integer> sortedMap = new TreeMap<>();
		sortedMap.put(0, -1);
		int sum = 0;
		int len = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			Integer ceiling = sortedMap.ceilingKey(sum);
			if (ceiling != null) {
				len = Math.max(len, i - sortedMap.get(ceiling));
			} else {
				sortedMap.put(sum, i);
			}
		}
		return len;
	}

	// 想实现的解法3，时间复杂度O(N)
	public static int ways3(int[] arr, int v) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		for (int i = 0; i < arr.length; i++) {
			arr[i] -= v;
		}
		return maxLengthAwesome(arr, 0);
	}

	// 找到数组中累加和<=k的最长子数组
	public static int maxLengthAwesome(int[] arr, int k) {
		int N = arr.length;
		int[] sums = new int[N];
		int[] ends = new int[N];
		sums[N - 1] = arr[N - 1];
		ends[N - 1] = N - 1;
		for (int i = N - 2; i >= 0; i--) {
			if (sums[i + 1] < 0) {
				sums[i] = arr[i] + sums[i + 1];
				ends[i] = ends[i + 1];
			} else {
				sums[i] = arr[i];
				ends[i] = i;
			}
		}
		int end = 0;
		int sum = 0;
		int res = 0;
		for (int i = 0; i < N; i++) {
			while (end < N && sum + sums[end] <= k) {
				sum += sums[end];
				end = ends[end] + 1;
			}
			res = Math.max(res, end - i);
			if (end > i) {
				sum -= arr[i];
			} else {
				end = i + 1;
			}
		}
		return res;
	}

	// 用于测试
	public static int[] randomArray(int maxLen, int maxValue) {
		int len = (int) (Math.random() * maxLen) + 1;
		int[] ans = new int[len];
		for (int i = 0; i < len; i++) {
			ans[i] = (int) (Math.random() * maxValue);
		}
		return ans;
	}

	// 用于测试
	public static int[] copyArray(int[] arr) {
		int[] ans = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			ans[i] = arr[i];
		}
		return ans;
	}

	// 用于测试
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 用于测试
	public static void main(String[] args) {
		System.out.println("测试开始");
		int maxLen = 20;
		int maxValue = 100;
		int testTime = 500000;
		for (int i = 0; i < testTime; i++) {
			int[] arr = randomArray(maxLen, maxValue);
			int value = (int) (Math.random() * maxValue);
			int[] arr1 = copyArray(arr);
			int[] arr2 = copyArray(arr);
			int[] arr3 = copyArray(arr);
			int ans1 = ways1(arr1, value);
			int ans2 = ways2(arr2, value);
			int ans3 = ways3(arr3, value);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("测试出错！");
				System.out.print("测试数组：");
				printArray(arr);
				System.out.println("子数组平均值不小于 ：" + value);
				System.out.println("方法1得到的最大长度：" + ans1);
				System.out.println("方法2得到的最大长度：" + ans2);
				System.out.println("方法3得到的最大长度：" + ans3);
				System.out.println("=========================");
			}
		}
		System.out.println("测试结束");
	}

}
