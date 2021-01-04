package leo.class04;


import leo.util.ArrayUtil;

import java.util.Arrays;


/**
 * @author Leo
 * @ClassName MergeSort
 * @DATE 2020/11/21 9:21 下午
 * @Description 归并排序
 */
public class MergeSort {


    /**
     * @author Leo
     * @ClassName MergeSort
     * @DATE 2020/11/21 9:21 下午
     * @Description 递归版归并排序
     */
    public static class Recursion {

        /**
         * 功能描述 : 递归版归并排序
         *
         * @param arr
         * @return void
         * @author Leo
         * @date 2020/11/21 9:23 下午
         */
        public static void mergeSort(int[] arr) {
            if (arr == null || arr.length < 2) {
                return;
            }
            process(arr, 0, arr.length - 1);
        }

        private static void process(int[] arr, int L, int R) {
            if (L == R) {
                return;
            }
            int mid = L + ((R - L) >> 1);
            process(arr, L, mid);
            process(arr, mid + 1, R);
            merge(arr, L, mid, R);
        }

        /**
         * 功能描述 : 合并
         *
         * @param arr 数组
         * @param L   左面第一个下标
         * @param M   中间下标
         * @param R   右面最后一个下标
         * @return void
         * @author Leo
         * @date 2020/11/21 9:28 下午
         */
        private static void merge(int[] arr, int L, int M, int R) {
            int[] help = new int[R - L + 1];
            int i = 0;
            int p1 = L;
            int p2 = M + 1;
            while (p1 <= M && p2 <= R) {
                help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
            }
            while (p1 <= M) {
                help[i++] = arr[p1++];
            }
            while (p2 <= R) {
                help[i++] = arr[p2++];
            }

            for (i = 0; i < help.length; i++) {
                arr[L + i] = help[i];
            }

        }

    }

    public static class Recursion1 {
        public static void mergeSort(int[] arr) {
            if (arr.length < 2 || arr == null) {
                return;
            }
            process(arr, 0, arr.length - 1);

        }

        private static void process(int[] arr, int L, int R) {
            if (L == R) {
                return;
            }
            int M = L + ((R - L) >> 1);
            process(arr, L, M);
            process(arr, M + 1, R);
            merge(arr, L, M, R);
        }

        private static void merge(int[] arr, int L, int M, int R) {
            int p1 = L;
            int p2 = M + 1;
            int i = 0;
            int[] help = new int[R - L + 1];
            while (p1 <= M && p2 <= R) {
                help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
            }
            while (p1 <= M) {
                help[i++] = arr[p1++];
            }
            while (p2 <= R) {
                help[i++] = arr[p2++];
            }
            for (i = 0; i < help.length; i++) {
                arr[L + i] = help[i];
            }

        }


    }

    public static class Recursion2 {
        public static void mergeSort(int[] arr) {
            if (arr.length < 2 || arr == null) {
                return;
            }
            process(arr, 0, arr.length - 1);
        }

        private static void process(int[] arr, int l, int r) {
            if (l == r) {
                return;
            }
            int mid = l + ((r - l) >> 1);
            process(arr, l, mid);
            process(arr, mid + 1, r);
            merge(arr, l, mid, r);
        }

        private static void merge(int[] arr, int l, int mid, int r) {
            if (l == r) {
                return;
            }
            int p1 = l;
            int p2 = mid + 1;
            int[] help = new int[r - l + 1];
            int i = 0;
            while (p1 <= mid && p2 <= r) {
                help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
            }
            while (p1 <= mid) {
                help[i++] = arr[p1++];
            }
            while (p2 <= r) {
                help[i++] = arr[p2++];
            }
            for (i = 0; i < help.length; i++) {
                arr[l + i] = help[i];
            }
        }
    }

    public static class Recursion3 {
        public static void mergeSort(int[] arr) {
            if (arr.length < 2 || arr == null) {
                return;
            }
            process(arr, 0, arr.length - 1);
        }

