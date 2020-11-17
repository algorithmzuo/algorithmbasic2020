package leo.class01;

import sun.applet.Main;

/**
 * @author Leo
 * @ClassName EvenTimesOddTimes
 * @DATE 2020/11/13 3:34 下午
 * @Description
 */
public class EvenTimesOddTimes {

    /**
     * 功能描述 : 取出唯一一个出现奇数次的数字
     * @author Leo
     * @date 2020/11/13 4:28 下午
     * @param arr
     * @throw
     * @return void
     */
    public static void printOdd(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    public static void printOdd1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    public static void printOdd2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    public static void printOdd3(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);

    }

    public static void printOdd4(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);

    }


    /**
     * 功能描述 : 有两种数出现了奇数次,找出他
     * @author Leo
     * @date 2020/11/14 4:21 下午
     * @param arr
     * @throw
     * @return void
     */
    public static void printOddTwo(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        int rightOnly = eor & (-eor);

        int eorOther = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((rightOnly & arr[i]) != 0) {
                eorOther ^= arr[i];
            }
        }
        System.out.println(eorOther + "  " + (eor ^ eorOther));
    }

    public static void printOddTwo2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        int rightOne = eor & (-eor);

        int eorOther = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((rightOne & arr[i]) != 0) {
                eorOther ^= arr[i];
            }
        }
        System.out.println(eorOther + "  " + (eor ^ eorOther));

    }

    public static void printOddTwo3(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }

        int rightOnly = eor & (-eor);

        int eorOther = 0;
        for (int i = 0; i < arr.length; i++) {

            if ((rightOnly & arr[i]) != 0) {
                eorOther ^= arr[i];
            }
        }
        System.out.println(eorOther + "   " + (eor ^ eorOther));

    }

    public static void printOddTwo4(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        int rightOne = eor & (-eor);
        int eorOther = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((rightOne & arr[i]) != 0) {
                eorOther ^= arr[i];
            }
        }
        System.out.println(eorOther + "  " + (eor ^ eorOther));

    }

    public static void printOddTwo5(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        int rightOne = eor & (-eor);
        int eorOther = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((rightOne & arr[i]) != 0) {
                eorOther ^= arr[i];
            }
        }
        System.out.println(eorOther + "  " + (eor ^ eorOther));
    }


    public static void main(String[] args){
        int[] arrOne = {1, 1, 5, 5, 8, 1, 8, 5, 5};

        printOdd4(arrOne);
        int[] arrTwo = {1, 1, 9, 5, 5, 8, 1, 8, 9, 5, 5, 5};
        printOddTwo5(arrTwo);

    }

}
