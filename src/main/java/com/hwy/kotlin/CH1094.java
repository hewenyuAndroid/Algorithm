package com.hwy.kotlin;

import java.util.Arrays;

/**
 * 拼车
 * <p>
 * https://leetcode.cn/problems/car-pooling/description/
 * <p>
 * 车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向）
 * <p>
 * 给定整数 capacity 和一个数组 trips ,  trip[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行有 numPassengersi 乘客，接他们和放他们的位置分别是 fromi 和 toi 。这些位置是从汽车的初始位置向东的公里数。
 * <p>
 * 当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false
 * <p>
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 4
 * 输出：false
 * <p>
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 5
 * 输出：true
 * <p>
 * 输入：trips = [[2,1,5],[3,5,7]], capacity = 3
 * 输出：true
 */
public class CH1094 {

    public static void main(String[] args) {

        int[][] trips = new int[][]{
                new int[]{2, 1, 5},
                new int[]{3, 3, 7}
        };
        System.out.println(carPooling(trips, 4));
        System.out.println(carPooling(trips, 5));

        trips = new int[][]{
                new int[]{2, 1, 5},
                new int[]{3, 5, 7}
        };
        System.out.println(carPooling(trips, 3));
    }

    /**
     * 使用差分数组的方式
     * 原数组: nums[]
     * 差分数组: diff[]
     * diff[0] = nums[0]
     * inde>0 时: diff[i] = nums[i] - nums[i-1]
     * <p>
     * ==> nums[i] = diff[0] + diff[1] + 。。 + diff[i]
     */
    public static boolean carPooling(int[][] trips, int capacity) {
        if (trips == null) {
            return false;
        }
        // 先循环一遍找到最长的位置 (最大的 toi)
        int maxTrip = 0;
        for (int i = 0; i < trips.length; i++) {
            maxTrip = Math.max(trips[i][2], maxTrip);
        }

        // 创建一个差分数组
        // 1. 默认值都为 0
        // 2. 数据index从0开始，因此差分数组数据长度 = maxTrip + 1
        // 3. 数据操作的区间为 [fromi, toi), 因此差分数组无需向后扩展一个位置
        int[] diff = new int[maxTrip + 1];

        // 操作差分数组
        for (int[] trip : trips) {
            // 需要操作的数量 人数
            int num = trip[0];
            // 上车位置 即需要操作的第一个数据
            int left = trip[1];
            // 下车位置，即需要操作的最后一个数据
            int right = trip[2];

            // 处理数据
            diff[left] = diff[left] + num;
            // 由于 toi 是一个开区间，因此无需向后移动一个位置
            diff[right] = diff[right] - num;
        }


        // 这里的代码为验证代码，从差分数组 diff 反推出原始数据nums
        int[] nums = new int[maxTrip + 1];
        nums[0] = diff[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = diff[i] + nums[i - 1];
        }
        System.out.println("--------------");
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("diff: " + Arrays.toString(diff));

        // 数据处理完成，判断数据是否超出容量
        // 由于原数组的数据为差分数组的前缀和，因此直接累加即可
        int count = 0;
        for (int num : diff) {
            count += num;
            if (count > capacity) {
                // 超过容量，返回 false
                return false;
            }
        }

        return true;
    }

}
