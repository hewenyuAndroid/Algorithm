package com.hwy.kotlin;

/**
 * 区域和检索 - 数组不可变
 * <p>
 * https://leetcode.cn/problems/range-sum-query-immutable/description/
 * <p>
 * 给定一个整数数组  nums，处理以下类型的多个查询:
 * <p>
 * 计算索引 left 和 right （包含 left 和 right）之间的 nums 元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 * <p>
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int i, int j) 返回数组 nums 中索引 left 和 right 之间的元素的 总和 ，包含 left 和 right 两点（也就是 nums[left] + nums[left + 1] + ... + nums[right] )
 * <p>
 * 输入：
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * 输出：
 * [null, 1, -1, -3]
 * <p>
 * 解释：
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
 * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
 * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
 */
public class CH303 {

    public static void main(String[] args) {
        NumArray array = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(array.sumRange(0, 2));
        System.out.println(array.sumRange(2, 5));
        System.out.println(array.sumRange(0, 5));
        System.out.println("----------");
        NumArray2 array2 = new NumArray2(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(array2.sumRange(0, 2));
        System.out.println(array2.sumRange(2, 5));
        System.out.println(array2.sumRange(0, 5));
    }

    /**
     * 方案1，使用循环，时间复杂度 O(n)
     */
    static class NumArray {
        int[] nums = null;

        public NumArray(int[] nums) {
            this.nums = nums;
        }

        public int sumRange(int left, int right) {
            if (nums == null) {
                return 0;
            }
            int sum = 0;
            while (left <= right) {
                sum += nums[left];
                left++;
            }
            return sum;
        }
    }

    /**
     * 方案2，使用前缀和 时间复杂度 O(1)
     * 原理图 src/main/resources/img/CH303_img.png
     */
    static class NumArray2 {
        int[] sumArr = null;

        public NumArray2(int[] nums) {
            // 定义一个长度比 nums 大1的数组，用来存储每一项的前面数据之和
            sumArr = new int[nums.length + 1];
            // 存储前缀和
            for (int i = 1; i < sumArr.length; i++) {
                sumArr[i] = sumArr[i - 1] + nums[i - 1];
            }
        }

        public int sumRange(int left, int right) {
            // nums[left] 位置的前缀和存储在 sumArr[left+1]
            // nums[right] 位置的前缀和存储在 sumArr[right + 1]
            // 由于 [left, right] 是闭区间，因此 需要减去 left 的前一个位置的前缀和， sumArr[left -1 + 1]
            return sumArr[right + 1] - sumArr[left];
        }
    }

}
