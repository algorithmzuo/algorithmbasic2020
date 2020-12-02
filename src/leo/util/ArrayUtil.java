package leo.util;

import org.omg.CORBA.IRObject;

import java.util.Arrays;

/**
 * @author Leo
 * @ClassName util
 * @DATE 2020/11/11 6:55 下午
 * @Description 对数器工具
 */
public class ArrayUtil {



    /**
     * 功能描述 : 随机生成数组
     * @author Leo
     * @date 2020/11/11 6:57 下午
     * @param maxSize 数组最大值
     * @param range 数组元素范围
     * @return int[]
     */
    public static int[] randomArray(int maxSize, int range) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((range + 1) * Math.random()- (range * Math.random())) ;
        }
        return arr;
    }


    /**
     * 功能描述 : 生成随机有序数组
     * @author Leo
     * @date 2020/11/12 10:42 上午
     * @param maxSize
     * @param range
     * @return int[]
     */
    public static int[] randomSortArray(int maxSize, int range) {
        int[] arr = randomArray(maxSize, range);
        Arrays.sort(arr);
        return arr;
    }

    /**
     * 功能描述 : 复制数组
     * @author Leo
     * @date 2020/11/11 6:57 下午
     * @param arr
     * @return int[]
     */
    public static int[] copyArray(int[] arr) {
        int[] newArr = new int[arr.length];
        if (arr != null && arr.length > 0) {
            for (int i = 0; i < arr.length; i++) {
                newArr[i] = arr[i];
            }
            return newArr;
        }
        return newArr ;
    }

    /**
     * 功能描述 : 判断数组是否一样
     * @author Leo
     * @date 2020/11/11 7:20 下午
     * @param arr1
     * @param arr2
     * @return boolean
     */
    public static boolean isEqual(int[] arr1, int[] arr2) {
        return isEqual(arr1, arr2, false);
    }

    /**
     * 功能描述 : 判断数组是否一样并打印
     * @author Leo
     * @date 2020/11/11 7:21 下午
     * @param arr1
     * @param arr2
     * @return boolean
     */
    public static boolean isEqualAndPrint(int[] arr1, int[] arr2) {
        return isEqual(arr1, arr2, true);
    }


    /**
     * 功能描述 : 判断数组
     * @author Leo
     * @date 2020/11/11 7:15 下午
     * @param arr1
     * @param arr2
     * @param printJudge 是否打印
     * @return boolean
     */
    private static boolean isEqual(int[] arr1, int[] arr2,boolean printJudge) {

        if (printJudge) {
            printArr(arr1, "arr1");
            printArr(arr2, "arr2");
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }else if (arr1 == null || arr2 == null) {
            return false;
        }else if (arr1.length != arr2.length) {
            return false;

        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 功能描述 : 打印数组
     * @author Leo
     * @date 2020/11/11 7:15 下午
     * @param arr
     * @param arrName
     * @return void
     */
    public static void printArr(int[] arr, String arrName) {
        StringBuffer arrStr = new StringBuffer();
        arrName = arrName == null ? "arr" : arrName;
        arrStr.append(" " + arrName + " = [");
        if (arr == null || arr.length <= 0) {
            arrStr.append("]");
            System.out.println(arrStr);
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            arrStr.append(" " + arr[i] + (i==arr.length-1?"":","));
        }
        arrStr.append(" ]");
        System.out.println(arrStr);
    }

    /**
     * 功能描述 : 打印数组
     * @author Leo
     * @date 2020/11/12 2:17 下午
     * @param arr
     * @return void
     */
    public static void printArr(int[] arr) {
        printArr(arr, null);

    }

    /**
     * 功能描述 : 随机生成无序数组且任意相邻的数不相等
     * @author Leo
     * @date 2020/11/13 11:21 上午
     * @param maxSize
     * @param range
     * @return int[]
     */
    public static int[] randomAdjacentNotEqualArray(int maxSize, int range) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        Integer preNum = null;
        for (int i = 0; i < arr.length; i++) {
            int randomNum = (int) ((range + 1) * Math.random() - range  * Math.random());

            if (preNum != null) {
                while (preNum == randomNum) {
                    randomNum = (int) ((range + 1) * Math.random() - range  * Math.random());
                }
            }
            preNum = randomNum;
            arr[i] = randomNum;
        }
        return arr;
    }



}
