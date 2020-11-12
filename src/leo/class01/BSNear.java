package leo.class01;

import leo.util.ArrayUtil;

/**
 * @author Leo
 * @ClassName BSNear
 * @DATE 2020/11/12 11:18 上午
 * @Description
 */
public class BSNear {

    /**
     * 功能描述 : 在有序数组中找出>=某个数最左侧的位置
     * @author Leo
     * @date 2020/11/12 3:01 下午
     * @param arr
     * @param value
     * @return int
     */
    public static int BSNearLeft(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int index = -1;
        int L = 0;
        int R = arr.length - 1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }

        }
        return index;
    }

    /**
     * 功能描述 : 在有序数组中找出>=某个数最左侧的位置(for test)
     * @author Leo
     * @date 2020/11/12 3:02 下午
     * @param arr
     * @param value
     * @return int
     */
    public static int forTestBSNearLeft(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= value) {
                return i;
            }
        }
        return -1;
    }





    /**
     * 功能描述 : 在有序数组中找出<=某个数最右侧的位置
     * @author Leo
     * @date 2020/11/12 3:05 下午
     * @param arr
     * @param value
     * @throw
     * @return int
     */
    public static int BSNearRight(int[] arr, int value) {
        int index = -1;
        if (arr == null || arr.length == 0) {
            return index;
        }
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        while (L <= R) {
            mid = (L + R) >> 1;
            if (arr[mid] <= value) {
                index = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return index;
    }

    public static int forTestBSNearRight(int[] arr, int value) {
        int index = -1;
        if (arr == null || arr.length == 0) {
            return index;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= value) {
                return i;
            }
        }
        return index;
    }


    public static void main(String[] args){
        int maxSize = 80;
        int range = 800;
        int testTime = 10000;
        boolean success = true;
        for (int i = 0; i < testTime; i++) {
            int[] sortArr = ArrayUtil.randomSortArray(maxSize, range);
            int value = (int) ((range + 1) * Math.random() - (range + 1) * Math.random());
            final int res1 = BSNearLeft(sortArr, value);
            final int res2 = forTestBSNearLeft(sortArr, value);
            final int res3 = BSNearRight(sortArr, value);
            final int res4 = forTestBSNearRight(sortArr, value);

            if (res1 != res2) {
                success = false;
                ArrayUtil.printArr(sortArr);
                System.out.println("BSNearLeft=" + res1);
                System.out.println("forTestBSNearLeft=" + res2);
                break;
            }
            if (res3 != res4) {
                success = false;
                ArrayUtil.printArr(sortArr);
                System.out.println("BSNearRight=" + res3);
                System.out.println("forTestBSNearRight=" + res4);
                break;
            }
        }
        System.out.println(success ? "Nice!!" : "Fucking Fucked!");
    }




}
