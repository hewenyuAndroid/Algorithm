package com.hwy.kotlin;

/**
 * 爱吃香蕉的珂珂
 * <p>
 * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
 * <p>
 * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
 * <p>
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * <p>
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 * <p>
 * 输入：piles = [3,6,7,11], h = 8
 * 输出：4
 * <p>
 * 输入：piles = [30,11,23,4,20], h = 5
 * 输出：30
 * <p>
 * 输入：piles = [30,11,23,4,20], h = 6
 * 输出：23
 */
public class CH875 {

    public static void main(String[] args) {
        System.out.println(minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
        System.out.println(minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));
        System.out.println(minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6));
    }

    public static int minEatingSpeed(int[] piles, int h) {
        if (piles == null) {
            return 0;
        }

        /*
        使用二分法: 最慢速度为1，最快速度为数组的最大值 maxValue

        1. 获取数组的最大值作为最快速度 maxValue
        2. 定义两个指针 left=1, right=maxValue
        3. 使用二分法判断 left < right
            3.1 计算 mid = (left + right) / 2 速度，吃香蕉的耗时 times
            3.1 判断 times 耗时与目标值 h 的大小，调整 left 或 right 指针
         */

        // 1. 获取数组的最大值
        int maxValue = 0;
        for (int pile : piles) {
            maxValue = Math.max(maxValue, pile);
        }

        // 定义两个指针分别为最慢，最快速度
        int lowSpeed = 1;
        int hightSpeed = maxValue;

        while (lowSpeed < hightSpeed) {
            // 取中间速度
            int midSpeed = (lowSpeed + hightSpeed) / 2;

            int times = 0;
            for (int pile : piles) {
                // 取余判断是否需要增加一个小时的偏移量
                int offset = (pile % midSpeed) > 0 ? 1 : 0;
                times += (pile / midSpeed) + offset;
            }

            if (times <= h) {
                // 当前 midSpeed 速度耗时 times <= h ，说明可以放慢速度，调整区间为 [left, midSpeed]
                hightSpeed = midSpeed;
            } else {
                // 当前 midSpeed 速度耗时 times > h, 说明可以加快速度，调整区间为 [midSpeed+1, right]
                lowSpeed = midSpeed + 1;
            }

        }

        // 返回的 lowSpeed 即为最慢速度
        return lowSpeed;
    }

}
