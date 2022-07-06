package class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 双向对列实现栈和队列
 */
public class Code03_DoubleEndsQueueToStackAndQueue_Study {
    public static class Node<T>{
        public T value;
        public Node next;
        public Node last;
        Node(T data) {
            this.value = data;
        }
    }

    public static class DoubleEndsQueue<T>{
        private Node<T> head;
        private Node<T> tail;

        /**
         * 从对列的底部移除元素
         * @param value
         */
        public void addFromBottom(T value){
            Node<T> cur = new Node<>(value);
            if (head == null){
                head = cur;
                tail = cur;
            }else {
                tail.next = cur;
                cur.last = tail;
                tail = cur;
            }
        }

        /**
         * 从队列的头部移除元素
         * @param value
         */
        public void addFromHead(T value){
            Node<T> cur = new Node<>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            }else {
                head.last = cur;
                cur.next = head;
                head = cur;
            }
        }

        /**
         * 从头部移除元素
         * @return
         */
        public T removeFromHead(){
            if (head == null) {
                return null;
            }

            Node<T> cur = head;
            if (head == tail) {
                head = null;
                tail = null;
            }else {
                head = head.next;
                head.last = null;
                cur.next = null;
            }
            return cur.value;
        }

        /**
         * 从底部移除元素
         * @return
         */
        public T removeFromBottom(){
            if (head == null) {
                return null;
            }
            Node<T> cur = tail;
            if (head == tail) {
                head = null;
                tail = null;
            }else {
                tail = tail.last;
                tail.next = null;
                cur.last = null;
            }
            return cur.value;
        }

        public boolean isEmpty(){
            return head == null;
        }
    }

    public static class MyStack<T>{
        private DoubleEndsQueue<T> queue;
        public MyStack() {
            queue = new DoubleEndsQueue<T>();
        }

        // 1.push
        public void push(T value) {
            queue.addFromHead(value);
        }

        // 2.pop
        public T pop(){
            return queue.removeFromHead();
        }
        // 3.isEmpty
        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }

    public static class MyQueue<T>{
        private DoubleEndsQueue<T> myQueue;
        public MyQueue(){
            myQueue = new DoubleEndsQueue();
        }

        // 1.add
        public void add(T value) {
            myQueue.addFromHead(value);
        }

        // 2.poll
        public T poll() {
            return myQueue.removeFromBottom();
        }

        // 3.isEmpty
        public boolean isEmpty(){
            return myQueue.isEmpty();
        }

    }

    private static boolean isEqual(Integer value1,Integer value2) {
        if ((value1 == null && value2 != null) || (value1 != null && value2 == null) ) {
            return false;
        }
        if (value1 == null && value2 == null) {
            return true;
        }
        return value1.equals(value2);
    }

    public static void main(String[] args) {
        int times = 50000;
        int numRange = 1000;
        int numQueueNum = 100;
        for (int i = 0 ;i < times;i++) {
            MyStack<Integer> myStack = new MyStack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();
            Queue<Integer> queue = new LinkedList<>();
            Stack<Integer> stack = new Stack<>();
            for (int j = 0 ;j < numQueueNum;j++) {
                int numS = (int) (Math.random() * numRange);
                if (stack.isEmpty()) {
                    stack.push(numS);
                    myStack.push(numS);
                }else {
                    if (Math.random() < 0.5) {
                        stack.push(numS);
                        myStack.push(numS);
                    }else {
                        if(!isEqual(stack.pop(),myStack.pop())) {
                            System.out.println("oopS!");
                        }
                    }
                }

                int numQ = (int) (Math.random() * numRange);
                if (queue.isEmpty()) {
                    queue.add(numQ);
                    myQueue.add(numQ);
                }else {
                    if(Math.random() < 0.5) {
                        queue.add(numQ);
                        myQueue.add(numQ);
                    }else {
                        if(!isEqual(queue.poll(),myQueue.poll())) {
                            System.out.println("oopQ!");
                        }
                    }
                }
            }
        }
        System.out.println("finish");
    }
}
