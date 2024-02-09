package com.hwy.kotlin;

/**
 * 验证回文串
 * <p>
 * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
 * 字母和数字都属于字母数字字符。
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
 * <p>
 * 输入: s = "A man, a plan, a canal: Panama"
 * 输出：true
 * 解释："amanaplanacanalpanama" 是回文串。
 * <p>
 * 输入：s = "race a car"
 * 输出：false
 * 解释："raceacar" 不是回文串。
 * <p>
 * 输入：s = " "
 * 输出：true
 * 解释：在移除非字母数字字符之后，s 是一个空字符串 "" 。
 * 由于空字符串正着反着读都一样，所以是回文串。
 */
public class CH125 {

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));
        System.out.println(isPalindrome(" "));
    }

    public static boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        if (s.isEmpty()) {
            return true;
        }

        int startPos = 0;
        int endPos = s.length() - 1;
        while (startPos <= endPos) {
            char startCH = s.charAt(startPos);
            char endCH = s.charAt(endPos);
            // 使用 Character 判断是否是数字或者字母
            if (!Character.isLetterOrDigit(startCH)) {
                startPos++;
                continue;
            }
            if (!Character.isLetterOrDigit(endCH)) {
                endPos--;
                continue;
            }
            // 转小写后比较
            if (Character.toLowerCase(startCH) != Character.toLowerCase(endCH)) {
                return false;
            }
            startPos++;
            endPos--;
        }
        return true;
    }

}
