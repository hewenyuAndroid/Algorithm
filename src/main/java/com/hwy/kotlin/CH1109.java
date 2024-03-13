package com.hwy.kotlin;

import java.util.Arrays;

/**
 * 航班预定系统
 * <p>
 * https://leetcode.cn/problems/corporate-flight-bookings/
 * <p>
 * 这里有 n 个航班，它们分别从 1 到 n 进行编号。
 * <p>
 * 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
 * <p>
 * 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
 * <p>
 * 输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * 输出：[10,55,45,25,25]
 * 解释：
 * 航班编号        1   2   3   4   5
 * 预订记录 1 ：   10  10
 * 预订记录 2 ：       20  20
 * 预订记录 3 ：       25  25  25  25
 * 总座位数：      10  55  45  25  25
 * 因此，answer = [10,55,45,25,25]
 * <p>
 * 输入：bookings = [[1,2,10],[2,2,15]], n = 2
 * 输出：[10,25]
 * 解释：
 * 航班编号        1   2
 * 预订记录 1 ：   10  10
 * 预订记录 2 ：       15
 * 总座位数：      10  25
 * 因此，answer = [10,25]
 */
public class CH1109 {

    public static void main(String[] args) {
        int[][] bookings = new int[][]{
                new int[]{1, 2, 10},
                new int[]{2, 3, 20},
                new int[]{2, 5, 25}
        };
        System.out.println(Arrays.toString(corpFlightBookings(bookings, 5)));

        bookings = new int[][]{
                new int[]{1, 2, 10},
                new int[]{2, 2, 15}
        };
        System.out.println(Arrays.toString(corpFlightBookings(bookings, 2)));
    }

    public static int[] corpFlightBookings(int[][] bookings, int n) {
        if (bookings == null) {
            return null;
        }

        // 注意这里的操作下标从 1 开始，转换数据地址需要减去1
        // 这里数据操作是一个闭区间

        int[] diff = new int[n + 1];
        for (int[] booking : bookings) {
            int left = booking[0];
            int right = booking[1];
            int num = booking[2];

            diff[left - 1] = diff[left - 1] + num;
            diff[right] = diff[right] - num;
        }

        int[] nums = new int[n];
        nums[0] = diff[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = diff[i] + nums[i - 1];
        }

        return nums;
    }

}
