package leo.class02;

import java.util.function.IntPredicate;

/**
 * @author Leo
 * @ClassName BiggerThanRightTwice
 * @DATE 2020/11/24 2:10 下午
 * @Description 对于每个数num，求有多少个后面的数 * 2 依然<左面的数，求总个数
 */
class BiggerThanRightTwice {

    public static int biggerTwice(int[] arr) {
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
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int l, int m, int r) {
        int res = 0;
        int R = m;
        for (int i = l; i <= m; i++) {
            while (R + 1 <= r && arr[i] > (arr[R + 1] << 1)) {
                R++;
            }
            res += R - m;
        }
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
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

class BiggerThanRightTwice1 {

    public static int biggerTwice(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int  process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int l, int m, int r) {
        int res = 0;
        int windowR = m + 1;
        for (int i = l; i <= m; i++) {
            while (windowR <= r && arr[i] > (arr[windowR] << 1)) {
                windowR++;
            }
            res += windowR - m - 1;
        }
        int[] help = new int[r - l + 1];
        int p1 = l;
        int p2 = m + 1;
        int i = 0;
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while(p1<=m){
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

class BiggerThanRightTwice2 {
    public static int biggerTwice(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {
        if (l==r){
            return 0;
        }
        int m = l + ((r - l) >> 1);
        return process(arr, l, m) + process(arr, m + 1, r) + merge(arr, l, m, r);

    }

    private static int merge(int[] arr, int l, int m, int r) {

        int windowR = m + 1;
        int res = 0;
        for (int i = l; i <= m; i++) {
            while (windowR <= r && arr[i] > (arr[windowR] << 1)) {
                windowR++;
            }
            res += windowR - m - 1;
        }
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
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

class BiggerThanRightTwice3 {

    public static int biggerTwice(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = l + ((r - l) >> 1);
        return process(arr, l, m) + process(arr, m + 1, r) + merge(arr, l, m, r);
    }

    private static int merge(int[] arr, int l, int m, int r) {
        int res = 0;
        int windowR = m + 1;
        for (int i = l; i <= m; i++) {
            while (windowR <= r && arr[i] > (arr[windowR] << 1)) {
                windowR++;
            }
            res += windowR - m-1;
        }

        int p1 = l;
        int p2 = m + 1;
        int i = 0;
        int[] help = new int[r - l + 1];
        while (p1 <= m && p2 <= r) {
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


class TestBiggerThanRightTwice {
    public static int biggerTwice(int[] arr) {
        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j] << 1) {
                    res++;
                }
            }
        }
        return res;
    }
}

class Main{
    public static void main(String[] args){
        int testTime = 1000;
        int sizeMax = 10;
        int range = 60;
        System.out.println("start");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(sizeMax, range);
            int[] copyArray = copyArray(arr);
            int res = BiggerThanRightTwice3.biggerTwice(arr);
            int res1 = TestBiggerThanRightTwice.biggerTwice(copyArray);
            if (res != res1) {
                System.out.println("res : " + res + " " + " res1: " + res1);
                return;
            }

        }
        System.out.println("end");




    }

    public static int[] generateRandomArray(int sizeMax, int range) {
        int[] arr = new int[(int) (sizeMax * Math.random() + 1)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((range * Math.random() - 1) - (range * Math.random() - 1));
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        int[] copyArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copyArr[i] = arr[i];
        }
        return copyArr;
    }
}
