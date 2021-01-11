package leo.class20;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Leo
 * @ClassName Coffee
 * @DATE 2021/1/11 3:00 下午
 * @Description
 * 业务限制模型,可变参数不能直观的得到变换范围,需要把可变参数估出来,
 * 样本对应模式,两个参数就是下标,明确知道两个参数的变化范围,只讨论当前的结尾如何组织可能性
 * 范围尝试模型,LR两个参数可变参数,左下半区没用,先求对角线或上一个对角线,,只用上半区,特别在意开头和结尾.
 * 从左往右模型,明确知道可变参数的最大值,
 *
 * 题目
 * 数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
 * 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
 * 认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
 * 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
 * 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
 * 四个参数：arr, n, a, b
 * 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
 */
public class Coffee {

    /**
     * 暴力递归到动态规划
     * 分析可变参数以及依赖关系
     * 如果分析不出依赖参数,使用记忆化搜索,傻缓存
     *
     * 动态规划模型:
     * 业务限制模型,可变参数不能直观的得到变换范围,需要把可变参数估出来,可变参数用业务最差参数估,
     * 样本对应模式,两个参数就是下标,明确知道两个参数的变化范围,只讨论当前的结尾如何组织可能性
     * 范围尝试模型,LR两个范围参数可变参数,左下半区没用,先求对角线或上一个对角线,L>R的时候不需要填,只用上半区,特别在意开头和结尾.
     * 从左往右模型,明确知道可变参数的最大值
     */


    static class Machine {
        //提供服务的时间点
        int timePoint;
        //工作一次的时间
        int workTime;
        public Machine(int t,int w) {
            timePoint = t;
            workTime = w;
        }
    }

    static class MachineComparator implements Comparator<Machine> {

        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
        }
    }
    static class Recursion {

        /**
         * 功能描述 : 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
         *          假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
         * @author Leo
         * @date 2021/1/11 3:15 下午
         * @param arr 数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
         * @param n 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
         * @param a 洗杯子的机器洗完一个杯子时间为a，
         * @param b 任何一个杯子自然挥发干净的时间为b。
         * @return int
         */
        public static int minTime(int[] arr, int n, int a, int b) {
            PriorityQueue<Machine> heap = new PriorityQueue<>(new MachineComparator());
            for (int i = 0; i < arr.length; i++) {
                heap.offer(new Machine(0, arr[i]));
            }
            //所有杯子可以开始洗的时间
            int[] drinks = new int[n];
            for (int i = 0; i < n; i++) {
                Machine cur = heap.poll();
                cur.timePoint += cur.workTime;
                drinks[i] = cur.timePoint;
                heap.offer(cur);
            }
            return process(drinks, a, b, 0, 0);
        }

        /**
         * 功能描述 :
         * @author Leo
         * @date 2021/1/11 3:21 下午
         * @param drinks 所有杯子可以开始洗的时间
         * @param wash  洗杯子的时间
         * @param air   不洗杯子挥发干净的时间
         * @param i     第几个杯子
         * @param free  洗杯子机器什么时间可用.
         * @throw
         * @return int
         */
        private static int process(int[] drinks, int wash, int air, int i, int free) {
            if (i == drinks.length) {
                return 0;
            }
            //洗
            int washClean = Math.max(drinks[i], free) + wash;
            //洗的方式剩余干净的时间
            int restWashClean = process(drinks, wash, air, i + 1, washClean);
            int p1 = Math.max(washClean, restWashClean);
            //挥发
            int airClean = drinks[i] + air;
            //挥发的方式剩余干净的时间
            int restAirClean = process(drinks, wash, air, i + 1, free);
            int p2 = Math.max(airClean, restAirClean);
            return Math.min(p1, p2);
        }


    }

    static class Dp {
        public static int minTime(int[] arr, int n, int a, int b) {
            PriorityQueue<Machine> heap = new PriorityQueue<>(new MachineComparator());
            for (int i = 0; i < arr.length; i++) {
                heap.offer(new Machine(0, arr[i]));
            }
            int[] drinks = new int[n];
            for (int i = 0; i < n; i++) {
                Machine cur = heap.poll();
                cur.timePoint += cur.workTime;
                drinks[i] = cur.timePoint;
                heap.offer(cur);
            }

            return process(drinks, a, b);
        }

        private static int process(int[] drinks, int wash, int air) {
            int n = drinks.length;
            //求每个杯子洗完的最大时间
            int maxFree = 0;
            for (int i = 0; i < n; i++) {
                maxFree = Math.max(maxFree, drinks[i]) + wash;
            }
            int[][] dp = new int[n + 1][maxFree + 1];
            //上面依赖下面,从地下开始填
            for (int i = n - 1; i >= 0; i--) {
                //从左往右填
                for (int free = 0; free < maxFree; free++) {
                    //洗
                    int washClean = Math.max(drinks[i], free) + wash;
                    //没有这个位置,防越界
                    if (washClean > maxFree) {
                        continue;
                    }
                    //洗的方式剩余干净的时间
                    int restWashClean = dp[i + 1][washClean];
                    int p1 = Math.max(washClean, restWashClean);
                    //挥发
                    int airClean = drinks[i] + air;
                    //挥发的方式剩余干净的时间
                    int restAirClean = dp[i + 1][free];
                    int p2 = Math.max(airClean, restAirClean);
                    dp[i][free]  = Math.min(p1, p2);
                }
            }
            return dp[0][0];
        }

    }


    public static void main(String[] args) {
        int len = 10;
        int max = 10;
        int testTime = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(len, max);
            int n = (int) (Math.random() * 7) + 1;
            int a = (int) (Math.random() * 7) + 1;
            int b = (int) (Math.random() * 10) + 1;
            int recursion = Recursion.minTime(arr, n, a, b);
            int dp = Dp.minTime(arr, n, a, b);
            if (recursion != dp ) {
                printArray(arr);
                System.out.println("n : " + n);
                System.out.println("a : " + a);
                System.out.println("b : " + b);
                System.out.println(recursion + " , " + dp);
                System.out.println("===============");
                break;
            }
        }
        System.out.println("测试结束");

    }

    // for test
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        System.out.print("arr : ");
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j] + ", ");
        }
        System.out.println();
    }
}
