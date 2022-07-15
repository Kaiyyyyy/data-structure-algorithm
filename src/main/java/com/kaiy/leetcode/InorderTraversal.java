package com.kaiy.leetcode;

import java.util.ArrayList;
import java.util.List;

public class InorderTraversal {


    public static void main(String[] args) {

    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderTraversal1(root, list);
        return list;
    }

    public void inorderTraversal1(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorderTraversal1(root.left, list);
        list.add(root.val);
        inorderTraversal1(root.right, list);

    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}


