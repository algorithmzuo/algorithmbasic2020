package leo.class04;

import leo.util.ArrayUtil;

/**
 * @author Leo
 * @ClassName ReversePair
 * @DATE 2020/11/23 9:48 下午
 * @Description 在一段数组中 返回有多少逆序对
 * 合并的时候 两侧下标--
 * 任何一个前面的数a，和任何一个后面的数b，
 * 如果(a,b)是降序的
 */
public class ReversePair {

    public static int reversePairNumber(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {

        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, 0, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = help.length - 1;
        int p1 = m;
        int p2 = r;
        int res = 0;
        while (p1 >= l && p2 > m) {
            res += arr[p1] > arr[p2] ? (p2 - m) : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l) {
            help[i--] = arr[p1--];
        }
        while (p2 > m) {
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }
}

class ReversePair1{

    public static int reversePairNumber(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {

        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int l, int m, int r) {

        int[] help = new int[r - l + 1];
        int i = help.length - 1;
        int p1 = m;
        int p2 = r;
        int res = 0;
        while (p1 >= l && p2 > m) {
            res += arr[p1] > arr[p2] ? (p2 - m) : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l) {
            help[i--] = arr[p1--];
        }

        while(p2>m){
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }

}

class ReversePair2{

    public static int reversePairNumber(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int l, int m, int r) {
        int res = 0;
        int[] help = new int[r - l + 1];
        int p1 = m;
        int p2 = r;
        int i = help.length - 1;
        while (p1 >= l && p2 > m) {
            res += arr[p1] > arr[p2] ? (p2 - m) : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l) {
            help[i--] = arr[p1--];
        }
        while (p2 > m) {
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }

}

class ReversePair3 {

    public static int reversePairNumber(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {


        if(l==r){
            return 0;
        }
        int m = l + ((r - l) >> 1);
        return process(arr, l, m) + process(arr, m + 1, r) + merge(arr, l, m, r);
    }

    private static int merge(int[] arr, int l, int m, int r) {

        int p1 = m;
        int p2 = r;
        int[] help = new int[r - l + 1];
        int i = help.length - 1;
        int res = 0;
        while (p1 >= l && p2 > m) {
            res += arr[p1] > arr[p2] ? p2 - m : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l) {
            help[i--] = arr[p1--];
        }
        while(p2>m){
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }
}

class ReversePair4{
    public static int reversePairNumber(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {

        if (l == r) {
            return 0;
        }
        int m = l + ((r - l) >> 1);
        return process(arr, l, m) + process(arr, m + 1, r) + merge(arr, l, m, r);
    }

    private static int merge(int[] arr, int l, int m, int r) {
        int p1 = m;
        int p2 = r;
        int[] help = new int[r - l + 1];
        int i = help.length - 1;
        int res = 0;
        while (p1 >= l && p2 > m) {
            res += arr[p1] > arr[p2] ? p2 - m : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l) {
            help[i--] = arr[p1--];
        }
        while(p2>m){
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;

    }
}

class ReversePair5{
    public static int reversePairNumber(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = l + ((r - l) >> 1);
        return process(arr, l, m) + process(arr, m + 1, r) + merge(arr, l, m, r);
    }

    private static int merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = help.length - 1;
        int p1 = m;
        int p2 = r;
        int res = 0;
        while (p1 >= l && p2 > m) {
            res += arr[p1] > arr[p2] ? p2 - m : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l) {
            help[i--] = arr[p1--];
        }
        while (p2 > m) {
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }


        return res;

    }
}

class ReversePair6 {
    public static int reversePairNumber(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = l + ((r - l) >> 1);
        return process(arr, l, m) + process(arr, m + 1, r) + merge(arr, l, m, r);
    }

    private static int merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = help.length - 1;
        int ans = 0;
        int p1 = m;
        int p2 = r;
        while (p1 >= l && p2 > m) {
            ans += arr[p1] > arr[p2] ? p2 - m : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l) {
            help[i--] = arr[p1--];
        }
        while (p2 > m) {
            help[i--] = arr[p2--];
        }
        i = 0;
        for (; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return ans;
    }
}


class TestReversePair{
    public static int reversePairNumber(int[] arr) {
        int res = 0;
        for (int i = arr.length-2; i >=0 ; i--) {
            for (int j = arr.length-1; j >i  ; j--) {
                if (arr[i] > arr[j]) {
                    res += 1;
                }
            }
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
        return res;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j || arr[i] == arr[j]) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


    public static void main(String[] args){
        int testTime = 1000;
        int sizeMax = 30;
        int range = 50;
        System.out.println("start!");

        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(sizeMax, range);
            int[] copyArr = copyArray(arr);
            int num = ReversePair6.reversePairNumber(arr);
            int copyNum = reversePairNumber(copyArr);
            if (num != copyNum) {
                System.out.println("num : "+num);
                System.out.println("copyNum : "+copyNum);
                return;
            }
            if (!ArrayUtil.isEqual(arr, copyArr)) {
                System.out.println("arr fuck!!");
                return;
            }
        }
        System.out.println("end!");

    }

    private static int[] copyArray(int[] arr) {
        int[] copyArr = new int[arr.length];
        for (int i = 0; i < copyArr.length; i++) {
            copyArr[i] = arr[i];
        }
        return copyArr;
    }

    private static int[] randomArray(int sizeMax, int range) {
        int[] arr = new int[(int) (sizeMax * Math.random() + 1)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((range * Math.random() + 1) - (range * Math.random() + 1));
        }
        return arr;
    }

}
