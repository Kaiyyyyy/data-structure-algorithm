package com.kaiy.sort;

import java.util.Arrays;

/**
 * 二分插入排序
 * 时间复杂度：O(N^2)
 * 空间复杂度：O(1) 原地排序
 * 稳定性：稳定排序
 */
public class BinaryInsertSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 1, 4, 7, 9, 5, 5, 76};
        binaryInsertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 与插入排序相比，优化点在于利用了前面数组有序的特性，直接用二分法找到位置，而非遍历，
     * 将找待插入位置这步操作时间复杂度 O(N) -> O(logN)
     * 减少的只是比较的次数，并没有减少交换的次数，所以时间复杂度并无本质变化还为O(N^2)
     */
    public static void binaryInsertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int L = 0;
            int R = i - 1;
            int value = arr[i];
            while (L <= R) {
                int M = L + ((R - L) >> 1);
                if (value < arr[M]) {
                    R = M - 1;
                } else {
                    L = M + 1;
                }
            }

            for (int j = i - 1; j >= L; j--) {
                arr[j + 1] = arr[j];
            }

            if (i != L) {
                arr[L] = value;
            }
        }
    }
}
