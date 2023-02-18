package class02;


/**
 * 数组中，一个数出现奇数次，一个数出现偶数次
 * 怎么找到并打印这个数
 * 相同数异或之后为0，0和数异或之后为该数
 * 提取最右边的一个1，用e^(-e)
 */
public class Code02_EvenTimesOddTimes2 {
    // 通过异或求得，只有一个数是奇数
    public static void evenTimesOddTimes1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return ;
        }
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    /**
     * 有两个数是奇数
     * 思路：
     *  将数据都先异或，得到的肯定是两个奇数a^b的结果eor
     *  然后求出eor最右边的那个1的位置i，通过最右边的那个1，把数组拆分成两组，一组是i位置不为1的，一组是i位置为1的
     *  任意一组的数据异或后都可以得到数据a或者数据b
     *  然后根据eor求出另一个值
     * @param arr
     * @return
     */
    public static void  evenTimesOddTimes2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }

        int rightOne = eor ^ (-eor);
        int onelyOne = 0;
        for (int i = 0; i < arr.length; i++) {
            // 按位& 不为0的一组，为0的一组计算
            if ((arr[i] & rightOne) != 0) {
                onelyOne ^= arr[i];
            }
        }
        System.out.println("a:"+onelyOne +" b:" + (eor ^ onelyOne));
    }


    public static void main(String[] args) {
        int a = 5;
        int b = 7;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

//        System.out.println(a);
//        System.out.println(b);

        int[] arr1 = { 3, 2, 3 };
        evenTimesOddTimes1(arr1);

        int[] arr2 = { 4, 3, 3,1,1,2};
        evenTimesOddTimes2(arr2);
    }
    
}
