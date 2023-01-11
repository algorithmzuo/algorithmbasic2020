package class08;

// 这是希尔排序，课上没有讲，因为比较简单，有兴趣的同学可以看一下
// 今天在面试场上也基本不怎么问了
// 这个排序就是调整步长的插入排序，也可以认为是插入排序的小改进版本
// 改变不了时间复杂度，只是优化了常数时间
// 我还写了和插入排序的性能对比
public class Code05_ShellSort {

	public static void shellSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		// 步长依次为5、2、1
		// 在插入排序中，一步一步往前交换，直到左边的数<=当前的数，停止
		// 在希尔排序中，如果步长依次为5、2、1
		// 那么来到每个数，每次跳5步往前交换，直到往前5步的数<=当前的数，停止
		// 然后再来到每个数，每次跳3步往前交换，直到往前3步的数<=当前的数，停止
		// 然后再来到每个数，每次跳1步往前交换，直到往前1步的数<=当前的数，停止
		int[] step = { 5, 2, 1 };
		for (int s = 0; s < step.length; s++) {
			for (int i = 0; i < arr.length; i++) {
				for (int j = i - step[s]; j >= 0 && arr[j] > arr[j + step[s]]; j -= step[s]) {
					swap(arr, j, j + step[s]);
				}
			}
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// 为了测试
	public static void insertionSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		// 不只1个数
		for (int i = 1; i < arr.length; i++) { // 0 ~ i 做到有序
			for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
				swap(arr, j, j + 1);
			}
		}
	}

	// 为了测试
	public static int[] generateRandomArray(int len, int maxValue) {
		int[] arr = new int[len];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// 为了测试
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// 为了测试
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// 为了测试
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 为了测试
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * maxSize);
			int[] arr1 = generateRandomArray(len, maxValue);
			int[] arr2 = copyArray(arr1);
			shellSort(arr1);
			insertionSort(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "功能测试通过！" : "功能测试不通过！");
		int len = 100000;
		System.out.println("数据样本长度 : " + len);
		int[] arr1 = generateRandomArray(len, maxValue);
		int[] arr2 = copyArray(arr1);
		long start = 0;
		long end = 0;
		start = System.currentTimeMillis();
		shellSort(arr1);
		end = System.currentTimeMillis();
		System.out.println("希尔排序运行时间 : " + (end - start) + " 毫秒");
		start = System.currentTimeMillis();
		insertionSort(arr2);
		end = System.currentTimeMillis();
		System.out.println("插入排序运行时间 : " + (end - start) + " 毫秒");
	}

}
