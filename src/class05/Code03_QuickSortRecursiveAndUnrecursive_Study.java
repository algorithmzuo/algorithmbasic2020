package class05;

import util.Util;

import java.util.Stack;

/**
 * 荷兰国旗问题
 * 1.给定一个数组，和一个整数num，请把小于等于num的数放在左边，大于num的数放右边
 * 2.给定一个数组，和一个数组num，请把下于num的数放在左边，大于num的数放右边，等于num的数放在中间
 * 解决过程：
 *  两个标志位，小于标志索引low，大于标志索引uper，当前位置索引index
 *  循环数组 当小于num时，当前数和low的下一个数交换，同时index右移一个位置
 *          当等于num时，low不动，index左移动一个位置
 *          当大于num时，该数组和uper的前一个位置的数交换，同时当前数不动停在原地
 */
public class Code03_QuickSortRecursiveAndUnrecursive_Study {
    public static int[] netherlandsFlag(int[] arr ,int L,int R){
        if (L > R) {
            return new int[]{-1,1};
        }
        if (L == R) {
            return new int[]{L,R};
        }

        int less = L - 1;
        int more = R;
        int index = L;
        while (index < more) {
            if (arr[index] < arr[R]) {
                swap(arr,++less,index++);
            }else if (arr[index] == arr[R]) {
                index++;
            }else {
                swap(arr,--more,index);
            }
        }
        // 所有数分好类后，需要将arr归类的等于区
        swap(arr,R,more);
        return new int[]{less + 1, more};
    }

    public static void swap(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 快排1.0处理
     * @param arr
     */
    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr,0,arr.length - 1);
    }

    public static void process(int[] arr,int L,int R){
        if (L > R) {
            return;
        }
        // 随机抽取一个数作为num,放在数组的最后面
        swap(arr,L + (int)(Math.random() * (R - L + 1)),R);
        int[] equalsArr = netherlandsFlag(arr,L,R);
        process(arr, L, equalsArr[0] - 1);
        process(arr,equalsArr[1] + 1,R);
    }

    /**
     * 快排非递归版本，辅助类
     * 处理的是什么范围上的排序
     */
    public static class Op{
        public int l;
        public int r;
        public Op(int left,int right) {
            l = left;
            r = right;
        }
    }

    public static void quickSort2(int[] arr) {
        if (arr == null && arr.length < 2) {
            return;
        }
        int N = arr.length;
        swap(arr,(int)(Math.random()*N),N-1);
        int[] equalArr = netherlandsFlag(arr,0,N-1);
        int el = equalArr[0];
        int er = equalArr[1];
        // 不用递归的话，这里就手动创建栈用来保存
        Stack<Op> stack = new Stack<>();
        stack.push(new Op(0,el - 1));
        stack.push(new Op(el+1,N - 1));
        while (!stack.isEmpty()) {
            Op op = stack.pop();
            if (op.l < op.r) {
                swap(arr, op.l + (int)(Math.random() * (op.r - op.l + 1)), op.r);
                equalArr = netherlandsFlag(arr,op.l,op.r);
                el = equalArr[0];
                er = equalArr[1];
                stack.push(new Op(op.l, el - 1));
                stack.push(new Op(er + 1,op.r));
            }
        }

    }


    public static void main(String[] args) {
        Util util = new Util();
        int testTime = 5000;
        int maxLength = 10;
        int maxValue = 100;
        boolean success = false;
        for (int i = 0 ;i <testTime;i++) {
            int[] arr1 = util.generateRandomArr(maxLength,maxValue);
            int[] arr2 = util.copyArr(arr1);
            quickSort1(arr1);
            util.printArr(arr1);
        }
    }

}
