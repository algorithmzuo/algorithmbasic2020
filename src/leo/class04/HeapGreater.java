package leo.class04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @author Leo
 * @ClassName HeapGreater
 * @DATE 2020/11/30 5:21 下午
 * @Description 加强堆
 * 1 反向索引
 * 2 比较器
 *
 */
public class HeapGreater<T> {

    //堆容器
    private ArrayList<T> heap ;
    //反向索引
    private HashMap<T, Integer> indexMap;
    //堆大小
    private int heapSize;
    //堆比较器
    private final Comparator<? super T> com;

    public HeapGreater(Comparator com) {
        this.com = com;
        heap = new ArrayList<T>();
        this.indexMap = new HashMap<>();
        this.heapSize = 0;
    }

    //push
    public void push(T value) {
        this.heap.add(value);
        this.indexMap.put(value, heapSize);
        this.heapInsert(heapSize++);
    }

    //peek
    public T peek() {
        return this.heap.get(0);
    }

    //pop
    public T pop() {
        if (heapSize == 0) {
            return null;
        }
        T value = this.heap.get(0);
        this.swap(0, heapSize - 1);
        this.indexMap.remove(value);
        this.heap.remove(--heapSize);
        this.heapify(0);
        return value;
    }

    //remove
    public void remove(T obj) {
        T t = this.heap.get(this.heapSize - 1);
        int objIndex = this.indexMap.get(obj);
        this.indexMap.remove(obj);
        this.heap.remove(--this.heapSize);
        if (obj != t) {
            this.heap.set(objIndex, t);
            this.indexMap.put(t, objIndex);
            this.resign(objIndex);
        }

    }

    //resign
    private void resign(int i) {
        this.heapify(i);
        this.heapInsert(i);
    }

    //getAllElements
    public List<T> getAllElements() {
        List<T> res = new ArrayList<>();
        for (T t : this.heap) {
            res.add(t);
        }
        return res;
    }


    //heapInsert 向上调整
    private void heapInsert(int i) {
        while (this.com.compare(this.heap.get(i), this.heap.get((i - 1) / 2)) < 0) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }


    //heapify 向下调整
    private void heapify(int i) {
        int left = i << 1 | 1;
        while (left < this.heapSize) {
            int largest = left + 1 < this.heapSize && com.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1 : left;
            largest = com.compare(heap.get(largest), heap.get(i)) < 0 ? largest : i;
            if (largest == i) {
                break;

            }
            this.swap(i, largest);
            i = largest;
            left = i << 1 | 1;
        }
    }

    //swap
    private void swap(int i, int j) {
        T t = this.heap.get(i);
        T t1 = this.heap.get(j);
        this.heap.set(i, t1);
        this.heap.set(j, t);
        indexMap.put(t1, i);
        indexMap.put(t, j);
    }

    //size
    public int getSize() {
        return this.heapSize;
    }

    //isEmpty
    public boolean isEmpty() {
        return this.heapSize == 0;
    }
    //contains
    public boolean contains(T obj) {
        return this.indexMap.containsKey(obj);
    }





















}
