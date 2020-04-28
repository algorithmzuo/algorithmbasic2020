package class12;

public class Code04_CardsInLine {

	public static int win1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
	}

	public static int f(int[] arr, int L, int R) {
		if (L == R) {
			return arr[L];
		}
		return Math.max(arr[L] + s(arr, L + 1, R), arr[R] + s(arr, L, R - 1));
	}

	public static int s(int[] arr, int L, int R) {
		if (L == R) {
			return 0;
		}
		return Math.min(f(arr, L + 1, R), f(arr, L, R - 1));
	}

	public static int windp(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int[][] f = new int[N][N];
		int[][] s = new int[N][N];
		for (int i = 0; i < N; i++) {
			f[i][i] = arr[i];
		}
		// 0,0 右下方移动
		// 0,1
		// 0,2
		// 0,N-1
		for (int col = 1; col < N; col++) {
			// 对角线出发位置(0,col)
			int L = 0;
			int R = col;
			while (L < N && R < N) {
				f[L][R] = Math.max(arr[L] + s[L + 1][R], arr[R] + s[L][R - 1]);
				s[L][R] = Math.min(f[L + 1][R], f[L][R - 1]);
				L++;
				R++;
			}
		}
		return Math.max(f[0][N - 1], s[0][N - 1]);
	}

	public static void main(String[] args) {
		int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
		System.out.println(win1(arr));
		System.out.println(windp(arr));

	}

}
