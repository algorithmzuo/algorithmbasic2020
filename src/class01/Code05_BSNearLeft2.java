package class01;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.Arrays;

/**
 * 二分法查找最左边的等于target值的下标，没有则返回-1
 * 1.空判断
 */
public class Code05_BSNearLeft2 {
    public static int bsNearLeft(int[] arr,int target) {
        if (arr == null && arr.length > 0) {
            return  -1;
        }
        int l = 0;
        int r = arr.length;
        int mid = 0;
        while (l < r) {
            mid = l + ((r-l)>>1);
            if (arr[mid] > target) {
                r = mid;
            }else if (arr[mid] < target){
                l = mid + 1;
            }else if (arr[mid] == target) {
                r = mid; // 因为这里是要找最左的那个下标，因此在相等时，让mid = r，不断向左缩小范围
            }
        }
        if (l == arr.length) return -1;
        if (arr[l] != target) return -1;
        return r;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.random() > 0.5 ?((int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random())):4;
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int comparator(int[] arr,int target) {
        if (arr == null && arr.length == 0) {
            return -1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int testTime = 500;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
//            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            int value = 4;
            if (bsNearLeft(arr, value) != comparator(arr, value)) {
                System.out.println(Arrays.toString(arr));
                System.out.println(value);
                System.out.println(bsNearLeft(arr, value));
                System.out.println(comparator(arr, value));
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
