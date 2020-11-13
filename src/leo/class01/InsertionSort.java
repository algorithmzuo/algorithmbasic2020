package leo.class01;

import leo.util.ArrayUtil;

import java.util.Arrays;
/**
 * @author Leo
 * @ClassName InsertionSort
 * @DATE 2020/11/12 9:21 上午
 * @Description
 */
public class InsertionSort {


    /**
     * 功能描述 : 插入排序
     * @author Leo
     * @date 2020/11/12 9:58 上午
     * @param arr
     * @throw
     * @return void
     */
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    public static void insertionSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >=0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }

    }


    public static void insertionSort2(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    public static void insertionSort3(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }


    private static void swap(int[] arr, int i, int j) {
        if (arr == null
                || arr.length < 2
                || arr.length <= i
                || arr.length <= j) {
            return;
        }

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];


    }


    public static void main(String[] args){
        int maxSize = 50;
        int range = 80;
        int testOfTime = 100000;
        boolean succeed = true;
        for (int i = 0; i < testOfTime; i++) {
            int[] arr = ArrayUtil.randomArray(maxSize, range);
            int[] anotherArr = ArrayUtil.copyArray(arr);
            insertionSort3(arr);
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
