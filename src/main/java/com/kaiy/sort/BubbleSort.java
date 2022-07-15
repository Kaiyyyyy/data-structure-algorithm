package com.kaiy.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 时间复杂度：O(N^2)
 * 空间复杂度：O(1) 原定排序
 * 稳定性：稳定排序
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] arr = new int[]{4, 3, 1, 4, 7, 9, 5, 3};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = arr.length; i > 0; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
