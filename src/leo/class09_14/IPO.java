package leo.class09_14;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Leo
 * @ClassName IPO
 * @DATE 2020/12/22 5:17 下午
 * @Description
 * 输入: 正数数组costs、正数数组profits、正数K、正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
 * 输出：你最后获得的最大钱数。
 */
public class IPO {


    /**
     * 功能描述 :
     * @author Leo
     * @date 2020/12/22 5:56 下午
     * @param K 能做几次项目
     * @param W 初始资金
     * @param Profits 收益
     * @param Capital 成本
     * @throw
     * @return int
     */
    public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {

        PriorityQueue<Program> minC = new PriorityQueue<>(new MinCComparator());
        PriorityQueue<Program> maxP = new PriorityQueue<>(new MaxPComparator());
        for (int i = 0; i < Profits.length; i++) {
            minC.add(new Program(Profits[i], Capital[i]));
        }
        for (int i = 0; i < K; i++) {
            while (!minC.isEmpty() && minC.peek().c <= W) {
                maxP.add(minC.poll());
            }
            if (maxP.isEmpty()) {
                return W;
            }
            W += maxP.poll().p;
        }
        return W;
    }
    static class MinCComparator implements Comparator<Program> {
        public int compare(Program p1, Program p2) {
            return p1.c - p2.c;
        }
    }

    static class MaxPComparator implements Comparator<Program> {
        public int compare(Program p1,Program p2) {
            return p2.p - p1.p;
        }

    }

    static class Program {
        //收益
        int p;
        //成本
        int c;

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }
}
