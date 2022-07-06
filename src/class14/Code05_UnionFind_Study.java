package class14;

import java.util.*;

public class Code05_UnionFind_Study {
    public static class Node<V>{
        public V value;
        public Node(V value){
            this.value = value;
        }
    }

    public static class UnionFind<V>{
        public Map<V,Node<V>> nodes;
        public Map<Node<V>,Node<V>> parents;
        public Map<Node<V>,Integer> sizeMap;

        public UnionFind(List<V> list) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value : list) {
                Node<V> node = new Node<>(value);
                nodes.put(value,node);
                parents.put(node,node);
                sizeMap.put(node,1);
            }
        }

        public Node<V> findFather(Node<V> node){
            Stack<Node<V>> path = new Stack<>();
            while (node != parents.get(node)){
                path.push(node);
                node = parents.get(node);
            }
            // 这里优化将数据结构扁平化，把代表节点下得子节点都直接挂到代表节点
            while (!path.isEmpty()) {
                parents.put(path.pop(),node);
            }
            return node;

        }

        public boolean isSameSet(V a,V b) {
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        /**
         * 小集合挂靠大集合
         * @param a
         * @param b
         */
        public void union(V a,V b) {
            Node aParent = findFather(nodes.get(a));
            Node bParent = findFather(nodes.get(b));
            if (aParent != bParent) {
                int aSetSize = sizeMap.get(aParent);
                int bSetSize = sizeMap.get(bParent);
                Node max = aSetSize >= bSetSize ? aParent:bParent;
                Node small = max == aParent ? bParent : aParent;
                parents.put(small,max);
                sizeMap.put(max,aSetSize + bSetSize);
                sizeMap.remove(bParent);
            }
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.get(1);
        stack.get(2);
    }
}
