package class01;

import java.util.Arrays;
import java.util.Random;

public class Code05_BSNearLeft_Study {
    /**
     * 在一个有序数组中，找到>=某个数最左侧的那个数
     * @param arr
     * @param findValue
     */
    private static Integer bsNearLeft(int[] arr,int findValue){
        if (arr == null && arr.length == 0) {
            return -1;
        }
        int R = arr.length -1;
        int L = 0;
        int minIndex = -1;
        while(R>=L){
            int mid = L + ((R - L)>>1);
            if (arr[mid] >= findValue) {
                minIndex = mid;
                R = mid - 1;
            }else {
                L = mid + 1;
            }
        }
        return minIndex;
    }

    private static Integer test(int[] arr,int finalValue) {
        for (int i = 0;i < arr.length;i++) {
            if (arr[i] >= finalValue){
                return i;
            }
        }
        return -1;
    }

    private static int[] generateArr(int maxValue,int maxSize){
        int[] arr = new int[(int)(Math.random()*maxSize)];
        for (int i = 0;i < arr.length;i++){
            arr[i] = (int)(Math.random()*(maxValue +1)) - (int)(Math.random()*maxValue);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 5000;
        int maxSize = 20;
        int maxValue = 20;
        boolean isExit = true;
        for (int i = 0;i < testTime;i++){
            int[] arr = generateArr(maxValue,maxSize);
            if (arr.length == 0) {
                continue;
            }
            Arrays.sort(arr);
            int findIndex = (int) (Math.random()*arr.length);
            int index1 = bsNearLeft(arr,arr[findIndex]);
            int index2 = test(arr,arr[findIndex]);
            if (!Integer.valueOf(index1).equals(index2)) {
                isExit =false;
                System.out.println(index1);
                System.out.println(index2);
                System.out.println(Arrays.toString(arr));
                System.out.println(" findValue :"+arr[findIndex]);
                break;
            }
        }
        System.out.println(isExit ? "exit":"not exit");
    }
}
