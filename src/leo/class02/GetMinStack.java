package leo.class02;

import jdk.nashorn.internal.ir.IfNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author Leo
 * @ClassName GetMinStack
 * @DATE 2020/11/20 4:54 下午
 * @Description
 */
public class GetMinStack {

    /**
     * 每个数进数据栈，最小栈只有栈顶要更新才加  省一点点空间
     */
    public static class MyStack1{

        private Stack<Integer> data;
        private Stack<Integer> min;
        public MyStack1() {
            this.data = new Stack<>();
            this.min = new Stack<>();
        }
        public void push(int value) {
            if (this.min.isEmpty()||value <= this.getMin()) {
                this.min.push(value);
            }
            data.push(value);
        }
        public int pop() {
            if (data.isEmpty()) {
                throw new RuntimeException("stack isEmpty");
            }
            Integer value = data.pop();
            if (this.getMin() == value) {
                min.pop();
            }
            return value;
        }
        public int getMin() {
            if (min.isEmpty()) {
                throw new RuntimeException("stack isEmpty");
            }
            return min.peek();
        }




    }


    public static class MyStack3{

        private Stack<Integer> data;
        private Stack<Integer> min;

        public MyStack3() {
            data = new Stack<>();
            min = new Stack<>();
        }

        public void push(int value) {
            if (min.isEmpty() || this.getMin() >= value) {
                min.push(value);
            }
            data.push(value);
        }

        public Integer pop() {
            if (data.isEmpty()) {
                throw new RuntimeException("Stack is Empty");
            }
            int value = data.pop();
            if (value == this.getMin()) {
                min.pop();
            }
            return value;
        }


        public Integer getMin() {
            if (min.isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            return min.peek();
        }

    }

    public static class MyStack5{
        private Stack<Integer> data;
        private Stack<Integer> min;

        public MyStack5() {
            data = new Stack<Integer>();
            min = new Stack<Integer>();
        }

        public void push(int value) {
            try {
                if (data.isEmpty() || this.getMin() >= value) {
                    min.push(value);
                }
            } catch (Exception e) {
                min.push(value);
            }
            data.push(value);
        }

        public int pop() throws Exception {
            if (data.isEmpty()) {
                throw new Exception("stack is empty");
            }
            int value = data.pop();
            if (value == this.getMin()) {
                min.pop();
            }
            return value;
        }


        public int getMin() throws Exception {
            if (min.isEmpty()) {
                throw new Exception("stack is empty");
            }
            return min.peek();
        }



    }

    /**
     * 只有最小值入栈,最小值重复入栈
     */
    public static class MyStack2 {
        private Stack<Integer> data;
        private Stack<Integer> min;

        public MyStack2() {
            data = new Stack<>();
            min = new Stack<>();
        }
        public void push(int value) {
            if (data.isEmpty()) {
                min.push(value);
            } else if (this.getMin() > value) {
                min.push(value);
            }else {
                min.push(this.getMin());
            }
            data.push(value);
        }

        public int pop() {
            if (data.isEmpty()) {
                throw new RuntimeException("Stack is Empty");
            }
            this.min.pop();
            return data.pop();
        }


        public int getMin() {
            if (min.isEmpty()) {
                throw new RuntimeException("stack is Empty");
            }
            return min.peek();
        }



    }


    public static class MyStack4{
        private Stack<Integer> data;
        private Stack<Integer> min;

        public MyStack4() {
            this.data = new Stack<>();
            this.min = new Stack<>();
        }

        public void push(int value) {
            try {
                if (data.isEmpty() || this.getMin() > value) {
                    this.min.push(value);
                } else {
                    min.push(this.getMin());
                }
            } catch (Exception e) {
                this.min.push(value);
            }
            this.data.push(value);
        }

        public Integer pop() throws Exception {
            if (data.isEmpty()) {
                throw new Exception("Stack is Empty!");
            }
            this.min.pop();
            return this.data.pop();
        }

        public int getMin() throws Exception {
            if (this.min.isEmpty()) {
                throw new Exception("stack is empty");
            }
            return min.peek();
        }
    }

    public static class MyStack6 {
        private Stack<Integer> data;
        private Stack<Integer> min;

        public MyStack6() {
            this.data = new Stack<>();
            this.min = new Stack<>();
        }

