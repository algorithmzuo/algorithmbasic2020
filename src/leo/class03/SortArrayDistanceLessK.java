package leo.class03;

import leo.util.ArrayUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author Leo
 * @ClassName SortArrayDistanceLessK
 * @DATE 2020/11/27 6:28 下午
 * @Description
 * 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的
 */
public class SortArrayDistanceLessK {


    public static void sortArrayDistanceLessK(int[] arr, int k) {
        if (arr.length < 2 || arr == null || k == 0) {
            return;
        }
        //默认是小根堆
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int index = 0;
        for (; index < Math.min(arr.length - 1, k - 1); index++) {
            queue.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            queue.add(arr[index]);
            arr[i] = queue.poll();
        }
        while (!queue.isEmpty()) {
            arr[i++] = queue.poll();
        }
    }

}


class MainK {


    public static void main(String[] args){
        int testTime = 1000;
        int sizeMax = 30;
        int range = 50;
        System.out.println("start!");
        for (int i = 0; i < testTime; i++) {
            int[] arr = ArrayUtil.randomSortArray(sizeMax, range);

            int k = (int) (Math.random() * arr.length-1) + 1;

            UpsetArray(arr, k);
            int[] copyArray = ArrayUtil.copyArray(arr);
            Arrays.sort(copyArray);
            SortArrayDistanceLessK.sortArrayDistanceLessK(arr,k);
            if (!ArrayUtil.isEqual(arr, copyArray)) {
                ArrayUtil.printArr(arr);
                ArrayUtil.printArr(copyArray);
                System.out.println("fuck");
                break;
            }
        }
        System.out.println("end!");

    }

    public static void UpsetArray(int[] arr, int k) {
        Set<Integer> indexSet = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            int indexK = 0;
            while (indexK < k && i < arr.length) {
                int j;
                do {
                    j = (int) (i + (Math.random() * k));
                } while (j > arr.length - 1);
                if (!indexSet.contains(j) && !indexSet.contains(i)) {
                    indexSet.add(i);
                    indexSet.add(j);
                    swap(arr, i, j);
                }
                i++;
            }
            i += k;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j || arr[i] == arr[j]) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
