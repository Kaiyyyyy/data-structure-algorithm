package com.kaiy.sort;

import java.util.Arrays;

/**
 * 希尔排序
 * 时间复杂度：O(N*(logN)^2)
 * 空间复杂度：O(1) 原地排序
 * 稳定性：无
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 1, 4, 7, 9, 5, 5, 76};
//        shellSort(arr);
        shellSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void shellSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int gap = arr.length >> 1; gap > 0; gap >>= 1) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                while (j - gap >= 0 && arr[j - gap] > arr[j]) {
                    swap(arr, j - gap, j);
                    j = j - gap;
                }
            }
        }
    }

    /**
     *
     */
    public static void shellSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int gap = arr.length >> 1; gap > 0; gap >>= 1) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int waiting = arr[i];
                if (arr[i - gap] > waiting) {
                    while (j - gap >= 0 && arr[j - gap] > waiting) {
                        arr[j] = arr[j - gap];
                        j = j - gap;
                    }
                    arr[j] = waiting;
                }

            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
