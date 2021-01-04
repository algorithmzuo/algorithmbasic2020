package leo.class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Leo
 * @ClassName StackAndQueue
 * @DATE 2020/11/20 11:05 上午
 * @Description 双向链表实现栈和队列
 */
public class StackAndQueue {



    public static class DoubleNodeQueue<T> {
        /**
         * @ClassName : DoubleNode
         * @author Leo
         * @date 2020/11/20 11:09 上午
         */
        public static class DoubleNode<T>{
            T value;
            DoubleNode pre;
            DoubleNode next;
            public DoubleNode(T value) {
                this.value = value;
            }





        }
        DoubleNode<T> head;
        DoubleNode<T> tail;

        /**
         * 功能描述 : 向头部添加节点
         * @author Leo
         * @date 2020/11/20 11:22 上午
         * @param value
         * @return void
         */
        public void addHead(T value) {
            DoubleNode<T> node = new DoubleNode<>(value);
            if (head == null) {
                head = node;
                tail = node;
            }else{
                node.next = head;
                head.pre = node;
                head = node;
            }
        }

        /**
         * 功能描述 : 向尾部添加节点
         * @author Leo
         * @date 2020/11/20 11:21 上午
         * @param value
         * @return void
         */
        public void addTail(T value) {
            DoubleNode<T> node = new DoubleNode<>(value);
            if (tail == null) {
                head = node;
                tail = node;
            }else{
                tail.next = node;
                node.pre = tail;
                tail = node;
            }
        }

        /**
         * 功能描述 : 弹出头部
         * @author Leo
         * @date 2020/11/20 11:33 上午
         * @return T
         */
        public T popHead() {
            if (head == null) {
                return null;
            }
            DoubleNode<T> node = head;
            if (head.next != null) {
//            if (head != tail) {
                head = node.next;
                head.pre = null;
                node.next = null;
            } else {
                head = null;
                tail = null;
            }
            return node.value;
        }

        /**
         * 功能描述 : 弹出尾部
         * @author Leo
         * @date 2020/11/20 11:34 上午
         * @return T
         */
        public T popTail() {
            DoubleNode<T> node = this.tail;
            if (tail != head) {
                tail = tail.pre;
                tail.next = null;
                node.pre = null;
            }else{
                tail = null;
                head = null;
            }
            return node.value;

        }

        public boolean isEmpty() {
            return head == null;
        }

    }

    public static class DoubleNodeQueue1<T> {
        /**
         * @ClassName : DoubleNode
         * @author Leo
         * @date 2020/11/20 11:09 上午
         */
        public static class DoubleNode<T>{
            T value;
            DoubleNode pre;
            DoubleNode next;
            public DoubleNode(T value) {
                this.value = value;
            }





        }

        DoubleNode<T> head;
        DoubleNode<T> tail;

        public void pushHead(T value) {
            DoubleNode<T> node = new DoubleNode<T>(value);
            if (head == null) {
                head = node;
                tail = node;
            }else{
                head.pre = node;
                node.next = head;
                head = node;
            }
        }

        public T popHead() {
            if (head == null) {
                return null;
            }
            DoubleNode<T> node = head;
            if (head == tail) {
                head = null;
                tail = null;
            }else{
                head = head.next;
                head.pre = null;
                node.next = null;
            }
            return node.value;

        }

        public void pushTail(T value) {
            DoubleNode<T> node = new DoubleNode<T>(value);
            if (tail == null) {
                tail = node;
                head = node;
            }else{
                node.pre = tail;
                tail.next = node;
                tail = node;
            }

        }