        public static void process(int[] arr, int l, int r) {
            if (l >= r) {
                return;
            }
            int m = l + ((r - l) >> 1);
            process(arr, l, m);
            process(arr, m + 1, r);
            merge(arr, l, m, r);
        }

        private static void merge(int[] arr, int l, int m, int r) {
            int p1 = l;
            int p2 = m + 1;
            int[] help = new int[r - l + 1];
            int i = 0;
            while (p1 <= m && p2 <= r) {
                help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
            }
            while (p1 <= m) {
                help[i++] = arr[p1++];
            }
            while(p2<=r){
                help[i++] = arr[p2++];
            }
            for (i = 0; i < help.length; i++) {
                arr[l + i] = help[i];
            }

        }
    }

    /**
     * @author Leo
     * @ClassName MergeSort
     * @DATE 2020/11/21 9:21 下午
     * @Description 非递归版归并排序
     */
    public static class NonRecursive{

        public static void mergeSort(int[] arr) {
            if (arr.length < 2 || arr == null) {
                return;
            }
            int N = arr.length;
            int mergeSize = 1;
            while (mergeSize < N) {
                int L = 0;
                while (L < N) {
                    int M = mergeSize + L - 1;
                    if (M >= N) {
                        break;
                    }
                    int R = Math.min(M + mergeSize, N - 1);
                    merge(arr, L, M, R);
                    L = R + 1;
                }
                if (mergeSize > N / 2) {
                    break;
                }
                mergeSize <<= 1;

            }

        }
        private static void merge(int[] arr, int L, int M, int R) {
            if (L == R) {
                return;
            }
            int[] help = new int[R - L + 1];
            int p1 = L;
            int p2 = M + 1;
            int i = 0;
            while (p1 <= M && p2 <= R) {
                help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
            }
            while (p1 <= M) {
                help[i++] = arr[p1++];
            }
            while (p2 <= R) {
                help[i++] = arr[p2++];
            }
            for (i = 0; i < help.length; i++) {
                arr[L + i] = help[i];
            }
        }
    }

    public static class NonRecursive1 {

        public static void mergeSort(int[] arr) {
            if (arr.length < 2 || arr == null) {
                return;
            }
            int arrLength = arr.length;
            int mergeSize = 1;

            while (mergeSize < arrLength) {
                int L = 0;
                while (L < arrLength) {
                    int M = L + mergeSize - 1;
                    if (M >= arrLength) {
                        break;
                    }
                    int R = Math.min(M + mergeSize, arrLength - 1);
                    merge(arr, L, M, R);
                    L = R + 1;
                }
                //防越界
                if (mergeSize > arrLength / 2) {
                    break;
                }
                mergeSize <<= 1;
            }

        }

        private static void merge(int[] arr, int l, int m, int r) {
            if (l == r) {
                return;
            }
            int pl = l;
            int pr = m + 1;
            int[] help = new int[r - l + 1];
            int i = 0;
            while (pl <= m && pr <= r) {
                help[i++] = arr[pl] <= arr[pr] ? arr[pl++] : arr[pr++];
            }
            while (pl <= m) {
                help[i++] = arr[pl++];
            }
            while (pr <= r) {
                help[i++] = arr[pr++];
            }
            for (i = 0; i < help.length; i++) {
                arr[l + i] = help[i];
            }
        }

    }

    public static void main(String[] args){
        int maxSize = 50;
        int range = 80;
        int testOfTime = 10000;
        boolean succeed = true;
        for (int i = 0; i < testOfTime; i++) {
            int[] arr = ArrayUtil.randomArray(maxSize, range);
            int[] anotherArr = ArrayUtil.copyArray(arr);
            Recursion3.mergeSort(arr);
            Arrays.sort(anotherArr);
            if (!ArrayUtil.isEqual(arr, anotherArr)) {
                succeed = false;
                ArrayUtil.printArr(arr, "arr");
                ArrayUtil.printArr(anotherArr, "anotherArr");
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");


    }




}


