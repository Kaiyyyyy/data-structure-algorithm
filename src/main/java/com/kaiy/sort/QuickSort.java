package com.kaiy.sort;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 快速排序
 * 时间复杂度：O(N*logN)
 * 空间复杂度：O(logN)
 * 稳定性：无
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 8, 4, 1, 7, 5, 8, 76, 88, 77};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));

        // 小根堆的实现
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(5);
        minHeap.add(2);
        minHeap.add(9);
        minHeap.add(0);
        minHeap.add(3);
        int size = minHeap.size();
        for (int i = 0; i < size; i++) {
            System.out.println(minHeap.poll());
        }
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    /**
     * 将arr[L...R]上变成有序
     * 1、在L...R上随机选取一个位置randomIndex
     * 2、将randomIndex位置的数据(arr[random])与R(arr[R])位置的交换
     * 3、在L...R上以arr[R]作为标识等于arr[R]区域的起始位置equalArea
     * 4、将arr[L...equalArea[0]]变成有序(<区递归)
     * 5、将arr[equalArea[1]...R]变成有序(<区递归)
     */
    public static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // 随机选取的一个下标是为了概率问题，当选取的下标越靠近中点时时间复杂度越靠近O(N*logN)
        // netherlands时间复杂度为O(N) 当随机值出现在重点位置时两边的递归的的时间复杂度为O(logN),
        // 故最好的时间复杂度为O(N*logN)，期望收敛于O(N*logN)
        int randomIndex = L + (int) (Math.random() * (R - L + 1));
        swap(arr, randomIndex, R);
        int[] equalArea = netherlands(arr, L, R);
        process(arr, L, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, R);
    }

    /**
     * 在L...R范围上以arr[R]元素为标识将arr[]分为三部分，
     * 分别为小于arr[R]区域、等于arr[R]区域、大于arr[R]区域，返回等于arr[R]区域的起始位置
     * 1、
     *    i.   设置<区右边界less为L的前一个位置(less = L -1)、
     *    ii.  设置>区左边界more为R的后一个位置(more = R + 1)
     *    iii. 设置开始下标index为L，从给出范围的第一个元素开始(index = R)
     *    iiii.将arr[R]赋给临时变量value
     * 2、比较arr[index]和value元素的大小。
     *    i.  arr[index] == value,将index++
     *    ii. arr[index] <  value,将arr[<区右边界+1](arr[less+1])与arr[index]交换位置、<区右边界往右扩一个(less++)、index++
     *    iii.arr[index] >  value,将arr[>区左边界-1](arr[more-1])与arr[index]交换位置、>区左边界往左扩一个(more--)
     * 3、循环2,直到index到达>区左边界(index == more)
     */
    public static int[] netherlands(int[] arr, int L, int R) {

        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }

        // <区右边界
        int less = L - 1;
        // >区左边界，将arr[R]划在>区内,最后将其和>区的第一个换位置
        int more = R;
        int index = L;
        while (index < more) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
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
