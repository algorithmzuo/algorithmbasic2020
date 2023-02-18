package class01;

import java.util.Arrays;

/**
 * 查找最右边等于目标值的小标索引
 * 这个最需要注意的就是在找到目标值时，需要收缩l，左边的下标，让其往右走
 *
 */
public class Code05_BSNearRight2 {
    public static int bsNearRight(int[] arr,int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int l = 0;
        int mid = 0;
        int r = arr.length -1;
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            System.out.println("start testmid:"+mid+" l:"+l + " r:" + r);
            if (arr[mid] > target) {
                r = mid - 1;
            } else if (arr[mid] < target) {
                l = mid + 1;
            } else if (arr[mid] == target) {
                l = mid + 1;
            }
            System.out.println("end testmid:"+mid+" l:"+l + " r:" + r);

        }
        if (l -1 < 0)return -1;
//        if (arr[l-1] != target) return -1;
//        return l-1; // 这里返回l-1是因为l = mid + 1;所以当查询结束的时候，那么mid = left - 1
        System.out.println("mid end:" + mid + " value mid:" + arr[mid]);
        // 这了不能返回mid是因为while结束的条件是固定的，一定是在l = r + 1的请款下结束的
        // 而l = mid + 1 所以 为了求到那个准确的下标，需要mid = l - 1 => r
        // 所以l - 1可以被r代替，但是不意味着可以用mid代替，因为mid最后的值可能是l 也可能是 r
        // 因此，如果真的想用mid，那就判断如果mid == l，就返回mid - 1，如果mid == r，则直接返回r，这个和上面一个意思
        return arr[l -1] == target ? r : -1;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.random() > 0.5 ?((int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random())):4;
        }
        return arr;
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


    public static int comparator(int[] arr,int target) {
        if (arr == null && arr.length == 0) {
            return -1;
        }
        int targertIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                targertIndex = i;
            }
        }
        return targertIndex;
    }

    public static void main(String[] args) {
        int testTime = 500;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
//            int value = 4;
            if (bsNearRight(arr, value) != comparator(arr, value)) {
                System.out.println(Arrays.toString(arr));
                System.out.println(value);
                System.out.println(bsNearRight(arr, value));
                System.out.println(comparator(arr, value));
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
