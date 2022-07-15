package com.kaiy.leetcode;

public class IsPalindrome {

    public static void main(String[] args) {
        int a = 112111;
        System.out.println(isPalindrome(a));

//        System.out.println(11 % 10);
//        System.out.println(4 % 2);
    }

    public static boolean isPalindrome(int x) {

        if (x < 0) {
            return false;
        }

        if (x < 10) {
            return true;
        }

        int l = x;
        int res = 0;

        while (x > 0) {
            int tem = x % 10;
            res = res * 10 + tem;
            x /= 10;
        }

        return l == res;
    }
}
