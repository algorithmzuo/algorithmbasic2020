package leo.class17;

import class17.Code02_Hanoi;

/**
 * @author Leo
 * @ClassName Hanoi
 * @DATE 2020/12/30 11:17 上午
 * @Description 汉诺塔问题
 */
public class Hanoi {

    public static void hanoi(int n) {
        if (n == 0) {
            return;
        }
        process(n, "left", "right", "mid");
    }

    private static void process(int n, String form, String to, String other) {
        if (n == 1) {
            System.out.println("move " + 1 + " " + form + " =>" + to);
        }else{
            process(n - 1, form, other, to);
            System.out.println("move " + n + " " + form + " =>" + to);
            process(n - 1, other, to, form);
        }

    }
    public static void main(String[] args){
        int n = 3;
        hanoi(n);
        System.out.println("-------");
        Code02_Hanoi.hanoi2(n);
    }


}
