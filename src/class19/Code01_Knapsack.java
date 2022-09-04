package class19;

/**
 * 题目：给定两个长度都为N的数组weights和values
 * weights[i] 和values[i]分别代表i号物品的重量和价值
 * 给定一个证数bag，表示一个载重bag的袋子
 * 你装的物品不能超过这个重量
 * 返回你能装下物品的价值最多是多少
 */

/***
 * 动态规划要从尝试入手，从左往右的尝试模型
 * 这里采用暴力枚举，所以这里的尝试思路就是，比如有三个货物[0,1,2],一号货物要或不要，一号要的情况下，二号要或不要，依次展开，就是一个二叉树
 * 那么，这里需要用递归表示出来
 */
public class Code01_Knapsack {

	// 所有的货，重量和价值，都在w和v数组里
	// 为了方便，其中没有负数
	// bag背包容量，不能超过这个载重
	// 返回：不超重的情况下，能够得到的最大价值
	public static int maxValue(int[] w, int[] v, int bag) {
		if (w == null || v == null || w.length != v.length || w.length == 0) {
			return 0;
		}
		// 尝试函数！
		return process(w, v, 0, bag);
	}

	// index 0~N
	// rest 负~bag
	public static int process(int[] w, int[] v, int index, int rest) {
		if (rest < 0) {
			return -1;
		}
		// 越界了，那么重量返回0
		if (index == w.length) {
			return 0;
		}
		int p1 = process(w, v, index + 1, rest);
		int p2 = 0;
		// 如果rest - w[index]是小于0的，那么此次选择就是无效的
		// 这里设置无效解，那么后面递归时需要区分下
		int next = process(w, v, index + 1, rest - w[index]);
		if (next != -1) {
			p2 = v[index] + next;
		}
		return Math.max(p1, p2);
	}


	/**
	 * 改动态规划一定要有利可图，即有重复解
	 * @param w
	 * @param v
	 * @param bag
	 * @return
	 */
	public static int dp(int[] w, int[] v, int bag) {
		if (w == null || v == null || w.length != v.length || w.length == 0) {
			return 0;
		}
		int N = w.length;
		int[][] dp = new int[N + 1][bag + 1];
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= bag; rest++) {
				int p1 = dp[index + 1][rest];
				int p2 = 0;
				int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
				if (next != -1) {
					p2 = v[index] + next;
				}
				dp[index][rest] = Math.max(p1, p2);
			}
		}
		return dp[0][bag];
	}

	public static void main(String[] args) {
		int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
		int[] values = { 5, 6, 3, 19, 12, 4, 2 };
		int bag = 15;
		System.out.println(maxValue(weights, values, bag));
		System.out.println(dp(weights, values, bag));
	}

}
