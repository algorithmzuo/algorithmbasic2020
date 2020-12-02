package leo.class05_08;

import leo.util.ArrayUtil;
import sun.jvm.hotspot.oops.BranchData;

import java.util.Arrays;

/**
 * @author Leo
 * @ClassName CountSort
 * @DATE 2020/12/2 2:03 下午
 * @Description 计数排序(桶排序)
 */
public class CountSort {


    public static void countSort(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] bucket = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                arr[i++] = j;
            }
        }
    }





    public static void main(String[] args){
        int maxSize = 100;
        int range = 100;
        int testTime = 10000;
        System.out.println("start!");

        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxSize, range);
            int[] copyArray = copyArray(arr);
            countSort(arr);
            Arrays.sort(copyArray);
            if (!ArrayUtil.isEqual(arr, copyArray)) {
                ArrayUtil.printArr(arr, "arr");
                ArrayUtil.printArr(copyArray, "copyArray");
                System.out.println("fuck!");
                break;
            }
        }
        System.out.println("end!");

    }

    public static int[] randomArray(int maxSize, int range) {
        int[] arr = new int[(int) (maxSize * Math.random() + 1)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (range * Math.random()) + 1;
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
