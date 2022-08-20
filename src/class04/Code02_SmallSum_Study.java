package class04;

import java.util.Arrays;

public class Code02_SmallSum_Study {
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr,0,arr.length -1 );
    }
    private static int process(int[] arr,int L,int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return process(arr,L ,mid) +process(arr , mid + 1, R) + merge(arr,L,mid,R);
    }

    private static int merge(int[] arr,int L,int M,int R) {
        int[] help = new int[R - L + 1];
        int p1 = L;
        int p2 = M +1;
        int res = 0;
        int i = 0;
        while (p1 <= M && p2 <= R ) {
            res += arr[p1] < arr[p2] ? (R - p2 +1) * arr[p1] :0;
            // 这里注意是严格<，也就是当左边等于右边时，右边往后移动，因为是求这里要统计多少个数比左边大。
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (int j =0 ;j < help.length ;j++) {
            arr[L + j] = help[j];
        }
        return res;
    }

    private static int[] generateRandomArr(int maxLen ,int maxValue){
        int[] arr = new int[(int)(Math.random() * (maxLen + 1))];
        for (int i = 0;i < arr.length ;i++){
            arr[i] = (int) (Math.random() * (maxValue+1)) - (int) (Math.random() * maxValue);
        }
        return arr;
    }

    private static int[] copyArr(int[] arr){
        int[] newArr = new int[arr.length];
        for (int i = 0;i < arr.length;i++){
            newArr[i] = arr[i];
        }
        return newArr;
    }

    private static int compator(int[] arr){
        if (arr == null && arr.length < 2){
            return 0;
        }
        int res = 0;
        for (int i = 1;i < arr.length;i++) {
            for (int j = 0;j < i;j++){
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }

    private static boolean isEqual(int[] arr1,int[] arr2){
        if ((arr1 == null && arr2 != null ) || (arr2 == null &&arr1 != null)) {
            return false;
        }
        if (arr1 == null && arr2 == null){
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0 ;i < arr1.length;i++){
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int time = 500;
        int maxValue = 20;
        int maxLength = 10;
        boolean isEqual = true;
        for (int i = 0;i < time;i++){
            int[] arr = generateRandomArr(maxLength,maxValue);
            int[] copyArr = copyArr(arr);
            System.out.println(Arrays.toString(arr));
            int res1 = smallSum(arr);
            int res2 = compator(copyArr);
            if (res1 != res2 ) {
                isEqual = false;
                System.out.printf("res1:%s,res2:%s",res1,res2);
                System.out.println("smallSum:" +  Arrays.toString(arr));
                System.out.println("compatorSort:" + Arrays.toString(copyArr));
                break;
            }

        }
        System.out.println(isEqual ? "success":"fail");

    }

}
