package class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 获取得奖名单
 *
 */
public class Code02_EveryStepShowBoss_Study {
    public static class Customer{
        public int id;
        public int buy;
        public int enterTime;
        public Customer(int id,int buy){
            this.id = id;
            this.buy = buy;
            this.enterTime = 0;
        }
    }

    /**
     * 候奖区需要根据购买数量从大到小排序，如果购买数量一致，则根据时间排序，时间长的在前面
     */
    public static class CandidateCompator implements Comparator<Customer> {
        @Override
        public int compare(Customer o1, Customer o2) {
            return o2.buy != o1.buy ? o2.buy-o1.buy :o1.enterTime - o2.enterTime;
        }
    }
    /**
     * 领奖区需要根据购买数量从大到小排序，如果购买数量一致，则根据时间排序，时间长的在前面
     */
    public static class DaddyCompator implements Comparator<Customer> {
        @Override
        public int compare(Customer o1, Customer o2) {
            return o2.buy != o1.buy ? o1.buy-o2.buy :o1.enterTime - o2.enterTime;
        }
    }

    public static class WhosYourDaddy{
        private HashMap<Integer,Customer> customer;
        private HeapGreater_Study<Customer> daddy;
        private HeapGreater_Study<Customer> candidate;
        private final int daddyLimit;

        public WhosYourDaddy(int daddyLimit){
            customer = new HashMap<>();
            daddy = new HeapGreater_Study<>(new DaddyCompator());
            candidate = new HeapGreater_Study<>(new CandidateCompator());
            this.daddyLimit = daddyLimit;
        }

        /**
         * 1.判断用户是不是之前有买过，是否在之前的名单，如果不在还发生了退货操作则该次操作无效
         * 2.根据操作，增加或减少用户的购买量
         * 3.根据领奖区的限制，把该用户插入领奖区或候奖区
         * 4.调整领奖区和候奖区
         * @param time
         * @param id
         * @param op
         */
        public void operate(int time,int id,boolean op) {
            if (!op && !customer.containsKey(id)) {
                return;
            }
            if (!customer.containsKey(id)) {
                customer.put(id,new Customer(id,0));
            }
            Customer curCus = customer.get(id);
            if (op) {
                curCus.buy++;
            }else {
                curCus.buy--;
            }
            if (curCus.buy == 0) {
                customer.remove(id);
            }
            if (!candidate.contains(curCus) && !daddy.contains(curCus) ) {
                curCus.enterTime = time;
                if (daddy.size() < daddyLimit) {
                    daddy.push(curCus);
                }else {
                    candidate.push(curCus);
                }
            }else if (candidate.contains(curCus)){
                if (curCus.buy == 0) {
                    candidate.remove(curCus);
                }else {
                    candidate.resign(curCus);
                }
            }else {
                if (curCus.buy == 0) {
                    daddy.remove(curCus);
                }else {
                    daddy.resign(curCus);
                }
            }
            daddyMove(time);
        }

        public void daddyMove(int time) {
            if (candidate.isEmpty()) {
                return;
            }
            if (daddy.size() < daddyLimit) {
                Customer cus = candidate.pop();
                cus.enterTime = time;
                daddy.push(cus);
            }else {
                if (daddy.peek().buy < candidate.peek().buy) {
                    Customer orgionDad = daddy.pop();
                    Customer origionCand = daddy.pop();
                    orgionDad.enterTime = time;
                    origionCand.enterTime = time;
                    candidate.push(orgionDad);
                    daddy.push(origionCand);
                }
            }
        }

    }

