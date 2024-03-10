package com.hwy.kotlin;

/**
 * K个一组反转链表
 * <p>
 * https://leetcode.cn/problems/reverse-nodes-in-k-group/description/
 * <p>
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * <p>
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 */
public class CH25 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        /*
        注意点:
        1. 如果链表最后的几个节点不是 k 的整数倍，则保留原有的顺序不反转，因此需要先取出 K 区间长度的链表，在进行反转
        2. 链表反转后的最后一个节点需要将 next 置为 null

        解题思路:
        1. K == 1 无需处理直接返回
        2. 先从 head 循环出 K 个节点的链表 头节点记为 subHead
        3. 针对 subFirst 的链表进行反转，注意，这里只能反转 k 个节点，反转的时候需要带上数量
        4. 反转完成后得到自链表的头节点 prev, 末尾节点 subHead
        5. reverseHead 反转后输出的链表为 null 表示当前是第一个反转的子链表，直接保存 prev
        6. prevEnd == null 表示，当前是第一个反转的子链表，直接保存 subFirst
        7. prevEnd != null, 表示当前不是第一个反转的子链表
            7.1 将当前反转的链表拼接 prevEnd.next = prev
            7.2 更新 prevEnd 为当前子链表反转后的最后一个节点 subFirst
            7.3 需要将最后一个节点的 next 置为 null, 否则这里可能会出现环
        8. 整体的 while 循环结束后，需要判断是否有剩余的节点 count > 0，此时需要拼接剩余的节点 prevEnd.next = temp
         */
        if (head == null) {
            return null;
        }

        // k = 1 表示反转一个节点，无需处理直接返回
        if (k == 1) {
            return head;
        }

        ListNode temp = null;
        ListNode prev = null;
        // 记录每段区间反转后的链表最后一个节点
        ListNode prevEnd = null;

        // 记录反转处理后的头节点
        ListNode reverseHead = null;

        // 记录每段需要反转区间链表的头节点
        ListNode subFist = null;
        int count = 0;

        while (head != null) {
            count++;
            if (subFist == null) {
                subFist = head;
            }
            head = head.next;

            // 循环遍历出 count 个节点的子链表，链表头为 subFirst
            if (count == k) {
                // 反转 子链表，反转完成后，子链表的 头节点为 prev, 末尾节点为 subFirst
                ListNode node = subFist;
                while (node != null && count > 0) {
                    temp = node.next;
                    node.next = prev;
                    prev = node;
                    node = temp;
                    count--;
                }
                if (reverseHead == null) {
                    // 如果是第一个反转的子链表，直接将头节点保存为目标链表的头节点
                    reverseHead = prev;
                }
                if (prevEnd != null) {
                    // 如果不是第一个反转的子链表，需要将当前子链表的头节点拼接到上一个子链表的末尾节点
                    prevEnd.next = prev;
                }
                // 更新反转后的末尾节点
                prevEnd = subFist;
                // 将末尾节点 next 置为 null，防止出现环
                prevEnd.next = null;
                subFist = null;
            }
        }

        if (count != 0 && prevEnd != null) {
            // head 遍历结束后，如果count > 0 ,则表示存在没有被反转的子链表 temp，此时直接拼接到反转链表的末尾
            prevEnd.next = temp;
        }

        return reverseHead;
    }

}
