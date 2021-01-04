package leo.class06;

import java.util.PriorityQueue;

/**
 * @author Leo
 * @ClassName MyMaxHeap
 * @DATE 2020/11/27 11:01 上午
 * @Description 堆结构
 * 堆结构比堆排序重要
 * 优先级队列[PriorityQueue,默认是小根堆]结构就是堆结构.
 * 完全二叉树:如果一个树是满的就是完全二叉树,如果一棵树不满,也是最后一层,也在变满的路上,如果一个树是从左到右变满的就是完全二叉树.
 * 堆结构:用数组实现的完全二叉树结构,
 * 大根堆:在完全二叉树中,每棵子树的最大值都在顶部
 * 小根堆:在完全二叉树中,每棵子树的最小值都在顶部
 * 堆结构操作:
 * heapInsert 向上调整
 * heapify  向下调整
 * i 左子节点的位置:2*i+1
 * i 右子节点的位置:2*i+2
 * i 父节点的位置:(i-1)/2
 *
 *

 */
public class MyMaxHeap {
    private final int limit;
    private int heapSize;
    private int[] arr;

    public MyMaxHeap(int limit) {
        this.limit = limit;
        this.heapSize = 0;
        this.arr = new int[limit];
    }

    public void push(int value) {
        if (this.heapSize >= this.limit) {
            throw new RuntimeException("heap is full");
        }
        this.arr[this.heapSize] = value;
        heapInsert(arr, this.heapSize++);
    }

    public int pop() {
        if (heapSize == 0) {
            throw new RuntimeException("heap is empty");
        }
        int value = this.arr[0];
        this.swap(arr, 0, --heapSize);
        heapify(arr, 0, heapSize);
        return value;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }


    /**
     * 将index向上调整
     */
    private void heapInsert(int[] arr, int index) {

        while (arr[index] > arr[(index - 1) / 2]) {
            this.swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int[] arr, int index, int heapSize) {
        int left = index << 1 | 1;
        while (left < heapSize) {
            //选择左右哪个子节点,哪个大选择那个
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, index, largest);
            index = largest;
            left = index << 1 | 1;
        }
    }


    private void swap(int[] arr, int i, int j) {
        if (i == j || arr[i] == arr[j]) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

}

class MyMaxHeap1 {

    private int size;
    private final int limit;
    private int[] arr;


    MyMaxHeap1(int limit) {
        this.limit = limit;
        this.arr = new int[limit];
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(int value) {
        if (size == limit) {
            throw new RuntimeException("heap is full");
        }
        arr[size] = value;
        heapInsert(arr, size++);
    }

    public int pop() {
        if (size == 0) {
            throw new RuntimeException("heap is empty!");
        }
        int value = this.arr[0];
        this.swap(arr, 0, --size);
        heapify(arr, 0, size);
        return value;
    }

    private void heapify(int[] arr, int i, int size) {
        int left = i << 1 | 1;
        while (left < size) {
            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[i] >= arr[largest] ? i : largest;
            if (largest == i) {
                break;
            }
            swap(arr, largest, i);
            i = largest;
            left = i << 1 | 1;
        }



    }


    private void heapInsert(int[] arr, int i) {
        while (arr[i] > arr[(i - 1) / 2]) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }

    }

    private void swap(int[] arr, int i, int j) {
        if (i == j || arr[i] == arr[j]) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}

class MyMaxHeap2{

    private int size;
    private int[] arr;
    private final int limit;

