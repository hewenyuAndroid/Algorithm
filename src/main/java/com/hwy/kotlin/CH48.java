package com.hwy.kotlin;

import java.util.Arrays;

/**
 * 旋转图像
 * <p>
 * https://leetcode.cn/problems/rotate-image/
 * <p>
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * <p>
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * <p>
 * <p>
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 * <p>
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 */
public class CH48 {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                new int[]{7, 8, 9}
        };
        rotate(matrix);
        for (int[] rows : matrix) {
            System.out.println(Arrays.toString(rows));
        }
        System.out.println("============");

        matrix = new int[][]{
                new int[]{5, 1, 9, 11},
                new int[]{2, 4, 8, 10},
                new int[]{13, 3, 6, 7},
                new int[]{15, 14, 12, 16}
        };
        rotate(matrix);
        for (int[] rows : matrix) {
            System.out.println(Arrays.toString(rows));
        }
    }

    /**
     *
     * 原理图:
     * 转换目标: src/main/resources/img/CH48_0_target.png
     * 1. 镜像转换 src/main/resources/img/CH48_1_mirror_image.png
     * 2. 反转 src/main/resources/img/CH48_2_reverse.png
     */
    public static void rotate(int[][] matrix) {
        if (matrix == null) {
            return;
        }

        /*
        解题思路:
        1. 沿着 (0, 0) - (row, col) 对角线进行镜像堆成转换处理
        2. 反转每一行数字

        // 具体转换规则如下
        // 原始二位数组
        1, 2, 3
        4, 5, 6
        7, 8, 9

        // 1. 对角线镜像处理
        1, 4, 7
        2, 5, 8
        3, 6, 9

        // 2. 反转每一行
        7, 4, 1
        8, 5, 2
        9, 6, 3
         */

        // 1. 沿着对角线做镜像转换处理
        int row = 0;
        int col = 0;
        while (row < matrix.length) {
            while (col < matrix[row].length) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = temp;
                col++;
            }
            row++;
            // 注意这里，每一行数据只要循环处理对角线右侧的数据即可
            col = row;
        }

        // 2. 反转每一行
        for (int[] rows : matrix) {
            int left = 0;
            int right = rows.length - 1;
            while (left < right) {
                int temp = rows[left];
                rows[left] = rows[right];
                rows[right] = temp;
                left++;
                right--;
            }
        }
    }

}
