package com.hwy.kotlin;

/**
 * 最长回文子串
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

}
