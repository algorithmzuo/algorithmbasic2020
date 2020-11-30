package leo.class02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Leo
 * @ClassName TwoStacksImplementQueue
 * @DATE 2020/11/21 12:09 下午
 * @Description 使用栈数据结构实现队列
 * 队列 先进先出
 * 栈 先进后出
 */
public class TwoStacksImplementQueue {

    public static class MyQueue{
        private Stack<Integer> push;
        private Stack<Integer> pop;

        public MyQueue() {
            this.push = new Stack<>();
            this.pop = new Stack<>();
        }

        private void pushToPop() {
            if (this.pop.isEmpty()) {
                while (!this.push.isEmpty()) {
                    this.pop.push(this.push.pop());
                }
            }
        }


        public void push(int value) {
            push.push(value);
            pushToPop();

        }

        public int  poll() throws Exception {
            if (this.push.isEmpty() && this.pop.isEmpty()) {
                throw new Exception("queue is empty");
            }
            pushToPop();
            return pop.pop();
        }

        public int peek() throws Exception {
            if (this.push.isEmpty() && this.pop.isEmpty()) {
                throw new Exception("queue is empty");
            }
            pushToPop();
            return this.pop.peek();
        }

    }

    public static class MyQueue1{

        private Stack<Integer> push;
        private Stack<Integer> pop;

        public MyQueue1() {
            this.pop = new Stack<>();
            this.push = new Stack<>();
        }

        public boolean isEmpty() {
            return this.push.isEmpty();
        }

        public void push(int value) {
            push.push(value);
            pushToPop();
        }

        public int poll() throws Exception {
            if (pop.isEmpty()) {
                throw new Exception("queue is empty");
            }
            Integer pop = this.pop.pop();
            pushToPop();
            return pop;
        }

        public int peek() throws Exception {
            if (this.pop.isEmpty()) {
                throw new Exception("queue is empty");
            }
            pushToPop();
            return this.pop.peek();
        }

        public void pushToPop() {

            if (this.pop.isEmpty()) {
                while (!this.push.isEmpty()) {
                    this.pop.push(this.push.pop());
                }
            }
        }
    }


    public static void main(String[] args) {

        int testTime = 1000000;
        int range = 30;
        Queue<Integer> queue = new LinkedList<>();
        MyQueue1 myQueue = new MyQueue1();
        for (int i = 0; i < testTime; i++) {
            if (Math.random() < 0.5) {
                int value = randomInt(range);
                queue.offer(value);
                myQueue.push(value);
            }else{
                try {
                    int myQueueValue = myQueue.poll();
                    int queueValue = queue.poll();
                    if (myQueueValue != queueValue) {
                        System.out.println("queueValue : "+queueValue);
                        System.out.println("myQueueValue : "+myQueueValue);
                        return;
                    }

                } catch (Exception e) {
                    continue;
                }

            }

        }
        System.out.println("OK!🙆‍️");

    }


    public static int randomInt(int range) {
        return (int) (range * Math.random() - range * Math.random()) + 2;
    }




}
