package com.hwy.kotlin;

/**
 * 最长回文子串
 * <p>
 * https://leetcode.cn/problems/longest-palindromic-substring/description/
 * <p>
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 * <p>
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * <p>
 * 输入：s = "cbbd"
 * 输出："bb"
 */
public class CH05 {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));

        System.out.println(longestPalindrome2("babad"));
        System.out.println(longestPalindrome2("cbbd"));
        System.out.println(longestPalindrome2("aba"));
        System.out.println(longestPalindrome2("bb"));
        System.out.println(longestPalindrome2("tattarrattat"));
    }

    public static String longestPalindrome(String s) {
        if (s == null) {
            // 空字符串直接返回
            return "";
        }
        if (s.length() == 1) {
            // 只有一个字符直接返回
            return s;
        }
        // 默认取第一个字符作为回文
        String maxPalindrome = s.substring(0, 1);
        // 1.从左往右循环获取每一个字符
        // 2.从右开始往左取每个字符与 i 比较是否是回文
        for (int i = 0; i < s.length(); i++) {
            int end = s.length() - 1;

            int posStart = i;
            int posEnd = end;
            while (posStart < posEnd) {
                char chStart = s.charAt(posStart);
                char chEnd = s.charAt(posEnd);
                if (chStart != chEnd) {
                    posStart = i;
                    end--;
                    posEnd = end;
                } else {
                    posStart++;
                    posEnd--;
                }
            }
            if (end - i + 1 > maxPalindrome.length()) {
                // 是回文 && 长度比之前大
                maxPalindrome = s.substring(i, end + 1);

            }
        }
        return maxPalindrome;
    }

    public static String longestPalindrome2(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        /*
        方案2:
        假设每个字符都是回文字符串，从字符左右开始向两边扩展
        此时要判断两种情况:
        回文字符串的长度是奇数时，需要判断 left <= subStr <= right
        回文字符串的长度是偶数时，需要判断 left-1 <= subStr <= right // 注意这里要判断 left 是否越界
         */

        int left = 0;
        int right = 0;

        int index = 0;
        String subStr = "";

        while (index < s.length()) {
            left = index;
            right = index;

            while (true) {
                if (left < 0 || right >= s.length()) {
                    break;
                }

                boolean flag = false;

                if (isPalindrome(s, left, right)) { // 判断是否是奇数长度的回文字符串
                    if (right - left + 1 > subStr.length()) {
                        subStr = s.substring(left, right + 1);
                    }
                    flag = true;
                }
                if (left > 0 && isPalindrome(s, left - 1, right)) { // 判断是否是偶数长度的回文字符串
                    if (right - left + 2 > subStr.length()) {
                        subStr = s.substring(left - 1, right + 1);
                    }
                    flag = true;
                }
                if (!flag) {
                    break;
                }
                left--;
                right++;
            }
            index++;
        }

        return subStr;
    }

    private static boolean isPalindrome(String str, int left, int right) {
        if (left == right) {
            return true;
        }
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

}
