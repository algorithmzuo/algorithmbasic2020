package leo.class01;

import leo.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author Leo
 * @ClassName BubbleSort
 * @DATE 2020/11/11 9:05 下午
 * @Description 冒泡算法
 */
public class BubbleSort {

    /**
     * 功能描述 : 冒泡算法 O(n²)
     * 将最大的数放到后面
     * @author Leo
     * @date 2020/11/11 10:09 下午
     * @param arr
     * @return void
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = arr.length-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }

    }

    public static void bubbleSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }


    public static void bubbleSort3(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return;
        }
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void bubbleSort4(int[] arr)  {
        if (arr.length < 2 || arr == null) {
            return;
        }
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void bubbleSort5(int[] arr){
        if (arr.length < 2 || arr == null) {
            return;
        }
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }


    public static void bubbleSort6(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return;
        }
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void bubbleSort7(int[] arr) {
        if (arr.length == 0 || arr == null) {
            return;
        }
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void bubbleSort8(int[] arr) {
        if (arr.length == 0 || arr == null) {
            return;
        }
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * 功能描述 : 交换
     * @author Leo
     * @date 2020/11/11 6:34 下午
     * @param arr
     * @param i
     * @param j
     * @return void
     */
    private static void swap(int[] arr, int i, int j) {
        if (arr == null ||
                arr.length <= i ||
                arr.length <= j ||
                i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


    public static void main(String[] args){
        int maxSize = 50;
        int range = 20;
        int testOfTime = 1000;
        boolean succeed = true;
        for (int i = 0; i < testOfTime; i++) {
            int[] arr = ArrayUtil.randomArray(maxSize, range);
            int[] anotherArr = ArrayUtil.copyArray(arr);
            bubbleSort8(arr);
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
