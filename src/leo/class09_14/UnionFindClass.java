package leo.class09_14;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author Leo
 * @ClassName UnionFind
 * @DATE 2020/12/23 11:31 上午
 * @Description 并查集
 */
public class UnionFindClass {

    static class Node<V>{
        V value;
        public Node(V v){
            this.value = v;
        }
    }

    public class UnionFind<V> {
        HashMap<V,Node<V>> nodes;
        HashMap<Node<V>,Node<V>> parents;
        HashMap<Node<V>, Integer> sizeMap;


        public UnionFind(List<V> list) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V v : list) {
                Node node = new Node(v);
                nodes.put(v, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }

        }


        public void union(V a,V b) {
            Node<V> aF = findFather(a);
            Node<V> bF = findFather(b);
            if (aF != bF) {
                Integer aSize = sizeMap.get(a);
                Integer bSize = sizeMap.get(b);
                Node big = aSize >= bSize ? aF : bF;
                Node small = big == aF ? bF : aF;
                parents.put(small, big);
                sizeMap.put(big, bSize + aSize);
                sizeMap.remove(small);
            }
        }
        //判断两个变量是否处于同一个集合.
        public boolean isSameSet(V a,V b){
            Node<V> aF = findFather(a);
            Node<V> bF = findFather(b);
            return aF == bF;
        }
        //找顶级父节点.
        public Node<V> findFather(V v){
            Stack<Node<V>> stack = new Stack<>();
            Node cur = nodes.get(v);
            //如果顶级父节点不是自己,就代表还有父节点.
            while (cur != parents.get(cur)) {
                //记录找过的父节点
                stack.push(cur);
                cur = parents.get(cur);
            }
            //将找过的父节点的父节点设置为顶级父节点
            while (!stack.isEmpty()) {
                parents.put(stack.pop(), cur);
            }
            return cur;
        }

        public int sets() {
            return sizeMap.size();
        }
    }

}
