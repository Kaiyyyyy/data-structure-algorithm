package com.kaiy.leetcode;

import java.util.*;

public class TwoSum {
    public static void main(String[] args) {

        int[] nums = new int[]{2, 7, 11, 15};
        System.out.println(Arrays.toString(twoSum(nums, 9)));
        int[] nums2 = new int[]{3, 2, 4};
        System.out.println(Arrays.toString(twoSum(nums2, 6)));
        int[] nums3 = new int[]{3, 3};
        System.out.println(Arrays.toString(twoSum(nums3, 6)));


    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> collect = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            Integer t = collect.get(target - num);
            if (t != null) {
                return new int[]{t, i};
            } else {
                collect.put(num, i);
            }
        }
        return null;
    }
}
