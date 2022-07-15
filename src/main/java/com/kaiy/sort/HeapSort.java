package com.kaiy.sort;

import java.util.Arrays;

/**
 * 堆排序
 * 时间复杂度：O(N*logN)
 * 空间复杂度：O(1) 原地排序
 * 稳定性：无
 */
public class HeapSort {


    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 1, 7, 5, 76, 88, 77};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     *  1、将arr[]变成大根堆
     *  2、让arr[0]去最后一位
     *  3、heapSize大小-1
     *  4、进行heapify,调整成大根堆（0位置为最大）
     *  5、循环执行2、3、4
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 将arr改为大根堆
        // 用heapify改为大根堆的时间复杂度为O(N*logN)
//        for (int i = 0; i < arr.length; i++) {
//            // 一次heapInsert的时间复杂度为O(logN)
//            heapInsert(arr, i);
//        }

        // 用heapify改为大根堆的时间复杂度为O(N)
        for (int i = arr.length - 1; i >= 0 ; i--) {
            heapify(arr, i, arr.length);
        }

        // 1、让arr[0]去最后一位
        // 2、heapSize大小-1
        // 3、进行heapify,调整成大根堆（0位置为最大）
        // 4、循环执行1、2、3
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr,0, heapSize);
            swap(arr,0, --heapSize);
        }

    }

    public static class MaxHeap {
        private final int[] heap;
        private final int limit;
        private int heapSize;

        public MaxHeap(int limit) {
            this.limit = limit;
            heap = new int[limit];
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if (heapSize == limit) {
                throw new RuntimeException("heap is full");
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
        }

        public int pop() {
            int ans = heap[0];
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return ans;
        }
    }

    /**
     * 从下往上改为大根堆
     * 当前index节点 > 父节点((index-1)/2),与父节点交换，将父节点的index((index-1)/2)赋值给index，以此往上换
     */
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 从上往下改为大根堆
     * 1、计算出当前节点的左孩子位置
     * 2、找到左右孩子中比自己大的那个largest
     * 3、与largest交换位置
     * 4、重置index = largest、计算出新的左孩子位置
     */
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            // 找到三者中最大值的位置
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                return;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
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
