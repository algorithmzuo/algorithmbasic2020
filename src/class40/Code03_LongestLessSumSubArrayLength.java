package class40;

public class Code03_LongestLessSumSubArrayLength {

	public static int maxLengthAwesome(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int[] minSums = new int[arr.length];
		int[] minSumEnds = new int[arr.length];
		minSums[arr.length - 1] = arr[arr.length - 1];
		minSumEnds[arr.length - 1] = arr.length - 1;
		for (int i = arr.length - 2; i >= 0; i--) {
			if (minSums[i + 1] < 0) {
				minSums[i] = arr[i] + minSums[i + 1];
				minSumEnds[i] = minSumEnds[i + 1];
			} else {
				minSums[i] = arr[i];
				minSumEnds[i] = i;
			}
		}
		int end = 0;
		int sum = 0;
		int res = 0;
		// i是窗口的最左的位置，end扩出来的最右有效块儿的最后一个位置的，再下一个位置
		// end也是下一块儿的开始位置
		// 窗口：[i~end)
		for (int i = 0; i < arr.length; i++) {
			// while循环结束之后：
			// 1) 如果以i开头的情况下，累加和<=k的最长子数组是arr[i..end-1]，看看这个子数组长度能不能更新res；
			// 2) 如果以i开头的情况下，累加和<=k的最长子数组比arr[i..end-1]短，更新还是不更新res都不会影响最终结果；
			while (end < arr.length && sum + minSums[end] <= k) {
				sum += minSums[end];
				end = minSumEnds[end] + 1;
			}
			res = Math.max(res, end - i);
			if (end > i) { // 窗口内还有数 [i~end) [4,4)
				sum -= arr[i];
			} else { // 窗口内已经没有数了，说明从i开头的所有子数组累加和都不可能<=k
				end = i + 1;
			}
		}
		return res;
	}

	public static int maxLength(int[] arr, int k) {
		int[] h = new int[arr.length + 1];
		int sum = 0;
		h[0] = sum;
		for (int i = 0; i != arr.length; i++) {
			sum += arr[i];
			h[i + 1] = Math.max(sum, h[i]);
		}
		sum = 0;
		int res = 0;
		int pre = 0;
		int len = 0;
		for (int i = 0; i != arr.length; i++) {
			sum += arr[i];
			pre = getLessIndex(h, sum - k);
			len = pre == -1 ? 0 : i - pre + 1;
			res = Math.max(res, len);
		}
		return res;
	}

	public static int getLessIndex(int[] arr, int num) {
		int low = 0;
		int high = arr.length - 1;
		int mid = 0;
		int res = -1;
		while (low <= high) {
			mid = (low + high) / 2;
			if (arr[mid] >= num) {
				res = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return res;
	}

	// for test
	public static int[] generateRandomArray(int len, int maxValue) {
		int[] res = new int[len];
		for (int i = 0; i != res.length; i++) {
			res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
		}
		return res;
	}

	public static void main(String[] args) {
		System.out.println("test begin");
		for (int i = 0; i < 10000000; i++) {
			int[] arr = generateRandomArray(10, 20);
			int k = (int) (Math.random() * 20) - 5;
			if (maxLengthAwesome(arr, k) != maxLength(arr, k)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
	}

}
