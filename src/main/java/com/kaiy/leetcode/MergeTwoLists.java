package com.kaiy.leetcode;

import com.kaiy.leetcode.entity.ListNode;

public class MergeTwoLists {

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

        System.out.println("mergeTwoLists(listNode1,listNode4) = " + mergeTwoLists(listNode1, listNode4));
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode now = null;
        ListNode r = null;

        while (list1 != null && list2 != null) {

            if (list1.val <= list2.val) {
                if (r == null) {
                    r = list1;
                    now = list1;
                }else {
                    r.next = list1;
                    r = r.next;
                }
                list1 = list1.next;
            }else {
                if (r == null) {
                    r = list2;
                    now = list2;
                }else {
                    r.next = list2;
                    r = r.next;
                }
                list2 = list2.next;
            }
        }

        while (list1 == null && list2 != null) {
            if (r == null) {
                r = list2;
                now = list2;
            }else {
                r.next = list2;
                r = r.next;
            }
            list2 = list2.next;
        }

        while (list1 != null && list2 == null) {
            if (r == null) {
                r = list1;
                now = list1;
            }else {
                r.next = list1;
                r = r.next;
            }
            list1 = list1.next;
        }
        return now;
    }
}
