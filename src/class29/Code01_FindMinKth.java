package class29;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code01_FindMinKth {

	public static class MaxHeapComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}

	}

	// 利用大根堆，时间复杂度O(N*logK)
	public static int minKth1(int[] arr, int k) {
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new MaxHeapComparator());
		for (int i = 0; i < k; i++) {
			maxHeap.add(arr[i]);
		}
		for (int i = k; i < arr.length; i++) {
			if (arr[i] < maxHeap.peek()) {
				maxHeap.poll();
				maxHeap.add(arr[i]);
			}
		}
		return maxHeap.peek();
	}

	// 改写快排，时间复杂度O(N)
	// k >= 1
	public static int minKth2(int[] array, int k) {
		int[] arr = copyArray(array);
		return process2(arr, 0, arr.length - 1, k - 1);
	}

	public static int[] copyArray(int[] arr) {
		int[] ans = new int[arr.length];
		for (int i = 0; i != ans.length; i++) {
			ans[i] = arr[i];
		}
		return ans;
	}

	// arr 第k小的数
	// process2(arr, 0, N-1, k-1)
	// arr[L..R]  范围上，如果排序的话(不是真的去排序)，找位于index的数
	// index [L..R]
	public static int process2(int[] arr, int L, int R, int index) {
		if (L == R) { // L = =R ==INDEX
			return arr[L];
		}
		// 不止一个数  L +  [0, R -L]
		int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
		int[] range = partition(arr, L, R, pivot);
		if (index >= range[0] && index <= range[1]) {
			return arr[index];
		} else if (index < range[0]) {
			return process2(arr, L, range[0] - 1, index);
		} else {
			return process2(arr, range[1] + 1, R, index);
		}
	}

	public static int[] partition(int[] arr, int L, int R, int pivot) {
		int less = L - 1;
		int more = R + 1;
		int cur = L;
		while (cur < more) {
			if (arr[cur] < pivot) {
				swap(arr, ++less, cur++);
			} else if (arr[cur] > pivot) {
				swap(arr, cur, --more);
			} else {
				cur++;
			}
		}
		return new int[] { less + 1, more - 1 };
	}

	public static void swap(int[] arr, int i1, int i2) {
		int tmp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = tmp;
	}

	// 利用bfprt算法，时间复杂度O(N)
	public static int minKth3(int[] array, int k) {
		int[] arr = copyArray(array);
		return bfprt(arr, 0, arr.length - 1, k - 1);
	}

	// arr[L..R]  如果排序的话，位于index位置的数，是什么，返回
	public static int bfprt(int[] arr, int L, int R, int index) {
		if (L == R) {
			return arr[L];
		}
		// L...R  每五个数一组
		// 每一个小组内部排好序
		// 小组的中位数组成新数组
		// 这个新数组的中位数返回
		int pivot = medianOfMedians(arr, L, R);
		int[] range = partition(arr, L, R, pivot);
		if (index >= range[0] && index <= range[1]) {
			return arr[index];
		} else if (index < range[0]) {
			return bfprt(arr, L, range[0] - 1, index);
		} else {
			return bfprt(arr, range[1] + 1, R, index);
		}
	}

	// arr[L...R]  五个数一组
	// 每个小组内部排序
	// 每个小组中位数领出来，组成marr
	// marr中的中位数，返回
	public static int medianOfMedians(int[] arr, int L, int R) {
		int size = R - L + 1;
		int offset = size % 5 == 0 ? 0 : 1;
		int[] mArr = new int[size / 5 + offset];
		for (int team = 0; team < mArr.length; team++) {
			int teamFirst = L + team * 5;
			// L ... L + 4
			// L +5 ... L +9
			// L +10....L+14
			mArr[team] = getMedian(arr, teamFirst, Math.min(R, teamFirst + 4));
		}
		// marr中，找到中位数
		// marr(0, marr.len - 1,  mArr.length / 2 )
		return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
	}

	public static int getMedian(int[] arr, int L, int R) {
		insertionSort(arr, L, R);
		return arr[(L + R) / 2];
	}

	public static void insertionSort(int[] arr, int L, int R) {
		for (int i = L + 1; i <= R; i++) {
			for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
				swap(arr, j, j + 1);
			}
		}
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) (Math.random() * maxSize) + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * (maxValue + 1));
		}
		return arr;
	}

	public static void main(String[] args) {
		int testTime = 1000000;
		int maxSize = 100;
		int maxValue = 100;
		System.out.println("test begin");
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			int k = (int) (Math.random() * arr.length) + 1;
			int ans1 = minKth1(arr, k);
			int ans2 = minKth2(arr, k);
			int ans3 = minKth3(arr, k);
			if (ans1 != ans2 || ans2 != ans3) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
	}

}
