package class42;

public class Code01_ThrowChessPiecesProblem {

	public static int solution1(int nLevel, int kChess) {
		if (nLevel < 1 || kChess < 1) {
			return 0;
		}
		return Process1(nLevel, kChess);
	}

	// rest还剩多少层楼需要去验证
	// k还有多少颗棋子能够使用
	// 一定要验证出最高的不会碎的楼层！但是每次都是坏运气。
	// 返回至少需要扔几次？
	public static int Process1(int rest, int k) {
		if (rest == 0) {
			return 0;
		}
		if (k == 1) {
			return rest;
		}
		int min = Integer.MAX_VALUE;
		for (int i = 1; i != rest + 1; i++) { // 第一次扔的时候，仍在了i层
			min = Math.min(min,
					Math.max(Process1(i - 1, k - 1), Process1(rest - i, k)));
		}
		return min + 1;
	}

	public static int solution2(int nLevel, int kChess) {
		if (nLevel < 1 || kChess < 1) {
			return 0;
		}
		if (kChess == 1) {
			return nLevel;
		}
		int[][] dp = new int[nLevel + 1][kChess + 1];
		for (int i = 1; i != dp.length; i++) {
			dp[i][1] = i;
		}
		for (int i = 1; i != dp.length; i++) {
			for (int j = 2; j != dp[0].length; j++) {
				int min = Integer.MAX_VALUE;
				for (int k = 1; k != i + 1; k++) {
					min = Math.min(min, Math.max(dp[k - 1][j - 1], dp[i - k][j]));
				}
				dp[i][j] = min + 1;
			}
		}
		return dp[nLevel][kChess];
	}

	public static int solution3(int nLevel, int kChess) {
		if (nLevel < 1 || kChess < 1) {
			return 0;
		}
		if (kChess == 1) {
			return nLevel;
		}
		int[] preArr = new int[nLevel + 1];
		int[] curArr = new int[nLevel + 1];
		for (int i = 1; i != curArr.length; i++) {
			curArr[i] = i;
		}
		for (int i = 1; i != kChess; i++) {
			int[] tmp = preArr;
			preArr = curArr;
			curArr = tmp;
			for (int j = 1; j != curArr.length; j++) {
				int min = Integer.MAX_VALUE;
				for (int k = 1; k != j + 1; k++) {
					min = Math.min(min, Math.max(preArr[k - 1], curArr[j - k]));
				}
				curArr[j] = min + 1;
			}
		}
		return curArr[curArr.length - 1];
	}

	public static int solution4(int nLevel, int kChess) {
		if (nLevel < 1 || kChess < 1) {
			return 0;
		}
		if (kChess == 1) {
			return nLevel;
		}
		int[][] dp = new int[nLevel + 1][kChess + 1];
		for (int i = 1; i != dp.length; i++) {
			dp[i][1] = i;
		}
		int[] cands = new int[kChess + 1];
		for (int i = 1; i != dp[0].length; i++) {
			dp[1][i] = 1;
			cands[i] = 1;
		}
		for (int i = 2; i < nLevel + 1; i++) {
			for (int j = kChess; j > 1; j--) {
				int min = Integer.MAX_VALUE;
				int minEnum = cands[j];
				int maxEnum = j == kChess ? i / 2 + 1 : cands[j + 1];
				for (int k = minEnum; k < maxEnum + 1; k++) {
					int cur = Math.max(dp[k - 1][j - 1], dp[i - k][j]);
					if (cur <= min) {
						min = cur;
						cands[j] = k;
					}
				}
				dp[i][j] = min + 1;
			}
		}
		return dp[nLevel][kChess];
	}

	public static int solution5(int nLevel, int kChess) {
		if (nLevel < 1 || kChess < 1) {
			return 0;
		}
		int bsTimes = log2N(nLevel) + 1;
		if (kChess >= bsTimes) {
			return bsTimes;
		}
		int[] dp = new int[kChess];
		int res = 0;
		while (true) {
			res++;
			int previous = 0;
			for (int i = 0; i < dp.length; i++) {
				int tmp = dp[i];
				dp[i] = dp[i] + previous + 1;
				previous = tmp;
				if (dp[i] >= nLevel) {
					return res;
				}
			}
		}
	}

	public static int log2N(int n) {
		int res = -1;
		while (n != 0) {
			res++;
			n >>>= 1;
		}
		return res;
	}

	public static void main(String[] args) {
		System.out.println(solution1(21, 2));
		System.out.println(solution2(21, 2));
		System.out.println(solution3(21, 2));
		System.out.println(solution4(21, 2));
		System.out.println(solution5(21, 2));

		System.out.println("==============");

		System.out.println(solution2(105, 2));
		System.out.println(solution3(105, 2));
		System.out.println(solution4(105, 2));
		System.out.println(solution5(105, 2));

		System.out.println("==============");

		System.out.println(solution2(3000, 10));
		System.out.println(solution3(3000, 10));
		System.out.println(solution4(3000, 10));
		System.out.println(solution5(3000, 10));

		System.out.println("==============");

		System.out.println(solution2(6884, 5));
		System.out.println(solution3(6884, 5));
		System.out.println(solution4(6884, 5));
		System.out.println(solution5(6884, 5));

		System.out.println("==============");

		System.out.println(solution2(6885, 5));
		System.out.println(solution3(6885, 5));
		System.out.println(solution4(6885, 5));
		System.out.println(solution5(6885, 5));

		System.out.println("==============");

		int nLevel = 100000000;
		int kChess = 10;
		long start = System.currentTimeMillis();
		System.out.println(solution5(nLevel, kChess));
		long end = System.currentTimeMillis();
		System.out.println("cost time: " + (end - start) + " ms");

	}

}
