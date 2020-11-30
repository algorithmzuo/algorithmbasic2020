package leo.class03;

import com.sun.org.apache.bcel.internal.generic.POP2;
import com.sun.xml.internal.bind.v2.model.core.ID;
import leo.util.ArrayUtil;

import java.util.Arrays;
import java.util.Stack;
import java.util.function.IntPredicate;

/**
 * @author Leo
 * @ClassName QuickSort1
 * @DATE 2020/11/26 11:17 上午
 * @Description 快排1.0
 * O(N²) 最差情况
 * 范围内最后一个数为比较值x,[<=x>],x一定在左侧的最后一个位置,返回比较值得下标
 * 只记录右侧最小位置下标,如果index的值小于或等于右侧最后位置的数,
 * index与最小位置的数后一位交换,最小位置下标更新
 *
 * T(N) = a * T(N/b) + O(N^d)(其中的a、b、d都是常数)
 * 的递归函数，可以直接通过Master公式来确定时间复杂度
 * 如果 log(b,a) < d，复杂度为O(N^d)
 * 如果 log(b,a) > d，复杂度为O(N^log(b,a))
 * 如果 log(b,a) == d，复杂度为O(N^d  * logN)
 *
 */
class QuickSort1 {
    public static void quickSort(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int m = partition(arr, l, r);
        process(arr, l, m-1);
        process(arr, m + 1, r);
    }

    private static int partition(int[] arr, int l, int r) {
        if (l > r) {
            return -1;
        }
        if (l == r) {
            return l;
        }
        int less = l-1;
        int index = l;
        while (index < r) {
            if (arr[index] <= arr[r]) {
                swap(arr, index, ++less);
            }
            index++;
        }
        swap(arr, r, ++less);
        return less;
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


/**
 * @author Leo
 * @ClassName QuickSort1
 * @DATE 2020/11/26 11:17 上午
 * @Description 快排2.0
 * O(N²) 最差情况
 * 中间位置固定,左右两边有序
 *
 */
class QuickSort2 {

    public static void quickSort(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] equalsArea = partition(arr, l, r);
        process(arr, l, equalsArea[0] - 1);
        process(arr, equalsArea[1] + 1, r);
    }

    //netherlandsFlag
    //荷兰国旗问题
    private static int[] partition(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }

        int leftIndex = l - 1;
        int rightIndex = r;
        int index = l;
        while (index < rightIndex) {
            if (arr[index] < arr[r]) {
                swap(arr, index++, ++leftIndex);
            } else if (arr[index] > arr[r]) {
                swap(arr, index, --rightIndex);
            }else{
                index++;
            }
        }
        swap(arr, r, rightIndex);
        return new int[]{leftIndex+1, rightIndex};
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


/**
 * @author Leo
 * @ClassName QuickSort1
 * @DATE 2020/11/26 11:17 上午
 * @Description 快排3.0
 * O(N*logN)
 * 额外空间复杂度o(n)
 *
 */
class QuickSort3 {

    public static void quickSort(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        swap(arr, (int) (l + (Math.random() * (r - l + 1))), r);
        int[] equalArea = partition(arr, l, r);
        process(arr, l, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, r);
    }

    public static int[] partition(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }
        int leftIndex = l - 1;
        int rightIndex = r;
        int index = l;
        while (index < rightIndex) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] < arr[r]) {
                swap(arr, index++, ++leftIndex);
            } else if (arr[index] > arr[r]) {
                swap(arr, index, --rightIndex);
            }
        }
        swap(arr, r, rightIndex);

        return new int[]{leftIndex + 1, rightIndex};
    }

    public static void swap(int[] arr, int i, int j) {
        if (arr[i] == arr[j] || i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}


class QuickSort3_1{

    public static void quickSort(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return;
        }

        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int l, int r) {

        if (l >= r) {
            return;
        }

        swap(arr, (int) (l + ((r - l + 1) * Math.random())), r);
        int[] equalArea = partition(arr, l, r);
        process(arr, l, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, r);

    }

    private static int[] partition(int[] arr, int l, int r) {
        if (l == r) {
            return new int[]{l, r};
        }
        int leftIndex = l - 1;
        int rightIndex = r;
        int index = l;
        while (index < rightIndex) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] > arr[r]) {
                swap(arr, index, --rightIndex);
            } else if (arr[index] < arr[r]) {
                swap(arr, index++, ++leftIndex);
            }
        }
        swap(arr, r, rightIndex);
        return new int[]{leftIndex + 1, rightIndex};

    }


    public static void swap(int[] arr, int i, int j) {
        if (arr[i] == arr[j] || i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];

    }


}


