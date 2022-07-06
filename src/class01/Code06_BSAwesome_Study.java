package class01;

/**
 * 局部最小
 * 题目：无需数组求局部最小，相邻的两个数据不相等
 * 思路：1.a[0] > a[1]
 *      2.a[n-2] < a[n-1]
 *      3.满足上面两个条件，才有可能说产生局部最小，先降后升
 *      4.通过二分法找到局部最小，
 */
public class Code06_BSAwesome_Study {
    private static int getLessIndex(int[] arr){
        // 特殊情况
        if (arr == null || arr.length == 0){
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length -2]) {
            return arr.length -1;
        }

        int l = 1;
        int r = arr.length -2;
        int mid = 0;
        while (l < r){
            mid = (l + r) /2;
            if (arr[mid] > arr[mid-1]) {
                r = mid-1;
            }else if (arr[mid] > arr[mid + 1]) {
                l = mid + 1;
            }else {
                return mid;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        int[] arr = {2,3,4,2,3,4,21,3,4,32,1,2};
        System.out.println(getLessIndex(arr));
    }
}
