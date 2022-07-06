package class13;

import java.util.ArrayList;
import java.util.List;

/**
 * 最大快乐值得问题；
 * 假设x 的下属有 a,b,c三个人
 * 1.x来的情况下
 *  快乐值=x.happy + a不来.happy + b不来.happy + c不来.happy
 * 2.x不来的情况下
 *  快乐值 = Max(a来.happy,a不来.happy) +Max(b来.happy,b不来.happy)+Max(c来.happy,c不来.happy)
 */
public class Code04_MaxHappy_Study {
    public static class Employee{
        public int happy;
        public List<Employee> nexts;
        public Employee(int happy){
            this.happy = happy;
            this.nexts = new ArrayList<>();
        }
    }

    public static int maxHappy1(Employee boss) {
        if (boss ==null) {
            return 0;
        }
        return prcess1(boss,false);
    }

    /**
     * 每一位员工来，或者不来产生的最大快乐值
     * @param come 代表当前员工的上级是否来，true 来，false 不来
     * @return
     */
    public static int prcess1(Employee employee,boolean come) {
        // 如果上级不来，那么员工可来可不来，如果上级来，那么该员工就不能来
        if (come) {
            // 上级来，最大快乐值等于下属员工不来的快来值之和
            int ans = 0;
            for (Employee next : employee.nexts) {
                ans += prcess1(next,false);
            }
            return ans;
        }else {
            // 上级员工不来，那么员工可来，可不来，这里去最大值
            int p1 = employee.happy;
            int p2 = 0;
            for (Employee next : employee.nexts) {
                p1 += prcess1(next,true);
                p2 += prcess1(next,false);
            }
            return Math.max(p1,p2);
        }
    }

    /**
     * 每个雇员来的最大值，和不来的最大值
     */
    public static class Info{
        public int yes;
        public int no;
        public Info(int yes,int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    public static Integer maxHappy2(Employee boss) {
        Info info = process2(boss);
        return Math.max(info.yes,info.no);
    }

    public static Info process2(Employee boss){
        if (boss == null) {
            return new Info(0,0);
        }
        int p1 = boss.happy;
        int p2 = 0;

        for (Employee next : boss.nexts) {
            Info info = process2(next);
            p1 += process2(next).no;
            p2 += Math.max(info.yes,info.no);
        }
        return new Info(p1,p2);
    }

    /**
     * 构建随机无环多叉树
     */
    public static Employee generateBoss(int maxLevel,int maxHappy,int maxNextSize) {
        if (Math.random() < 0.2) {
            return  null;
        }
        Employee boss = new Employee((int)(Math.random() * maxHappy + 1)) ;
        generateNexts(boss,1,maxLevel,maxHappy,maxNextSize);
        return boss;
    }

    public static void generateNexts(Employee em,int curLevel,int maxLevel,int maxHappy,int maxNexSize) {
        if (curLevel > maxLevel) {
            return;
        }
        int randomNextSize = (int) (Math.random() * maxNexSize + 1);
        for (int i = 0;i < randomNextSize;i++) {
            Employee next = new Employee((int)(Math.random() * maxHappy + 1)) ;
            em.nexts.add(next);
            generateNexts(next,curLevel + 1,maxLevel,maxHappy,maxNexSize);
        }
    }

    public static void main(String[] args) {
        int testTime = 1000;
        int maxVlue = 10;
        int maxLevel = 4;
        int maxNextSize = 3;
        for (int i = 0;i < testTime;i++) {
            Employee employee = generateBoss(maxLevel,maxVlue,maxNextSize);
            if (maxHappy1(employee) != maxHappy2(employee)) {
                System.out.println("oop");
            }
        }
        System.out.println("finish");
    }

}
