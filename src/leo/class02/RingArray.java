package leo.class02;


import java.util.Queue;
import java.util.LinkedList;

/**
 * @author Leo
 * @ClassName RingArray
 * @DATE 2020/11/20 2:53 下午
 * @Description 使用数组实现队列
 */
public class RingArray {

    /**
     * 使用数组实现队列
     */
    public static class MyQueue {
        int[] arr;
        int head;
        int tail;
        int size;
        final int limit;


        public MyQueue(int limit) {
            arr = new int[limit];
            head=0;
            tail=0;
            size=0;
            this.limit = limit;
        }

        public void push(int value) {
            if (size == limit) {
                new RuntimeException("队列满了!");
            }
            arr[head] = value;
            size++;
            head = head < limit - 1 ? head + 1 : 0;
        }

        public int poll() {
            if (size == 0) {
                new RuntimeException("队列空了!");
            }
            int value = arr[tail];
            size--;
            tail = tail < limit - 1 ? tail + 1 : 0;
            return value;
        }

        public boolean isEmpty() {
            return size == 0;
        }

    }


    public static class MyQueue1{
        private int[] arr;
        private int size;
        private int push;
        private int poll;
        private final int limit;

        public MyQueue1(int limit) {
            arr = new int[limit];
            this.size = 0;
            this.push = 0;
            this.poll = 0;
            this.limit = limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("队列满了");
            }
            arr[push] = value;
            size++;
            push = nextIndex(push);
        }

        public int poll() {
            if (size == 0) {
                throw new RuntimeException("队列空了");
            }
            int value = arr[poll];
            size--;
            poll = nextIndex(poll);
            return value;
        }

        public int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    public static class MyQueue2{
        private int push;
        private int poll;
        private int size;
        private final int limit;
        private int[] arr;
        public MyQueue2(int limit) {
            this.push = 0;
            this.poll = 0;
            this.size = 0;
            this.arr = new int[limit];
            this.limit = limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("队列满了!");
            }
            this.arr[this.push] = value;
            this.size++;
            this.push = nextIndex(this.push);
        }

        public int poll() {
            if (size == 0) {
                throw new RuntimeException("队列空了!");
            }
            int value = this.arr[poll];
            size--;
            this.poll = nextIndex(poll);
            return value;
        }

        public boolean isEmpty() {
            return size == 0;
        }
        private int nextIndex(int i) {
            return i < limit-1 ? i+1 : 0;
        }
    }

    public static void main(String[] args) {
        int testTime = 10000;
        int range = 100;
        int sizeMax = 80;
        for (int i = 0; i < testTime; i++) {
            int length = randomInt(sizeMax);
            MyQueue2 myQueue = new MyQueue2(length);
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < length; j++) {
                int value = randomInt(range);
                if (myQueue.isEmpty()){
                    myQueue.push(value);
                    queue.offer(value);
                }else{
                    if (Math.random() < 0.5) {
                        myQueue.push(value);
                        queue.offer(value);
                    }else {
                        int myQueueValue = myQueue.poll();
                        Integer queueValue = queue.poll();
                        if (!isEquals(myQueueValue, queueValue)) {
                            System.out.println(myQueueValue);
                            System.out.println(queueValue);
                            System.out.println("fuck!!");
                            break;
                        }
                    }
                }
            }

        }
        System.out.println("OK!👌");

    }

    public static int randomInt(int range) {
        return (int) (range * Math.random()) + 1;
    }

    public static boolean isEquals(Integer a, Integer b) {
        if (a == null && b != null) {
            return false;
        }
        if (a != null && b == null) {
            return false;
        }
        if (a == null || b == null) {
            return true;
        }
        return a.equals(b);
    }

}
