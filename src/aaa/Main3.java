package aaa;

import com.sun.source.tree.WhileLoopTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main3 {
    static int[][] nums;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            String str = in.nextLine();
            nums = new int[str.length()][2];
            getNum(str);
            List<Integer> res = split(str.length());
            for (Integer re : res) {
                System.out.print(re+" ");
            }
            System.out.println();
        }
    }

    public static void getNum(String str) {
        char[] chars = str.toCharArray();
        int index = 0;
        int a=0,b=0;
        for (char aChar : chars) {
            if(aChar == 'D') a++;
            if(aChar == 'K') b++;
            nums[index][0] = a;
            nums[index++][1] = b;
        }
    }

    public static List<Integer> split (int len) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            int a = nums[i][0];
            int b = nums[i][1];
            if(a == 0 || b == 0) {
                res.add(i+1);
                continue;
            }
            if (common(a,b) == 1) {
                res.add(1);
            }else {
                int max = 1;
                for(int j=2;j<=common(a,b);j++) {
                    if(a % j != 0 || b % j != 0) continue;
                    int width = a/j + b/j;
                    int right = width-1;
                    boolean flag = true;
                    while (right < i) {
                        int m = nums[right][0];
                        int n = nums[right][1];
                        if(m/n != a/b) {
                            flag = false;
                            break;
                        }
                        right = right + width;
                    }
                    if(flag) max = Math.max(max,j);
                }
                res.add(max);
            }
        }
        return res;
    }

    public static int common(int a, int b) {
        if(a<b) {
            int temp = a;
            a = b;
            b = temp;
        }
        while(b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

}
