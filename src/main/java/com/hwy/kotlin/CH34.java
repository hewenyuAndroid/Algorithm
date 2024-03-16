package com.hwy.kotlin;

import java.util.Arrays;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 * <p>
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/description/
 * <p>
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 */
public class CH34 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)));
        System.out.println(Arrays.toString(searchRange(new int[0], 0)));
        System.out.println("-------------------");
        System.out.println(Arrays.toString(searchRange2(new int[]{5, 7, 7, 8, 8, 10}, 8)));
        System.out.println(Arrays.toString(searchRange2(new int[]{5, 7, 7, 8, 8, 10}, 6)));
        System.out.println(Arrays.toString(searchRange2(new int[0], 0)));
    }

    public static int[] searchRange2(int[] nums, int target) {
        /*
        使用二分查找法
        注意，寻找 end 时，mid位置需要向上+1
         */
        int[] result = new int[]{-1, -1};
        if (nums == null || nums.length < 1) {
            return result;
        }

        // 1. 先查找开始位置
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                // 中间位置小于target，取右半边数据  [mid+1, right]
                left = mid + 1;
            } else if (nums[mid] == target) {
                // 中间位置==target，由于是寻找start，取左半边 [left, mid]
                right = mid;
            } else if (nums[mid] > target) {
                // 中间位置大于target，取左半边    [left, mid-1]
                right = mid - 1;
            }
        }

        if (nums[left] != target) {
            // start没有找到target时，直接返回
            return result;
        } else {
            // 存在target，则，left位置即为第一个target
            result[0] = left;
        }

        // 寻找最后一个位置
        left = 0;
        right = nums.length - 1;
        while (left < right) {
            // 特别注意: 取end时，计算的mid需要向后移动一位
            int mid = (left + right) / 2 + 1;
            if (nums[mid] < target) {
                // 中间位置小于target，取右半边数据 [mid+1, right]
                left = mid + 1;
            } else if (nums[mid] == target) {
                // 数据匹配，由于是寻找 end，取右半边数据 [mid, right]
                left = mid;
            } else if (nums[mid] > target) {
                // 中间位置大于target，取左半边数据 [left, mid-1]
                right = mid - 1;
            }
        }

        if (nums[left] == target) {
            result[1] = left;
        }

        return result;
    }

    public static int[] searchRange(int[] nums, int target) {
        /*
        暴力解法
         */
        int[] result = new int[]{-1, -1};
        if (nums == null || nums.length < 1) {
            return result;
        }

        int pos = 0;
        int count = 0;
        while (pos < nums.length) {
            if (nums[pos] == target) {
                if (result[0] == -1) {
                    result[0] = pos;
                }
                count++;
            }
            pos++;
        }

        if (count > 0) {
            result[1] = result[0] + count - 1;
        }

        return result;
    }

}
