package class01;

import java.util.Arrays;

/**
 * 1.边界条件判断
 * 2.字面意思理解，元素一个个往外冒
 * 0～N-1 选择最大的放到N-1
 * 0～N-2 选择最大的放到N-2
 * ……
 * 最外层循环控制范围
 * 内层循环比较相邻的两位数
 * 时间负责度都是O(n^2)
 */
public class Code02_BubbleSort2 {
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
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
            int[] arr1 = generate(length,random);
            // 复制数组
            int[] arr2 = copyArr(arr1);
            // 自定义方法排序
            bubbleSort(arr1);
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
