package com.kaiy.leetcode;

/**
 * 62. 不同路径
 * <a href="https://leetcode-cn.com/problems/unique-paths/">...</a>
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        if (m == 1 && n == 1) {
            return 1;
        }
        int[][] ints = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    if (j == 0) {
                        ints[i][j] = 0;
                    } else {
                        ints[i][j] = 1;
                    }
                } else {
                    if (j == 0) {
                        ints[i][j] = 1;
                    } else {
                        ints[i][j] = ints[i - 1][j] + ints[i][j - 1];
                    }
                }
            }
        }
        return ints[m - 1][n - 1];
    }

    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        int i = uniquePaths.uniquePaths(3, 7);
        System.out.println(i);
    }


}
