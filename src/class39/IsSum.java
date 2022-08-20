package class39;

import java.util.HashMap;
import java.util.HashSet;

// 这道题是一个小小的补充，课上没有讲
// 但是如果你听过体系学习班动态规划专题和本节课的话
// 这道题就是一道水题
public class IsSum {

	// arr中的值可能为正，可能为负，可能为0
	// 自由选择arr中的数字，能不能累加得到sum
	// 暴力递归方法
	public static boolean isSum1(int[] arr, int sum) {
		if (sum == 0) {
			return true;
		}
		if (arr == null || arr.length == 0) {
			return false;
		}
		return process1(arr, arr.length - 1, sum);
	}

	// 可以自由使用arr[0...i]上的数字，能不能累加得到sum
	public static boolean process1(int[] arr, int i, int sum) {
		if (sum == 0) {
			return true;
		}
		if (i == -1) {
			return false;
		}
		return process1(arr, i - 1, sum) || process1(arr, i - 1, sum - arr[i]);
	}

	// arr中的值可能为正，可能为负，可能为0
	// 自由选择arr中的数字，能不能累加得到sum
	// 记忆化搜索方法
	// 从暴力递归方法来，加了记忆化缓存，就是动态规划了
	public static boolean isSum2(int[] arr, int sum) {
		if (sum == 0) {
			return true;
		}
		if (arr == null || arr.length == 0) {
			return false;
		}
		return process2(arr, arr.length - 1, sum, new HashMap<>());
	}

	public static boolean process2(int[] arr, int i, int sum, HashMap<Integer, HashMap<Integer, Boolean>> dp) {
		if (dp.containsKey(i) && dp.get(i).containsKey(sum)) {
			return dp.get(i).get(sum);
		}
		boolean ans = false;
		if (sum == 0) {
			ans = true;
		} else if (i != -1) {
			ans = process2(arr, i - 1, sum, dp) || process2(arr, i - 1, sum - arr[i], dp);
		}
		if (!dp.containsKey(i)) {
			dp.put(i, new HashMap<>());
		}
		dp.get(i).put(sum, ans);
		return ans;
	}

	// arr中的值可能为正，可能为负，可能为0
	// 自由选择arr中的数字，能不能累加得到sum
	// 经典动态规划
	public static boolean isSum3(int[] arr, int sum) {
		if (sum == 0) {
			return true;
		}
		if (arr == null || arr.length == 0) {
			return false;
		}
		int min = 0;
		int max = 0;
		for (int num : arr) {
			min += num < 0 ? num : 0;
			max += num > 0 ? num : 0;
		}
		if (sum < min || sum > max) {
			return false;
		}
		int N = arr.length;
		boolean[][] dp = new boolean[N][max - min + 1];
		dp[0][-min] = true;
		dp[0][arr[0] - min] = true;
		for (int i = 1; i < N; i++) {
			for (int j = min; j <= max; j++) {
				dp[i][j - min] = dp[i - 1][j - min];
				int next = j - min - arr[i];
				dp[i][j - min] |= (next >= 0 && next <= max - min && dp[i - 1][next]);
			}
		}
		return dp[N - 1][sum - min];
	}

	// arr中的值可能为正，可能为负，可能为0
	// 自由选择arr中的数字，能不能累加得到sum
	// 分治的方法
	// 如果arr中的数值特别大，动态规划方法依然会很慢
	// 此时如果arr的数字个数不算多(40以内)，哪怕其中的数值很大，分治的方法也将是最优解
	public static boolean isSum4(int[] arr, int sum) {
		if (sum == 0) {
			return true;
		}
		if (arr == null || arr.length == 0) {
			return false;
		}
		if (arr.length == 1) {
			return arr[0] == sum;
		}
		int N = arr.length;
		int mid = N >> 1;
		HashSet<Integer> leftSum = new HashSet<>();
		HashSet<Integer> rightSum = new HashSet<>();
		process4(arr, 0, mid, 0, leftSum);
		process4(arr, mid, N, 0, rightSum);
		for (int l : leftSum) {
			if (rightSum.contains(sum - l)) {
				return true;
			}
		}
		return false;
	}

	public static void process4(int[] arr, int i, int end, int pre, HashSet<Integer> ans) {
		if (i == end) {
			ans.add(pre);
		} else {
			process4(arr, i + 1, end, pre, ans);
			process4(arr, i + 1, end, pre + arr[i], ans);
		}
	}

	// 为了测试
	// 生成长度为len的随机数组
	// 值在[-max, max]上随机
	public static int[] randomArray(int len, int max) {
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * ((max << 1) + 1)) - max;
		}
		return arr;
	}

	// 对数器验证所有方法
	public static void main(String[] args) {
		int N = 20;
		int M = 100;
		int testTime = 100000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int size = (int) (Math.random() * (N + 1));
			int[] arr = randomArray(size, M);
			int sum = (int) (Math.random() * ((M << 1) + 1)) - M;
			boolean ans1 = isSum1(arr, sum);
			boolean ans2 = isSum2(arr, sum);
			boolean ans3 = isSum3(arr, sum);
			boolean ans4 = isSum4(arr, sum);
			if (ans1 ^ ans2 || ans3 ^ ans4 || ans1 ^ ans3) {
				System.out.println("出错了！");
				System.out.print("arr : ");
				for (int num : arr) {
					System.out.print(num + " ");
				}
				System.out.println();
				System.out.println("sum : " + sum);
				System.out.println("方法一答案 : " + ans1);
				System.out.println("方法二答案 : " + ans2);
				System.out.println("方法三答案 : " + ans3);
				System.out.println("方法四答案 : " + ans4);
				break;
			}
		}
		System.out.println("测试结束");
	}

}
