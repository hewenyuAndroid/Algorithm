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
        if (s == null) {
            return null;
        }
        /*
        解题思路: 使用双指针+滑动窗口
         */
        int left = 0;
        int right = 0;

        // 缓存t字符串中的每个字符数量
        // key: t中每个字符
        // value: 每个字符出现的次数
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            // 更新 c 对应的数量
            // map.getOrDefault(c, 0) 返回 c 对应的数据，不存在时返回 0
            if (t.contains(c + "")) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            // 扩大窗口
            right++;
            while (map.size() == t.length() && left <= right) {
                // 取出left对应的数据
                char d = s.charAt(left);
                // 如果 left 对应的数据在 t 中存在，说明当前需要对 map 缓存的数据进行更新
                // 此时说明  [left, right-1] 区间的首尾都是 t 字符串中的字符
                if (t.contains(d + "")) {
                    // end == start 说明还未记录过满足条件的字符串，此时的 [left, right) 区间就是第一个满足条件的子串
                    // (right - left) < (end - start) 说明最新的子串比已经找到的数据还要短，更新 [start, end)
                    if (end == start || (right - left) < (end - start)) {
                        start = left;
                        end = right;
                    }

                    // 获取 map 缓存的 d 字符对应的数量
                    int count = map.getOrDefault(d, 0);
                    count--;
                    if (count <= 0) {
                        // 注意，如果count<=0，说明此时 [left, right) 窗口内已经没有完整的包含t字符串中的所有字符
                        // 此时 remove d 字符，跳出 left 的循环，继续调整 right 指针扩大窗口，寻找下一个满足条件的子串
                        map.remove(d);
                    } else {
                        // 如果 count > 0, 说明当前 [left, right) 窗口内还有其他位置存在 d 字符，
                        // 更新count后继续调整 left 指针缩小窗口寻找下一个满足条件的子串
                        map.put(d, count);
                    }
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
