package leetcode;
//给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是 nums[i] 右侧小于 num
//s[i] 的元素的数量。
//
//
//
// 示例：
//
// 输入：nums = [5,2,6,1]
//输出：[2,1,1,0]
//解释：
//5 的右侧有 2 个更小的元素 (2 和 1)
//2 的右侧仅有 1 个更小的元素 (1)
//6 的右侧有 1 个更小的元素 (1)
//1 的右侧有 0 个更小的元素
//
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 10^5
// -10^4 <= nums[i] <= 10^4
//
// Related Topics 排序 树状数组 线段树 二分查找 分治算法
// 👍 499 👎 0


import javax.print.attribute.standard.NumberUp;
import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * @author Leo
 * @ClassName lc315_countSmaller
 * @DATE 2020/12/4 6:11 下午
 * @Description
 */
class lc315_countSmaller {
    public static  List<Integer> countSmaller(int[] nums) {
        if (nums.length < 1 || nums == null) {
            return null;
        }
        int[] res = new int[nums.length];
        process(nums, 0, nums.length - 1,res);
        List<Integer> list = new ArrayList<Integer>(res.length);
        for (int i = 0; i < res.length; i++) {
            list.add(res[i]);
        }
        return list;
    }

    public static void process(int[] arr, int l, int r, int[] res) {
        if (l >= r) {
            return;
        }
        int m = l + ((r - l) >> 1);
        process(arr, l, m,res);
        process(arr, m + 1, r,res);
        merge(arr, l, m, r, res);
    }

    private static void merge(int[] arr, int l, int m, int r, int[] res) {
        int[] help = new int[r - l + 1];
        int p1 = m;
        int p2 = r;
        int i = help.length - 1;
        while (p1 >= l && p2 > m) {
            res[i] += arr[p1] > arr[p2] ? (p2 - m) : 0;
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
    }
}

class Main_315{



    public static void main(String[] args){
        int[] nums = new int[]{3,7,5,2,6,1};
        List<Integer> list = lc315_countSmaller.countSmaller(nums);
        System.out.println(list);

    }

}
