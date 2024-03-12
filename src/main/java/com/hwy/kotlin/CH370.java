package com.hwy.kotlin;

import java.util.Arrays;

/**
 * 区间加法
 * <p>
 * 假设你有一个长度为 n 的数组，初始情况下所有的数字均为 0，你将会被给出 k个更新的操作。
 * <p>
 * 其中，每个操作会被表示为一个三元组：[startIndex, endIndex, inc]，你需要将子数组 A[startIndex ... endIndex]（包括 startIndex 和 endIndex）增加 inc。
 * <p>
 * 请你返回 k 次操作后的数组。
 * <p>
 * 输入: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
 * 输出: [-2,0,3,5,3]
 */
public class CH370 {

    public static void main(String[] args) {
        int[][] updates = new int[][]{
                new int[]{1, 3, 2},
                new int[]{2, 4, 3},
                new int[]{0, 2, -2}
        };
        System.out.println(Arrays.toString(getModifiedArray(5, updates)));
        System.out.println(Arrays.toString(getModifiedArray2(5, updates)));
    }

    /**
     * 方案1: 使用双指针的方式，时间复杂度 O(n2)
     */
    public static int[] getModifiedArray(int length, int[][] updates) {
        if (length <= 0) {
            return null;
        }

        int[] nums = new int[length];

        for (int[] update : updates) {
            // left right 为需要操作的指针
            int left = update[0];
            int right = update[1];
            // inc 表示需要操作的值
            int inc = update[2];
            while (left <= right) {
                nums[left] = nums[left] + inc;
                left++;
            }
        }

        return nums;
    }

    /**
     * 方案2: 使用差分数组的方案
     * 1. 构造一个差分数组 diff 差分数组， diff[i] = nums[i] - nums[i-1]
     * 2. 通过差分数组反推出原始数组 nums[i] = diff[i] + nums[i-1]
     * <p>
     * 可以看出 diff 数组反推 nums 数组时，如果对 diff[i] 数据进行了一次操作，
     * 那么该次操作会影响到 nums 数组的 [i, nums.length) 区间的所有数据
     */
    public static int[] getModifiedArray2(int length, int[][] updates) {
        if (length < 1) {
            return null;
        }

        // 由于初始化的数组 nums 值全为0，所以差分数组 diff 也全部为0
        int[] diff = new int[length];
        for (int[] update : updates) {
            // left right 为需要操作的指针
            int left = update[0];
            int right = update[1];
            // inc 表示需要操作的值
            int inc = update[2];

            // 差分数组操作数据
            // 1. 针对差分数组 diff[i] 操作后 +inc，通过 diff 数组反推 nums 数组时，i<=index<nums.length 区间的所有位置数据都+inc
            // 2. 因此需要在操作的 right+1 位置及时的 -inc 数据，保证只操作了 diff数组中的 [left, right] 区间中的数据

            // 操作差分数组的 left 位置的数据
            diff[left] = diff[left] + inc;
            if (right < length - 1) {
                // 如果 right 不是数组的最后一个数据，那么需要及时的减去 inc 数据，防止影响到 right 位置之后的数据
                diff[right + 1] = diff[right + 1] - inc;
            }
        }

        // 根据 diff 差分数组反推 nums 数组
        int[] nums = new int[length];
        // 第一个位置的数据保持一致
        nums[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            nums[i] = diff[i] + nums[i - 1];
        }

        return nums;
    }

}
