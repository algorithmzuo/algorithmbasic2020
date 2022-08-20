package class03.util;

public class MyQueue<T> {
    DoubleEndsQueue<T> queue;
    public void push(T value){
        queue.addFromHead(value);
    }
    public T poll(){
        return queue.pollFormBottom();
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }
}
