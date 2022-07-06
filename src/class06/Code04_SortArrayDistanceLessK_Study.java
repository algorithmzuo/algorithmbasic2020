package class06;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的。
 *
 * 解法：
 * 1，先limit长度的数据放到堆里去
 * 2.然后弹出最小值放到arr[0]位置
 * 3.继续放一个数据到堆里去，弹出最小值，放单arr[1]位置，以此类推
 * 4.最后把堆里剩余的数填回到数组末尾
 */
public class Code04_SortArrayDistanceLessK_Study {
    public static int[] sortArrayDistanceLessK(int[] arr,int limit) {
        if (limit == 0) {
            return null;
        }
        int[] sortArr = new int[arr.length];
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (;index < Math.min(arr.length,limit);index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (;index < arr.length;i++,index++) {
            sortArr[i] = heap.poll();
            heap.add(arr[index]);
        }
        while (!    heap.isEmpty()) {
            sortArr[++i] = heap.poll();
        }
        return sortArr;
    }

    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static void main(String[] args) {

    }
}
