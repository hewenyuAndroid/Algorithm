package com.hwy.kotlin;

import java.util.ArrayList;
import java.util.List;

/**
 * 无重复字符的最长子串
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
    }

    public static int lengthOfLongestSubstring(String s) {
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

}
