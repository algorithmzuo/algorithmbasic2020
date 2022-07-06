package class14;

import java.util.HashSet;

/**
 * 点灯问题
 * 1.如果i位置是一个X，该位置不需要点亮，直接去到下一个点
 * 2.如果i位置是一个.，i+1位置是X ，那么i位置需要放灯，不然i就无法照亮（灯 +1，i去到 i+2）
 * 3.如果i+1位置是一个.，那么考虑i+2位置，如果i+2位置是X，那么灯可以在i位置或者i+1位置点亮（灯+1，i去到i+3）
 * 4.如果i+2位置是一个.，那么此时i，i+1，i+2都是.，那么此时灯放在i+1位置（灯 +1，i去到i+4）
 */
public class Code01_Light_Study {
    public static int minLight1(String road) {
        if (road == null || road == "") {
            return 0;
        }
        return 0;
    }

    /**
     * 暴力方法
     * @param arr
     * @param index
     * @param lights
     * @return
     */
    public static int process(String[] arr, int index, HashSet<Integer> lights){
        if (index== arr.length) {
            for (int i = 0;i < arr.length;i++) {
                if (arr[i] != "x"){
                    // 如果说作为圆点，那么就意味着这个位置前后，包括本身必须要有一盏灯是亮的
                    if (!lights.contains(i-1) && !lights.contains(i)&& !lights.contains(i+1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        }else {
            int no = process(arr,index + 1,lights);
            int yes = Integer.MAX_VALUE;
            if (arr[index] == ".") {
                lights.add(index);
                yes = process(arr,index + 1,lights);
                lights.remove(index);
            }
            return Math.min(no,yes);
        }
    }
}
