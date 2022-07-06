package class06;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code02_Heap_Study {
    public static class MyMaxHeap{
        private int[] heap;
        private final int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull(){
            return heap.length == limit;
        }

        public void push(int value) {
            if (heapSize == limit) {
                throw  new RuntimeException("heap is full");
            }
            heap[heapSize] = value;
            heapInsert(heap,heapSize++);
        }

        /**
         * 弹出返回最大的值同时删除该值
         * @return
         */
        public int pop(){
            int ans = heap[0];
            //将最后一个数和栈顶交换
            swap(heap,0,--heapSize);
            heapify(heap,0,heapSize);
            return ans;
        }

        /**
         * 从第一个数开始向后遍历
         * @param heap
         * @param index
         * @param heapSize
         */
        private void heapify(int[] heap,int index,int heapSize){
            int left = index*2 + 1;
            while (left < heapSize) {
                int largest = left + 1 < heapSize && heap[left + 1] > heap[left] ? left + 1 : left;
                largest = heap[largest] > heap[index] ? largest :index;
                if (largest == index) {
                    break;
                }
                swap(heap,largest,index);
                index = largest;
                left = index *2 +1;
            }
        }



        /**
         * 调整新插入进来的数为大根堆
         * 新值和父节点比较，知道为0或小于父节点
         * @param arr
         * @param index
         */
        private void heapInsert(int[] arr,int index) {
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr,index,(index-1)/2);
                index = (index -1) / 2;
            }
        }

        private void swap(int[] arr,int i,int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static class RightMaxHeap{
        private int[] arr;
        private final int limit;
        private int size;

        RightMaxHeap(int limit) {
            this.limit = limit;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        public boolean isFull(){
            return size == limit;
        }

        public int popMax(){
            int maxIndex = 0;
            for (int i = 1;i < size ;i++){
                if (arr[i] > arr[maxIndex] ){
                    maxIndex = i;
                }
            }
            return maxIndex;
        }
    }

    public static class MyComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    public static void main(String[] args) {
        // 小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());
        heap.add(5);
        heap.add(5);
        heap.add(5);
        heap.add(3);
        System.out.println(heap.peek());
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        System.out.println(heap.peek());
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }

    }
}


