package class01;

import java.util.Arrays;
import java.util.Random;

/**
 * 选择排序
 * 1.边界判断
 * 2.选择概念
 * 从0-N-1位置找到最小值放到0位
 * 从1-N-1找到最小值放到第1位
 * ……
 * 3.
 */
public class Code01_SelectionSort2 {
    public static void selection(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0;i < arr.length-1;i++) {
            int minindex = i;
            for (int j = i + 1;j < arr.length;j++) {
                minindex = arr[j] < arr[minindex] ? j : minindex;
            }
            swap(arr,i,minindex);
        }
    }

    private static void swap(int[] arr, int i, int minindex) {
        int temp = arr[i];
        arr[i] = arr[minindex];
        arr[minindex] = temp;
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
            int[] arr1 = generate(length,random);
            // 复制数组
            int[] arr2 = copyArr(arr1);
            // 自定义方法排序
            selection(arr1);
            // 工具类方法排序
            compartor(arr2);
            // 比较两种方式排序后的结果

            for (int j = 0; j < arr1.length; j++) {
                if (arr1[j] != arr2[j]) {
                    success = false;
                    return;
                }
            }
        }
        System.out.println(success?"success":"fail");
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
     * @param length 最大长度
     * @param random 最大随机数
     * @return
     */
    private static int[] generate(int length, int random) {
        int[] randomlength = new int[(int) (Math.random()*(length + 1))];
        for (int i = 0; i < randomlength.length; i++) {
            // [-?,+?] Math.random() [0,1)
            randomlength[i] = (int) (Math.random() * (random + 1)) - (int)(Math.random()*random);
        }
        return randomlength;
    }


}
