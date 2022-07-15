package com.kaiy.search;

/**
 * 1、在有序数组上是否存在x
 * 2、在有序数组上查找第一个等于x的位置
 * 3、在有序数组上查询第一个大于等于X值的位置
 * 4、在有序数组上查询最后一个小于等于X值的位置
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 3, 5, 5, 8, 7, 9, 56, 341,341};

        System.out.println(binarySearch(arr, 56));
        System.out.println(firstValueIndex(arr, 3));
        System.out.println(lastValueIndex(arr, 341));
        System.out.println(firstGtOrEquals(arr, 3));
        System.out.println(lastLtOrEquals(arr, 3));
    }

    public static int binarySearch(int[] arr, int x) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int l = 0;
        int r = arr.length - 1;
        while (r >= l) {
            int m = l + ((r - l) >> 1);
            if (arr[m] == x) {
                return m;
            }
            if (arr[m] > x) {
                r = m - 1;
            }
            if (arr[m] < x) {
                l = m + 1;
            }
        }
        return -1;
    }

    public static int recursionBinarySearch(int[] arr, int x) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        return process(arr, x, 0, arr.length - 1);
    }

    public static int process(int[] arr, int x, int l, int r) {
        if (l == r) {
            return arr[l] == x ? l : -1;
        }
        int m = l + ((r - l) >> 1);
        if (arr[m] == x) {
            return m;
        } else if (arr[m] > x) {
            return process(arr, x, l, m - 1);
        } else {
            return process(arr, x, m + 1, r);
        }
    }

    public static int firstValueIndex(int[] arr, int x) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int l = 0;
        int r = arr.length - 1;
        while (r >= l) {
            int m = l + ((r - l) >> 1);
            if (arr[m] == x) {
                // 是第一个或者前一个位置不为x,返回当前m,否则修改右边界
                if (m == 0 || arr[m - 1] != x) {
                    return m;
                }
                r = m - 1;
            }
            if (arr[m] > x) {
                r = m - 1;
            }
            if (arr[m] < x) {
                l = m + 1;
            }
        }
        return -1;
    }

    public static int lastValueIndex(int[] arr, int x) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int l = 0;
        int r = arr.length - 1;
        while (r >= l) {
            int m = l + ((r - l) >> 1);
            if (arr[m] == x) {
                // 是最后一个或者后一个位置不为x,返回当前m,否则修改左边界
                if (m == arr.length - 1 || arr[m + 1] != x) {
                    return m;
                }
                l = m + 1;
            }
            if (arr[m] > x) {
                r = m - 1;
            }
            if (arr[m] < x) {
                l = m + 1;
            }
        }
        return -1;
    }

    public static int firstGtOrEquals(int[] arr, int x) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int l = 0;
        int r = arr.length - 1;
        int index = -1;
        while (r >= l) {
            int m = l + ((r - l) >> 1);
            if (arr[m] >= x) {
                index = m;
                r = m - 1;
            }else {
                l = m + 1;
            }
        }
        return index;
    }
    public static int lastLtOrEquals(int[] arr, int x) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int l = 0;
        int r = arr.length - 1;
        int index = -1;
        while (r >= l) {
            int m = l + ((r - l) >> 1);
            if (arr[m] <= x) {
                index = m;
                l = m + 1;
            }else {
                r = m - 1;
            }
        }
        return index;
    }

}
