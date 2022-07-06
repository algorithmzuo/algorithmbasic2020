package class14;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 返回安排最多场次的次数
 * 分析：
 * 按照结束时间最早的来排序，遍历排序时间最小的元素
 * 把剩余元素中，开始时间早于元素结束时间的去掉
 * 求得场次最多的元素
 */
public class Code03_BestArrange_Study {
    public static class Program{
        public int start;
        public int end;
        public Program(int start,int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Program{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static int bestArrange1(Program[] programs ){
        if (programs == null || programs.length == 0) {
            return 0;
        }
        Arrays.sort(programs,new MyCompator());
        int done = 0;
        int timeEnd = 0;
        for (int i = 0;i < programs.length;i++){
            if (programs[i].start >= timeEnd ) {
                done++;
                timeEnd = programs[i].end;
            }
        }
        return done;
    }

    // 暴力求所有会议安排中，安排次数最多的
    // 使用递归，把每一个会议时间都和剩下的会议时间组合，看得到的最多会议安排数量
    public static int bestArrange2(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }
        return process(programs,0,0);
    }

    /**
     * 通过递归比较计算每个时间段和剩下时间段组合的最多会议次数
     * @param programs 所有的会议安排
     * @param endTime 结束时间
     * @param done 已经安排的次数
     * @return
     */
    public static int process(Program[] programs,int endTime,int done){
        if (programs.length == 0) {
            return done;
        }
        int max = done;
        for (int i = 0;i < programs.length;i++) {
            if (programs[i].start >= endTime) {
                Program program = programs[i];
                Program[] next = removeIndexElement(programs, i);
                max = Math.max(max,process(next,program.end,done + 1));
            }
        }
        return max;
    }

    public static Program[] removeIndexElement(Program[] programs,int index) {
        int N = programs.length - 1;
        Program[] newPro = new Program[N];
        int curI = 0;
        for (int i = 0;i < programs.length;i++) {
            if (i != index) {
                newPro[curI++] = programs[i];
            }
        }
        return newPro;
    }

    public static Program[] genreateRandomProgram(int maxSize,int maxEnd) {
        Program[] programs = new Program[(int)(Math.random() * maxSize +1)];
        for (int i = 0;i < programs.length;i++) {
            int value1 = (int)(Math.random() * maxEnd);
            int value2 = (int)(Math.random() * maxEnd);
            Program program ;
            if(value1 == value2) {
                program = new Program(value1,value1 + 1);
            }else {
                program = new Program(Math.min(value1,value2),Math.max(value1,value2));
            }
            programs[i] = program;
        }
        return programs;
    }


    public static class MyCompator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    public static Program[] copyArr(Program[] programs) {
        Program[] newPro = new Program[programs.length];
        for (int i = 0; i < programs.length; i++) {
            int start = programs[i].start;
            int end = programs[i].end;
            newPro[i] = new Program(start, end);
        }
        return newPro;
    }

    public static void main(String[] args) {
        int testTime = 10000;
        int maxSize = 5;
        int maxVlue = 20;
        for (int i = 0;i < testTime;i++) {
            Program[] programs = genreateRandomProgram(maxSize,maxVlue);
            Program[] programs1 = copyArr(programs);
            int ans1 = bestArrange1(programs);
            int ans2 = bestArrange2(programs1);
            if ( ans1 !=  ans2) {
                for (Program program : programs1) {
                    System.out.print(program);
                }
                System.out.print("ans1:"+ans1+",ans2:"+ans2);
                System.out.println("oop");
            }
        }
        System.out.println("finish");
    }
}
