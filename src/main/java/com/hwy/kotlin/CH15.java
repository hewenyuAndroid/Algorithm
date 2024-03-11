package com.hwy.kotlin;

import java.util.ArrayList;
import java.util.List;

/**
 * 三数之和
 * <p>
 * https://leetcode.cn/problems/3sum/description/
 * <p>
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 * <p>
 * 你返回所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组
 * <p>
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 * <p>
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 * <p>
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 */
public class CH15 {

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
        nums = new int[]{0, 1, 1};
        System.out.println(threeSum(nums));
        nums = new int[]{0, 0, 0};
        System.out.println(threeSum(nums));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return null;
        }

        /*
        注意审题:
        和为0，且不重复
        1. 排序，得到一个递增的数组
        2. 排序得到的数据后，开启for循环，内部嵌套 while 循环，while循环中将三位数的比较转换为两位数的比较
         */

        // 1. 排序
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }

        // 存储结果的列表
        List<List<Integer>> list = new ArrayList<>();

        // 比较逻辑，三位数据的比较，first取到倒数第三位数据即可
        for (int i = 0; i < nums.length - 2; i++) {
            // 开启 for 循环，获取不重复的第一位数据
            int num = nums[i];
            if (i > 0 && num == nums[i - 1]) {
                // 过滤重复的数据，得到最后一个重复数据的 index
                continue;
            }

            // 优化点1，如果顺序数组中从i开始往后连续三位数据之和>0，则表示后续的数据和都 >0,可以直接结束for循环
            if (num + nums[i + 1] + nums[i + 2] > 0) {
                break;
            }
            // 优化点2: 如果顺序数组中 i 和 数组最后的两位数据之和<0,则表示没有和 i 匹配为0的两位正整数，跳过本次i的循环
            if (num + nums[nums.length - 1] + nums[nums.length - 2] < 0) {
                continue;
            }

            // 到这里，将三位数的比较转换为二位数的比较 -nums[i] = nums[left] + nums[right]
            // 由于数组是顺序的，因此
            // left 从 i 的左侧开始从左往右取数据
            // right 从数组的右侧开始从右往左取数据
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                if (num + nums[left] + nums[right] < 0) {
                    // 匹配不成功，< 0 表示负数比较大，需要调整 left 指针

                    // 移动左侧指针，过滤重复的数据
                    left++;
                    while (left < right && nums[left - 1] == nums[left]) {
                        left++;
                    }

                } else if (num + nums[left] + nums[right] > 0) {
                    // 匹配不成功, > 0 表示正数比较大，需要调整 right 指针

                    // 移动右侧指针, 过滤重复指针
                    right--;
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else {
                    // 匹配成功，移动 left 和 right 指针进行下一组数据的判断

                    // 1. 保存数据
                    List<Integer> tripper = new ArrayList<>();
                    tripper.add(num);
                    tripper.add(nums[left]);
                    tripper.add(nums[right]);
                    list.add(tripper);

                    // 移动左侧指针，过滤重复的数据
                    left++;
                    while (left < right && nums[left - 1] == nums[left]) {
                        left++;
                    }

                    // 移动右侧指针, 过滤重复指针
                    right--;
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
            }
        }
        return list;
    }

}
