package com.hwy.kotlin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 移动零
 * <p>
 * https://leetcode.cn/problems/move-zeroes/description/
 * <p>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * <p>
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * <p>
 * 输入: nums = [0]
 * 输出: [0]
 */
public class CH283 {

    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 0, 3, 12};
//        moveZeroes(arr);
        moveZeroes2(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        /*
        方案1:
        1. 使用一个list记录所有为0的index
        2. while 循环时，判断 nums[q] 不为0时，跟list记录的数据进行替换 (注意，替换完成后，q位置需要重新保存到list中)
         */
        List<Integer> zeroIndexList = new ArrayList<>();
        int q = 0;
        while (q < nums.length) {
            if (nums[q] == 0) {
                zeroIndexList.add(q);
            } else if (nums[q] != 0 && !zeroIndexList.isEmpty()) {
                // 从 list 列表中取出第一个为0的index
                int zeroIndex = zeroIndexList.remove(0);
                nums[zeroIndex] = nums[q];
                nums[q] = 0;
                // 替换完成后，q 位置的值变为了0，因此需要重新保存到list中
                zeroIndexList.add(q);
            }
            q++;
        }
    }

    public static void moveZeroes2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        // 使用双指针的方式

        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                left++;
            }
            right++;
        }
    }

}
