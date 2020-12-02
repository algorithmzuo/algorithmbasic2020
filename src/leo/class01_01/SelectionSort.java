package leo.class01_01;

import leo.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author Leo
 * @ClassName SelectionSort
 * @DATE 2020/11/11 6:24 下午
 * @Description 选择排序
 */
public class SelectionSort {

    /**
     * 功能描述 : 选择排序 O(n²)
     * @author Leo
     * @date 2020/11/11 6:38 下午
     * @param arr
     * @return void
     */
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] > arr[minIndex] ? minIndex : j;
            }
            swap(arr, i, minIndex);
        }
    }


    /**
     * 功能描述 :
     * @author Leo
     * @date 2020/11/11 9:15 下午
     * @param arr
     * @return void
     */
    public static void selectionSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }


    public static void selectionSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                minIndex = arr[j] > arr[minIndex] ? minIndex : j;
            }
            swap(arr, i, minIndex);
        }
    }


    public static void selectionSort4(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    public static void selectionSort5(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return;
        }
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }


    public static void selectionSort6(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }


    public static void selectionSort7(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    public static void selectionSort8(int[] arr) {
        if (arr == null || arr.length < 0) {
            return;
        }
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] > arr[j ]) {
                    swap(arr, i, j );
                }
            }

        }
    }

    public static void selectionSort9(int[] arr) {
        if (arr == null || arr.length <=1) {
            return;
        }
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    public static void selectionSort10(int[] arr){
        if (arr.length <= 0 || arr == null) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    public static void selectionSort11(int[] arr) {
        if (arr.length == 0 || arr == null) {
            return;
        }
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }

            }
        }
    }

    public static void selectionSort12(int[] arr) {
        if (arr.length == 0 || arr == null) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr,i,j);
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
        arr[j] = arr[j] ^ arr[i];
        arr[i] = arr[j] ^ arr[i];
        arr[j] = arr[j] ^ arr[i];
    }


    public static void main(String[] args){
        int maxSize = 100;
        int range = 50;
        int testOfTime = 1000;
        boolean succeed = true;
        for (int i = 0; i < testOfTime; i++) {
            int[] arr = ArrayUtil.randomArray(maxSize, range);
            int[] anotherArr = ArrayUtil.copyArray(arr);
            selectionSort12(arr);
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
