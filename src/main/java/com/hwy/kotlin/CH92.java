package com.hwy.kotlin;

/**
 * 反转链表2
 * <p>
 * https://leetcode.cn/problems/reverse-linked-list-ii/description/
 * <p>
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * <p>
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 * <p>
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 */
public class CH92 {

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

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) {
            return null;
        }

        if (left == right) {
            // left跟right相等表示只有一个节点，无需反转
            return head;
        }

        /*
        注意点:
            1. 这里的 left right 数据是从 1 开始的，也就是 index = 0，left = 1
            2. 反转的数据包含 left 和 right
        解题思路:
            1. 先从 head 开始遍历到 offset < left (即: index + 1 < left)的位置，找到需要反转的第一个节点(node)和其父节点(prevEnd)
            2. 从 node 位置开始，使用双指针反转链表，直到 offset <= right
            3. 此时得到反转后的链表头结点 pre, 剩下的未反转链表头结点 temp，加上之前的未反转的链表头节点 prevEnd
            4. 输出: prevEnd + pre + temp
            5. 注意，如果 left = 1， 则表示 prevEnd 为null，此时只需要返回 pre + temp 即可
         */

        ListNode node = head;
        int offset = 1;
        ListNode prevEnd = null;
        // 先遍历找到需要反转的第一个节点 node, 以及未反转的最后一个节点 prevEnd
        while (offset < left) {
            prevEnd = node;
            node = node.next;
            offset++;
        }

        ListNode temp = null;
        ListNode prev = null;
        // 记录需要反转的第一个节点，反转后，该节点为反转链表的最后一个节点
        ListNode reverseFirst = node;
        // 反转 left <= offset <= right 位置的链表
        // 反转后的链表头结点为 prev
        // offset > right 之后的链表头结点为 temp
        while (node != null && offset <= right) {
            temp = node.next;
            node.next = prev;
            prev = node;
            node = temp;
            offset++;
        }

        if (prevEnd != null) {
            // 如果 left > 1 此时，prevEnd 不为空，返回 prevEnd + prev + temp
            prevEnd.next = prev;
        } else {
            // 如果 left = 1 此时，prevEnd == null, 只需要返回 prev + temp 即可
            head = prev;
        }
        reverseFirst.next = temp;

        return head;
    }

}
