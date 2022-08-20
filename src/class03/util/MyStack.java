package class03.util;

public class MyStack<T> {
    DoubleEndsQueue<T> doubleEndsQueue;

    public MyStack(){
        doubleEndsQueue = new DoubleEndsQueue<>();
    }

    public void push(T value){
        doubleEndsQueue.addFromHead(value);
    }

    public T poll(){
        return doubleEndsQueue.pollFromHead();
    }

    public boolean isEmpty(){
        return doubleEndsQueue.isEmpty();
    }
}
