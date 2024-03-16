package com.hwy.kotlin;

import java.util.HashMap;

/**
 * 字符串的排列
 * <p>
 * https://leetcode.cn/problems/permutation-in-string/description/
 * <p>
 * <p>
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 * <p>
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 * <p>
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 */
public class CH567 {

    public static void main(String[] args) {
        System.out.println(checkInclusion("ab", "eidbaooo"));
        System.out.println(checkInclusion("ab", "eidboaoo"));
    }

    public static boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        /*
        解题思路: 使用滑动窗口的方式
         */


        // 先创建一个map，解析目标字符串 s1 中每个字符出现的数量
        HashMap<Character, Integer> checkedMap = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            checkedMap.put(c, checkedMap.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = 0;

        int validCount = 0;
        HashMap<Character, Integer> window = new HashMap<>();
        while (right < s2.length()) {
            char c = s2.charAt(right);
            if (checkedMap.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(checkedMap.get(c))) {
                    validCount++;
                }
            }

            right++;

            while (right - left >= s1.length()) {
                if (validCount == checkedMap.size()) {
                    return true;
                }

                char d = s2.charAt(left);
                if (checkedMap.containsKey(d)) {
                    // 更新满足条件字符的数量
                    if (window.get(d).equals(checkedMap.get(d))) {
                        validCount--;
                    }
                    window.put(d, window.getOrDefault(d, 0) - 1);
                }
                left++;
            }
        }
        return false;
    }

}
