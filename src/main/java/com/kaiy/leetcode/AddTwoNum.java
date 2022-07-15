package com.kaiy.leetcode;


import com.kaiy.leetcode.entity.ListNode;

public class AddTwoNum {

    public static void main(String[] args) {


        System.out.println(13 / 10);
        System.out.println(13 % 10);

        ListNode listNode1 = new ListNode(0);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);
        listNode1.next = listNode2;
//        listNode2.next = listNode3;
        ListNode listNode4 = new ListNode(0);
        ListNode listNode5 = new ListNode(6);
        ListNode listNode6 = new ListNode(4);

        listNode4.next = listNode5;
//        listNode5.next = listNode6;

        System.out.println(addTwoNumbers(listNode1, listNode4));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null || l2 == null) {
            return null;
        }

        ListNode r = null, n = null;

        int up = 0;
        boolean b = true;

        while (l1 != null || l2 != null || up > 0) {

            int value1 = l1 != null ? l1.val : 0;
            int value2 = l2 != null ? l2.val : 0;

            int num = value1 + value2 + up;
            up = 0;

            int downLocation;
            if (num == 0) {
                downLocation = 0;
            } else {
                up = num / 10;
                downLocation = num % 10;
            }

            ListNode node = new ListNode(downLocation);
            if (b) {
                n = r = node;
                b = false;
            } else {
                n = n.next = node;
            }

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;

        }

        return r;
    }

}

//class ListNode {
//    int val;
//    ListNode next;
//
//    ListNode() {
//    }
//
//    ListNode(int val) {
//        this.val = val;
//    }
//
//    ListNode(int val, ListNode next) {
//        this.val = val;
//        this.next = next;
//    }
//
//    @Override
//    public String toString() {
//        return "ListNode{" +
//                "val=" + val +
//                ", next=" + next +
//                '}';
//    }
//}