        public T popTail() {
            if (tail == null) {
                return null;
            }
            DoubleNode<T> node = tail;
            if (tail.pre == null) {
                tail = null;
                head = null;
            }else{
                tail.next = null;
                tail = tail.pre;
                node.pre = null;
            }
            return node.value;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    public static class DoubleNodeQueue2<T> {
        /**
         * @ClassName : DoubleNode
         * @author Leo
         * @date 2020/11/20 11:09 上午
         */
        public static class DoubleNode<T>{
            T value;
            DoubleNode pre;
            DoubleNode next;
            public DoubleNode(T value) {
                this.value = value;
            }





        }
        DoubleNode<T> head;
        DoubleNode<T> tail;

        public void pushHead(T value) {
            DoubleNode<T> node = new DoubleNode<>(value);
            if (head == null) {
                head = node;
                tail = node;
            }else{
                head.pre = node;
                node.next = head;
                head = node;
            }
        }

        public void pushTail(T value) {
            DoubleNode<T> node = new DoubleNode<>(value);
            if (tail == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.pre = tail;
                tail = node;
            }
        }

        public T popHead() {
            if (head == null) {
                return null;
            }
            T value = head.value;

            if (head.next == null) {
                head = null;
                tail = null;
            }else{
                head = head.next;
                head.pre = null;
            }

            return value;
        }

        public T popTail() {
            if (tail == null) {
                return null;
            }
            T value = tail.value;
            if (tail.pre == null) {
                head = null;
                tail = null;
            }else{
                tail = tail.pre;
                tail.next = null;
            }

            return value;
        }

        public boolean isEmpty() {
            return head == null;
        }


    }

    public static class DoubleNodeStackAndQueue<T>{
        private static class DoubleNode<T>{
            T value;
            DoubleNode pre;
            DoubleNode next;

            public DoubleNode(T value) {
                this.value = value;
            }
        }

        private DoubleNode<T> head;
        private DoubleNode<T> tail;

        public void pushHead(T value) {
            DoubleNode<T> node = new DoubleNode<T>(value);
            if (this.head == null) {
                this.head = node;
                this.tail = node;
            }else{
                node.next = this.head;
                this.head.pre = node;
                this.head = node ;
            }
        }

        public void pushTail(T value) {
            DoubleNode<T> node = new DoubleNode<T>(value);
            if (this.tail == null) {
                tail = node;
                head = node;
            }else{
                tail.next = node;
                node.pre = tail;
                tail = node;
            }
        }

        public T popHead(){
            if (this.head == null) {
                return null;
            }
            T value = this.head.value;
            if (this.head.next != null) {
                this.head = this.head.next;
                this.head.pre = null;
            }else{
                this.head = null;
                this.tail = null;
            }
            return value;
        }

        public T popTail() {
            if (this.tail == null) {
                return null;
            }
            T value = this.tail.value;
            if (this.tail.pre != null) {
                this.tail = this.tail.pre;
                this.tail.next.pre = null;
                this.tail.next = null;
            }else{
                this.tail = null;
                this.head = null;
            }
            return value;
        }

        public boolean isEmpty() {
            return this.head == null;
        }

    }

    public static class DoubleNodeStackAndQueue1<T> {
        class DoubleNode<T>{
            T value;
            DoubleNode pre;
            DoubleNode next;
            public DoubleNode(T v){
                this.value = v;
            }
        }

        private DoubleNode<T> tail;
        private DoubleNode<T> head;

        public void pushHead(T v) {
            DoubleNode node = new DoubleNode(v);
            if (head == null) {
                tail = node;
                head = node;
            }else {
                node.next = head;
                head.pre = node;
                head = node;
            }
        }
        public void pushTail(T v) {
            DoubleNode node = new DoubleNode(v);
            if (tail == null) {
                tail = node;
                head = node;
            }else {
                tail.next = node;
                node.pre = tail;
                tail = node;
            }
        }

        public T popHead() {
            if (head == null) {
                return null;
            }
            T v = head.value;
            if (head.next != null) {
                head = head.next;
                head.pre.next = null;
                head.pre = null;
            } else {
                head = null;
                tail = null;
            }
            return v;
        }

        public T popTail() {
            if (tail == null) {
                return null;
            }
            T value = tail.value;
            if (tail.pre == null) {
                tail = null;
                head = null;
            } else {
                tail = tail.pre;
                tail.next.pre = null;
                tail.next = null;
            }
            return value;
        }

        public boolean isEmpty() {
            return head == null;
        }



    }

    /**
     * @author Leo
     * @ClassName MyStack
     * @DATE 2020/11/20 11:05 上午
     * @Description 栈 先进后出
     */
    public static class MyStack<T> {
        DoubleNodeQueue<T> myStack;

        public MyStack() {
            myStack = new DoubleNodeQueue<>();
        }

        /**
         * 功能描述 : 压栈
         * @author Leo
         * @date 2020/11/20 11:49 上午
         * @param value
         * @return void
         */
        public void push(T value) {
            myStack.addHead(value);
        }

        /**
         * 功能描述 : 出栈
         * @author Leo
         * @date 2020/11/20 11:49 上午
         * @return T
         */
        public T pop() {
            return myStack.popHead();
        }

        public boolean isEmpty() {
            return myStack.isEmpty();
        }
    }

    /**
     * 栈 先进后出
     */
    public static class MyStack1<T> {
        DoubleNodeQueue1<T> queue;
        public MyStack1(){
            queue = new DoubleNodeQueue1<T>();
        }

        /**
         * 入栈
         */
        public void push(T value) {
            queue.pushHead(value);
        }

        /**
         * 出栈
         */
        public T pop() {
            return queue.popHead();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

    }

    public static class MyStack2<T> {
        DoubleNodeQueue2<T> stack;
        public MyStack2() {
            this.stack = new DoubleNodeQueue2<>();
        }

        public void push(T value) {
            this.stack.pushHead(value);
        }

        public T pop() {
            return stack.popHead();
        }

        public boolean isEmpty() {
            return this.stack.isEmpty();
        }

    }

    public static class MyStack3<T>{
        private DoubleNodeStackAndQueue<T> myStack;

        public MyStack3() {
            this.myStack = new DoubleNodeStackAndQueue<>();
        }

        public void push(T value) {
            this.myStack.popHead();
        }

        public T pop() {
            return this.myStack.popHead();
        }

        public boolean isEmpty() {
            return this.myStack.isEmpty();
        }
    }


    public static class MyStack4<T>{

        private DoubleNodeStackAndQueue1<T> myStack;

        public MyStack4() {
            myStack = new DoubleNodeStackAndQueue1<T>();
        }

        public void push(T value) {
            myStack.pushHead(value);
        }

        public T pop() {
            return myStack.popHead();
        }

        public boolean isEmpty() {
            return myStack.isEmpty();
        }
    }






    /**
     * @author Leo
     * @ClassName MyQueue
     * @DATE 2020/11/20 11:05 上午
     * @Description 队列 先进先出
     */
    public static class MyQueue<T>{

        DoubleNodeQueue<T> myQueue;

        public MyQueue() {
            this.myQueue = new DoubleNodeQueue<>();
        }

        public void push(T value) {
            myQueue.addHead(value);
        }

        public T poll() {
            return myQueue.popTail();
        }

        public boolean isEmpty() {
            return myQueue.isEmpty();
        }

    }

    /**
     * 队列 先进先出
     */
    public static class MyQueue1<T> {

        DoubleNodeQueue1<T> queue;
        public MyQueue1(){
            queue = new DoubleNodeQueue1<T>();
        }

        /**
         * 入列
         */
        public void push(T value) {
            queue.pushHead(value);
        }

        /**
         * 出列
         */
        public T poll() {
            return queue.popTail();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }



    }

    public static class MyQueue2<T> {
        private DoubleNodeQueue2<T> queue;

        public MyQueue2() {
            this.queue = new DoubleNodeQueue2<T>();
        }

        public void push(T value) {
            this.queue.pushHead(value);
        }

        public T poll() {
            return this.queue.popTail();
        }

        public boolean isEmpty() {
            return this.queue.isEmpty();
        }

    }

    public static class MyQueue3<T> {
        private DoubleNodeStackAndQueue<T> myQueue;

        public MyQueue3() {
            this.myQueue = new DoubleNodeStackAndQueue<>();
        }

        public void push(T value) {
            this.myQueue.popHead();
        }

        public T poll() {
            return this.myQueue.popTail();
        }

        public boolean isEmpty() {
            return this.myQueue.isEmpty();
        }
    }

    public static class MyQueue4<T> {
        DoubleNodeStackAndQueue1<T> myQueue;

        public MyQueue4() {
            myQueue = new DoubleNodeStackAndQueue1<>();
        }

        public void push(T v) {
            myQueue.pushHead(v);
        }

        public T poll() {
            return myQueue.popTail();
        }

        public boolean isEmpty() {
            return myQueue.isEmpty();
        }
    }


    public static boolean isEqual(Integer o1, Integer o2) {
        if (o1 == null && o2 != null) {
            return false;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        if (o1 == null && o2 == null) {
            return true;
        }
        return o1.equals(o2);
    }

    public static void main(String[] args){
        int testTime = 1000;
        int forTime = 1000;
        int range = 8000;
        for (int i = 0; i < forTime; i++) {
            MyStack4<Integer> myStack = new MyStack4<>();
            MyQueue4<Integer> myQueue = new MyQueue4<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < testTime; j++) {
                int queueValue = (int) (range * Math.random());
                if (myQueue.isEmpty()) {
                    myQueue.push(queueValue);
                    queue.offer(queueValue);
                } else {
                    if (Math.random() < 0.5) {
                        if(!isEqual(myQueue.poll(),queue.poll())) {
                            System.out.println("myQueue fuck!");
                        }
                    }else{
                        myQueue.push(queueValue);
                        queue.offer(queueValue);
                    }
                }
                int stackValue = (int) (range * Math.random());
                if (myStack.isEmpty()) {
                    myStack.push(stackValue);
                    stack.push(stackValue);
                }else{
                    if (Math.random() < 0.5) {
                        myStack.push(stackValue);
                        stack.push(stackValue);
                    }else{
                        if (!isEqual(myStack.pop(),stack.pop())) {
                            System.out.println("myStack fuck!");
                        }
                    }
                }

            }
        }
        System.out.println("OK!");
    }

}
