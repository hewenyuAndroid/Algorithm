package com.hwy.kotlin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 无重复字符的最长子串
 * <p>
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 * <p>
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class CH03 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring2("abcabcbb"));
    }

    public static int lengthOfLongestSubstring(String s) {
        /*
        解题思路:
        使用 双重for循环找到所有包含所有满足条件的子串
         */
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        int maxLength = 0;
        List<Character> list = new ArrayList<Character>();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            list.clear();
            list.add(ch);
            for (int j = i + 1; j < length; j++) {
                char ch2 = s.charAt(j);
                if (list.contains(ch2)) {
                    break;
                } else {
                    list.add(ch2);
                }
            }
            maxLength = Math.max(list.size(), maxLength);
        }
        return maxLength;
    }

    public static int lengthOfLongestSubstring2(String s) {
        if (s == null) {
            return 0;
        }
        /*
        解题思路:
        使用滑动窗口
        1. 定一两个指针 left, right,表示区间 (left, right]
        2. 指针初始值都为 0，先向右移动 right 指针，达到满足条件后，向右移动 left 指针
         */

        int left = 0;
        int right = 0;
        int maxLength = 0;
        // 用来存储不重复的字符
        Set<Character> set = new HashSet<>();
        while (right < s.length()) {
            // 找到right位置对应过的字符
            char c = s.charAt(right);
            // 判断存储的数据中是否已经包含了c字符
            while (set.contains(c)) {
                // 判断左指针的字符是否是重复字符
                // 移动做指针，直到删除到重复的 c 字符位置 （注意：这里左指针移动过的字符都需要删除）
                char d = s.charAt(left);
                set.remove(d);
                left++;
            }
            // 将c字符存入缓存
            set.add(c);
            // 移动右指针
            right++;
            // 此时 right - left 为一个没有重复字符的子串，判断是否是最长子串
            maxLength = Math.max(right - left, maxLength);
        }

        return maxLength;
    }

}
