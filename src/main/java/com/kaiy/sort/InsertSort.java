package com.kaiy.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 时间复杂度：O(N^2)
 * 空间复杂度：O(1) 原定排序
 * 稳定性：稳定排序
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 1, 4, 7, 9, 5, 76};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 往后遍历，每次轮询都会把前N个元素排好序，然后再将N+1个元素插入已经排好序的“子数组”里面
     * 直接插入排序判断往哪个下标位置插入时，是遍历了整个子数组，查询的最好时间复杂度是O(1),最坏时间复杂度是O(N),平均时间复杂度是O(N)。
     */
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int x = arr[i];
            int j = i - 1;
            for (; j >= 0 && arr[j] > x; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = x;
        }
    }
}
