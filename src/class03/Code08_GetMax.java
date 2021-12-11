package class03;

import java.util.Arrays;

public class Code08_GetMax {

	// 求arr中的最大值
	public static int getMax(int[] arr) {
		return process(arr, 0, arr.length - 1);
	}

	// arr[L..R]范围上求最大值  L ... R   N
	public static int process(int[] arr, int L, int R) {
		// arr[L..R]范围上只有一个数，直接返回，base case
		if (L == R) { 
			return arr[L];
		}
		// L...R 不只一个数
		// mid = (L + R) / 2
		int mid = L + ((R - L) >> 1); // 中点   	1
		int leftMax = process(arr, L, mid);
		int rightMax = process(arr, mid + 1, R);
		return Math.max(leftMax, rightMax);
	}
	private static int[] generateArr(int maxValue,int maxSize){
		int[] arr = new int[(int)(Math.random()*maxSize)];
		for (int i = 0;i < arr.length;i++){
			arr[i] = (int)(Math.random()*(maxValue +1)) - (int)(Math.random() * maxValue);
		}
		return arr;
	}

	public static void main(String[] args) {
		int testTime = 10;
		int maxValue = 100;
		int maxSize = 10;
		for (int i = 0;i < testTime;i++){
			int[] arr = generateArr(maxValue,maxSize);

			if (!Integer.valueOf(getMax(arr)).equals(Arrays.stream(arr).max().getAsInt())){
				System.out.println(getMax(arr));
				System.out.println(Arrays.stream(arr).max());
				System.out.println("function has problem");
			}
		}
		System.out.println("success");
	}

}
