package com.kaiy.tree;

import java.util.ArrayList;
import java.util.Comparator;

public class AVLTree<E> {

    public static void main(String[] args) {

        AVLTree<Integer> tree = new AVLTree<>();

        for (int i = 0; i < 20; i++) {
            tree.insert(i);
        }
        tree.insert(1);
        tree.insert(1);

        System.out.println("是否为平衡树：" + tree.isBalanceTree(tree.root));
        System.out.println();

        tree.inorderTraverse();

        tree.remove(1);
        tree.remove(14);
        tree.remove(15);
        tree.remove(6);

        tree.inorderTraverse();
    }

    private Tree<E> root;

    private Comparator<? super E> comparator;

    private int size;

    static class Tree<E> {
        E data;
        Tree<E> left;
        Tree<E> right;
        int height;

        Tree(E e) {
            this.data = e;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private int getHeight(Tree<E> node) {
        return node == null ? -1 : node.height;
    }

    public boolean contains(E e) {
        return contains(e, root);
    }

    private boolean contains(E e, Tree<E> node) {
        if (node == null) {
            return false;
        }
        int compare = compare(e, node.data);
        if (compare < 0) {
            return contains(e, node.left);
        } else if (compare > 0) {
            return contains(e, node.right);
        } else {
            return true;
        }
    }


    public void insert(E e) {
        root = insert(e, root);
    }

    private Tree<E> insert(E e, Tree<E> root) {
        if (root == null) {
            size++;
            return new Tree<E>(e);
        }
        if (e == null) {
            return root;
        }
        int compare = compare(e, root.data);
        if (compare < 0) {
            root.left = insert(e, root.left);
        } else if (compare > 0) {
            root.right = insert(e, root.right);
        }
        return rebalance(root);
    }

    public void remove(E e) {
        remove(e, root);
    }

    private Tree<E> remove(E e, Tree<E> root) {
        if (root == null) {
            return null;
        }

        int compare = compare(e, root.data);
        if (compare < 0) {
            root.left = remove(e, root.left);
        } else if (compare > 0) {
            root.right = remove(e, root.right);
        } else {
            if (root.left != null && root.right != null) {
                root.data = findMin(root.right).data;
                root.right = remove(root.data, root.right);
            } else {
                root = root.left != null ? root.left : root.right;
                --size;
            }
        }
        return rebalance(root);
    }

    private Tree<E> rebalance(Tree<E> root) {
        if (root == null) {
            return null;
        }
        if ((getHeight(root.left) - getHeight(root.right)) > 1) {
            if (getHeight(root.left.left) >= getHeight(root.left.right)) {
                // 右旋
                root = rotateRight(root);
            } else {
                // 左旋-右旋
                root = rotateLeftAndRight(root);
            }
        }
        if ((getHeight(root.right) - getHeight(root.left)) > 1) {
            if (getHeight(root.right.right) >= getHeight(root.right.left)) {
                // 左旋
                root = rotateLeft(root);
            } else {
                // 右旋-左旋
                root = rotateRightAndLeft(root);
            }
        }
        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        return root;
    }


    private Tree<E> rotateLeft(Tree<E> node) {
        Tree<E> right = node.right;
        node.right = right.left;
        right.left = node;
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        right.height = Math.max(node.height, getHeight(right.right)) + 1;
        return right;
    }

    private Tree<E> rotateRight(Tree<E> node) {
        Tree<E> left = node.left;
        node.left = left.right;
        left.right = node;
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        left.height = Math.max(getHeight(left.left), node.height) + 1;
        return left;
    }


    private Tree<E> rotateLeftAndRight(Tree<E> node) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    private Tree<E> rotateRightAndLeft(Tree<E> node) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    private Tree<E> findMin(Tree<E> node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        }
        return findMin(node.left);
    }

    private Tree<E> findMax(Tree<E> node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node;
        }
        return findMin(node.right);
    }


    boolean isBalanceTree(Tree<E> node) {
        return process(node).isBalance;
    }

    IsTree.BalanceTreeResult process(Tree<E> node) {
        if (node == null) {
            return new IsTree.BalanceTreeResult(true, 0);
        }
        IsTree.BalanceTreeResult left = process(node.left);
        IsTree.BalanceTreeResult right = process(node.right);
        int height = Math.max(left.height, right.height) + 1;
        boolean isBalance = left.isBalance && right.isBalance && Math.abs(left.height - right.height) < 2;
        return new IsTree.BalanceTreeResult(isBalance, height);
    }

    ArrayList<E> array = new ArrayList<>();

    void inorderTraverse() {
        inorderTraverse(root);
        System.out.println(array);
        array.clear();
    }

    void inorderTraverse(Tree<E> node) {

        if (node == null) {
            return;
        }
        inorderTraverse(node.left);
        array.add(node.data);
//        System.out.print(node.data);
        inorderTraverse(node.right);

    }
}
