package com.hwy.kotlin;

/**
 * 7. 整数反转
 * <p>
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * <p>
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 * <p>
 * 输入：x = -123
 * 输出：-321
 * 示例 3：
 * <p>
 * 输入：x = 120
 * 输出：21
 */
public class CH07 {

    public static void main(String[] args) {
        System.out.println(reverse(123));
        System.out.println(reverse(-123));
        System.out.println(reverse(120));
        System.out.println(reverse(-120));
    }

    public static int reverse(int x) {
        int tempVal = 0;
        while (x != 0) {
            // 这里对最大值，最小值除以10判断，如果此时不满足条件（溢出），则 tempVal = tempVal * 10 + val 肯定不满足条件，直接返回0
            if (tempVal < Integer.MIN_VALUE / 10 || tempVal > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int val = x % 10;
            x /= 10;
            tempVal = tempVal * 10 + val;
        }
        return tempVal;
    }

}
