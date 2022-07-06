package class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class HeapGreater_Study<T> implements Heap<T>{

    private ArrayList<T> heap;
    // 反向索引
    private HashMap<T,Integer> indexMap;
    // 堆大小
    private int heapSize;
    private Comparator<? super T> comparator;

    public HeapGreater_Study(Comparator<T> comparator){
        this.heap = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.heapSize = 0;
        this.comparator = comparator;
    }

    @Override
    public boolean isEmpty() {
        return heapSize  == 0;
    }

    @Override
    public int size() {
        return heapSize;
    }

    @Override
    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    @Override
    public T peek() {
        return heap.get(0);
    }

    @Override
    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj,heapSize);
        heapInsert(heapSize++);
    }

    /**
     * 弹出堆的第一个参数，把最后一个和第一替换，然后重排序
     * @return
     */
    @Override
    public T pop() {
        T ans = heap.get(0);
        swap(0,heapSize - 1);
        indexMap.remove(ans);
        heap.remove(--heapSize);
        heapify(0);
        return ans;
    }

    /**
     * 该函数能够删除任意位置的元素
     * @param obj
     */
    @Override
    public void remove(T obj) {
        T replace= heap.get(heapSize-1);
        int index = indexMap.get(obj);
        indexMap.remove(obj);
        heap.remove(--heapSize);
        if (replace != obj) {
            heap.set(index,replace);
            indexMap.put(replace,index);

        }
    }

    @Override
    public void resign(T obj) {
        heapify(indexMap.get(obj));
        heapInsert(indexMap.get(obj));
    }

    @Override
    public List<T> getAllElements() {
        List<T> list = new ArrayList<>();
        for (T a : heap) {
            list.add(a);
        }
        return list;
    }

    /**
     * heapInsert每次新增一个元素的时候，都会从后面插入，然后向上比较，知道小于父节点或到索引为0的位置
     * @param index
     */
    @Override
    public void heapInsert(int index) {
        while (comparator.compare(heap.get(index),heap.get((index - 1)/2)) < 0){
            swap(index,(index-1)/2);
            index = (index - 1)/2;
        }
    }


    /**
     * heapify从0位置开始向下开始比较，知道比左右子树都小
     * @param index
     */
    @Override
    public void heapify(int index) {
        int left = index * 2 +1;
        while (left < heapSize ){
            int best = left + 1 <heapSize &&  comparator.compare(heap.get(left),heap.get(left + 1)) < 0?left:left+1;
            best = comparator.compare(heap.get(index),heap.get(best)) < 0 ? index:best;
            if (best == size()) {
                break;
            }
            swap(best,index);
            index = best;
            left = index *2 +1;
        }
    }

    private void swap(int index1,int index2) {
        T o1 = heap.get(index1);
        T o2 = heap.get(index2);
        heap.set(index1,o2);
        heap.set(index2,o1);
        indexMap.put(o1,index2);
        indexMap.put(o2,index1);
    }
}
