package class03.util;

public class DoubleEndsQueue<T> {
    private DoubleNode<T> head;
    private DoubleNode<T> tail;

    /**
     * 从头部添加元素
     * @param value
     */
    public void addFromHead(T value){
        DoubleNode cur = new DoubleNode(value);
        if (head == null){
            head = cur;
            tail = cur;
        }else {
            head.last = cur;
            cur.next = head;
            head = cur;
        }
    }

    /**
     * 从底部插入节点
     * @param value
     */
    public void addFromBottom(T value){
        DoubleNode cur = new DoubleNode(value);
        if (head == null){
            head = cur;
            tail = cur;
        }else {
            tail.next = cur;
            cur.last = tail;
            tail = cur;
        }
    }


    public T pollFromHead(){
        if (head == null){
            return null;
        }
        DoubleNode<T> cur = head;
        if (head == tail) {
            head = null;
            tail = null;
        }else {
            head = head.next;
            cur.next = null;
            head.last = null;
        }
        return cur.value;
    }

    public T pollFormBottom(){
        if (head == null){
            return null;
        }
        DoubleNode<T> cur = tail;
        if (head == tail){
            head = null;
            tail = null;
        }else {
            tail = tail.next;
            cur.last = null;
            tail.next = null;
        }
        return cur.value;
    }

    public boolean isEmpty(){
        return head == null;
    }
}
