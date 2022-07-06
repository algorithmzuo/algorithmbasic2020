package class05;

public class Code01_CountOfRangeSum_Study {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1 ;i < sum.length;i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return process(sum,lower,upper,0,sum.length-1);
    }

    public int process(long[] sum,int lower,int upper,int l,int r) {
        if (l == r) {
            return sum[l] >= lower && sum[l] <= upper ? 1 : 0;
        }
        int mid = l +  ((r - l) >> 1);
        return process(sum,lower,upper,l,mid) + process(sum,lower,upper,mid + 1,r) +
                merge(sum,lower,upper,l,mid,r);
    }

    public int merge(long[] sum,int lower,int upper,int l,int m,int r) {
        int result = 0;
        int moveL = l;
        int moveR = l;
        for (int i = m + 1; i <= r;i++) {
            long min = sum[i] - upper;
            long max = sum[i] - lower;
            while (moveL <= m && sum[moveL] < min){
                moveL++;
            }
            while (moveR <= m && sum[moveR] <= max) {
                moveR++;
            }
            result += moveR - moveL;
        }

        long[] help = new long[r - l + 1];
        int i = 0;
        int pL = l;
        int pR = m + 1;
        while (pL <= m && pR <= r) {
            help[i++] = sum[pL] <= sum[pR] ? sum[pL++] : sum[pR++];
        }
        while (pL <= m) {
            help[i++] = sum[pL++];
        }
        while (pR <= r) {
            help[i++] = sum[pR++];
        }
        for (i = 0 ;i < help.length;i++) {
            sum[l + i] = help[i];
        }
        return result;
    }
}
