package class24;

import java.util.LinkedList;

// 测试链接：https://leetcode.com/problems/gas-station
public class Code03_GasStation {

	// 这个方法的时间复杂度O(N)，额外空间复杂度O(N)
	public static int canCompleteCircuit(int[] gas, int[] cost) {
		boolean[] good = goodArray(gas, cost);
		for (int i = 0; i < gas.length; i++) {
			if (good[i]) {
				return i;
			}
		}
		return -1;
	}

	public static boolean[] goodArray(int[] g, int[] c) {
		int N = g.length;
		int M = N << 1;
		int[] arr = new int[M];
		for (int i = 0; i < N; i++) {
			arr[i] = g[i] - c[i];
			arr[i + N] = g[i] - c[i];
		}
		for (int i = 1; i < M; i++) {
			arr[i] += arr[i - 1];
		}
		LinkedList<Integer> w = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			while (!w.isEmpty() && arr[w.peekLast()] >= arr[i]) {
				w.pollLast();
			}
			w.addLast(i);
		}
		boolean[] ans = new boolean[N];
		for (int offset = 0, i = 0, j = N; j < M; offset = arr[i++], j++) {
			if (arr[w.peekFirst()] - offset >= 0) {
				ans[i] = true;
			}
			if (w.peekFirst() == i) {
				w.pollFirst();
			}
			while (!w.isEmpty() && arr[w.peekLast()] >= arr[j]) {
				w.pollLast();
			}
			w.addLast(j);
		}
		return ans;
	}

}
