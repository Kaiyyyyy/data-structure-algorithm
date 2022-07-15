package com.kaiy.leetcode;

import java.util.LinkedList;
import java.util.Stack;

public class RemoveNthFromEnd {

    public static void main(String[] args) {


        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
//        ListNode listNode3 = new ListNode(3);
//        ListNode listNode4 = new ListNode(4);
//        ListNode listNode5 = new ListNode(5);

        listNode1.next = listNode2;
//        listNode2.next = listNode3;
//        listNode3.next = listNode4;
//        listNode4.next = listNode5;

        System.out.println(removeNthFromEnd(listNode1,2));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {

        if (head == null || n == 0) {
            return head;
        }

        ListNode newHead = head;

        LinkedList<ListNode> deque = new LinkedList<>();


        int i = 0;

        while (head != null) {
            i++;
            deque.addLast(head);
            head = head.next;
        }

        if (n > i) {
            return newHead;
        }

        if (i == 1 && n == 1) {
            return null;
        }


        if (n == i) {
            return newHead.next;
        }

        ListNode prev = null;
        while (true) {
            if (i == n) {
                deque.removeFirst();
                if (deque.isEmpty()) {
                    prev.next = null;
                }else {
                    prev.next = deque.removeFirst();
                }
                return newHead;
            }
            prev = deque.removeFirst();
            i--;
        }

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
