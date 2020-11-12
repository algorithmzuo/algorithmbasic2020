package leo.class01;


import leo.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author Leo
 * @ClassName BSExist
 * @DATE 2020/11/12 10:14 上午
 * @Description 二分查找是否存在
 */
public class BSExist {

    /**
     * 功能描述 : 二分查找某数是否存在
     * @author Leo
     * @date 2020/11/12 10:36 上午
     * @param arr 有序数组
     * @param value
     * @return boolean
     */
    public static boolean exist(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        while (L < R) {
            //右移一位表示除以2
            //mid = (L + R) / 2; 不安全 有溢出的风险
            mid = L + ((R - L) >> 1);
            if (arr[mid] == value) {
                return true;
            } else if (arr[mid] > value) {
                R = mid - 1;
            }else {
                L = mid + 1;
            }
        }
        return arr[L] == value;
    }

    /**
     * 功能描述 : test
     * @author Leo
     * @date 2020/11/12 10:36 上午
     * @param arr 二分查找某数是否存在
     * @param value
     * @return boolean
     */
    public static boolean forExist(int[] arr, int value) {
        for (int i : arr) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args){

        int maxSize = 50;
        int range = 500;
        int testTime  = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] sortArr = ArrayUtil.randomSortArray(maxSize, range);
            int value = (int) ((range + 1) * Math.random() - (range + 1) * Math.random());

            if (exist(sortArr, value) != forExist(sortArr, value)) {
                succeed = false;
                ArrayUtil.printArr(sortArr);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking Fucked!");





    }

}
