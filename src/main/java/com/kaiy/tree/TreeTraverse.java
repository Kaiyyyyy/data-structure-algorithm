package com.kaiy.tree;

import java.util.*;

public class TreeTraverse {

    static class Node<T> {
        Node<T> left;
        Node<T> right;
        T value;

        public Node(Node<T> left, T value, Node<T> right) {
            this.left = left;
            this.value = value;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        Node<Integer> node1 = new Node<>(null, 1, null);
        Node<Integer> node3 = new Node<>(null, 3, null);
        Node<Integer> node2 = new Node<>(node1, 2, node3);
        Node<Integer> node5 = new Node<>(null, 5, null);
        Node<Integer> node7 = new Node<>(null, 7, null);
        Node<Integer> node6 = new Node<>(node5, 6, node7);
        Node<Integer> node4 = new Node<>(node2, 4, node6);

        System.out.print("先序遍历：");
        preorderTraverse(node4);
        System.out.println();

        System.out.print("中序遍历：");
        inorderTraverse(node4);
        System.out.println();

        System.out.print("后序遍历：");
        postorderTraverse(node4);
        System.out.println();

        System.out.print("广度优先遍历：");
        levelTraverse(node4);
        System.out.println();

        System.out.print("深度优先遍历：");
        widthTraverse(node4);
        System.out.println();

        System.out.print("树的最大宽度："+treeMaxWidth(node4));
        System.out.println();
    }

    /**
     * 先序遍历
     */
    public static void preorderTraverse(Node<Integer> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value);
        preorderTraverse(node.left);
        preorderTraverse(node.right);
    }

    /**
     * 中序遍历
     */
    public static void inorderTraverse(Node<Integer> node) {
        if (node == null) {
            return;
        }
        inorderTraverse(node.left);
        System.out.print(node.value);
        inorderTraverse(node.right);

    }

    /**
     * 后序遍历
     */
    public static void postorderTraverse(Node<Integer> node) {
        if (node == null) {
            return;
        }
        postorderTraverse(node.left);
        postorderTraverse(node.right);
        System.out.print(node.value);
    }

    /**
     * 广度优先遍历
     */
    public static void levelTraverse(Node<Integer> node) {
        if (node == null) {
            return;
        }
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node<Integer> n = queue.poll();
            System.out.print(n.value);
            if (n.left != null) {
                queue.add(n.left);
            }
            if (n.right != null) {
                queue.add(n.right);
            }
        }
    }

    /**
     * 深度优先遍历
     */
    public static void widthTraverse(Node<Integer> node) {
        if (node == null) {
            return;
        }
        Stack<Node<Integer>> stack = new Stack<>();
        stack.add(node);
        while (!stack.isEmpty()) {
            Node<Integer> n = stack.pop();
            System.out.print(n.value);
            if (n.right != null) {
                stack.add(n.right);
            }
            if (n.left != null) {
                stack.add(n.left);
            }
        }
    }

    /**
     * 求树的最大宽度,使用map
     */
    public static Integer treeMaxWidthMap(Node<Integer> node) {
        if (node == null) {
            return 0;
        }
        Queue<Node<Integer>> queue = new LinkedList<>();
        Map<Node<Integer>, Integer> levelMap = new HashMap<>();
        queue.add(node);
        levelMap.put(node, 1);
        int curLevel = 1;
        int curLevelNodes = 0;
        int max = 0;
        while (!queue.isEmpty()) {
            Node<Integer> n = queue.poll();
            Integer nodeCurLevel = levelMap.get(n);
            if (n.left != null) {
                queue.add(n.left);
                levelMap.put(n.left, nodeCurLevel + 1);
            }
            if (n.right != null) {
                queue.add(n.right);
                levelMap.put(n.right, nodeCurLevel + 1);
            }
            if (nodeCurLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    /**
     * 求树的最大宽度
     */
    public static Integer treeMaxWidth(Node<Integer> node) {
        if (node == null) {
            return 0;
        }
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(node);
        Node<Integer> curEnd = node;
        Node<Integer> nextEnd = null;
        int curLevelNodes = 0;
        int max = 0;
        while (!queue.isEmpty()) {
            Node<Integer> cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++;
            if (cur == curEnd) {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }
}
