package class14;

import java.util.PriorityQueue;

/**
 * 分割金条：
 * 1，贪心算法，使用小根堆，小根堆元素依次抛出，小根堆每抛出两个元素，两个元素求和
 * 2.暴力方法，每两个元素都去求和，再加上之前的元素产生的结果，用递归
 */
public class Code02_LessMoneySplitGold_Study {
    // 暴力方法
    public static int lessMoneySplit1(int[] arr){
        if (arr == null || arr.length ==0) {
            return 0;
        }
        return process(arr,0);
    }

    /**
     *
     * @param arr 剩余待合并的数组
     * @param pre 之前合并产生的结果
     * @return
     */
    public static int process(int[] arr,int pre){
        // 这里至少会有一个元素
        if (arr.length == 1) {
            return pre;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0 ;i < arr.length;i++) {
            for (int j = i + 1; j < arr.length;j++){
                ans = Math.min(ans,process(expactElement(arr,i,j),pre + arr[i]+arr[j]));
            }
        }
        return ans;
    }

    public static int[] expactElement(int[] arr,int i,int j){
        int[] ans = new int[arr.length - 1];
        int ansIndex = 0;
        for (int p = 0;p < arr.length;p++){
            if (p != i && p != j) {
                ans[ansIndex++] = arr[p];
            }
        }
        // 把i+j两个元素和加进去
        ans[ansIndex] = arr[i] + arr[j];
        return ans;
    }

    public static int lessMoneySplit2(int[] arr) {
        if (arr == null || arr.length <=0) {
            return 0;
        }
        PriorityQueue<Integer> ps = new PriorityQueue<>();
        for (int i = 0;i < arr.length;i++) {
            ps.add(arr[i]);
        }
        int ans = 0;
        while (ps.size()>1) {
            int cur = ps.poll() + ps.poll();
            ans += cur;
            ps.add(cur);
        }
        return ans;
    }
    public static int[] generateRandomArr(int maxSize,int maxValue) {
        int[] arr = new int[(int)(Math.random()*(maxSize +1))];
        for (int i = 0 ; i < arr.length ;i++) {
            arr[i] = (int)(Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
//        int[] arrs = {2,6,7,10,8,6,2,6,3,10};
//        if (lessMoneySplit1(arrs) != lessMoneySplit2(arrs)) {
//            System.out.println("oop");
//        }

        // 元素不能太多，太多程序会崩了
        int maxSize = 6;
        int maxValue = 10;
        int testTime = 1000000;
        for (int i = 0;i < testTime;i++) {
            int[] arr = generateRandomArr(maxSize,maxValue);
//            for (int va : arr) {
//                System.out.print(va + ",");
//            }
//            System.out.println();
            if (lessMoneySplit1(arr) != lessMoneySplit2(arr)) {
                System.out.println("oop");
            }
        }
        System.out.println("finish");
    }
}
