package class02;

import java.util.*;

/**
 * 求数组中一个数出现K次，其他数出现M次，求这个出现K次的数(K < M,否则会出现对M取余后的值小于K)
 * 1.将每个数转为二进制，对所有数据每个二进制上为1的进行累加
 * 2.每个1位置上的数量对M取余，如果余数为0，则该位置上都是出现M次的数
 * 3.循环执行第二步，最后得到次数为k数据的二进制式即可得到该数
 * 4.该方法和直接使用内置函数处理先比，空间复杂度为1.
 */
public class Code03_KM_Study {
    private static int getKTimesNum(int[] arr,int m,int k){
        if(map.size() == 0) {
            generateMap();
        }
        // 1.统计每个数据二进制位中为1的数量
        int[] t = new int[32]; //用来统计每个位置为1的个数
        for (int num : arr){
            while (num!= 0) {
                int rightOne = num & (-num);
                t[map.get(rightOne)]++;
                num ^= rightOne;
            }
        }
        // 2.对位数取余，获取出现奇数次的数
        int result = 0;
        for (int i = 0 ;i < t.length;i++){
            if ((t[i] % m) != 0) {
                if (t[i] % m == k) {
                    result |= (1 << i);
                } else {
                    return -1;
                }
            }
        }

        // 3.如果result为0，还需要校验下实际中0的个数是不是等于k，因为这里对M取余后，有可能数据都是M次的，导致结果为0
        if (result == 0) {
            int count = 0;
            for (int value : arr) {
                if (value == 0) {
                    count++;
                }
            }
            if (count != k) {
                return -1;
            }
        }
        return result;
    }

    private static Map<Integer,Integer> map = new HashMap<>();

    /**
     * 这里将数据的32位二进制每个数位和数组的下标对应起来
     */
    private static void generateMap(){
        int key = 1;
        for (int i = 0;i < 32;i++) {
            map.put(key,i);
            key <<= 1;
        }
    }

    private static int test(int[] arr,int k,int m){
        Map<Integer,Integer> map = new HashMap<>();
        for (int value : arr){
            if (map.containsKey(value)){
                map.put(value,map.get(value) + 1);
            }else {
                map.put(value,1);
            }
        }
        for (int key : map.keySet()) {
            if (map.get(key) == k) {
                return key;
            }
        }
        return -1;
    }

    /**
     * 产生随机数
     * @param maxKind 最多有多少种数
     * @param maxValue 数组中的值得范围[-maxValue,maxvalue]
     * @param k 出现k次的数
     * @param m 出现m次的数
     * @return
     */
    private static int[] generateArr(int maxKind,int maxValue,int k,int m){
        // 获取一种出现k次和其他出现m次的数组
        int kValue = generateRandomNum(maxValue);
        // 真命天子出现的次数,通过随机数产生当找不到数据时是不是一样
        int kValueTimes = Math.random()  < 0.5 ? k : (int)((Math.random() * (m-1)) +1);
        // 一共出现多少种数
        int allValueType =(int) (Math.random() * maxKind) + 2;
        int[] arr = new int[kValueTimes + (allValueType - 1) * m];
        int index = 0;
        for (;index < kValueTimes;index++) {
            arr[index] = kValue;
        }
        allValueType--;
        //这里要对生成的随机数进行去重
        HashSet<Integer> set = new HashSet<>();
        set.add(kValue);
        while (allValueType > 0) {
            int mValue;
            do {
               mValue = generateRandomNum(maxValue);
            }while (set.contains(mValue));
            set.add(mValue);
            allValueType--;
            for (int i =0 ;i < m;i++) {
                arr[index++] = mValue;
            }
        }
        System.out.println("原数组：" + Arrays.toString(arr));
        // 将数组随机排列
        for (int i= 0 ;i < arr.length;i++) {
            int j = (int)(Math.random() * arr.length);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j]= temp;
        }
        return  arr;
    }

    /**
     * 生成随机数
     * @param range 随机数范围[-range,range]
     * @return
     */
    private static int generateRandomNum(int range) {
        return (int)(Math.random() * (range + 1)) - (int)(Math.random() * range);
    }

    public static void main(String[] args) {
        int time = 5000;
        int maxValue = 30;
        int max = 9;
        int kinds = 5;
        boolean isSuccess = true;
        for (int i = 0 ;i < time;i++) {
            int a = (int)(Math.random() * max) + 1; // k,m取值范围在1~9之间
            int b = (int)(Math.random() * max) +1;
            // 保证 m > k
            int m = Math.max(a,b);
            int k = Math.min(a,b);
            if (k == m){
                m++;
            }
            int[] arr = generateArr(kinds,maxValue,k,m);
            int kTimeNum = getKTimesNum(arr,m,k);
            int kTimeNumCompare = test(arr,k,m);
            if ( kTimeNum != kTimeNumCompare) {
                System.out.println(Arrays.toString(arr));
                System.out.println(kTimeNum + ":" + kTimeNumCompare);
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "success" : "false");
    }




}
