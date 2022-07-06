package class04;

/**
 * 求每个的右边有多少个数*2比它小，求总数
 * 这里可以先求个数，再排序，时间复杂度都是O(n*logn)
 */
public class Code04_BiggerThanRightTwice_Study {
    public static int biggerThanRightTwice(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr,0,arr.length - 1);
    }

    public static int process(int[] arr,int l,int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r -l) >>1);
        return process(arr,l,mid) + process(arr ,mid + 1,r) + merge(arr,l,mid,r);
    }
    public static int merge(int[] arr,int l,int m,int r) {
        int res = 0;
        // 这里的范围是[m + 1, m +1),当rposi = m +1，代表一个数都没有
        int rPosi = m + 1;
        for (int i = l;i <= m ;i++) {
            while (rPosi <= r && arr[i] > (arr[rPosi] << 1) ) {
                rPosi++;
            }
            res += rPosi - m - 1;
        }

        int p1 = l;
        int p2 = m + 1;
        int[] help = new int[r - l + 1];
        int i = 0;
        while (p1 <= m && p2 <= r ) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0;i < help.length ;i++) {
            arr[l + i] = help[i];
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
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
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 5;
        int maxValue = 20;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int res1 = biggerThanRightTwice(arr1);
            int res2 = comparator(arr2);
            if (res1 != res2) {
                System.out.println("Oops!");
                System.out.println("res1 : " + res1);
                System.out.println("res2 : " + res2);
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}

