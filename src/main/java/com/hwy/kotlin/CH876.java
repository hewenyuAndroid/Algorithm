package com.hwy.kotlin;

/**
 * 链表的中间节点
 * <p>
 * https://leetcode.cn/problems/middle-of-the-linked-list/description/
 * <p>
 * 给你单链表的头结点 head ，请你找出并返回链表的中间结点。
 * <p>
 * 如果有两个中间结点，则返回第二个中间结点。
 * <p>
 * 输入：head = [1,2,3,4,5]
 * 输出：[3,4,5]
 * 解释：链表只有一个中间结点，值为 3
 * <p>
 * 输入：head = [1,2,3,4,5,6]
 * 输出：[4,5,6]
 * 解释：该链表有两个中间结点，值分别为 3 和 4 ，返回第二个结点。
 */
public class CH876 {

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

    public static ListNode middleNode(ListNode head) {
        /*
        思路: 使用快慢指针
        1. 快指针比慢指针 step 快一步 (快指针的位置增长为 1, 3, 5, 7, ...)
        2. 当快指针执行完毕时，慢指针刚好在中间位置，此时有两种情况
        2.1 快指针.next == null 此时链表的长度为奇数，此时慢指针的位置刚好在链表中间节点
        2.2 快指针.next.next == null ，此时链表的长度为偶数，此时需要返回中位数的靠后位置的节点
         */
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast.next == null) {
            // 奇数
            return slow;
        } else {
            // 偶数
            return slow.next;
        }
    }

}
