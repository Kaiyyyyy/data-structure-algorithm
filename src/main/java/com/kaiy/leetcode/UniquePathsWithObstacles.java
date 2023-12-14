package com.kaiy.leetcode;

/**
 * 不同路径 II
 * <a href="https://leetcode-cn.com/problems/unique-paths-ii/">...</a>
 */
public class UniquePathsWithObstacles {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m, n = 0;
        if (obstacleGrid == null || (m = obstacleGrid.length) == 0 || (n = obstacleGrid[0].length) == 0) {
            return 0;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                obstacleGrid[i][j] = obstacleGrid[i][j] == 1 ? -1 : 0;
            }
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == -1) {
                break;
            }
            obstacleGrid[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == -1) {
                break;
            }
            obstacleGrid[i][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == -1) {
                    continue;
                }
                obstacleGrid[i][j] = (obstacleGrid[i - 1][j] == -1 ? 0 : obstacleGrid[i - 1][j]) +
                        (obstacleGrid[i][j - 1] == -1 ? 0 : obstacleGrid[i][j - 1]);
            }
        }
        return obstacleGrid[m - 1][n - 1] == -1 ? 0 : obstacleGrid[m - 1][n - 1];
    }

    public static void main(String[] args) {
        UniquePathsWithObstacles uniquePaths = new UniquePathsWithObstacles();
        int i = uniquePaths.uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}});
        System.out.println(i);
    }
}
