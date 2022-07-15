package com.kaiy.tree;

import java.util.LinkedList;
import java.util.Queue;

public class IsTree {

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

        Node<Integer> node9 = new Node<>(null, 6, null);
        Node<Integer> node8 = new Node<>(node9, 8, null);

//        Node<Integer> node8 = new Node<>(null, 8, null);
//        Node<Integer> node0 = new Node<>(null, 0, null);

//        Node<Integer> node1 = new Node<>(null, 1, node0);
        Node<Integer> node1 = new Node<>(null, 1, null);
        Node<Integer> node3 = new Node<>(null, 3, null);
        Node<Integer> node2 = new Node<>(node1, 2, node3);
        Node<Integer> node5 = new Node<>(null, 5, null);
//        Node<Integer> node7 = new Node<>(null, 7, null);
        Node<Integer> node7 = new Node<>(null, 7, node8);
        Node<Integer> node6 = new Node<>(node5, 6, node7);
        Node<Integer> node4 = new Node<>(node2, 4, node6);


        System.out.println("是否搜索二叉树：" + IsBST.isBst(node4));
        System.out.println("是否搜索二叉树2：" + IsBST.isBst2(node4));
        inorderTraverse(node4);
        System.out.println();
        System.out.println();

        System.out.println("是否平衡二叉树：" + IsBalanceTree.isBalanceTree(node4));
        System.out.println();

        System.out.println("是否完全二叉树：" + IsBCT.isBct(node4));
        System.out.println();

        System.out.println("是否满二叉树：" + IsBFT.isBft(node4));
        System.out.println();

