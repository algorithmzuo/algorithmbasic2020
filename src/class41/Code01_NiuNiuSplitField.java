package class41;

import java.util.*;

public class Code01_NiuNiuSplitField {

	// arr都是正数，0~i切的时候，怎么切最优，最优的结论生成一个数组返回
	public static int[] getAnswers(int[] arr) {
		if (arr == null || arr.length < 1) {
			return null;
		}
		// ans[i] 代表的含义：
		// arr[0..i]切两块的各种方案中，哪一种方案能让左右两部份的累加和最小值，尽量大
		int[] ans = new int[arr.length];
		ans[0] = 0;
		int best = 0;// 左 0..0 右 无
		for (int i = 1; i < arr.length; i++) {
			while (best < i - 1 && getSplit(arr, best, i) < getSplit(arr, best + 1, i)) {
				best++;
			}
			ans[i] = getSplit(arr, best, i);
		}
		return ans;
	}

	public static int getSum(int[] arr, int i, int j) {
		return 0;
	}

	// arr[0..j] 如果我把切的这一刀定在m位置，左0..m，右m+1..j
	public static int getSplit(int[] arr, int m, int j) {
		return 0;
	}

	public static int maxMinSumIn16(int[][] matrix) {
		if (matrix == null || matrix.length < 4 || matrix[0].length < 4) {
			return 0;
		}
		// help[i][j] 含义，左上角为(0,0) 右下角为(i,j)的子矩阵累加和是多少
		int[][] help = generateSumRecord(matrix);
		// 通过help，得到任何子矩阵的累加和
		int col = matrix[0].length;
		int res = Integer.MIN_VALUE;
		// 3个for循环在暴力枚举纵向三刀
		for (int c1 = 0; c1 < col - 3; c1++) {
			for (int c2 = c1 + 1; c2 < col - 2; c2++) {
				for (int c3 = c2 + 1; c3 < col - 1; c3++) {
					// c1 c2 c3
					res = Math.max(res, getBestDicision(help, c1, c2, c3));
				}
			}
		}
		return res;
	}

	// record[i][j] 左上角点0,0， 有效角点i、j , 该矩阵的累加和
	public static int[][] generateSumRecord(int[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		int[][] record = new int[row][col];
		record[0][0] = matrix[0][0];
		for (int i = 1; i < row; i++) {
			record[i][0] = record[i - 1][0] + matrix[i][0];
		}
		for (int j = 1; j < col; j++) {
			record[0][j] = record[0][j - 1] + matrix[0][j];
		}
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				record[i][j] = record[i][j - 1] + record[i - 1][j] - record[i - 1][j - 1] + matrix[i][j];
			}
		}
		return record;
	}

	// 忘掉原来的矩阵，只使用help
	// 竖着三刀固定，c1，c2，c3
	// 所有横着切三刀的可能性中，那种切法能得到最好的结果，把结果返回
	public static int getBestDicision(int[][] help, int c1, int c2, int c3) {
		// 0～i 切一刀，出来八块，最优指标，up[i]
		int[] up = getUpSplitArray(help, c1, c2, c3); // 0行~i行 切一刀，分8块，min max

		// i ~ N-1 切一刀，出来八块，最优指标，down[i]
		int[] down = getDownSplitArray(help, c1, c2, c3); // i~n-1 切一刀

		int res = Integer.MIN_VALUE;
		for (int mid = 1; mid < help.length - 2; mid++) {
			res = Math.max(res, Math.min(up[mid], down[mid + 1]));
		}
		return res;
	}

	public static int value(int[][] record, int c1, int c2, int c3, int prow, int crow) {
		int value1 = area(record, prow, 0, crow, c1);
		int value2 = area(record, prow, c1 + 1, crow, c2);
		int value3 = area(record, prow, c2 + 1, crow, c3);
		int value4 = area(record, prow, c3 + 1, crow, record[0].length - 1);
		return Math.min(Math.min(value1, value2), Math.min(value3, value4));
	}

	public static int area(int[][] record, int i1, int j1, int i2, int j2) {
		int all = record[i2][j2];
		int left = j1 > 0 ? record[i2][j1 - 1] : 0;
		int up = i1 > 0 ? record[i1 - 1][j2] : 0;
		int makeUp = (i1 > 0 && j1 > 0) ? record[i1 - 1][j1 - 1] : 0;
		return all - left - up + makeUp;
	}

	public static int[] getUpSplitArray(int[][] record, int c1, int c2, int c3) {
		int size = record.length;
		int[] up = new int[size];
		int split = 0;
		up[1] = Math.min(value(record, c1, c2, c3, 0, 0), value(record, c1, c2, c3, 1, 1));
		for (int i = 2; i < size; i++) {
			int minsMax = towSubMatrixMin(record, c1, c2, c3, 0, split, i);
			while (split < i) {
				if (split == i - 1) {
					break;
				}
				int moved = towSubMatrixMin(record, c1, c2, c3, 0, split + 1, i);
				if (moved < minsMax) {
					break;
				} else {
					minsMax = moved;
					split++;
				}
			}
			up[i] = minsMax;
		}
		return up;
	}

	public static int[] getDownSplitArray(int[][] record, int c1, int c2, int c3) {
		int size = record.length;
		int[] down = new int[size];
		int split = size - 1;
		down[size - 2] = Math.min(value(record, c1, c2, c3, size - 2, size - 2),
				value(record, c1, c2, c3, size - 1, size - 1));
		for (int i = size - 3; i >= 0; i--) {
			int minsMax = towSubMatrixMin(record, c1, c2, c3, i, split - 1, size - 1);
			while (split > i) {
				if (split == i + 1) {
					break;
				}
				int moved = towSubMatrixMin(record, c1, c2, c3, i, split - 2, size - 1);
				if (moved < minsMax) {
					break;
				} else {
					minsMax = moved;
					split--;
				}
			}
			down[i] = minsMax;
		}
		return down;
	}

	public static int towSubMatrixMin(int[][] record, int c1, int c2, int c3, int i, int split, int j) {
		return Math.min(value(record, c1, c2, c3, i, split), value(record, c1, c2, c3, split + 1, j));
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			int m = in.nextInt();
			int[][] matrix = new int[n][m];
			for (int i = 0; i < n; i++) {
				char[] chas = in.next().toCharArray();
				for (int j = 0; j < m; j++) {
					matrix[i][j] = chas[j] - '0';
				}
			}
			System.out.println(maxMinSumIn16(matrix));
		}
		in.close();
	}

}