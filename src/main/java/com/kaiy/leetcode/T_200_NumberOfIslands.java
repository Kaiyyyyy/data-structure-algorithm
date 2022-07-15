package com.kaiy.leetcode;

import java.util.*;

/**
 * 200. 岛屿数量
 *
 * <a href="https://leetcode.cn/problems/number-of-islands/">...</a>
 */
public class T_200_NumberOfIslands {


    /**
     * 上下左右感染法
     */
    public int numIslands(char[][] grid) {
        int islands = 0;
        for (int i = 0; i < grid.length; i++) {
            char[] chars = grid[i];
            for (int j = 0; j < chars.length; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    infect(grid, i, j);
                }
            }
        }
        return islands;
    }

    public static void infect(char[][] grid, int i, int j) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = 2;
        infect(grid, i - 1, j);
        infect(grid, i + 1, j);
        infect(grid, i, j - 1);
        infect(grid, i, j + 1);
    }

    /**
     * 并查集法
     */
    public int numIslands2(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        List<Object> dots = new ArrayList<>();
        Object[][] dot = new Object[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    Object o = new Object();
                    dots.add(o);
                    dot[i][j] = o;
                }
            }
        }

        UnionFriend<Object> dotUnionFriend = new UnionFriend<>(dots);

        for (int i = 1; i < row; i++) {
            if (grid[i - 1][0] == '1' && grid[i][0] == '1') {
                dotUnionFriend.union(dot[i - 1][0], dot[i][0]);
            }
        }

        for (int j = 1; j < col; j++) {
            if (grid[0][j - 1] == '1' && grid[0][j] == '1') {
                dotUnionFriend.union(dot[0][j - 1], dot[0][j]);
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (grid[i][j] == '1') {
                    if (grid[i][j - 1] == '1') {
                        dotUnionFriend.union(dot[i][j], dot[i][j - 1]);
                    }
                    if (grid[i - 1][j] == '1') {
                        dotUnionFriend.union(dot[i][j], dot[i - 1][j]);
                    }
                }
            }
        }

        return dotUnionFriend.sets();
    }


    public static class Node<V> {
        public V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionFriend<V> {

        Map<V, Node<V>> nodes = new HashMap<>();
        Map<Node<V>, Node<V>> parents = new HashMap<>();
        Map<Node<V>, Integer> sizeMap = new HashMap<>();

        public UnionFriend(List<V> e) {
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
    }

}
