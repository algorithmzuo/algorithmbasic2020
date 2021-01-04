package leo.class13;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leo
 * @ClassName MaxHappy
 * @DATE 2020/12/16 9:01 上午
 * @Description 派对的最大快乐值
 *  公司的每个员工都符合 Employee 类的描述。整个公司的人员结构可以看作是一棵标准的、 没有环的多叉树。
 *  树的头节点是公司唯一的老板。除老板之外的每个员工都有唯一的直接上级。
 *  叶节点是没有任何下属的基层员工(subordinates列表为空)，除基层员工外，每个员工都有一个或多个直接下级。
 *  这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，规则：
 * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来
 * 2.派对的整体快乐值是所有到场员工快乐值的累加
 * 3.你的目标是让派对的整体快乐值尽量大
 * 给定一棵多叉树的头节点boss，请返回派对的最大快乐值
 */
public class MaxHappy {

    static class Code {
        public static int maxHappy(Employee node){
            if (node == null) {
                return 0;
            }
            Info info = process(node);
            return Math.max(info.no, info.yes);

        }
        public static Info process(Employee node) {
            if (node == null) {
                return new Info(0, 0);
            }
            int yes = node.happy;
            int no = 0;

            List<Employee> nexts = node.nexts;
            for (Employee next : nexts) {
                Info nextInfo = process(next);
                yes += nextInfo.no;
                no += Math.max(nextInfo.yes, nextInfo.no);
            }
            return new Info(no, yes);
        }

        static class Info {
            int no;
            int yes;

            public Info(int no, int yes) {
                this.no = no;
                this.yes = yes;
            }
        }
    }

    static class Code1 {
        static int maxHappy(Employee employee) {
            if (employee == null) {
                return 0;
            }
            Info info = process(employee);
            return Math.max(info.no, info.yes);
        }

        static Info process(Employee e) {
            if (e == null) {
                return new Info(0, 0);
            }
            int yes = e.happy;
            int no = 0;
            List<Employee> nexts = e.nexts;
            for (Employee next : nexts) {
                Info info = process(next);
                yes += info.no;
                no += Math.max(info.no, info.yes);
            }

            return new Info(yes, no);
        }

        static class Info {
            int yes;
            int no;

            public Info(int yes, int no) {
                this.yes = yes;
                this.no = no;
            }
        }
    }


    static class Logarithm {
        public static int maxHappy(Employee employee) {
            if (employee == null) {
                return 0;
            }
            return process(employee,false);
        }
        static int process(Employee e,boolean verify) {

            if (verify) {
                int ans = 0;
                List<Employee> nexts = e.nexts;
                for (Employee next : nexts) {
                    ans += process(next, false);
                }
                return ans;
            }
            int yes = e.happy;
            int no = 0;
            List<Employee> nexts = e.nexts;
            for (Employee next : nexts) {
                yes += process(next, true);
                no += process(next, false);
            }
            return Math.max(yes, no);

        }
    }



    public static void main(String[] args){
        int maxLevel = 5;
        int listSize = 4;
        int range = 1000;
        int testTime = 10000;
        System.out.println("start");
        for (int i = 0; i < testTime; i++) {
            Employee head = new Employee((int) (range * Math.random() + 1));
            generateNexts(head, 1, maxLevel, listSize, range);
            int i1 = Code1.maxHappy(head);
            int i2 = Logarithm.maxHappy(head);
            if (i1 != i2) {
                System.out.println("FUCK!!!");
                break;
            }
        }
        System.out.println("end");



    }

    static void generateNexts(Employee node, int i, int n, int s, int r) {
        if (i > n) {
            return;
        }
        int nextSize = (int) (s * Math.random()) + 1;
        for (int j = 0; j < nextSize; j++) {
            Employee e = new Employee((int) (r * Math.random() + 1));
            node.nexts.add(e);
            generateNexts(e, i + 1, n, s, r);
        }
    }
    static class Employee {
        public int happy;
        public List<Employee> nexts;

        public Employee(int h) {
            happy = h;
            nexts = new ArrayList<>();
        }

    }
}



