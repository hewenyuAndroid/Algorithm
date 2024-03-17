package com.hwy.kotlin;

/**
 * 二分查找
 * <p>
 * https://leetcode.cn/problems/binary-search/
 * <p>
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * <p>
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * <p>
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 */
public class CH704 {

    public static void main(String[] args) {
        System.out.println(search(new int[]{-1, 0, 3, 5, 9, 12}, 9));
        System.out.println(search(new int[]{-1, 0, 3, 5, 9, 12}, 2));
    }

    public static int search(int[] nums, int target) {
        int result = -1;
        if (nums == null) {
            return result;
        }

        /*
        使用二分查找法
         */

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                // mid < target 说明 left 到 mid 区间的数据都小于 target
                // 调整区间为 [mid+1, right]
                left = mid + 1;
            } else if (nums[mid] > target) {
                // mid > target 说明 mid 到 right 区间的数据都大于 target
                // 调整区间为 [left, mid-1]
                right = mid - 1;
            } else {
                // 找到匹配target的数据,直接返回
                result = mid;
                break;
            }
        }

        return result;
    }
}
