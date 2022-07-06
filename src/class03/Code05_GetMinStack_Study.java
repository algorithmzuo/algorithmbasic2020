package class03;

import java.util.Stack;

/**
 * 获取栈中最小的元素，且pop,push,getMin操作的时间复杂度是O(1)
 */
public class Code05_GetMinStack_Study {
    public static class MyStack1{
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public  MyStack1(){
            stackData = new Stack<>();
            stackMin = new Stack<>();
        }
        public void push(int value) {
            if (this.stackMin.isEmpty()) {
                stackMin.push(value);
            }else if (value < getMin()) {
                stackMin.push(value);
            }
            stackData.push(value);
        }

        public int pop(){
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("statck no elements");
            }
            int value = this.stackData.pop();
            if (value == this.stackMin.peek()) {
                this.stackMin.pop();
            }
            return value;
        }

        public int getMin(){
            if (stackMin.isEmpty()) {
                throw new RuntimeException("stackMin is empth");
            }
            return stackMin.peek();
        }
    }

    public static class MyStack2{
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack2(){
            stackData = new Stack<>();
            stackMin = new Stack<>();
        }

        public void push(int value) {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(value);
            } else if (value < getMin()) {
                this.stackMin.push(value);
            }else {
                int min = this.stackMin.peek();
                this.stackMin.push(min);
            }

            this.stackData.push(value);
        }

        private int poll(){
            this.stackMin.pop();
            return this.stackData.pop();
        }

       public int getMin(){
            if (stackMin.isEmpty()) {
                throw new RuntimeException("stackMin is empth");
            }
            return stackMin.peek();
       }
    }
    public static void main(String[] args) {
        MyStack1 myStack1 = new MyStack1();
        myStack1.push(4);
        System.out.println(myStack1.getMin());
        myStack1.push(3);
        System.out.println(myStack1.getMin());
        myStack1.push(5);
        System.out.println(myStack1.getMin());


        MyStack2 myStack2 = new MyStack2();
        myStack2.push(4);
        System.out.println(myStack2.getMin());
        myStack2.push(3);
        System.out.println(myStack2.getMin());
        myStack2.push(5);
        System.out.println(myStack2.getMin());
    }
}
