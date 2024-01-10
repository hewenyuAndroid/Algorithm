package com.hwy.kotlin;

import java.util.ArrayList;

/**
 * 寻找两个正序数组的中位数
 * 注意: 如果是相同的数字，则该数字需要重复添加到新的数组中
 * <p>
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * <p>
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 */
public class CH04 {

    public static void main(String[] args) {
        int[] num1 = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4};
        int[] num2 = new int[]{1, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4};
        // >> 3.0

//        int[] num1 = new int[]{1, 1};
//        int[] num2 = new int[]{1, 2};
        // >> 1.5
        System.out.println(findMedianSortedArrays(num1, num2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        ArrayList<Integer> list = new ArrayList<>();
        int pos1 = 0;
        int pos2 = 0;
        Integer value1 = safeGetData(nums1, pos1);
        Integer value2 = safeGetData(nums2, pos2);
        while (value1 != null || value2 != null) {
            if (value1 != null && value2 != null) {
                if (value1 < value2) {
                    insert(list, value1);
                    value1 = safeGetData(nums1, ++pos1);
                } else if (value2 < value1) {
                    insert(list, value2);
                    value2 = safeGetData(nums2, ++pos2);
                } else {
                    insert(list, value1);
                    insert(list, value2);
                    value1 = safeGetData(nums1, ++pos1);
                    value2 = safeGetData(nums2, ++pos2);
                }
            } else if (value1 != null) {
                insert(list, value1);
                value1 = safeGetData(nums1, ++pos1);
            } else {
                insert(list, value2);
                value2 = safeGetData(nums2, ++pos2);
            }
        }

        if (list.isEmpty()) {
            return 0;
        }
        if (list.size() % 2 == 0) {
            // 偶数返回两个数之和的一半
            return (list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2.0;
        } else {
            // 奇数返回中间的数字
            return list.get(list.size() / 2);
        }
    }

    private static void insert(ArrayList<Integer> list, int value) {
        list.add(value);
    }

    private static Integer safeGetData(int[] arr, int index) {
        if (arr != null && index >= 0 && index < arr.length) {
            return arr[index];
        }
        return null;
    }

}
