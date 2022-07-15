package com.kaiy.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 时间复杂度：O(N^2)
 * 空间复杂度：O(1) 原定排序
 * 稳定性：无
 */
public class SelectSort {
    public static void main(String[] args) {

        int[] arr = new int[]{4, 3, 1, 4, 7, 9, 5, 3};

        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {

            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(arr, minIndex, i);
            }

        }

    }


    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
