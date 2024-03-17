package com.hwy.kotlin;

/**
 * 分割数组的最大值
 * <p>
 * https://leetcode.cn/problems/split-array-largest-sum/description/
 * <p>
 * 给定一个非负整数数组 nums 和一个整数 k ，你需要将这个数组分成 k 个非空的连续子数组。
 * <p>
 * 设计一个算法使得这 k 个子数组各自和的最大值最小。
 * <p>
 * 输入：nums = [7,2,5,10,8], k = 2
 * 输出：18
 * 解释：
 * 一共有四种方法将 nums 分割为 2 个子数组。
 * 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 * <p>
 * 输入：nums = [1,2,3,4,5], k = 2
 * 输出：9
 * <p>
 * 输入：nums = [1,4,4], k = 3
 * 输出：4
 */
public class CH410 {

    public static void main(String[] args) {
        System.out.println(splitArray(new int[]{7, 2, 5, 10, 8}, 2));
        System.out.println(splitArray(new int[]{1, 2, 3, 4, 5}, 2));
        System.out.println(splitArray(new int[]{1, 4, 4}, 3));
    }

    public static int splitArray(int[] nums, int k) {
        if (nums == null) {
            return 0;
        }
        /*
        解题思路: 使用二分法，整数数组的子数组和的取值范围为 [nums数组的最大值, sum(nums)]
        使用二分 sum 的取值来计算分组数量

        1. 获取 nums 数组的子数组的最大值以及数组 sum，分别对应 left 和 right 指针
        2. 使用二分法，判断 mid = (left + right) / 2 时，对应需要几个分组 (由于是连续的子数组，因此通过nums循环即可判断)
        3. 判断二分法的组数和 k 的大小，动态调整 left 或 right指针
         */

        int maxNum = 0;
        int sum = 0;
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
            sum += num;
        }

        int left = maxNum;
        int right = sum;

        while (left < right) {
            int mid = (left + right) / 2;

            // 以 mid 作为最大值时，当前计算的组数
            int count = 1;
            // 记录当前循环累加的和，用于判断是否达到阈值 mid
            int subSum = 0;

            for (int num : nums) {
                // 判断下一项数据的累加和是否溢出
                if (subSum + num > mid) {
                    // 如果溢出，那么更新分组的数量，此时需要重置 count
                    count++;
                    subSum = 0;
                }
                // 累加
                subSum += num;
            }

            if (count <= k) {
                // count <= k，说明此时 mid >= target 值，需要调整区间为 [left, mid]
                right = mid;
            } else {
                // count > k，说明此时 mid < target 值，需要调整区间为 [mid+1, right]
                left = mid + 1;
            }
        }

        return left;
    }

}
