package com.kaiy.sort;

import java.util.Arrays;

/**
 * 归并排序
 * 时间复杂度为: O(N*logN)
 * 空间复杂度：O(N),merge需要临时数组
 * 稳定性: 稳定排序
 * 与O(N^2)的插入排序相比，将比较后的行为留存下来，所以时间复杂度为O(N*logN)
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 5, 7, 11, 9, 0, 5, 8};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 1、设置单区域的元素大小为1
     * 2、设置左边界位置l=0(从下标0开始执行)
     * 3、计算出中点位置m、右边界位置r
     * 4、将l...r范围上合并成一个有序范围，
     * 5、重新计算左边界,将左边界改为右边界+1,l = r + 1
     * 6、循环3、4、5完成此次mergeSize大小的合并
     * 7、mergeSize扩大一倍
     * 8、循环2、3、4、5、6、7知道mergeSize >= arr.length
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int n = arr.length;

        int mergeSize = 1;

        while (mergeSize < n) {
            int l = 0;
            while (l < n) {
                int m = l + mergeSize - 1;
                if (m >= n) {
                    break;
                }
                int r = Math.min(n - 1, m + mergeSize);
                merge(arr, l, m, r);
                l = r + 1;
            }
            if (mergeSize > n / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    public static void recursionMergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length);
    }

    /**
     * 让arr 在l...r范围上有序。
     * 1、在l...r范围上计算出arr[]中点位置m
     * 2、让左区域(l...m范围)有序
     * 3、让右区域(m+1...r范围)有序
     * 4、让l...r范围上有序，将左右都有序的区域合并成一个整体有序的,
     */
    public static void process(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, mid, r);

    }

    /**
     * 让两个有序数组arr[l...m]和arr[m+1...r]合并成一个有序数组。
     * 1、创建r-l+1长度的数组，并设置i指针指向help第一个为空的位置
     * 2、将左区域的p1指向l(范围上的第一个)、将右区域的p2指向m+1(中点的后一位)
     * 3、将p1和p2指向位置的元素比较
     *    i、p1 <= p2 将p1指向的元素放入的help[]的i位置，i++、p1++
     *    ii、p1 > p2 将p2指向的元素放入的help[]的i位置，i++、p2++
     * 4、p1、p2有一方未转移完，将其直接转移的help中
     * 5、将help[]中的元素刷回到arr[]中
     */
    public static void merge(int[] arr, int l, int m, int r) {

        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;

        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }

        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }

    }
}
