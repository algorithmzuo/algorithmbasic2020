package leo.class07_11;

/**
 * @author Leo
 * @ClassName PaperFolding
 * @DATE 2020/12/10 9:43 上午
 * @Description 打印一张纸条对着n次后的折痕
 */
public class PaperFolding {


    public static void printAllFolds(int n) {
        if (n < 0) {
            return;
        }
        processPrint(1, n, true);
        System.out.println();

    }

    public static void processPrint(int i, int n, boolean boo) {
        if (i > n) {
            return;
        }
        processPrint(i + 1, n, true);
        System.out.print(boo ? "凹 " : "凸 ");
        processPrint(i + 1, n, false);
    }

    public static void main(String[] args){
        //凹 凹 凸 凹 凹 凸 凸 凹 凹 凹 凸 凸 凹 凸 凸
        //凹 凹 凸 凹 凹 凸 凸 凹 凹 凹 凸 凸 凹 凸 凸
        printAllFolds(4);
    }
}
