package com.hwy.kotlin;

/**
 * 反转链表
 * <p>
 * https://leetcode.cn/problems/reverse-linked-list/description/
 * <p>
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * <p>
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * <p>
 * 输入：head = [1,2]
 * 输出：[2,1]
 * <p>
 * 输入：head = []
 * 输出：[]
 */
public class CH206 {

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

    public static ListNode reverseList(ListNode head) {
        /*
        解题思路: 双指针方式: 使用 temp 临时节点, pre 记录当前链表的上一个节点(默认null)
        1. 使用 temp 记录当前节点 head 的下一个节点 head.next
        2. 将当前节点的 head.next 指向 pre
        3. 更新 pre 为当前节点 head
        4. 将 temp 更新给 head 进行下一次节点判断
         */
        if (head == null) {
            return null;
        }

        ListNode temp = null;
        ListNode pre = null;

        while (head != null) {
            // 先记录当前节点的下一个节点
            temp = head.next;
            // 将当前节点的 next 指向pre (首次 pre 为 null)
            head.next = pre;
            // 更新 pre 值为当前节点
            pre = head;
            // 跟新 head 为原始链表的next
            head = temp;
        }

        return pre;
    }

    public static ListNode reverseList2(ListNode head) {
        /*
        解题思路: 使用递归的方式
         */
        return reverse(head, null);
    }

    private static ListNode reverse(ListNode curr, ListNode pre) {
        if (curr == null) {
            return pre;
        }
        // 先记录原始链表节点的下一个节点
        ListNode temp = curr.next;
        // 反转当前节点的next
        curr.next = pre;
        // 递归
        return reverse(temp, curr);
    }


}
