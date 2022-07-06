package util;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Arrays;

/**
 * 对数器
 */
public class Util {
    /**
     * 随机生成数组
     * @param maxLength 最大长度
     * @param maxValue 最大值
     * @return
     */
    public static int[] generateRandomArr(int maxLength,int maxValue) {
        int[] arr = new int[(int)(Math.random() * (maxLength + 1))];
        for (int i = 0 ;i < arr.length;i++) {
            arr[i] = (int)((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random());
        }
        return arr;
    }

    /**
     * 拷贝数组
     * @param orgin
     * @return
     */
    public static int[] copyArr(int[] orgin){
        if (orgin == null ){
            return null;
        }
        int[] newArr = new int[orgin.length];
        for (int i = 0;i < orgin.length; i++){
            newArr[i] = orgin[i];
        }
        return newArr;
    }

    public static boolean isEqual(int[] arr1,int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        for (int i = 0 ; i < arr1.length ;i++){
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印数组
     * @param arr
     */
    public static void printArr(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0 ; i < arr.length;i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int[] randomArrNoMoveMoreK(int maxSize,int maxValue,int k) {
        int[] arr = new int[(int)(Math.random() * (maxSize +1))];
        for (int i = 0;i < arr.length;i++) {
            arr[i] = (int)(Math.random()*(maxValue + 1)) - (int)(Math.random()*maxValue);
        }
        Arrays.sort(arr);
        //打乱数组顺序，每个数据有序后移动的位置不超过k
        boolean[] isSwap = new boolean[arr.length];
        for (int i = 0;i < arr.length;i++) {
            int j = (int) Math.min(i + (Math.random() * (k + 1)),arr.length - 1);
            if (!isSwap[i] && !isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                swap(arr,i,j);
            }
        }
        return arr;
    }

    public static void swap(int[] arr ,int i,int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}

