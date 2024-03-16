package com.hwy.kotlin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 找到字符串中所有字母异位词
 * <p>
 * https://leetcode.cn/problems/find-all-anagrams-in-a-string/
 * <p>
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * <p>
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）
 * <p>
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * <p>
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 */
public class CH438 {

    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
        System.out.println(findAnagrams("abab", "ab"));
    }

    public static List<Integer> findAnagrams(String s, String p) {
        if (s == null || p == null) {
            return null;
        }

        /*
        解题思路: 使用滑动窗口+双map的方式
        1. 解析字符串 p 中的所有字符数量信息到 checkedMap 中
        2. 定义双指针 left, right 表示滑动窗口区间 [left, right)
        3. 定义 validCount 字段，当某个字符在滑动窗口内部出现的次数与字符串 p 中出现的次数一致时 validCount++,反之--
         */

        // 缓存字符串 p 的每个字符数量信息
        // key: 字符串 p 的字符
        // value: 字符串 p 每个字符的数量
        HashMap<Character, Integer> checkedMap = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            checkedMap.put(c, checkedMap.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = 0;

        // 记录当前 window 区间内的有效字符数量
        int validCount = 0;

        // 记录滑动窗口有效字符信息
        // key: 滑动窗口 [left, right) 区间内，在 p 字符串内出现过的字符
        // value: 滑动窗口 [left, right) 区间内，key 出现的次数
        HashMap<Character, Integer> window = new HashMap<>();

        List<Integer> result = new ArrayList<>();

        while (right < s.length()) {
            char c = s.charAt(right);

            if (checkedMap.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(checkedMap.get(c))) {
                    // 滑动窗口内部字符 c 的数量与字符串 p 中出现的数量一致时，有效字符数量+1
                    validCount++;
                }
            }
            right++;

            while (right - left >= p.length()) {
                if (validCount == checkedMap.size()) {
                    result.add(left);
                }

                char d = s.charAt(left);
                if (checkedMap.containsKey(d)) {
                    // 注意，这里一定要先判断，在更新数量
                    if (window.get(d).equals(checkedMap.get(d))) {
                        validCount--;
                    }
                    // 更新窗口中有效字符的数量
                    window.put(d, window.getOrDefault(d, 0) - 1);
                }
                left++;
            }

        }

        return result;
    }

}
