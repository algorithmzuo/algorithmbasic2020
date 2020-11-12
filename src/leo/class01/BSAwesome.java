package leo.class01;

/**
 * @author Leo
 * @ClassName BSAwesome
 * @DATE 2020/11/12 5:09 下午
 * @Description 在无序数组中,且无序数组任意相邻的数不相等,找到一个局部最小值.>value<
 */
public class BSAwesome {

    public static int BSAwesome(int[] arr) {
        int index = -1;
        if (arr.length == 0 || arr == null) {
            return index;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int L = 1;
        int R = arr.length - 2;
        int mid = 0;
        while (L < R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;
            } else {
                return mid;
            }
        }
        return L;


    }
}