        System.out.println("是否平衡二叉搜索树：" + IsBBST.isBbst2(node4));
    }

    /**
     * 搜索二叉树
     * 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值
     * 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值
     */
    static class IsBST {

        public static Integer preValue = Integer.MIN_VALUE;

        /**
         * 是否为搜索二叉树（广度优先遍历）
         */
        public static boolean isBst(Node<Integer> node) {
            if (node == null) {
                return true;
            }
            boolean isLeftBst = isBst(node.left);
            if (!isLeftBst) {
                return false;
            }
            if (node.value < preValue) {
                return false;
            }
            preValue = node.value;
            return isBst(node.right);
        }

        /**
         * 是否为搜索二叉树（树形DP套路）
         */
        static boolean isBst2(Node<Integer> node) {
            return isBstProcess(node).isBinarySearchTree;
        }

        static BinarySearchTreeResult isBstProcess(Node<Integer> node) {
            if (node == null) {
                return null;
            }
            BinarySearchTreeResult left = isBstProcess(node.left);
            BinarySearchTreeResult right = isBstProcess(node.right);
            int min = node.value;
            int max = node.value;
            if (left != null) {
                min = Math.min(min, left.min);
                max = Math.max(max, left.max);
            }
            if (right != null) {
                min = Math.min(min, right.min);
                max = Math.max(max, right.max);
            }
            boolean isBst =
                    (left == null || (left.isBinarySearchTree && left.max <= node.value))
                            && (right == null || (right.isBinarySearchTree && node.value <= right.min));
            return new BinarySearchTreeResult(isBst, max, min);
        }
    }

    /**
     * 满二叉树
     * 一个二叉树，如果每一个层的结点数都达到最大值，则这个二叉树就是满二叉树。也就是说，如果一个二叉树的层数为K，且结点总数是(2^k) -1 ，则它就是满二叉树
     */
    static class IsBFT {

        /**
         * 是否为满二叉树（树形DP套路）
         */
        static boolean isBft(Node<Integer> node) {
            if (node == null) {
                return true;
            }
            FullTreeResult fullTreeResult = isBftProcess(node);
            return fullTreeResult.nodes == ((1 << fullTreeResult.height) - 1);
        }

        static FullTreeResult isBftProcess(Node<Integer> node) {
            if (node == null) {
                return new FullTreeResult(0, 0);
            }
            FullTreeResult left = isBftProcess(node.left);
            FullTreeResult right = isBftProcess(node.right);
            int height = Math.max(left.height, right.height) + 1;
            int nodes = left.nodes + right.nodes + 1;
            return new FullTreeResult(nodes, height);
        }
    }

    /**
     * 完全二叉树
     * 所有节点全部填满或者所有节点从左往右依次填满的
     * 一棵深度为k的有n个结点的二叉树，对树中的结点按从上至下、从左到右的顺序进行编号，如果编号为i（1≤i≤n）的结点与满二叉树中编号为i的结点在二叉树中的位置相同，则这棵二叉树称为完全二叉树。
     */
    static class IsBCT {

        /**
         * 使用宽度优先遍历
         * 1、任意节点有右无左，返回false
         * 2、满足条件1的前提下，遇到第一个左右孩子不双全的节点，后续节点必须全为叶子节点
         */
        static boolean isBct(Node<Integer> node) {
            if (node == null) {
                return true;
            }
            Queue<Node<Integer>> queue = new LinkedList<>();
            queue.add(node);
            boolean isFirstNoFull = false;
            while (!queue.isEmpty()) {
                Node<Integer> curNode = queue.poll();
                Node<Integer> l = curNode.left;
                Node<Integer> r = curNode.right;

                // 1、存在一个左孩子为空右孩子不为空的节点
                // 2、已存在左右孩子不全的节点且后续节点不全为叶子节点
                if ((isFirstNoFull && (l != null || r != null)) || (l == null && r != null)) {
                    return false;
                }
                if (l != null) {
                    queue.add(l);
                }
                if (r != null) {
                    queue.add(r);
                }
                // 找到第一个左右节点不双全的节点
                if (l == null || r == null) {
                    isFirstNoFull = true;
                }
            }
            return true;
        }
    }

    /**
     * 平衡二叉树
     * 任意节点的子树的高度差都小于等于1
     */
    static class IsBalanceTree {

        /**
         * 判断是否为平衡二叉树（树形DP套路）
         */
        static boolean isBalanceTree(Node<Integer> node) {
            return process(node).isBalance;
        }

        static BalanceTreeResult process(Node<Integer> node) {
            if (node == null) {
                return new BalanceTreeResult(true, 0);
            }
            BalanceTreeResult left = process(node.left);
            BalanceTreeResult right = process(node.right);
            int height = Math.max(left.height, right.height) + 1;
            boolean isBalance = left.isBalance && right.isBalance && Math.abs(left.height - right.height) < 2;
            return new BalanceTreeResult(isBalance, height);
        }

    }

    /**
     * 平衡二叉搜索树
     */
    static class IsBBST {

        static boolean isBbst(Node<Integer> node) {
            return IsBST.isBst2(node) && IsBalanceTree.isBalanceTree(node);
        }

        static boolean isBbst2(Node<Integer> node) {
            return isBbst2Process(node).isBalanceBinarySearchTree;
        }

        static BalanceBinarySearchTreeResult isBbst2Process(Node<Integer> node) {
            if (node == null) {
                return new BalanceBinarySearchTreeResult(true, new BalanceTreeResult(true, 0), null);
            }

            BalanceBinarySearchTreeResult left = isBbst2Process(node.left);
            BalanceBinarySearchTreeResult right = isBbst2Process(node.right);

            int min = node.value;
            int max = node.value;
            if (left.binarySearchTreeResult != null) {
                min = Math.min(min, left.binarySearchTreeResult.min);
                max = Math.max(max, left.binarySearchTreeResult.max);
            }
            if (right.binarySearchTreeResult != null) {
                min = Math.min(min, right.binarySearchTreeResult.min);
                max = Math.max(max, right.binarySearchTreeResult.max);
            }
            boolean isBst =
                    (left.binarySearchTreeResult == null || (left.binarySearchTreeResult.isBinarySearchTree && left.binarySearchTreeResult.max <= node.value))
                            && (right.binarySearchTreeResult == null || (right.binarySearchTreeResult.isBinarySearchTree && node.value <= right.binarySearchTreeResult.min));
            // 组装BST结果集
            BinarySearchTreeResult binarySearchTreeResult = new BinarySearchTreeResult(isBst, max, min);

            int height = Math.max(left.balanceTreeResult.height, right.balanceTreeResult.height) + 1;
            boolean isBalance = left.balanceTreeResult.isBalance && right.balanceTreeResult.isBalance && Math.abs(left.balanceTreeResult.height - right.balanceTreeResult.height) < 2;
            // 组装BT结果集
            BalanceTreeResult balanceTreeResult = new BalanceTreeResult(isBalance, height);

            return new BalanceBinarySearchTreeResult(balanceTreeResult.isBalance && binarySearchTreeResult.isBinarySearchTree, balanceTreeResult, binarySearchTreeResult);
        }

    }

    static class BinarySearchTreeResult {
        boolean isBinarySearchTree;
        Integer max;
        Integer min;

        BinarySearchTreeResult(boolean isBinarySearchTree, Integer max, Integer min) {
            this.isBinarySearchTree = isBinarySearchTree;
            this.max = max;
            this.min = min;
        }
    }

    static class BalanceTreeResult {
        boolean isBalance;
        Integer height;

        BalanceTreeResult(boolean isBalance, Integer height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    static class FullTreeResult {
        Integer nodes;
        Integer height;

        FullTreeResult(Integer nodes, Integer height) {
            this.nodes = nodes;
            this.height = height;
        }
    }

    static class BalanceBinarySearchTreeResult {
        boolean isBalanceBinarySearchTree;
        BalanceTreeResult balanceTreeResult;
        BinarySearchTreeResult binarySearchTreeResult;

        public BalanceBinarySearchTreeResult(boolean isBalanceBinarySearchTree, BalanceTreeResult balanceTreeResult, BinarySearchTreeResult binarySearchTreeResult) {
            this.isBalanceBinarySearchTree = isBalanceBinarySearchTree;
            this.balanceTreeResult = balanceTreeResult;
            this.binarySearchTreeResult = binarySearchTreeResult;
        }
    }


    public static void inorderTraverse(Node<Integer> node) {
        if (node == null) {
            return;
        }
        inorderTraverse(node.left);
        System.out.print(node.value);
        inorderTraverse(node.right);

    }

}
