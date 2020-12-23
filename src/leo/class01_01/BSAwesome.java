package leo.class01_01;

import leo.util.ArrayUtil;

import java.util.function.IntPredicate;

/**
 * @author Leo
 * @ClassName BSAwesome
 * @DATE 2020/11/12 5:09 下午
 * @Description 在无序数组中,且无序数组任意相邻的数不相等,找到一个局部最小值的位置.>value<
 */
public class BSAwesome {

    public static int BSAwesome(int[] arr) {
        int index = -1;
        if (arr.length == 0 || arr == null) {
            return index;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int L = 1;
        int R = arr.length - 2;
        int mid = 0;
        while (L < R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;
            } else {
                return mid;
            }
        }
        return L;


    }


    public static int BSAwesome1(int[] arr) {
        if (arr.length == 0 || arr == null) {
            return -1;
        }
        if (arr.length == 1||arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length-1]<arr[arr.length-2]){
            return arr.length - 1;
        }
        int L = 1;
        int R = arr.length - 2;
        int mid = 0;
        while (L < R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 功能描述 : 重头遍历
     * @author Leo
     * @date 2020/11/14 4:14 下午
     * @param arr
     * @throw
     * @return int
     */
    public static int testBSAwesome(int[] arr) {
        if (arr.length == 0 || arr == null) {
            return -1;
        }
        if (arr.length >= 1&&arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        Integer first = null;

        Integer end = null;

        for (int i = 1; i < arr.length-2; i++) {
            int res = arr[i];
            if (first == null) {
                first = i-1;
            }
            if (end == null) {
                end = i + 1;
            }
            if (arr[first] > res && res < arr[end]) {
                return i;
            }
        }
        return -1;
    }


    public static int BSAwesome2(int[] arr) {
        if (arr.length == 0 || arr == null) {
            return -1;
        }
        if (arr.length == 1||arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length-1] < arr[arr.length-2]) {
            return arr.length - 1;
        }
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;
            } else if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            }else {
                return mid;
            }
        }
        return -1;

    }

    public static int BSAwesome3(int[] arr) {
        if (arr.length == 0 || arr == null) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }

        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        while (L < R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;
            } else if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            }else {
                return mid;
            }
        }
        return -1;
    }


    public static int BSAwesome4(int[] arr) {
        if (arr.length <= 0 || arr == null) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;
            } else if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            }else{
                return mid;
            }
        }
        return -1;

    }


    public static int BSAwesome5(int[] arr) {
        if (arr.length == 0 || arr == null) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;
            } else if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            }else {
                return mid;
            }
        }
        return -1;
    }


    public static int BSAwesome6(int[] arr) {
        if (arr.length == 0 || arr == null) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;
            } else if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    public static int BSAwesome7(int[] arr) {
        if (arr.length == 0 || arr == null) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;
            } else if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    public static int BSAwesome8(int[] arr) {
        if (arr.length == 0 || arr == null) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (arr[m] > arr[m + 1]) {
                l = m + 1;
            } else if (arr[m] > arr[m - 1]) {
                r = m - 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    public static int verifyBSAwesome(int[] arr, int index) {
        if (arr.length == 0 || index == -1) {
            return -1;
        }
        if (arr.length == 1 && index == 0) {
            return index;
        }
        if (arr.length >= 1 && index == 0 ){
            if (index + 1< arr.length && arr[index] < arr[index + 1]) {
                return index;
            }
        }
        if (index - 1 >= 0) {
            if (index == arr.length-1  ) {
                if (arr[index] < arr[index - 1]) {
                    return index;
                }
            }
            if (index == arr.length - 1) {
                if (arr[arr.length - 1] < arr[arr.length - 2]) {
                    return index;
                }
            }
            if (arr[index - 1] > arr[index] && arr[index] < arr[index + 1]) {
                return index;
            }
        }
        return -1;
    }

    public static void main(String[] arg) {
        int maxSize = 80;
        int range = 80;
        int testTime  = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = ArrayUtil.randomAdjacentNotEqualArray(maxSize, range);
            final int index = BSAwesome8(arr);
            final int verifyIndex = verifyBSAwesome(arr,index);
            if (index != verifyIndex) {
                succeed = false;
                ArrayUtil.printArr(arr, "index : " + index + " | testIndex : " + verifyIndex);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking Fucked!");
    }




}