    MyMaxHeap2(int limit) {
        this.limit = limit;
        this.size = 0;
        this.arr = new int[limit];
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public void push(int value) {
        if (size == limit) {
            throw new RuntimeException("heap is full");
        }
        this.arr[size] = value;
        this.heapInsert(arr, size++);
    }

    public int pop() {
        int value = this.arr[0];
        swap(this.arr, 0, --this.size);
        heapify(arr, 0, this.size);
        return value;
    }

    private void heapify(int[] arr, int i, int size) {
        int left = i << 1 | 1;
        while (left < size) {
            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[i] ? largest : i;
            if (largest == i) {
                break;
            }
            swap(arr, largest, i);
            i = largest;
            left = i << 1 | 1;
        }
    }

    private void heapInsert(int[] arr, int i) {
        while (arr[i] > arr[(i - 1) / 2]) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }


    private void swap(int[] arr, int i, int j) {
        if (i == j || arr[i] == arr[j]) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}

class MyMaxHeap3 {
    private int size;
    private int[] arr;
    private final int limit;

    public MyMaxHeap3(int limit) {
        this.limit = limit;
        this.size = 0;
        this.arr = new int[limit];
    }

    public void push(int value) {
        if (size == limit) {
            throw new RuntimeException("heap is full");
        }
        arr[size] = value;
        heapInsert(arr, size++);

    }

    public int pop() {
        if (size == 0) {
            throw new RuntimeException("heap is empty");
        }
        int value = arr[0];
        swap(arr, 0, --size);
        heapify(arr, 0, size);
        return value;
    }

    private void heapify(int[] arr, int i, int heapSize) {
        int left = i << 1 | 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[i] ? largest : i;
            if (largest == i) {
                break;
            }
            this.swap(arr, largest, i);
            i = largest;
            left = i << 1 | 1;
        }
    }

    private void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void swap(int[] arr, int i, int j) {
        if (i == j || arr[i] == arr[j]) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }



}

class MyMaxHeap4{

    int size;
    int[] arr;
    final int limit;

    public MyMaxHeap4(int limit) {
        this.limit = limit;
        this.size = 0;
        arr = new int[limit];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(int v) {
        if (size==limit){
            throw new RuntimeException("heap is full");
        }
        arr[size] = v;
        heapInsert(arr, size++);
    }

    public int pop() {
        if (size == 0) {
            throw new RuntimeException("heap is empty");
        }
        int value = arr[0];
        swap(arr, 0, --size);
        heapify(arr, 0, size);
        return value;
    }

    private void heapInsert(int[] arr, int i) {
        while (arr[i] > arr[(i - 1) / 2]) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }

    }

    private void heapify(int[] arr, int index, int heapSize) {
        int left = index << 1 | 1;
        while (left < heapSize) {
            int largest = left+1<heapSize&&arr[left+1]>arr[left]?left+1:left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                return;
            }
            swap(arr, largest, index);
            index = largest;
            left = index << 1 | 1;
        }
    }
    public static void swap(int[] arr, int i, int j) {
        if (i == j || arr[i] == arr[j]) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

}


class MyMaxHeap5{
    private int size;
    private int[] arr;
    private final int limit;

    public MyMaxHeap5(int limit) {
        this.limit = limit;
        this.arr = new int[limit];
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(int value) {
        if (this.size == this.limit) {
            throw new RuntimeException("heap is full");
        }
        arr[size] = value;
        heapInsert(arr, size++);
    }

    public int pop() {
        if (size == 0) {
            throw new RuntimeException("heap is empty");
        }
        int value = arr[0];
        swap(arr, 0, --size);
        heapify(arr, 0, size);
        return value;
    }

    public void heapify(int[] arr, int i, int heapSize) {
        int left = i << 1 | 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[i] ? largest : i;
            if (i == largest) {
                return;
            }
            swap(arr, largest, i);
            i = largest;
            left = i << 1 | 1;
        }
    }
    public void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public void swap(int[] arr, int i, int j) {
        if (i == j || arr[i] == arr[j]) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}


class Main{

    public static void main(String[] args){

        int testTimes = 1000;
        int range = 50;
        System.out.println("start!");
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)-> {return b-a;});
        MyMaxHeap5 heap = new MyMaxHeap5(testTimes);
        for (int i = 0; i < testTimes; i++) {
            if (heap.isEmpty()) {
                int num = (int) ((range * Math.random() + 1) - (range * Math.random() + 1));
                queue.add(num);
                heap.push(num);
            }else {
                if (Math.random() < 0.5) {
                    int num = (int) ((range * Math.random() + 1) - (range * Math.random() + 1));
                    queue.add(num);
                    heap.push(num);
                }else{
                    Integer poll = queue.poll();
                    int pop = heap.pop();
                    if (poll != pop) {
                        System.out.println(poll);
                        System.out.println(pop);
                        System.out.println("fuck!");
                        return;
                    }

                }
            }
        }
        System.out.println("end!");


    }


}