    /**
     * 这是暴力破解，没有优化的解题方法
     * @param arr 用户id数组
     * @param op 用户对应操作
     * @param k 领奖区限制人数
     * @return
     */
    public static List<List<Integer>> compator(int[] arr,boolean[] op,int k){
        // 用户id和用户详情的映射关系表
        HashMap<Integer,Customer> map = new HashMap<>();
        // 领奖区
        ArrayList<Customer> dads = new ArrayList<>();
        // 候奖区
        ArrayList<Customer> candiate = new ArrayList<>();
        // 每个事件对应的候奖名单
        List<List<Integer>> ans = new ArrayList<>();

        for (int i =0;i< arr.length;i++) {
            int id = arr[i];
            boolean buyOrRefund = op[i];
            if (!buyOrRefund && !map.containsKey(id)) {
                ans.add(getCurAns(dads));
                continue;
            }
            // 用户购买数量为0，发生退货，该事件不发生
            // 用户之前购买数量0，此时发生购买
            // 用户购买数量>0，发生购买
            // 用户购买数量<0，发生购买
            if (!map.containsKey(id)) {
                map.put(id,new Customer(id,0 ));
            }
            Customer customer = map.get(id);
            if (op[i]) {
                customer.buy++;
            }else {
                customer.buy--;
            }
            if (customer.buy == 0) {
                map.remove(id);
            }
            if (!dads.contains(customer) && !candiate.contains(customer)) {
                if (dads.size() < k) {
                    customer.enterTime = i;
                    dads.add(customer);
                } else {
                    customer.enterTime = i;
                    candiate.add(customer);
                }
            }
            cleanZeroBuy(candiate);
            cleanZeroBuy(dads);

            dads.sort(new DaddyCompator());
            candiate.sort(new CandidateCompator());
            move(dads,candiate,i,k);
            ans.add(getCurAns(dads));
        }
        return ans;
    }

    public static void cleanZeroBuy(ArrayList<Customer> arr) {
        List<Customer> noZero = new ArrayList<Customer>();
        for (Customer c : arr) {
            if (c.buy != 0) {
                noZero.add(c);
            }
        }
        arr.clear();
        for (Customer c : noZero) {
            arr.add(c);
        }
    }

    public static List<Integer> getCurAns(ArrayList<Customer> dads) {
        List<Integer> ans = new ArrayList<>();
        for (Customer cus : dads) {
            ans.add(cus.id);
        }
        return ans;
    }

    /**
     * 当有一个事件发生时，这里需要重新把数据调整下。候奖区中有购买数量大于领奖区，候奖区和领奖区有购买数量相等，但是
     * 老用户等待时间更长的
     * @param dad 领奖区
     * @param candidate 候奖区
     * @param time 进入时间
     * @param k 领奖区人数
     */
    public static void move(ArrayList<Customer> dad,ArrayList<Customer> candidate,int time,int k){
        if (candidate.isEmpty()) {
            return;
        }
        if (dad.size() < k) {
            Customer cus = candidate.get(0);
            cus.enterTime = time;
            dad.add(cus);
            candidate.remove(cus);
        }else {
            Customer origionDad = dad.get(0);
            Customer candi = candidate.get(0);
            if (origionDad.buy < candi.buy) {
                candidate.remove(candi);
                dad.remove(origionDad);
                candi.enterTime = time;
                origionDad.enterTime = time;
                dad.add(candi);
                candidate.add(origionDad);
            }
        }
    }
    // 为了测试
    public static Code02_EveryStepShowBoss.Data randomData(int maxValue, int maxLen) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] arr = new int[len];
        boolean[] op = new boolean[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
            op[i] = Math.random() < 0.5 ? true : false;
        }
        return new Code02_EveryStepShowBoss.Data(arr, op);
    }
    public static List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        Code02_EveryStepShowBoss.WhosYourDaddy whoDaddies = new Code02_EveryStepShowBoss.WhosYourDaddy(k);
        for (int i = 0; i < arr.length; i++) {
            whoDaddies.operate(i, arr[i], op[i]);
            ans.add(whoDaddies.getDaddies());
        }
        return ans;
    }

    // 为了测试
    public static boolean sameAnswer(List<List<Integer>> ans1, List<List<Integer>> ans2) {
        if (ans1.size() != ans2.size()) {
            return false;
        }
        for (int i = 0; i < ans1.size(); i++) {
            List<Integer> cur1 = ans1.get(i);
            List<Integer> cur2 = ans2.get(i);
            if (cur1.size() != cur2.size()) {
                return false;
            }
            cur1.sort((a, b) -> a - b);
            cur2.sort((a, b) -> a - b);
            for (int j = 0; j < cur1.size(); j++) {
                if (!cur1.get(j).equals(cur2.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int maxValue = 10;
        int maxLen = 100;
        int maxK = 6;
        int testTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Code02_EveryStepShowBoss.Data testData = randomData(maxValue, maxLen);
            int k = (int) (Math.random() * maxK) + 1;
            int[] arr = testData.arr;
            boolean[] op = testData.op;
            List<List<Integer>> ans1 = topK(arr, op, k);
            List<List<Integer>> ans2 = compator(arr, op, k);
            if (!sameAnswer(ans1, ans2)) {
                for (int j = 0; j < arr.length; j++) {
                    System.out.println(arr[j] + " , " + op[j]);
                }
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
