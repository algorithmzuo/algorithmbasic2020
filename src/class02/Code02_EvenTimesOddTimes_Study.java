package class02;

public class Code02_EvenTimesOddTimes_Study {
    /**
     * arr 中只有一个数出现奇数次
     * @param arr
     * @return
     */
    private static Integer printOddTimesNum1(int[] arr){
        if (arr == null || arr.length ==0) {
            return null;
        }
        int result = 0;
        for (int value : arr){
            result ^= value;
        }
        return result;
    }

    /**
     * arr中有两个次数为奇数次的数
     * 1.把所有数进行亦或，这样最后拿到的数据就是两个奇数的亦或结果eor
     * 2.获取eor最右侧1的值，两个奇数在这个1的位置上肯定是不同的，一个为0，一个为1
     * 3.轮询数组，把在该位置为1的数亦或，这样就得到该位置为1，且出现次数为奇数次的那个数A
     * 4.因为eor = A ^ B，所以通过第三步拿到A后，B = eor ^ A
     * @param arr
     * @return
     */
    private static void printOddTimesNum2(int[] arr){
        if (arr == null || arr.length ==0 ){
            return;
        }
        int eor = 0;
        for (int i = 0 ;i < arr.length ;i++){
            eor ^= arr[i];
        }
        // 获取数据最右侧的1
        int rightOne = eor & (-eor);
        int value = 0;
        for (int i = 0 ; i < arr.length ;i++){
            if( (arr[i] ^ rightOne) != 0) {
                value ^= arr[i];
            }
        }
        System.out.println("奇数A=" + value + "奇数B=" + (eor ^ value));
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,3,3,2,2,3,2,4,5,5,4,1,2};
        System.out.println(printOddTimesNum1(arr));
        int[] arr2 = {1,2,3,3,4,4,5,5};
        printOddTimesNum2(arr2);
    }
}
