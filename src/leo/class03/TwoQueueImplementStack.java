package leo.class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Leo
 * @ClassName TwoQueueImplementStack
 * @DATE 2020/11/21 12:29 下午
 * @Description 两个队列实现栈
 */
public class TwoQueueImplementStack {

    public static class MyStack<T>{
        private Queue<T> queue;
        private Queue<T> help;

        public MyStack() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }

        public void push(T value) {
            queue.offer(value);
        }

        /**
         * 将queue的最后一位返回,其余的数据push到辅助队列里
         * 在将辅助队列与queue交换
         */
        public T pop() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T value = queue.poll();
            Queue<T> temp = queue;
            queue = help;
            help = temp;
            return value;
        }

        /**
         * 将队列的最后一位拿出来 在放回队列
         */
        public T peek() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T value = queue.poll();
            help.offer(value);
            Queue<T> temp = queue;
            queue = help;
            help = temp;
            return value;

        }

        public boolean isEmpty() {
            return queue.size() == 0;
        }
    }

    public static class MyStack1<T>{
        private Queue<T> queue;
        private Queue<T> help;

        public MyStack1() {
            queue = new LinkedList<T>();
            help = new LinkedList<T>();
        }

        public void push(T value) {
            this.queue.offer(value);
        }

        public T pop() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T value = queue.poll();
            Queue<T> temp = queue;
            queue = help;
            help = temp;
            return value;
        }

        public T peek(){
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T value = queue.poll();
            this.help.offer(value);
            Queue<T> temp = queue;
            queue = help;
            help = temp;
            return value;
        }

        public boolean isEmpty() {
            return this.queue.size() == 0;
        }


    }

    public static class MyStack2<T>{
        private Queue<T> stack;
        private Queue<T> help;

        public MyStack2() {
            this.stack = new LinkedList<>();
            this.help = new LinkedList<>();
        }
        public boolean isEmpty() {
            return stack.isEmpty();
        }

        public void push(T value) {
            this.stack.offer(value);
        }

        public T pop() {
            while (stack.size() > 1) {
                help.offer(this.stack.poll());
            }
            T poll = this.stack.poll();
            Queue<T> temp = stack;
            stack = help;
            help = temp;
            return poll;
        }

        public T peek() {
            while (this.stack.size() > 1) {
                help.offer(stack.poll());
            }
            T poll = this.stack.poll();
            Queue<T> temp = stack;
            this.stack = this.help;
            this.help = temp;
            this.stack.offer(poll);
            return poll;
        }

    }


    public static void main(String[] args){
        int testTime = 1000;
        int range = 500;
        MyStack2<Integer> myStack = new MyStack2<>();
        Stack<Integer> stack = new Stack<>();
        System.out.println("Start!");
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!stack.isEmpty()) {
                    System.out.println("stack isEmpty fuck");
                    return;
                }
                int value = (int) (range * Math.random());
                myStack.push(value);
                stack.push(value);
            }else{
                double random = Math.random();
                if (random < 0.25) {
                    int value = (int) (range * Math.random());
                    myStack.push(value);
                    stack.push(value);
                } else if (random < 0.5) {
                    if (!myStack.pop().equals(stack.pop())) {
                        System.out.println("pop fuck!");
                        return;
                    }
                }else if (random < 0.75){
                    if (!myStack.peek().equals(stack.peek())) {
                        System.out.println("peek fuck!");
                        return;
                    }
                }else{
                    if (myStack.isEmpty() != stack.isEmpty()) {
                        System.out.println("isEmpty fuck!");
                        return;
                    }
                }

            }
        }
        System.out.println("End!");

    }
}
