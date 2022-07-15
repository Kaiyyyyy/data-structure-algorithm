package com.kaiy.union;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 并查集
 */
public class UnionFind<V> {

    Map<V, Node<V>> nodes = new HashMap<>();
    Map<Node<V>, Node<V>> parents = new HashMap<>();
    Map<Node<V>, Integer> sizeMap = new HashMap<>();

    public UnionFind(List<V> e) {
        for (V v : e) {
            Node<V> node = new Node<>(v);
            nodes.put(v, node);
            parents.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    public Node<V> findFather(Node<V> cur) {
        Stack<Node<V>> path = new Stack<>();
        while (cur != parents.get(cur)) {
            path.push(cur);
            cur = parents.get(cur);
        }
        while (!path.isEmpty()) {
            parents.put(path.pop(), cur);
        }
        return cur;
    }

    public void union(V a, V b) {
        Node<V> aHead = findFather(nodes.get(a));
        Node<V> bHead = findFather(nodes.get(b));
        if (aHead == bHead) {
            return;
        }
        int aSetSize = sizeMap.get(aHead);
        int bSetSize = sizeMap.get(bHead);
        Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
        Node<V> small = big == aHead ? bHead : aHead;
        parents.put(small, big);
        sizeMap.put(big, aSetSize + bSetSize);
        sizeMap.remove(small);
    }

    public boolean isSame(V a, V b) {
        return findFather(nodes.get(a)) == findFather(nodes.get(b));
    }

    public int sets() {
        return sizeMap.size();
    }


    public static class Node<V> {
        public V value;

        public Node(V v) {
            value = v;
        }
    }
}
