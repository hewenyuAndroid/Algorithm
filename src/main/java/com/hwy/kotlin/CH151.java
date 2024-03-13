package com.hwy.kotlin;

/**
 * 反转字符串中的单词
 * <p>
 * https://leetcode.cn/problems/reverse-words-in-a-string/description/
 * <p>
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 * <p>
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * <p>
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 * <p>
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 * <p>
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 * <p>
 * 输入：s = "  hello world  "
 * 输出："world hello"
 * 解释：反转后的字符串中不能存在前导空格和尾随空格。
 * <p>
 * 输入：s = "a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，反转后的字符串需要将单词间的空格减少到仅有一个。
 */
public class CH151 {

    public static void main(String[] args) {
        System.out.println(reverseWords("the sky is blue"));
        System.out.println(reverseWords("  hello world  "));
        System.out.println(reverseWords("a good   example"));
    }

    public static String reverseWords(String s) {
        if (s == null) {
            return null;
        }

        /*
        解题思路:
        1. 去掉字符串首尾的 ' ' 空字符串以及单词之间多余的 ' ' 空字符串 (单词之间连续两个以上的空字符串保留1个)
        2. 反转字符串
        2. 反转每个单词
         */

        // 1.1 去除头部的空字符串
        StringBuilder sb = new StringBuilder(s);
        while (sb.charAt(0) == ' ') {
            sb.deleteCharAt(0);
        }
        // 1.2 去除尾部的空字符串
        while (sb.charAt(sb.length() - 1) == ' ') {
            sb.deleteCharAt(sb.length() - 1);
        }
        // 1.3 删除单词之间多余的空字符串 (连续的两个及以上的空字符串只保留一个)
        int pos = 1;
        while (pos < sb.length()) {
            if (sb.charAt(pos) == ' ' && sb.charAt(pos - 1) == ' ') {
                // 连续两个空字符串
                sb.deleteCharAt(pos);
            } else {
                pos++;
            }
        }

        // 2. 反转字符串
        reverse(sb, 0, sb.length() - 1);

        // 3. 反转每个字符
        int left = 0;
        int right = 0;
        while (left < sb.length()) {
            // 考虑到最后一个单词
            if (right == sb.length() - 1) {
                reverse(sb, left, right);
                break;
            }
            if (sb.charAt(right) == ' ') {
                reverse(sb, left, right - 1);
                left = right + 1;
            }
            right++;
        }

        return sb.toString();
    }

    private static void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char temp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, temp);
            left++;
            right--;
        }
    }

}
