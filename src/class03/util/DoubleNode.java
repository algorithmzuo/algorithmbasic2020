package class03.util;

public class DoubleNode<T> {
    public T value;
    public DoubleNode<T> next;
    public DoubleNode<T> last;

    public DoubleNode(T data) {
        value = data;
    }
}
