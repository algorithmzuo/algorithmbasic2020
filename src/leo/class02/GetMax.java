package leo.class02;

import leo.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author Leo
 * @ClassName GetMax
 * @DATE 2020/11/21 7:52 下午
 * @Description
 */
public class GetMax {

    public static int getMax(int[] arr) {
        int max = process(arr, 0, arr.length - 1);
        return max;
    }

    private static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }

    public static int getMax1(int[] arr) {

        return process1(arr, 0, arr.length - 1);
    }

    private static int process1(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        int mid = l + ((r - l) >> 1);
        int leftMax = process1(arr, 0, mid);
        int rightMax = process1(arr, mid+1, r);
        return Math.max(leftMax, rightMax);
    }

    private static int testGetMax(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length - 1];
    }

    public static void main(String[] args){
        int maxSize = 5;
        int range = 100;
        int testTime = 5;
        System.out.println("Start!");

        for (int i = 0; i < testTime; i++) {
            int[] arr = ArrayUtil.randomAdjacentNotEqualArray(maxSize, range);
            int max = getMax1(arr);
            int testMax = testGetMax(arr);
            if (max != testMax) {
                System.out.println("fuck");
                break;
            }
        }
        System.out.println("End!");
    }


}
