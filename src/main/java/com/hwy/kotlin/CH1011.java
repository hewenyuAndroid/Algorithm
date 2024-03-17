package com.hwy.kotlin;

/**
 * 在D天内送达包裹的能力
 * <p>
 * https://leetcode.cn/problems/capacity-to-ship-packages-within-d-days/description/
 * <p>
 * 传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。
 * <p>
 * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 * <p>
 * 返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。
 * <p>
 * 输入：weights = [1,2,3,4,5,6,7,8,9,10], days = 5
 * 输出：15
 * 解释：
 * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
 * 第 1 天：1, 2, 3, 4, 5
 * 第 2 天：6, 7
 * 第 3 天：8
 * 第 4 天：9
 * 第 5 天：10
 * <p>
 * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
 * <p>
 * 输入：weights = [3,2,2,4,1,4], days = 3
 * 输出：6
 * 解释：
 * 船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
 * 第 1 天：3, 2
 * 第 2 天：2, 4
 * 第 3 天：1, 4
 * <p>
 * 输入：weights = [1,2,3,1,1], days = 4
 * 输出：3
 * 解释：
 * 第 1 天：1
 * 第 2 天：2
 * 第 3 天：3
 * 第 4 天：1, 1
 */
public class CH1011 {

    public static void main(String[] args) {
        System.out.println(shipWithinDays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5));
        System.out.println(shipWithinDays(new int[]{1, 2, 3, 1, 1}, 4));
        System.out.println(shipWithinDays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5));
    }

    public static int shipWithinDays(int[] weights, int days) {
        if (weights == null) {
            return 0;
        }

        /*
        解题思路: 承载能力最小为最小货物的重量，最大为所有货物的重量

        1. 最低的运载能力为 最大的货物重量
        2. 最大的运载能力为 sumOf weights[]

        定义两个指针 left=最大货物重量， right=所有货物重量
        1. 使用二分法先认为 mid = (left + right) / 2 为合适的承载能力
        2. 此时判断需要使用的天数 (这里需要遍历 widgets 计算)
        3. 根据 中位数mid 所需的天数，动态调整 left 或 right 指针
         */

        // 1. 找到最大货物的重量和总量
        int maxWeight = 0;
        int sum = 0;
        for (int weight : weights) {
            maxWeight = Math.max(weight, maxWeight);
            sum += weight;
        }

        // left为最大货物重量
        int left = maxWeight;
        // right 为货物总重量
        int right = sum;

        // 如果只有一个货物，那么直接返回 left 即可，这里使用 < 即可
        while (left < right) {
            int mid = (left + right) / 2;
            // need 表示需要运送的天数
            int need = 1;
            // count 记录当前运送的重量总和
            int count = 0;

            // 遍历所有货物，计算当前mid承载能力所需要的天数
            for (int weight : weights) {
                if (count + weight > mid) {
                    need++;
                    count = 0;
                }
                count += weight;
            }

            if (need < days) {
                // mid承载能力需要的天数比目标天数小，说明可以缩小承载能力，调整为 [left, mid]
                // 注意这里要取 mid，因为这里 right 是开区间
                right = mid;
            } else if (need == days) {
                // mid 承载能力满足 days 天数，但是不代表是最小的承载能力，因此还需要继续判断，调整为 [left , mid]
                right = mid;
            } else {
                // mid 承载能力不足时，需要增加承载能力，调整窗口为 [mid+1, right]
                left = mid + 1;
            }
        }

        return left;
    }

}
