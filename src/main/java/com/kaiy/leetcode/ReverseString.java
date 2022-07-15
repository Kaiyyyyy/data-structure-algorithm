package com.kaiy.leetcode;

public class ReverseString {


    public static void main(String[] args) {
        char[] s = {'a','b','c','d'};
        reverseString(s);

    }

    public static void reverseString(char[] s) {

        if (s.length == 1) {
            System.out.println(s);
            return;
        }

        int l = 0;
        int r = s.length - 1;

        while (l < r) {
            s[l] = (char) (s[l] ^ s[r]);
            s[r] = (char) (s[l] ^ s[r]);
            s[l] = (char) (s[l] ^ s[r]);
            l++;
            r--;
        }

        System.out.println(s);
    }

    public static void swap(char[] s, int l, int r) {
        if (l == r) {
            return;
        }
        s[l] = (char) (s[l] ^ s[r]);
        s[r] = (char) (s[l] ^ s[r]);
        s[l] = (char) (s[l] ^ s[r]);
    }
}
