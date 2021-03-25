package class43;

// leetcode 464题
public class Code01_CanIWin {

	// 1~choose 拥有的数字
	// total 一开始的剩余
	// 返回先手会不会赢
	public static boolean canIWin0(int choose, int total) {
		if (total == 0) {
			return true;
		}
		if ((choose * (choose + 1) >> 1) < total) {
			return false;
		}
		int[] arr = new int[choose];
		for (int i = 0; i < choose; i++) {
			arr[i] = i + 1;
		}
		// arr[i] != -1 表示arr[i]这个数字还没被拿走
		// arr[i] == -1 表示arr[i]这个数字已经被拿走
		// 集合，arr，1~choose
		return process(arr, total);
	}

	// 当前轮到先手拿，
	// 先手只能选择在arr中还存在的数字，
	// 还剩rest这么值，
	// 返回先手会不会赢
	public static boolean process(int[] arr, int rest) {
		if (rest <= 0) {
			return false;
		}
		// 先手去尝试所有的情况
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != -1) {
				int cur = arr[i];
				arr[i] = -1;
				boolean next = process(arr, rest - cur);
				arr[i] = cur;
				if (!next) {
					return true;
				}
			}
		}
		return false;
	}

	// 这个是暴力尝试，思路是正确的，超时而已
	public static boolean canIWin1(int choose, int total) {
		if (total == 0) {
			return true;
		}
		if ((choose * (choose + 1) >> 1) < total) {
			return false;
		}
		return process1(choose, 0, total);
	}

	// 当前轮到先手拿，
	// 先手可以拿1~choose中的任何一个数字
	// status   i位如果为0，代表没拿，当前可以拿
	//          i位为1，代表已经拿过了，当前不能拿
	// 还剩rest这么值，
	// 返回先手会不会赢
	public static boolean process1(int choose, int status, int rest) {
		if (rest <= 0) {
			return false;
		}
		for (int i = 1; i <= choose; i++) {
			if (((1 << i) & status) == 0) { // i 这个数字，是此时先手的决定！
				if (!process1(choose, (status | (1 << i)), rest - i)) {
					return true;
				}
			}
		}
		return false;
	}

	// 暴力尝试改动态规划而已
	public static boolean canIWin2(int choose, int total) {
		if (total == 0) {
			return true;
		}
		if ((choose * (choose + 1) >> 1) < total) {
			return false;
		}
		int[] dp = new int[1 << (choose + 1)];
		// dp[status] == 1  true
		// dp[status] == -1  false
		// dp[status] == 0  process(status) 没算过！去算！
		return process2(choose, 0, total, dp);
	}

	// 为什么明明status和rest是两个可变参数，却只用status来代表状态(也就是dp)
	// 因为选了一批数字之后，得到的和一定是一样的，所以rest是由status决定的，所以rest不需要参与记忆化搜索
	public static boolean process2(int choose, int status, int rest, int[] dp) {
		if (dp[status] != 0) {
			return dp[status] == 1 ? true : false;
		}
		boolean ans = false;
		if (rest > 0) {
			for (int i = 1; i <= choose; i++) {
				if (((1 << i) & status) == 0) {
					if (!process2(choose, (status | (1 << i)), rest - i, dp)) {
						ans = true;
						break;
					}
				}
			}
		}
		dp[status] = ans ? 1 : -1;
		return ans;
	}

}
