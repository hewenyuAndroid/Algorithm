package com.hwy.kotlin;

/**
 * 分隔链表
 * <p>
 * https://leetcode.cn/problems/partition-list/description/
 * <p>
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * <p>
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 * <p>
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 * <p>
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 */
public class CH86 {

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

    public static ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }

        ListNode headA = null;
        ListNode headB = null;
        ListNode nA = null;
        ListNode nB = null;

        while (head != null) {
            if (head.val < x) {
                if (nA != null) {
                    nA.next = head;
                }
                headA = headA == null ? head : headA;
                nA = head;
            } else {
                if (nB != null) {
                    nB.next = head;
                }
                headB = headB == null ? head : headB;
                nB = head;
            }
            head = head.next;
        }

        if (nA != null) {
            nA.next = headB;
        } else {
            headA = headB;
        }

        // 这里 nB 作为右侧的最后一个节点，需要将next置为 null
        if (nB != null) {
            nB.next = null;
        }

        return headA;
    }

}
