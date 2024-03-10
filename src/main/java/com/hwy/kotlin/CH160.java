package com.hwy.kotlin;

/**
 * 相交链表
 * <p>
 * https://leetcode.cn/problems/intersection-of-two-linked-lists/description/
 * <p>
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 */
public class CH160 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /*
        思路:
        1. 分别从链表A和链表B的head开始往后遍历 (假设链表A独立的部分长度为LA，链表B独立的部分长度为LB，两个链表相交的长度为LC)
        2. 当一个链表遍历完成后，将指针指向另外一个链表的head开始遍历
        3. 此时链表A遍历的长度为: LA + LC + LB
        4. 链表B遍历的长度为: LB + LC + LA
        5. 如果两个链表相交，此时两个链表的指针指向的即为相交的节点
         */
        if (headA == null || headB == null) {
            return null;
        }
        // 记录两个链表的head
        ListNode pA = headA;
        ListNode pB = headB;
        // 判断两个链表的节点是否相等
        while (pA != pB) {
            // pA达到链表A的末尾后，从链表B重新开始
            pA = pA == null ? headB : pA.next;
            // pB达到链表B的末尾后，从链表A重新开始
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

}
