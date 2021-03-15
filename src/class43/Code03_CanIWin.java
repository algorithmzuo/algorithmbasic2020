package class43;

// leetcode 464题
public class Code03_CanIWin {

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

	public static boolean process1(int choose, int status, int rest) {
		if (rest <= 0) {
			return false;
		}
		for (int i = 1; i <= choose; i++) {
			if (((1 << i) & status) == 0) {
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
