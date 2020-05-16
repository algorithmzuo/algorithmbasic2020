package class11;

public class Code09_NQueens {

	public static int num1(int n) {
		if (n < 1) {
			return 0;
		}
		// record[0] ?  record[1]  ?  record[2]
		int[] record = new int[n]; // record[i] -> i行的皇后，放在了第几列
		return process1(0, record, n);
	}

	// 潜台词：record[0..i-1]的皇后，任何两个皇后一定都不共行、不共列，不共斜线
	// 目前来到了第i行
	// record[0..i-1]表示之前的行，放了的皇后位置
	// n代表整体一共有多少行  0~n-1行
	// 返回值是，摆完所有的皇后，合理的摆法有多少种
	public static int process1(int i, int[] record, int n) {
		if (i == n) { // 终止行
			return 1;
		}
		// 没有到终止位置，还有皇后要摆
		int res = 0;
		for (int j = 0; j < n; j++) { // 当前行在i行，尝试i行所有的列  -> j
			// 当前i行的皇后，放在j列，会不会和之前(0..i-1)的皇后，不共行共列或者共斜线，
			// 如果是，认为有效
			// 如果不是，认为无效
			if (isValid(record, i, j)) {
				record[i] = j;
				res += process1(i + 1, record, n);
			}
		}
		return res;
	}

	// record[0..i-1]你需要看，record[i...]不需要看
	// 返回i行皇后，放在了j列，是否有效
	public static boolean isValid(int[] record, int i, int j) {
		for (int k = 0; k < i; k++) { // 之前的某个k行的皇后	
			// k, record[k]   i, j
			if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
				return false;
			}
		}
		return true;
	}

	// 请不要超过32皇后问题
	public static int num2(int n) {
		if (n < 1 || n > 32) {
			return 0;
		}
		// 如果你是13皇后问题，limit 最右13个1，其他都是0
		int limit = n == 32 ? -1 : (1 << n) - 1;
		return process2(limit, 0, 0, 0);
	}

	// limit 划定了问题的规模 -> 固定
	
	// colLim 列的限制，1的位置不能放皇后，0的位置可以
	// leftDiaLim 左斜线的限制，1的位置不能放皇后，0的位置可以
	// rightDiaLim 右斜线的限制，1的位置不能放皇后，0的位置可以
	public static int process2(
			int limit, 
			int colLim, 
			int leftDiaLim,
			int rightDiaLim) {
		if (colLim == limit) { // base case
			return 1;
		}
		// 所有可以放皇后的位置，都在pos上
		// colLim | leftDiaLim | rightDiaLim   -> 总限制
		// ~ (colLim | leftDiaLim | rightDiaLim) -> 左侧的一坨0干扰，右侧每个1，可尝试
		int pos = limit & ( ~(colLim | leftDiaLim | rightDiaLim) );
		int mostRightOne = 0;
		int res = 0;
		while (pos != 0) {
			// 其取出pos中，最右侧的1来，剩下位置都是0
			mostRightOne = pos & (~pos + 1);
			pos = pos - mostRightOne;
			res += process2(limit, 
					colLim | mostRightOne,
					(leftDiaLim | mostRightOne) << 1,
					(rightDiaLim | mostRightOne) >>> 1);
		}
		return res;
	}

	public static void main(String[] args) {
		int n = 15;

		long start = System.currentTimeMillis();
		System.out.println(num2(n));
		long end = System.currentTimeMillis();
		System.out.println("cost time: " + (end - start) + "ms");

		start = System.currentTimeMillis();
		System.out.println(num1(n));
		end = System.currentTimeMillis();
		System.out.println("cost time: " + (end - start) + "ms");

	}
}
