package com.hwy.kotlin;

/**
 * 二维区域和检索-矩阵不可变
 * <p>
 * https://leetcode.cn/problems/range-sum-query-2d-immutable/description/
 * <p>
 * 给定一个二维矩阵 matrix，以下类型的多个请求：
 * <p>
 * 计算其子矩形范围内元素的总和，该子矩阵的 左上角 为 (row1, col1) ，右下角 为 (row2, col2) 。
 * 实现 NumMatrix 类：
 * <p>
 * NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
 * int sumRegion(int row1, int col1, int row2, int col2) 返回 左上角 (row1, col1) 、右下角 (row2, col2) 所描述的子矩阵的元素 总和 。
 * <p>
 * <p>
 * 参考图片 src/main/resources/img/CH304.png
 * 输入:
 * ["NumMatrix","sumRegion","sumRegion","sumRegion"]
 * [[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,2,2],[1,2,2,4]]
 * 输出:
 * [null, 8, 11, 12]
 * <p>
 * 解释:
 * NumMatrix numMatrix = new NumMatrix([[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]);
 * numMatrix.sumRegion(2, 1, 4, 3); // return 8 (红色矩形框的元素总和)
 * numMatrix.sumRegion(1, 1, 2, 2); // return 11 (绿色矩形框的元素总和)
 * numMatrix.sumRegion(1, 2, 2, 4); // return 12 (蓝色矩形框的元素总和)
 */
public class CH304 {

    public static void main(String[] args) {
        NumMatrix numMatrix = new NumMatrix(new int[][]{
                new int[]{3, 0, 1, 4, 2},
                new int[]{5, 6, 3, 2, 1},
                new int[]{1, 2, 0, 1, 5},
                new int[]{4, 1, 0, 1, 7},
                new int[]{1, 0, 3, 0, 5}
        });
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4));

        System.out.println("----------------");


        NumMatrix2 numMatrix2 = new NumMatrix2(new int[][]{
                new int[]{3, 0, 1, 4, 2},
                new int[]{5, 6, 3, 2, 1},
                new int[]{1, 2, 0, 1, 5},
                new int[]{4, 1, 0, 1, 7},
                new int[]{1, 0, 3, 0, 5}
        });
        System.out.println(numMatrix2.sumRegion(2, 1, 4, 3));
        System.out.println(numMatrix2.sumRegion(1, 1, 2, 2));
        System.out.println(numMatrix2.sumRegion(1, 2, 2, 4));
    }

    /**
     * 方案1：使用循环的方式，时间复杂度 O(n2)
     */
    static class NumMatrix {

        int[][] matrix;

        public NumMatrix(int[][] matrix) {
            this.matrix = matrix;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (matrix == null) {
                return 0;
            }
            int sum = 0;
            int row = row1;
            int col = col1;
            while (row <= row2 && col <= col2) {
                sum += matrix[row][col];
                if (row < row2) {
                    row++;
                } else if (col < col2) {
                    row = row1;
                    col++;
                } else {
                    break;
                }
            }
            return sum;
        }
    }

    /**
     * 方案2：使用前缀和的方案
     * 1. 将二维数组的 [0, 0, i, j] 区域的和存储到前缀和数组 preSums[i+1][j+1]中
     * 2. 二维数组区域 [x1, y1, x2, y2] 区域的和可以拆分计算
     * 拆分结构如图 src/main/resources/img/CH304-2.png
     */
    static class NumMatrix2 {

        // 定义一个存储前缀和的二维数组
        // preSums[i][j] 存储的是 [0, 0, i-1, j-1] 矩阵二位区域的和
        int[][] preSums = null;

        public NumMatrix2(int[][] matrix) {
            int row = matrix.length;
            int col = matrix[0].length;
            if (col == 0) {
                return;
            }
            // preSums 需要比原二位数组增加一行一列
            preSums = new int[row + 1][col + 1];
            for (int i = 1; i < row + 1; i++) {
                for (int j = 1; j < col + 1; j++) {
                    // 前缀和计算
                    // preSums[i][j - 1] + preSums[i][j - 1]
                    //      重复计算了 [0, 0, i-2, j-2] 区域，该区域的和保存在 preSums[i-1][j-1]  ==> 需要减去
                    //      缺少了 matrix[i - 1][j - 1] 位置的数据  ==> 需要加上
                    preSums[i][j] = preSums[i][j - 1] + preSums[i - 1][j] + matrix[i - 1][j - 1] - preSums[i - 1][j - 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return preSums[row2 + 1][col2 + 1]      // [0, 0, row2, col2] 区域的和
                    - preSums[row2 + 1][col1]   // 减去 [0, 0, row2, col1] 区域的和
                    - preSums[row1][col2 + 1]   // 减去 [0, 0, row1, col2] 区域的和
                    + preSums[row1][col1];  // 加上 被减去的重复的部分 [0, 0, row1-1, col1 -1]
        }
    }
}
