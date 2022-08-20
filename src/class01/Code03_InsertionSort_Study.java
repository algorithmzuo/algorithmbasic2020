package class01;

import java.util.Arrays;

public class Code03_InsertionSort_Study {
    private static void insertionSort(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1;i < arr.length; i++) {
            for (int j = i -1;j >= 0 && arr[j] > arr[j + 1] ;j--){
                swap(arr,i,j);
            }
        }
    }

    private static void swap(int[] arr,int i ,int j) {
        int temp = arr[i];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    private static void compator(int[] arr) {
        Arrays.sort(arr);
    }

    private static int[] generateSort(int maxValue,int maxSize) {
        int[] arr = new int[(int)(Math.random() * maxSize)];
        for (int i = 0;i < arr.length;i++) {
            arr[i] = (int)(Math.random()*(maxValue + 1)) - (int)(Math.random() * maxSize);
        }
        return arr;
    }

    private static void printArr(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0;i < arr.length;i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    private static boolean compareArr(int[] arr1,int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null) ){
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (!Integer.valueOf(arr1.length).equals(arr2.length) ) {
            return false;
        }
        for (int i = 0;i < arr1.length;i++) {
            if (!Integer.valueOf(arr1[i]).equals(arr2[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxValue = 100000;
        int maxSize = 100000;
        boolean isEqual = true;
        for (int i =0 ;i < testTime; i++) {
            int[] arr1 = generateSort(maxValue,maxSize);
            System.out.println(Arrays.toString(arr1));
            int[] arr2 = Arrays.copyOf(arr1,arr1.length);
            insertionSort(arr1);
            compator(arr2);
            if (!compareArr(arr1,arr2)) {
                printArr(arr1);
                printArr(arr2);
                break;
            }
        }
        System.out.println(isEqual ? "perfect": "fail sorted");
    }
}
