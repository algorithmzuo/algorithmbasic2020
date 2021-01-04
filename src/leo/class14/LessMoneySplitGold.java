package leo.class14;

import java.util.PriorityQueue;

/**
 * @author Leo
 * @ClassName LessMoneySplitGold
 * @DATE 2020/12/22 4:54 下午
 * @Description
 * 贪心策略
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的。
 * 比如长度为20的金条，不管怎么切，都要花费20个铜板。 一群人想整分整块金条，怎么分最省铜板?
 *
 * 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。
 *
 * 如果先把长度60的金条分成10和50，花费60; 再把长度50的金条分成20和30，花费50;一共花费110铜板。
 * 但如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20， 花费30;一共花费90铜板。
 * 输入一个数组，返回分割的最小代价。
 */
public class LessMoneySplitGold {

    static class Code {
        static int lessMoney(int[] arr) {
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            for (int i = 0; i < arr.length; i++) {
                queue.offer(arr[i]);
            }
            int sum = 0;
            int cur = 0;
            while (queue.size() > 1) {
                cur = queue.poll() + queue.poll();
                sum += cur;
                queue.offer(cur);
            }
            return sum;
        }

    }

    static class Code1 {
        static int lessMoney(int[] arr) {
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            for (int i = 0; i < arr.length; i++) {
                queue.offer(arr[i]);
            }
            int sum = 0;
            int cur = 0;
            while (queue.size() > 1) {
                cur = queue.poll() + queue.poll();
                sum += cur;
                queue.offer(cur);
            }
            return sum;
        }

    }

    static class Violence {
        // 纯暴力！
        public static int lessMoney(int[] arr) {
            if (arr == null || arr.length == 0) {
                return 0;
            }
            return process(arr, 0);
        }

        // 等待合并的数都在arr里，pre之前的合并行为产生了多少总代价
        // arr中只剩一个数字的时候，停止合并，返回最小的总代价
        public static int process(int[] arr, int pre) {
            if (arr.length == 1) {
                return pre;
            }
            int ans = Integer.MAX_VALUE;
            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    ans = Math.min(ans, process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]));
                }
            }
            return ans;
        }

        public static int[] copyAndMergeTwo(int[] arr, int i, int j) {
            int[] ans = new int[arr.length - 1];
            int ansi = 0;
            for (int arri = 0; arri < arr.length; arri++) {
                if (arri != i && arri != j) {
                    ans[ansi++] = arr[arri];
                }
            }
            ans[ansi] = arr[i] + arr[j];
            return ans;
        }

    }


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 6;
        int maxValue = 1000;
        System.out.println("start");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            if (Violence.lessMoney(arr) != Code1.lessMoney(arr)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("end!");
    }
}
