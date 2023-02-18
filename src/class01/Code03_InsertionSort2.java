package class01;

import java.util.Arrays;

/**
 * 插入排序：
 * 1.校验边界条件
 * 2.思路
 * [0~1]位置排好序
 * 第3个元素从1位置往前比较，找到比他小的插入
 * [0~2]位置排好序
 * 第4个元素从2位置往前比较，找到比他小的位置插入
 * ……
 * 3.插入排序
 * 最优的情况下，复杂度是O(n),最差的情况下是O(n^2)
 */
public class Code03_InsertionSort2 {
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j + 1, j);
            }
        }

    }

    private static void swap(int[] arr, int j, int i) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        // 测试次数
        int time = 5000;
        // 数组大小
        int length = 50;
        // 随机数范围
        int random = 100;
        boolean success = true;
        for (int i = 0; i < time; i++) {
            // 生成随机数
            int[] arr1 = generate(length, random);
            // 复制数组
            int[] arr2 = copyArr(arr1);
            // 自定义方法排序
            insertSort(arr1);
            // 工具类方法排序
            compartor(arr2);
            // 比较两种方式排序后的结果

            for (int j = 0; j < arr1.length; j++) {
                System.out.println(arr1[j] + " : " + arr2[j]);
                if (arr1[j] != arr2[j]) {
                    success = false;
                    return;
                }
            }
        }
        System.out.println(success ? "success" : "fail");
    }

    private static void compartor(int[] arr2) {
        Arrays.sort(arr2);
    }

    private static int[] copyArr(int[] arr1) {
        if (arr1 == null) {
            return null;
        }
        int[] copyArr = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            copyArr[i] = arr1[i];
        }
        return copyArr;
    }

    /**
     * 生成随机数
     *
     * @param length 最大长度
     * @param random 最大随机数
     * @return
     */
    private static int[] generate(int length, int random) {
        int[] randomlength = new int[(int) (Math.random() * (length + 1))];
        for (int i = 0; i < randomlength.length; i++) {
            // [-?,+?] Math.random() [0,1)
            randomlength[i] = (int) (Math.random() * (random + 1)) - (int) (Math.random() * random);
        }
        return randomlength;
    }
}
