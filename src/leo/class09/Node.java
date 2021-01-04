package leo.class09;

/**
 * @author Leo
 * @ClassName Node
 * @DATE 2020/12/2 8:56 下午
 * @Description
 */
public class Node{
    int value;
    Node next;
    public Node(int v){
        this.value = v;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Node node = (Node) object;

        if (value != node.value) return false;
        return next.equals(node.next);
    }

    @Override
    public int hashCode() {
        int result = value;
        result = 31 * result + next.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
