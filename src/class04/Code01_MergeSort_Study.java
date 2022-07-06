package class04;

import java.util.Arrays;

public class Code01_MergeSort_Study {
    /**
     * 递归版本
     * @param arr
     */
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr,0,arr.length - 1);
    }

    /**
     * 迭代器版本
     * @param arr
     */
    public static void mergeSort2(int arr[]){
        // 步长
        int mergeSize = 1;
        int N = arr.length;
        while(mergeSize < N) {
            int L = 0;
            while (L < N) {
                if (mergeSize > N - L) {
                    break;
                }
                int M = L + mergeSize -1;
                int R = M + Math.min(N - M - 1, mergeSize);
                merge(arr,L,M,R);
                L = R + 1;
            }
            if ( mergeSize > N /2) {
                break;
            }
            mergeSize = mergeSize << 1;
        }
    }

    private static void process(int[] arr ,int L,int R) {
        if (L ==R) {
            return;
        }
        int mid = L +( (R - L)>> 1);
        process(arr,L,mid);
        process(arr,mid + 1,R);
        merge(arr,L,mid,R);

    }

    private static void merge(int[] arr,int L,int mid,int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++]:arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }

        while (p2 <= R) {
            help[i++] = arr[p2++];
        }

        for (int j = 0; j < help.length;j++){
            arr[L + j] = help[j];
        }
    }

    private static int[] generateRandomArr(int maxLen ,int maxValue){
        int[] arr = new int[(int)(Math.random() * (maxLen + 1))];
        for (int i = 0;i < arr.length ;i++){
            arr[i] = (int) (Math.random() * (maxValue+1)) - (int) (Math.random() * maxValue);
        }
        return arr;
    }

    private static int[] copyArr(int[] arr){
        int[] newArr = new int[arr.length];
        for (int i = 0;i < arr.length;i++){
            newArr[i] = arr[i];
        }
        return newArr;
    }

    private static void compator(int[] arr){
        Arrays.sort(arr);
    }

    private static boolean isEqual(int[] arr1,int[] arr2){
        if ((arr1 == null && arr2 != null ) || (arr2 == null &&arr1 != null)) {
            return false;
        }
        if (arr1 == null && arr2 == null){
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0 ;i < arr1.length;i++){
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int time = 500;
        int maxValue = 20;
        int maxLength = 10;
        boolean isEqual = true;
        for (int i = 0;i < time;i++){
            int[] arr = generateRandomArr(maxLength,maxValue);
            int[] copyArr = copyArr(arr);
            int[] copyArr2 = copyArr(arr);
             mergeSort1(arr);
             compator(copyArr);
             mergeSort2(copyArr2);
             if (!isEqual(arr,copyArr)) {
                 System.out.println("mergesort:" + Arrays.toString(arr) );
                 System.out.println("compatorSort:" + Arrays.toString(copyArr));
                 isEqual = false;
                 break;
             }
             if (!isEqual(copyArr,copyArr2)) {
                 System.out.println("mergesort:" + Arrays.toString(copyArr2) );
                 System.out.println("compatorSort:" + Arrays.toString(copyArr));
                 isEqual = false;
                 break;
             }
        }
        System.out.println(isEqual ? "success":"fail");

    }

}
