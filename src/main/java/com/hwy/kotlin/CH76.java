package com.hwy.kotlin;

import java.util.HashMap;

/**
 * 最小覆盖子串
 * <p>
 * https://leetcode.cn/problems/minimum-window-substring/description/
 * <p>
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * 注意：
 * <p>
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * <p>
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 * <p>
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 */
public class CH76 {

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(minWindow("a", "aa"));
        System.out.println(minWindow("a", "a"));
        System.out.println(minWindow("aa", "aa"));
    }

    public static String minWindow(String s, String t) {
        if (s == null || t == null) {
            return null;
        }
        /*
        解题思路: 使用双指针+滑动窗口+双map的形式
        1. 定义两个map
            一个用于记录字符串t中每种字符的数量 checkedMap（考虑到t中可能会存在重复的字符）
            一个用于记录滑动窗口中的有效字符信息 map
        2. 遍历 t 字符串，将 t 字符串中的每个字符以及出现的次数记录在 checkMap 中
        3. 定义两个指针 left ，right表示滑动窗口区间为 [left, right)
        4. 定义两个指针 start, end 表示满足条件的最小长度区间 [start, end), 默认值都为0，表示还未找到满足条件的区间
        5. 定义 validCount = 0 字段，记录有效字符的数量, 一个字符在滑动窗口和字符串 t 中出现的次数一样，表示一个有效字符
        6. 滑动 right 指针，validCount+1 表示有增加的一个字符在滑动窗口区间内出现的次数和字符串 t 中出现的次数一致
        7. 滑动 left 指针，validCount-1 表示有一个删除的字符在滑动窗口区间内出现的次数和字符串 t 中出现的次数一致

         */
        int left = 0;
        int right = 0;

        // 存储 t 字符串中每个字符的数量，用于下面数据的校验
        HashMap<Character, Integer> checkedMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            checkedMap.put(c, checkedMap.getOrDefault(c, 0) + 1);
        }

        // 缓存t字符串中的每个字符数量
        // key: t中每个字符
        // value: 每个字符出现的次数
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;

        // 满足条件的字符数量，只有 map[key] == checkedMap[key] 时，value才会++
        // validCount == map.size 时，表示此时子串包含 t 字符串的所有字符
        int validCount = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            // 更新 c 对应的数量
            // map.getOrDefault(c, 0) 返回 c 对应的数据，不存在时返回 0
            if (checkedMap.containsKey(c)) {
                map.put(c, map.getOrDefault(c, 0) + 1);
                // 当 map[c] 的数量跟 t字符串中的字符 c 数量一致时，更新有效字符的数量
                if (map.get(c).equals(checkedMap.get(c))) {
                    validCount++;
                }
            }

            // 扩大窗口
            right++;

            // 有效字符的数量跟 checkedMap 的长度一致，表示此时子串[left, right) 区间内包含 t 字符串的所有字符(包括重复的字符串)
            while (validCount == checkedMap.size()) {

                // 这里的每一次循环都是有效的子串，因此在这里判断是否是最小子串
                // end == start 说明还未记录过满足条件的字符串，此时的 [left, right) 区间就是第一个满足条件的子串
                // (right - left) < (end - start) 说明最新的子串比已经找到的数据还要短，更新 [start, end)
                if (start == end || (right - left) < (end - start)) {
                    start = left;
                    end = right;
                }

                // 取出left对应的字符，该字符为即将要被移除的字符
                char d = s.charAt(left);
                if (map.containsKey(d)) {
                    if (map.get(d).equals(checkedMap.get(d))) {
                        validCount--;
                    }
                    // 更新字符d的数量
                    map.put(d, map.get(d) - 1);
                }

                // 移动left指针，缩小窗口
                left++;
            }
        }
        if (end > start) {
            return s.substring(start, end);
        } else {
            return "";
        }
    }

}