class QuickSort3_2{

    public static void quickSort(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        swap(arr, (int) (l + ((r - l + 1) * Math.random())), r);
        int[] equalArea = partition(arr, l, r);
        process(arr, l, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, r);
    }

    private static int[] partition(int[] arr, int l, int r) {
        if (l == r) {
            return new int[]{l, r};
        }
        int leftIndex = l - 1;
        int rightIndex = r;
        int index = l;
        while (index < rightIndex) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] > arr[r]) {
                swap(arr, index, --rightIndex);
            } else if (arr[index] < arr[r]) {
                swap(arr, index++, ++leftIndex);
            }
        }
        swap(arr, r, rightIndex);
        return new int[]{leftIndex + 1, rightIndex};


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

class QuickSort3_3{

    public static void quickSort(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] equalArea = partition(arr, l, r);
        process(arr, l, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, r);
    }

    public static int[] partition(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }
        int leftIndex = l - 1;
        int rightIndex = r;
        int index = l;
        while (index < rightIndex) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] > arr[r]) {
                swap(arr, index, --rightIndex);
            } else if (arr[index] < arr[r]) {
                swap(arr, index++, ++leftIndex);
            }
        }
        swap(arr, r, rightIndex);
        return new int[]{leftIndex + 1, rightIndex};
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

class QuickSortUnRecursive{

    private static class Op {
        public int l;
        public int r;

        public Op(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public static void quickSort(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return;
        }
        swap(arr, arr.length - 1, (int) (arr.length * Math.random()));
        int[] equalArea = partition(arr, 0, arr.length - 1);
        Stack<Op> stack = new Stack<>();
        int l = equalArea[0] - 1;
        int r = equalArea[1] + 1;
        stack.push(new Op(0, l));
        stack.push(new Op(r, arr.length - 1));
        while (!stack.isEmpty()) {
            Op pop = stack.pop();
            int pl = pop.l;
            int pr = pop.r;
            if (pl < pr) {
                swap(arr, (int) (pl + (Math.random() * (pr - pl + 1))), pr);
                equalArea = partition(arr, pl, pr);
                l = equalArea[0] - 1;
                r = equalArea[1] + 1;
                stack.push(new Op(pl, l));
                stack.push(new Op(r, pr));
            }
        }

    }



    public static int[] partition(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l==r){
            return new int[]{l, r};
        }
        int leftIndex = l - 1;
        int rightIndex = r;
        int index = l;
        while (index < rightIndex) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] < arr[r]) {
                swap(arr, index++, ++leftIndex);
            } else if (arr[index] > arr[r]) {
                swap(arr, index, --rightIndex);
            }
        }
        swap(arr, r, rightIndex);
        return new int[]{leftIndex + 1, rightIndex};
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


class TestMain {


    public static void main(String[] args){
        int testTimes = 1000;
        int sizeMax = 50;
        int range = 50;
        System.out.println("start");

        for (int i = 0; i < testTimes; i++) {
            int[] arr = ArrayUtil.randomArray(sizeMax, range);
            int[] copyArray = ArrayUtil.copyArray(arr);
            QuickSort3_3.quickSort(arr);
            Arrays.sort(copyArray);
            if (!ArrayUtil.isEqual(arr, copyArray)) {
                ArrayUtil.printArr(arr);
                ArrayUtil.printArr(copyArray);
                System.out.println("fuck");
                break;
            }
        }
        System.out.println("end");

    }

}
