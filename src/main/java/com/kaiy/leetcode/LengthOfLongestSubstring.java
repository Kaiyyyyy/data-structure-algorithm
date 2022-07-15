package com.kaiy.leetcode;


import java.util.HashMap;

public class LengthOfLongestSubstring {

    public static void main(String[] args) {

        String s1 = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s1));
        String s2 = "bbbbb";
        System.out.println(lengthOfLongestSubstring(s2));
        String s3 = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s3));
    }

    public static int lengthOfLongestSubstring(String s) {

        if (s == null || "".equals(s)) {
            return 0;
        }

        byte[] bytes = s.getBytes();

        int b = 0;
        int e = 0;

        int num = 0;
        int last = 0;

        HashMap<Byte, Integer> map = new HashMap<>();

        while (e < bytes.length) {
            byte n = bytes[e];
            Integer index = map.get(bytes[e]);
            map.put(n, e);
            e++;
            if (index != null && index >= b) {
                b = index + 1;
            }
            last = e - b;
            num = Math.max(last, num);
        }

        return num;
    }
}
