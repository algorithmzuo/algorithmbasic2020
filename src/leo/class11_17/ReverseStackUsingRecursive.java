package leo.class11_17;

import java.util.Stack;

/**
 * @author Leo
 * @ClassName ReverseStackUsingRecursive
 * @DATE 2020/12/30 2:46 下午
 * @Description
 * 给你一个栈，请你逆序这个栈，
 * 不能申请额外的数据结构，
 * 只能使用递归函数
 */
public class ReverseStackUsingRecursive {

    static class  Code{
        public static void reverse(Stack<Integer> stack) {
            if (stack.isEmpty()) {
                return;
            }
            int i = f(stack);
            reverse(stack);
            stack.push(i);
        }

        private static int f(Stack<Integer> stack) {
            int value = stack.pop();
            if (stack.isEmpty()){
                return value;
            }
            int last = f(stack);
            stack.push(value);
            return last;
        }
    }


    public static void main(String[] args){
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        Code.reverse(stack);
        System.out.println(stack);

    }


}
