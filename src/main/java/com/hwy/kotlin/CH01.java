package com.hwy.kotlin;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和:
 * <p>
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1]
 */
public class CH01 {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            // 通过目标值计算出匹配的值
            int key = target - nums[i];
            // 判断匹配的值是否已经存在
            if (map.containsKey(key)) {
                // 匹配成功，返回index数组
                return new int[]{map.get(key), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

}
