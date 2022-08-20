package class01;

import java.util.Arrays;

public class Code01_SelectionSort_Study {
    /**
     * 选举排序
     * 0~N 选择最小的一个放在第一个
     * 1~N 选择最小的一个放在第二个
     * ...
     */
    private static void selectionSort(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0;i < arr.length - 1;i++) {
            int minIndex = i;
            for (int j = 0;j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr,i,minIndex);
        }
    }

    private static void swap(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 使用java工具排序类
     * @param arr
     */
    private static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    private static int[] generatorRandomArray(int maxValue,int maxSize){
        // 最大为maxSize大小的数组
        int[] arr = new int[(int)Math.random() * (maxSize + 1)];
        for (int i = 0;i < arr.length;i++) {
            // 数值区间在[-maxValue,+maxvalue]区间的值
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int)Math.random()*maxSize;
        }
        return arr;
    }

    private static boolean compareArr(int[] arr1,int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)){
            return false;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }

        for (int i = 0;i < arr1.length ;i++) {
            if (arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }

    private static void printArr(int[] arr){
        if (arr == null){
            return;
        }
        for (int i=0;i < arr.length;i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 200;
        boolean isEqual = false;
        for (int i = 0;i < testTime;i++) {
            int[] arr = generatorRandomArray(maxValue,maxSize);
            int[] arr2 = Arrays.copyOf(arr,arr.length);
            selectionSort(arr);
            comparator(arr2);
            isEqual = compareArr(arr,arr2);
            if (!isEqual) {
                printArr(arr);
                printArr(arr2);
                break;
            }
        }
        System.out.println(isEqual ? "perfect" : "fail sorted");
    }
}
