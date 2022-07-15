package com.kaiy.sort;

import java.util.Arrays;

public class CountingSort {

    public static void main(String[] args) {
//        int[] arr = new int[]{3, 4, 1, 7, 5, 76, 88, 77};
        int[] arr = new int[]{-4,-5,-2,0, 3, 2,2,78};
        countingSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void countingSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int max = 0;
        int min = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        if (max == min) {
            return;
        }

        int gap = min < 0 ? -min : 0;

        int[] count = new int[max - min + 1];

        for (int i = 0; i < arr.length; i++) {
            count[arr[i] + gap]++;
        }

        int index = 0, i = 0;
        while (index < count.length) {
            if (count[index] == 0) {
                index++;
            }else {
                arr[i++] = index - gap;
                count[index]--;
            }
        }


    }
}
