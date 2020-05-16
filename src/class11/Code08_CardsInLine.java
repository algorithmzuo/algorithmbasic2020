package class11;

public class Code08_CardsInLine {

	public static int win1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		return Math.max(
				f(arr, 0, arr.length - 1),
				s(arr, 0, arr.length - 1)
				);
	}

	public static int f(int[] arr, int L, int R) {
		if (L == R) {
			return arr[L];
		}
		
		return Math.max(
				arr[L] + s(arr, L + 1, R),
				arr[R] + s(arr, L, R - 1)
				);
	}

	// i..j
	public static int s(int[] arr, int i, int j) {
		if (i == j) {
			return 0;
		}
		return Math.min(
				f(arr, i + 1, j), // arr[i]
				f(arr, i, j - 1)  // arr[j]
				);
	}

	public static int win2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int[][] f = new int[arr.length][arr.length];
		int[][] s = new int[arr.length][arr.length];
		for (int j = 0; j < arr.length; j++) {
			f[j][j] = arr[j];
			for (int i = j - 1; i >= 0; i--) {
				f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
				s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
			}
		}
		return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
	}

	public static void main(String[] args) {
		int[] arr = { 4,7,9,5 };
		// A 4 9
		// B 7 5
		System.out.println(f(arr,0,3));
		System.out.println(s(arr,0,3));

	}

}
