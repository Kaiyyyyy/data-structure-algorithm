package com.kaiy.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MergeKLists {

    public static void main(String[] args) {

        ListNode listNode1 = new ListNode(0);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        ListNode listNode4 = new ListNode(0);
        ListNode listNode5 = new ListNode(4);
        ListNode listNode6 = new ListNode(5);

        listNode4.next = listNode5;
        listNode5.next = listNode6;
        System.out.println(listNode4);

//        ListNode listNode4xx = new ListNode();
//        ListNode listNode4x = new ListNode();

//        System.out.println(mergeKLists(new ListNode[]{listNode4xx, listNode4x}));
        System.out.println(mergeKLists(new ListNode[]{listNode1, listNode4}));

    }

    public static ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v.val));

        for (ListNode node : lists) {

            if (node != null) {
                queue.add(node);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!queue.isEmpty()) {
            ListNode curNode = queue.poll();
            tail.next = curNode;
            tail = tail.next;
            if (curNode.next != null) {
                queue.add(curNode.next);
            }
        }

        return dummy.next;

    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}


