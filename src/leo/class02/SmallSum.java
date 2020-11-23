package leo.class02;

import leo.util.ArrayUtil;

/**
 * @author Leo
 * @ClassName SmallSum
 * @DATE 2020/11/23 10:46 上午
 * @Description 小和问题
 */
class SmallSum {

    public static int smallSum(int[] arr) {
        if (arr.length == 0 || arr == null) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int L, int R) {

        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);

        return process(arr, L, mid)
                + process(arr, mid + 1, R)
                + merge(arr, L, mid, R);
    }

    /**
     * 左<右,小和=(右总长-有下标+1)*左;
     * 左<右 左进入help数组,左移动下标,
     * 左等于右或左大于右,右进help数组,右移动下标.
     */
    private static int merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int p1 = l;
        int p2 = m + 1;
        int res = 0;
        int i = 0;
        while (p1 <= m && p2 <= r) {
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;

    }


}

class SmallSum1{

    public static int smallSum(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {

        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid)
                + process(arr, mid + 1, r)
                + merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int l, int m, int r) {
        int p1 = l;
        int p2 = m+1;
        int i = 0;
        int[] help = new int[r - l + 1];
        int res = 0;
        while (p1 <= m && p2 <= r) {
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }

}

class TestSmallSum{
    public static int smallSum(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
        return res;
    }


    public static void swap(int[] arr, int i, int j) {
        if (i == j||arr[i]==arr[j]) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}

class TestMain {

    public static void main(String[] args){
        int testTime = 1000;
        int range = 50;
        int maxSize = 100;
        System.out.println("start!");

        for (int i = 0; i < testTime; i++) {
            int[] arr = ArrayUtil.randomArray(maxSize, range);
            int[] copyArray = ArrayUtil.copyArray(arr);
            int sum = SmallSum1.smallSum(arr);
            int testSum = TestSmallSum.smallSum(copyArray);
            if (testSum != sum) {
                System.out.println("sum :" + sum + ", testSum : " + testSum);
                break;
            }
            if (!ArrayUtil.isEqual(arr, copyArray)) {
                System.out.println("arr :" + arr );
                System.out.println("copyArray : " + copyArray);
                break;
            }
        }
        System.out.println("end!");


    }
}
