package leo.class04;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Leo
 * @ClassName CoverMax
 * @DATE 2020/11/30 4:19 下午
 * @Description 最大线段重合问题
 * 给定很多线段，每个线段都有两个数[start, end]，
 * 表示线段开始位置和结束位置，左右都是闭区间
 * 规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 */
public class CoverMax {

    /**
     * 线段
     */
    public static class Line{
        private int start;
        private int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class LineStartComparator implements Comparator<Line>{

        /**
         * 正数第二在前
         * 负数第一个在前
         */
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static int maxCover(int[][] arr) {
        Line[] lines = new Line[arr.length];
        for (int i = 0; i < arr.length; i++) {
            lines[i] = new Line(arr[i][0], arr[i][1]);
        }
        //根据start排序正序
        Arrays.sort(lines, new LineStartComparator());
        //默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < lines.length; i++) {
            while (!heap.isEmpty() && heap.peek() <= lines[i].start) {
                heap.poll();
            }
            heap.add(lines[i].end);
            max = Math.max(max, heap.size());
        }
        return max;
    }

}


class CoverMaxForTest{

    public static int maxCover(int[][] arr) {
        int min = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i][0]);
            max = Math.max(max, arr[i][1]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i][0] < p && arr[i][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }

        return cover;
    }

}

class CoverMaxMain {

    public static void main(String[] args) {
        int testTime = 1000;
        int sizeMax = 50;
        int L = 0;
        int R = 100;
        System.out.println("start");

        for (int i = 0; i < testTime; i++) {
            int[][] lines = generateLines(sizeMax, L, R);
            int max = CoverMax.maxCover(lines);
            int maxForTest = CoverMaxForTest.maxCover(lines);
            if (max != maxForTest) {
                System.out.println("max : " + max + " maxForTest : " + maxForTest);
                System.out.println("fuck!");
                break;
            }
        }
        System.out.println("end");

    }

    private static int[][] generateLines(int sizeMax, int L, int R) {
        int[][] arr = new int[(int) (sizeMax * Math.random() + 1)][2];

        for (int i = 0; i < arr.length; i++) {
            int a = (int) (L + ((R - L) + 1) * Math.random());
            int b = (int) (L + ((R - L) + 1) * Math.random());
            if (a == b) {
                a++;
            }
            arr[i][0] = Math.min(a, b);
            arr[i][1] = Math.max(a, b);
        }

        return arr;
    }


}
