package class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code01_CoverMax_Study {
    /**
     * 每个区域取开始端+小数，保证不出现整数位置重合导致统计不准确
     * @param arr
     * @return
     */
    public static int coverMax1(int[][] arr){
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0;i < arr.length;i++) {
            min = Math.min(min,arr[i][0]);
            max = Math.max(max,arr[i][1]);
        }

        int cover = 0;
        for (double p = min + 0.5;p < max;p+=1) {
            int cur = 0;
            for (int j = 0; j < arr.length;j++){
                if (arr[j][0] < p && arr[j][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover,cur);
        }
        return cover;
    }

    /**
     * 使用最小堆来做
     * @param m
     * @return
     */
    public static int maxConver2(int[][] m) {
        Line[] line = new Line[m.length];
        for (int i = 0;i < line.length;i++) {
            line[i] = new Line(m[i][0],m[i][1]);
        }
        Arrays.sort(line,new EndComparator());

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (int i  = 0;i < line.length;i++) {
            while (!heap.isEmpty() && line[i].start >= heap.peek()) {
                heap.poll();
            }
            heap.add(line[i].end);
            max = Math.max(max,heap.size());
        }
        return max;
    }

    public static class EndComparator implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static class Line{
        public int start;
        public int end;

        public Line(int s,int e) {
            start = s;
            end = e;
        }
    }

    public static int[][] generateRandomLine(int n,int L,int R ) {
        int size = (int)(Math.random() * (n + 1));
        int[][] arr = new int[size][2];
        for (int i = 0 ;i < size;i++) {
            int a = L + (int)(Math.random() * (R - L + 1));
            int b = L + (int)(Math.random() * (R - L + 1));
            if(a == b) {
                b = a + 1;
            }
            arr[i][0] = Math.min(a,b);
            arr[i][1] = Math.max(a,b);
        }
        return arr;
    }

    public static void main(String[] args) {
        int n = 10;
        int L = 0;
        int R = 10;
        int testTime = 500;
        for (int i=0;i < testTime;i++) {
            int[][] arr = generateRandomLine(n,L,R);
            int ans1 = coverMax1(arr);
            int ans2 = maxConver2(arr);
            if (ans1 != ans2) {
                System.out.println("oops");
                System.out.println("ans1"+ans1);
                System.out.println("ans2"+ans2);

                for (int j = 0; j < arr.length;j++) {
                    System.out.println(arr[j][0] +":"+arr[j][1]);
                }
            }
        }
        System.out.println("test end");
    }
}
