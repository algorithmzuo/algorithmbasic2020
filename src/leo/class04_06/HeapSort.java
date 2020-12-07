package leo.class04_06;

import leo.util.ArrayUtil;
import sun.jvm.hotspot.debugger.Page;

import java.util.Arrays;

/**
 * @author Leo
 * @ClassName HeapSort
 * @DATE 2020/11/27 4:20 下午
 * @Description 堆排序
 * 最差时间复杂度为O(N*logN)
 * 数据量扩倍法推算 堆排序的时间复杂度下限是O(N*logN)
 * 上限和下限一样 所以时间复杂度为O(N*logN)
 * 建堆的时间复杂度是O(N*logN)
 * 每个数据插入的时间复杂度是O(logN)
 *
 * 从上往下建堆:O(N*logN) 一个一个数据插入只能从上往下建堆
 * 从下往上建堆:O(N) [错位相减]所有的数据都有,可以从下往上建堆
 * 堆结构要从上往下建堆
 * 堆排序要从下往上建堆
 * 堆排序额外空间复杂度O(1)
 *
 */
public class HeapSort {


    public static void heapSort(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;

        while (heapSize > 0) {
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }
        swap(arr, 0, heapSize);

    }

    private static void heapify(int[] arr, int i, int heapSize) {
        int left = i << 1 | 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[i] ? largest : i;
            if (largest == i) {
                break;
            }
            swap(arr, i, largest);
            i = largest;
            left = i << 1 | 1;
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
class HeapSort1{

    public static void heapSort(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        int length = arr.length;
        while (length > 0) {
            swap(arr, 0, --length);
            heapify(arr, 0, length);
        }
        heapify(arr, 0, length);
    }

    public static void heapify(int[] arr, int i, int heapSize) {
        int left = i << 1 | 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[i] ? largest : i;
            if (largest == i) {
                return;
            }
            swap(arr, largest, i);
            i = largest;
            left = i << 1 | 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        if (i == j || arr[i] == arr[j]) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}




class MainHeapSort {


    public static void main(String[] args){
        int testTime = 10000;
        int sizeMax = 70;
        int range = 50;
        System.out.println("start");

        for (int i = 0; i < testTime; i++) {
            int[] arr = ArrayUtil.randomArray(sizeMax, range);
            int[] copyArray = ArrayUtil.copyArray(arr);
            Arrays.sort(copyArray);
            HeapSort1.heapSort(arr);
            if (!ArrayUtil.isEqual(arr, copyArray)) {
                ArrayUtil.printArr(arr);
                ArrayUtil.printArr(copyArray);

                System.out.println("fuck!");
                break;

            }
        }
        System.out.println("end");

    }

}
