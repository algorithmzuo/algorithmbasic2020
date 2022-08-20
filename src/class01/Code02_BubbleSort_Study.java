package class01;

import java.util.Arrays;

public class Code02_BubbleSort_Study {
    /**
     * 选择最值的数排到最后
     * 0~N 把最值放到N位置
     * 0~N-1 把最值放到N-1位置
     * 0~N-2 把最值放到N-2位置
     * ...
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = arr.length -1 ;i >= 0;i--) {
            int maxIndex = i;
            for (int j = 0;j < i;j++) {
                maxIndex = arr[j] > arr[maxIndex]? j : maxIndex;
            }
            swap(arr,i,maxIndex);
        }
    }

    /**
     * 交换数据
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    private static void compator(int[] arr) {
        Arrays.sort(arr);
    }

    private static int[] generateSort(int maxValue,int maxSize) {
        int[] arr = new int[(int)Math.random() * maxSize];
        for (int i = 0;i < arr.length;i++) {
            arr[i] = (int)Math.random()*(maxValue + 1) - (int)Math.random() * maxSize;
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
        int maxValue = 1000;
        int maxSize = 1000;
        boolean isEqual = false;
        for (int i = 0;i < testTime;i++) {
            int[] arr1 = generateSort(maxValue,maxSize);
            int[] arr2 = Arrays.copyOf(arr1,arr1.length);
            bubbleSort(arr1);
            compator(arr2);
            isEqual = compareArr(arr1,arr2);
            if (!isEqual){
                printArr(arr1);
                printArr(arr2);
                break;
            }
        }
        System.out.println(isEqual ? "perfect" : "fail sorted");
    }
}
