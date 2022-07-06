package class01;

public class Code06_BSAwesome {

	public static int getLessIndex(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1; // no exist
		}
		if (arr.length == 1 || arr[0] < arr[1]) {
			return 0;
		}
		if (arr[arr.length - 1] < arr[arr.length - 2]) {
			return arr.length - 1;
		}
		int left = 1;
		int right = arr.length - 2;
		int mid = 0;
		while (left < right) {
			mid = (left + right) / 2;
			if (arr[mid] > arr[mid - 1]) {
				right = mid - 1;
			} else if (arr[mid] > arr[mid + 1]) {
				left = mid + 1;
			} else {
				return mid;
			}
		}
		// 这里返回left，是因为while循环中一直有两个数，且循环中每次都将left置为较小的
		return left;
	}
	public static void main(String[] args) {
		int[] arr = {2,3,4,2,3,4,21,3,4,32,1,2};
		System.out.println(getLessIndex(arr));
	}
}
