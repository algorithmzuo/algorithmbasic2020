package class03;

import java.util.Stack;

public class MyQueue {
    private Stack<Integer> pushStack;
    private Stack<Integer> popStack;

    public MyQueue() {
        pushStack = new Stack<>();
        popStack = new Stack<>();
    }

    /**
     * 将push中的元素倒入pop中
     */
    private void pushToPop() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
    }

    public void add(int pushValue) {
        pushStack.push(pushValue);
        pushToPop();
    }

    public int poll() {
        if (popStack.isEmpty() && pushStack.isEmpty()) {
            throw new RuntimeException("无可弹出元素");
        }
        pushToPop();
        return popStack.pop();
    }

    public int peek() {
        if (pushStack.isEmpty() && popStack.isEmpty()) {
            throw new RuntimeException("无可弹出元素");
        }
        pushToPop();
        return popStack.peek();
    }
}
