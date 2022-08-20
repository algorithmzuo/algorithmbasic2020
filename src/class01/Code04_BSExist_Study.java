package class01;

import java.util.Arrays;

/**
 * 二分查找法
 */
public class Code04_BSExist_Study {
    /**
     * 查找数组中是否包含某个数
     * @param sortedArr 排序数组
     * @param findValue 被查找值
     * @return
     */
    private static boolean bsExit(int[] sortedArr,int findValue){
        if (sortedArr == null || sortedArr.length == 0){
            return false;
        }
        int LIndex = 0;
        int RIndex = sortedArr.length - 1;
        int middleIndex = LIndex + ((RIndex - LIndex) >> 1);
        for (;RIndex>LIndex;middleIndex = LIndex + ((RIndex - LIndex) >> 1)) {
            if (sortedArr[middleIndex] == findValue) {
                return true;
            } if (sortedArr[middleIndex] > findValue) {
                RIndex = middleIndex -1;
            } else {
                LIndex = middleIndex + 1;
            }
        }
        return Integer.valueOf(findValue).equals(sortedArr[middleIndex]) ? true:false;
    }

    private static boolean compartor(int[] arr,int findValue){
        if (arr == null) {
            return false;
        }
        for (int i = 0;i < arr.length;i++){
            if (Integer.valueOf(findValue).equals(arr[i])){
                return true;
            }
        }
        return false;
    }

    private static int[] generateSortedArr(int maxValue,int maxSize){
        int[] arr = new int[(int)(Math.random() * (maxSize+1))];
        for (int i = 0;i < arr.length;i++){
            arr[i] = (int)(Math.random() * (maxValue + 1)) - (int)(Math.random()*maxValue);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 5000;
        int maxValue = 100;
        int maxSize = 100;
        boolean isExit = true;
        for (int i = 0;i < testTime;i++) {
            int[] arr = generateSortedArr(maxValue,maxSize);
            Arrays.sort(arr);
            // 要查找的数
            int findIndex = (int)(Math.random()*arr.length);
            if (arr.length > 0 && bsExit(arr,arr[findIndex]) != compartor(arr,arr[findIndex])) {
                isExit = false;
                return;
            }
        }
        System.out.println(isExit ? "perfect":"fail find");
    }

}
