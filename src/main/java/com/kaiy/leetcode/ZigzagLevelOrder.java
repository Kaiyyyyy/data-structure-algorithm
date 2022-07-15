package com.kaiy.leetcode;

import com.kaiy.leetcode.entity.TreeNode;

import java.util.*;

public class ZigzagLevelOrder {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1, null, null);
        TreeNode node3 = new TreeNode(3, null, null);
        TreeNode node2 = new TreeNode(2, node1, node3);
        TreeNode node5 = new TreeNode(5, null, null);
        TreeNode node7 = new TreeNode(7, null, null);
        TreeNode node6 = new TreeNode(6, node5, node7);
        TreeNode node4 = new TreeNode(4, node2, node6);

        System.out.println("levelOrder(node4) = " + zigzagLevelOrder(node4));

        System.out.println(0 & 1);
        System.out.println(1 & 1);
        System.out.println(2 & 1);
        System.out.println(3 & 1);
    }


    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> r = new ArrayList<>();
        List<Integer> everyLine = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        TreeNode nextNode = null;
        TreeNode currNode = root;


        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();

            if (curr.left != null) {
                queue.add(curr.left);
                nextNode = curr.left;
            }
            if (curr.right != null) {
                queue.add(curr.right);
                nextNode = curr.right;
            }



            everyLine.add(curr.val);

            if (curr == currNode) {
                if ((r.size() & 1) == 1) {
                    Collections.reverse(everyLine);
                }
                r.add(everyLine);
                everyLine = new ArrayList<>();
                currNode = nextNode;
            }

        }
        return r;
    }
}
