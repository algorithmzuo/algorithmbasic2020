package leo.class08;

import leo.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author Leo
 * @ClassName RadixSort
 * @DATE 2020/12/2 3:55 下午
 * @Description 基数排序 要求必须是正整数
 * O(n*log10^max)
 * O(n)
 */
public class RadixSort {

    public static void radixSort(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return;
        }
        radixSort(arr, 0, arr.length - 1);
    }

    private static void radixSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int i, j = 0;
        int bits = maxBits(arr, l, r);
        final int radix  = 10;
        int[] help = new int[r - l + 1];
        for (int b = 1; b <= bits; b++) {
            int[] count = new int[radix];
            for (i = l; i <= r; i++) {
                j = getRemainder(arr[i], b);
                count[j]++;
            }
            for (i = 1; i < count.length; i++) {
                count[i] = count[i] + count[i - 1];
            }
            for (i = r; i >= l; i--) {
                j = getRemainder(arr[i], b);
                count[j]--;
                help[count[j]] = arr[i];
            }
            for (i = 0; i <= r; i++) {
                arr[i + l] = help[i];
            }
        }
    }

    private static int maxBits(int[] arr, int l, int r) {
        int res = 0;
        int max = Integer.MIN_VALUE;
        for (int i = l; i <= r; i++) {
            max = Math.max(max, arr[i]);
        }
        while (max != 0) {
            max /= 10;
            res++;
        }
        return res;
    }

    private static int getRemainder(int num, int b) {
        return ((num / (int) Math.pow(10, b - 1)) % 10);
    }

}

class RadixSortMain {

    public static void main(String[] args){
        int testTime = 1000;
        int range = 1000;
        int maxSize  = 40;
        System.out.println("start");

        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxSize, range);
            int[] copyArray = copyArray(arr);
            Arrays.sort(copyArray);
            RadixSort.radixSort(arr);
            if (!ArrayUtil.isEqual(arr, copyArray)) {
                System.out.println("fuck!");
            }
        }
        System.out.println("end");

    }

    public static int[] randomArray(int maxSize, int range) {
        int[] arr = new int[(int) (maxSize * Math.random()) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (range * Math.random()) + 1;
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        int[] copyArr = new int[arr.length];
        for (int i = 0; i < copyArr.length; i++) {
            copyArr[i] = arr[i];
        }
        return copyArr;
    }

}
