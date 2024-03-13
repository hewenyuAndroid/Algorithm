package com.hwy.kotlin;

/**
 * 旋转链表
 * <p>
 * https://leetcode.cn/problems/rotate-list/description/
 * <p>
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * <p>
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * <p>
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 */
public class CH61 {

    public class ListNode {
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

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        if (k == 0) {
            return head;
        }

        // 反转单链表
        ListNode temp = head;
        ListNode pre = null;
        while (head != null) {
            temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }

        // 拿到反转后的链表头节点，计算链表长度
        head = pre;
        int length = 1;
        ListNode end = head;
        while (end.next != null) {
            length++;
            end = end.next;
        }

        // 拿到链表长度后，将链表闭环成一个环
        end.next = head;

        // 计算出偏移量，链表需要走的节点数量（控制在一圈以内）
        int count = k % length;

        // 先找到目标节点的上一个节点
        while (count > 0) {
            end = end.next;
            count--;
        }

        // 取到头节点
        head = end.next;
        // 关闭链表环
        end.next = null;

        // 再次反转单链表
        pre = null;
        while (head != null) {
            temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }

        return pre;
    }


}