        public void push(int value) {
            try {
                if (this.min.isEmpty() || this.getMin() > value) {
                    this.min.push(value);
                } else {
                    this.min.push(this.getMin());
                }
            } catch (Exception e) {
                this.min.push(value);
            }
            data.push(value);
        }

        public int pop() throws Exception {
            if (this.data.isEmpty()) {
                throw new Exception("stack is empty");
            }
            int value = this.data.pop();
            this.min.pop();
            return value;
        }

        public int getMin() throws Exception {
            if (this.min.isEmpty()) {
                throw new Exception("stack is empty");
            }
            return this.min.peek();
        }

    }

    /**
     * 用list实现 只存最小值的索引
     */
    public static class MyStackOfList{
        private List<Integer> data;
        private List<Integer> min;

        public MyStackOfList() {
            this.data = new ArrayList<>();
            this.min = new ArrayList<>();
        }

        public void push(int value) {
            this.data.add(value);
            if (this.min.size() == 0) {
                this.min.add(0);
            }else{
                int minValue = getMin();
                if (minValue > value) {
                    min.add(data.size() - 1);
                }
            }
        }


        public int pop() {
            if (data.size() == 0) {
                throw new RuntimeException("stack is empty!");
            }
            int dataIndex = this.data.size() - 1;
            int minIndex = this.min.get(min.size() - 1);
            int value = data.get(dataIndex);
            if (dataIndex == minIndex) {
                min.remove(minIndex);
            }
            data.remove(dataIndex);
            return value;
        }

        public int getMin() {
            if (min.size() == 0) {
                throw new RuntimeException("stack is empty");
            }
            return data.get(min.get(min.size() - 1));
        }

    }

    /**
     * test
     */
    public static class TestMyStack {
        private List<Integer> data ;

        public TestMyStack() {
            data = new ArrayList<>();
        }

        public void push(int value) {
            data.add(value);
        }

        public int pop() {
            if (data.size() == 0) {
                throw new RuntimeException("stack is empty");
            }
            int value = data.get(data.size() - 1);
            data.remove(data.size() - 1);
            return value;
        }

        public int gitMin() {
            if (data.size() == 0) {
                throw new RuntimeException("stack is empty");
            }
            Integer[] dataArray = data.toArray(new Integer[data.size()]);
            Arrays.sort(dataArray);
            return dataArray[0];
        }
    }


    public static void main(String[] args) {

        int testTime = 10000;
        int range = 200;
        int forTime = 100;
        System.out.println("Start!");

        for (int i = 0; i < testTime; i++) {
            MyStack5 myStack = new MyStack5();
            TestMyStack testMyStack = new TestMyStack();
            for (int j = 0; j < forTime; j++) {
                if (Math.random() < 0.5) {
                    int value = randomIndex(range);
                    myStack.push(value);
                    testMyStack.push(value);
                } else {
                    try {
                        int myStack1Value = myStack.pop();
                        int testMyStackValue = testMyStack.pop();
                        if (myStack1Value != testMyStackValue) {
                            System.out.println("myStack1Value: "+myStack1Value);
                            System.out.println("testMyStackValue: "+testMyStackValue);
                            return;
                        }
                    } catch (Exception e) {
                        int value = randomIndex(range);
                        myStack.push(value);
                        testMyStack.push(value);
                    }

                }
            }
            for (int j = 0; j < forTime; j++) {
                if (Math.random() < 0.5) {
                    try {
                        int myStack1Min = myStack.getMin();
                        int testMyStackMin = testMyStack.gitMin();
                        if (myStack1Min != testMyStackMin) {
                            System.out.println("myStack1Min: "+myStack1Min);
                            System.out.println("testMyStackMin: "+testMyStackMin);
                            break;
                        }
                    } catch (Exception e) {
                        int value = randomIndex(range);
                        myStack.push(value);
                        testMyStack.push(value);
                    }

                } else {

                    try {
                        int myStack1Value = myStack.pop();
                        int testMyStackValue = testMyStack.pop();
                        if (myStack1Value != testMyStackValue) {
                            System.out.println("myStack1Value: "+myStack1Value);
                            System.out.println("testMyStackValue: "+testMyStackValue);
                            break;
                        }
                    } catch (Exception e) {
                        int value = randomIndex(range);
                        myStack.push(value);
                        testMyStack.push(value);
                    }
                }
            }
        }
        System.out.println("End!");
    }


    public static int randomIndex(int range) {
        return (int) ((int) (range * Math.random() + 1) - (range * Math.random() + 1));
    }








}
