package class08;

import java.util.Arrays;

/**
 * 计数排序(桶排序)
 */
public class Code03_CountSort_Study {
    /**
     * 正整数排序
     * @param arr
     */
    public static void countSort(int[] arr) {
        if (arr == null || arr.length <2) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0;i < arr.length;i++) {
            max = Math.max(max,arr[i]);
        }
        int[] bucket = new int[max+1] ;
        for (int i = 0;i < bucket.length;i++) {
            bucket[arr[i]]++;
        }
        int i = 0;
        for (int j = 0 ;j < bucket.length;j++) {
            while (bucket[j]-- > 0 ) {
                arr[i++] = j;
            }
        }
    }

    public void compator(int[] arr) {
        Arrays.sort(arr);
    }

    public int[] generateRandomArr(int maxSize,int maxValue) {
        int[] arr = new int[(int)(Math.random()*(maxSize + 1))];
        for (int i = 0; i < arr.length;i++) {
            arr[i] = (int)(Math.random() * maxValue + 1 );
        }
        return arr;
    }

    public boolean compare(int[] arr1,int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null &&arr2 == null)) {
            return false;
        }
        if (arr1 == null &&arr2 == null){
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0 ;i < arr1.length;i++){
            if (arr1[i]!=arr2[i]) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int maxTime = 10000000;
        int maxValue = 100;
        int maxSize = 100;
        Code03_CountSort_Study cla = new Code03_CountSort_Study();
        for (int i = 0 ;i < maxTime;i++) {
            int[] arr =  cla.generateRandomArr(maxSize,maxValue);
            int[] copyAr = Arrays.copyOf(arr,arr.length);
            System.out.println(Arrays.toString(arr));
            if (!cla.compare(arr,copyAr)) {
                System.out.println("oop");
                return;
            }
        }
        System.out.println("finsh");
    }
}
