package class01;

import java.util.Arrays;

/**
 * 二分查找
 * 对于已经排好序的数列可以使用二分查找
 * 只要能正确构建左右两侧淘汰逻辑的就可以使用二分查找
 * 1.边界条件判断
 * 2.找到中点位置
 * 3.如果大于中点位置的数，则去右边继续找；
 * 如果小于中点位置的数，则去左边继续找
 */
public class Code04_BSExist2 {
    public static boolean bsexit(int[] arr,int target) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int L = 0 ;
        int R = arr.length;
        int middle = 0 ;
        while (L < R) {
            middle = L + ((R-L)>> 1);
            if (arr[middle] > target) {
                R = middle;
            }else if (arr[middle] < target){
                L = middle + 1;
            }else {
                return true;
            }
        }
        return false;
    }

    public static boolean compartor(int[] arr,int target) {
        for (int i : arr) {
            if (i == target) {
                return true;
            }
        }
        return false;
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
            Arrays.sort(arr1); // 随机数生成的数组，一定要排序
            // 生成目标
            System.out.println("arrays"+Arrays.toString(arr1));
            int target = (int)(Math.random()*(random +1)) - (int) (Math.random()*random);
            System.out.println("target:"+target);
            if (bsexit(arr1,target) != compartor(arr1,target)) {
                success = false;
                break;
            }
        }
        System.out.println(success?"success":"fail");
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
