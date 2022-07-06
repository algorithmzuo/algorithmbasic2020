package class04;

/**
 * 逆序对问题
 * 求每个数的右边有多少个数比它小，在把这些个数求和
 * 求小和是求左边有多少个数比它小，求逆序对需要反过来，因此在递归遍历时是从大到小遍历的。
 */
public class Code03_ReversePair_Study {
    public static int reversePair(int[] arr){
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr,0,arr.length - 1);
    }
    private static int process(int[] arr,int L,int R){
        if (L == R) {
            return 0;
        }
        int mid = L + ((R- L) >> 1);
        return process(arr,L ,mid) +process(arr,mid + 1,R) + merge(arr,L,mid,R);
    }

    /**
     * 这里要注意数组下标是逆序处理的，比较元素大小时，大的元素下标减1
     * @param arr
     * @param l
     * @param m
     * @param r
     * @return
     */
    private static int merge(int[] arr,int l,int m,int r) {
        int[] help = new int[r - l + 1];
        int p1 = m;
        int p2 = r;
        int i = help.length - 1;
        int res = 0;
        while (p1 >= l && p2 >m ) {
            res += arr[p1] > arr[p2] ? (p2 - m) : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l) {
            help[i--] = arr[p1--];
        }
        while (p2 > m) {
            help[i--] = arr[p2--];
        }

        for (i = 0;i < help.length;i++) {
            arr[l + i] = help[i];
        }
        return res;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 20;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (reversePair(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
