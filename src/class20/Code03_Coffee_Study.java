package class20;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 有n个人一起喝咖啡，店里有几台咖啡机，每台咖啡机煮咖啡的事件不一样，有一个这样的煮咖啡时间的数组，同时又只有一台洗咖啡机，每次只能洗一个咖啡杯
 * 那么用完的咖啡杯可以选风干，风干时间b，洗干净的时间是a，问这n个人都喝完最少的时间点是什么时候
 */
public class Code03_Coffee_Study {
    /**
     * 使用暴力方法：
     */
    public static int right(int[] arr, int n, int a, int b) {
        int[] times = new int[arr.length];
        int[] drink = new int[n];
        return process(arr, times, 0, drink, n, a, b);
    }

    private static int process(int[] arr, int[] times, int kth, int[] drink, int n, int a, int b) {
        if (kth == n) {
            int[] drinkSorted = Arrays.copyOf(drink, kth);
            Arrays.sort(drinkSorted);
            return forceWash(drinkSorted, a, b, 0, 0, 0);
        }
        int time = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int work = arr[i];
            int pre = times[i];
            drink[kth] = pre + work;
            times[i] = pre + work;
            time = Math.min(time, process(arr, times, kth + 1, drink, n, a, b));
            // 这里在恢复到上一步
            drink[kth] = 0;
            times[i] = pre;
        }
        return time;
    }

    /**
     * @param drinkSorted 所有杯子可以开始洗的时间
     * @param a           用机器洗
     * @param b           自然风干
     * @param index
     * @param washLine    什么时候可以开始用
     * @param time
     * @return
     */
    private static int forceWash(int[] drinkSorted, int a, int b, int index, int washLine, int time) {
        if (index == drinkSorted.length) {
            return time;
        }
        // 这里使用洗咖啡杯机器洗，drinkSorte[index]表示这个杯子可以开始洗的时间点，washline表示机器什么时候可以用
        int wash = Math.max(drinkSorted[index], washLine) + a;
        int ans1 = forceWash(drinkSorted, a, b, index + 1, wash, Math.max(wash, time));// 这里最后比较下是考虑说index号自己洗完的节点，和剩下的杯子洗完的时间点

        // 风干
        int dry = drinkSorted[index] + b;
        int ans2 = forceWash(drinkSorted, a, b, index + 1, washLine, Math.max(dry, time));
        return Math.min(ans1, ans2);
    }

    /**
     * 优良点的暴力尝试，使用递归完成
     * @param arr 咖啡机的数量的数量
     * @param n 有n个人
     * @param a 咖啡机洗杯子花费时间
     * @param b 风干杯子花费的时间
     * @return
     */
    private static int minTime1(int[] arr, int n, int a, int b) {
        PriorityQueue<Machine> queue = new PriorityQueue(new MachineComparator());
        for (int i = 0; i < arr.length; i++) {
            queue.add(new Machine(0,arr[i]));
        }
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Machine cur = queue.poll();
            cur.timePoint += cur.worktime;
            drinks[i] = cur.timePoint;
            queue.add(cur);
        }
        return bestTime(drinks,a,b,0,0);
    }

    /**
     * 上一步计算出客户喝到咖啡的时间点
     * @param drinks 所有杯子可以开始洗的时间
     * @param a 单杯洗干净的时间
     * @param b 挥发干净的时间
     * @param index drink[index] 都变干净，最早的结束时间
     * @param free 洗的机器什么时候可以开始用
     * @return
     */
    private static int bestTime(int[] drinks, int a, int b, int index, int free) {
        // 没有杯子的时候可以返回0，反正是要比较max
        if (index == drinks.length) {
            return 0;
        }
        // index杯子 决定洗
        // drink里是用户杯子可以开始洗的时间，free是咖啡机可以开始用的时间，这里要取最大的。比如，用户i在第50分钟才喝到咖啡，那么此时才可以洗，而咖啡机在
        // 第0秒就可以开始用，前面的人都选择风干，所以这里要取最大值。
        int selfClean1 = Math.max(drinks[index],free) + a;
        // 下一个人洗杯子的最大时间
        int resetClean1 = bestTime(drinks,a,b,index+1,selfClean1);
        // selfClean1是自己变干净的时间点（其中包括了前面所有的杯子变干净时间，restClean1是除了自己其他杯子变干净的时间


        // index杯子 决定风干,风干的话不需要洗杯子，因此时间就是用户可以洗杯子的时间加上风干的时间
        int selfClean2 = drinks[index] + b;
        int restClean2 = bestTime(drinks,a,b,index+1,free);
        return  0;
    }


    private static class Machine {
        private int timePoint;
        private int worktime;

        public Machine(int t, int worktime) {
            this.timePoint = t;
            this.worktime = worktime;
        }
    }

    private static class MachineComparator implements Comparator<Machine> {
        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.timePoint + o1.worktime) - (o2.timePoint + o2.worktime);
        }
    }


}
